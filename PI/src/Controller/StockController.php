<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\Stock2Repository;
use App\Repository\PartenairesRepository;

use Symfony\Component\HttpFoundation\Request;  
use App\Entity\Stock2; 
use App\Entity\Partenaires; 
use App\Form\StockType;
use App\Form\StockUpdateType;
use Dompdf\Dompdf;
use Dompdf\Options;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\BarChart;
use Psr\Log\LoggerInterface;

use App\Form\ContactType;
use App\Form\SearchAnnonceType;
use App\Repository\AnnoncesRepository;
use App\Service\SendMailService;


use Endroid\QrCode\QrCode;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Template;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;


use Knp\Component\Pager\Pagination\PaginationInterface;
use Knp\Component\Pager\PaginatorInterface;







class StockController extends AbstractController
{
################################################ CRUD ###########################################################################"" 
    /**
     * @Route("/stock", name="app_stock")
     */
    public function index(): Response
    {
        return $this->render('stock/index.html.twig', [
            'controller_name' => 'StockController',
        ]);
    }

    
    /**
     * @Route("/ajouterstock", name="addstock")
     */
    public function ajouter(Request $request)
    
    {
        $Stock2 = new Stock2();
        $form = $this->createForm(StockType::class,$Stock2);
        $form->handleRequest($request) ; 

        if ($form->isSubmitted() && $form->isValid()) {
$Stock2->setDateS(new \DateTime('now'));
$Stock2->setQualiteS('1');
            $em = $this->getDoctrine()->getManager() ; 
            $em->persist( $Stock2) ; 
            $em->flush() ;
            $this->addFlash('notice' , 'ajout avec Succes ') ;
           # $flashy->success('Event created!');
            return $this->redirectToRoute('displaystock') ; 
        }

        return $this->render('stock/ajouter.html.twig' , ['form'=> $form->createView() ]) ; 
    }

    
    /**
     * @Route("/affichestock", name="displaystock")
     * @return PaginationInterface
     */

    public function affiche(Stock2Repository $repo,Request $request, PaginatorInterface $paginator): Response
    {
        $donnees=$this->getDoctrine()->getRepository(Stock2::class)->findAll();
        $stock = $paginator->paginate(
            $donnees, //on passe les données
            $request->query->getInt('page', 1), //num page en cours par defaut 1
            3
        );

//stat

///pie chart
$nom= []; 
             
       $quantite=[];
$datac= $this->getDoctrine()->getRepository(Stock2::class)->selectNomStockQte();
foreach ($datac as $xB) { 
    $nom[]=$xB['nomStock'];
    $quantite[]=$xB['quantite'];
}


      /*  barchart */
      $dataB = $this->getDoctrine()->getRepository(Stock2::class)->findAll();
        $stockB= [
            ['Nom Stock','Qualité']

        ]  ; 
foreach ($dataB as $xB) {
    $stockB[] = [$xB->getNomS(),(int)$xB->getQualiteS()]; 
    
}
$bar = new BarChart();
$bar->getData()->setArrayToDataTable($stockB) ; 


$bar->getOptions()->setTitle('Stock en Fonction de la qualité');
$bar->getOptions()->getHAxis()->setTitle('Qualité');
$bar->getOptions()->getHAxis()->setMinValue(0);
$bar->getOptions()->getVAxis()->setTitle('Nom Stock');
$bar->getOptions()->setWidth(900);
$bar->getOptions()->setHeight(500); 
        



        return $this->render('stock/affiche.html.twig',['affiche'=>$stock,'nomStock' => json_encode($nom),
        'quantite' => json_encode($quantite),'BarChart' => $bar , 'list' => $dataB]);
    }

    
    /**
     * @Route("/updatestock/{id}", name="updatestock")
     */

    public function update(Request $request, $id)
    {
        $repos=$this->getDoctrine()->getRepository(Stock2::class);
        $stock=$repos->find($id);
   
        $form = $this->createForm(StockUpdateType::class, $stock);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

          

                $em = $this->getDoctrine()->getManager();
                $em->persist($stock);
                $em->flush();
                $this->addFlash('notice' , 'Modification avec Succes ') ;
                return $this->redirectToRoute('displaystock');
        }
        

        return $this->render('stock/update.html.twig', ['form' => $form->createView()]);
    }

/**
     *@Route("/delclass2/{id}", name="dellclass")
     */
    public function deleteclass($id) {
        $repos=$this->getDoctrine()->getRepository(Stock2::class);
        $result=$repos->find($id);
        $manager=$this->getDoctrine()->getManager();
        
        $manager->remove($result);
        $manager->flush();
        $this->addFlash('notice' , 'suppression avec Succes ') ;
        return $this->redirectToRoute('displaystock');
        
        }  


################################################ PDF ###########################################################################"" 

        
    /**
     * @Route("/downloadpdf", name="downloadpdf")
     */

    public function downloadpdf()
    {
        $data = $this->getDoctrine()->getRepository(Stock2::class)->findAll();
        $pdfoptions = new Options();
        $pdfoptions->set('defaultFont', 'Arial');
        $pdfoptions->setIsRemoteEnabled(true);
      


        $dompdf = new Dompdf($pdfoptions);
        $context =  stream_context_create([
            'ssl' => [
                'verify_peer' => FALSE,
                'verify_peer_name' => FALSE,
                'allow_self_signed' => TRUE
            ]
        ]);

        $dompdf->setHttpContext($context);
        $html = $this->renderView('stock/downloadpdf.html.twig', [
            'affiche' => $data
        ]);

        $dompdf->loadHTML($html);
        $dompdf->setPaper('A2', 'portrait');
        $dompdf->render();
        $file = 'test.pdf';
        $dompdf->stream($file, [

            'Attachment' => false
        ]);
        return new Response("The PDF file has been succesfully generated !");
        
    }

 ################################################ CALENDAR ###########################################################################"" 

    /**
     * @Route("/calender", name="calender")
     */

    public function viewcal()
    {
        $list = $this->getDoctrine()->getRepository(Stock2::class)->findAll();

        $res = [] ; 

        foreach ($list as $x)
        {
            $res[] = [
                'title'=>$x->getNomS(),
                'start'=>$x->getDateS()->format('Y-m-d'),
                'end'=>$x->getDateS()->format('Y-m-d'),
            ] ; 

        }

        $data = json_encode($res);

        

        return $this->render('stock/calendar.html.twig', compact('data'));

    }
    
 ################################################ Mail ###########################################################################"" 
 

    /**
     * @Route("/mailstock", name="mailstock")
     */
     public function mailstock(SendMailService $send,Stock2Repository $repo)
    {
        
       $mail=$repo->selectMailQte();
      
       
       
       foreach ($mail as $to ) {
        
      
     $from='khanfirkhadija66@gmail.com';
     $subject='Besoin Produit';
     $text='Bonjour Monsieur svp jai besoin de produit!';

     $k=strlen(json_encode($to)) - 4;
#dd($k,$to);
     $z=substr(json_encode($to),9);
    #dd($z);
    $pos=strrpos($z,'"');
     #dd($pos);
     $e=substr(json_encode($to),9,$pos);
     #dd($e);
   
      $send->send ($from,$e,$subject,$text); 
 
       
     $this->addFlash('notice' , 'Envoie avec succées '); 
    }
     return $this->redirectToRoute('displaystock'); 
          
        

    }

################################################ TRI PAR QUANTITE ###########################################################################"" 


/**
 * @Route("/tri", name="tri")
*/
public function Tri(Request $request,Stock2Repository $repo, PaginatorInterface $paginator)
    {

        //trie
        $stock=$this->getDoctrine()->getRepository(Stock2::class)->findAll();
      
        $stock2= $repo->TRIE();
       
      

        





################################################ STAT ###########################################################################"" 



///pie chart
$nom= []; 
             
       $quantite=[];
$datac= $this->getDoctrine()->getRepository(Stock2::class)->selectNomStockQte();
foreach ($datac as $xB) { 
    $nom[]=$xB['nomStock'];
    $quantite[]=$xB['quantite'];
}


      /*  barchart */
      $dataB = $this->getDoctrine()->getRepository(Stock2::class)->findAll();
        $stockB= [
            ['Nom Stock','Qualité']

        ]  ; 
foreach ($dataB as $xB) {
    $stockB[] = [$xB->getNomS(),(int)$xB->getQualiteS()]; 
    
}
$bar = new BarChart();
$bar->getData()->setArrayToDataTable($stockB) ; 


$bar->getOptions()->setTitle('Stock en Fonction de la qualité');
$bar->getOptions()->getHAxis()->setTitle('Qualité');
$bar->getOptions()->getHAxis()->setMinValue(0);
$bar->getOptions()->getVAxis()->setTitle('Nom Stock');
$bar->getOptions()->setWidth(900);
$bar->getOptions()->setHeight(500); 
        


return $this->render('stock/affichetrie.html.twig',['stock2'=>$stock2,'nomStock'=> json_encode($nom),
'quantite' => json_encode($quantite),'BarChart' => $bar , 'list' => $dataB]);

       
    
    }  



}





    


    

