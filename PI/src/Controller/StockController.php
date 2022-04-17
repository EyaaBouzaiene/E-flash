<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\Stock2Repository;
use App\Repository\PartenairesRepository;
use Symfony\Component\HttpFoundation\Request ;  
use App\Entity\Stock2 ; 
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






class StockController extends AbstractController
{
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
    #
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

           # $flashy->success('Event created!');
            return $this->redirectToRoute('displaystock') ; 
        }

        return $this->render('stock/ajouter.html.twig' , ['form'=> $form->createView() ]) ; 
    }

    
    /**
     * @Route("/affichestock", name="displaystock")
     */

    public function affiche(Stock2Repository $repo): Response
    {
        $stock=$this->getDoctrine()->getRepository(Stock2::class)->findAll();

        
//stat


$data = $repo->findAll();
$dest = array();
foreach ($data as $x) {
   //$dest[] = [$x->getDestination()] ; 
   array_push($dest, $x->getNomS());
}

$pieChart = new PieChart();
$array_dest_occ = array_count_values($dest);
$final = [
   ['nom ', 'Quantite']

];
foreach ($array_dest_occ as $x => $x_value) {
   $final[] = [$x, (int)$x_value];
}

    
          
           $pieChart->getData()->setArrayToDataTable( $final);

       $pieChart->getOptions()->setTitle('Stock en fonction des noms');
       $pieChart->getOptions()->setHeight(500);
       $pieChart->getOptions()->setWidth(500);
       $pieChart->getOptions()->getTitleTextStyle()->setColor('#07600');
       $pieChart->getOptions()->getTitleTextStyle()->setFontSize(35);


       
        


        return $this->render('stock/affiche.html.twig',['affiche'=>$stock, 'piechart' => $pieChart]);
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

        return new Response();
    }

/* 
    /**
     * @Route("/test", name="test")
     * @return \Symfony\Component\HttpFoundation\Response
     */
  /*   public function statAction(Stock2Repository $repo)
    {
       

 $data = $repo->findAll();
 $dest = array();
 foreach ($data as $x) {
    //$dest[] = [$x->getDestination()] ; 
    array_push($dest, $x->getNomS());
}

 $pieChart = new pieChart();
$array_dest_occ = array_count_values($dest);
$final = [
    ['nom ', 'Quantite']

];
foreach ($array_dest_occ as $x => $x_value) {
    $final[] = [$x, (int)$x_value];
}

     
           
            $pieChart->getData()->setArrayToDataTable( $final);

        $pieChart->getOptions()->setTitle('Stock en fonction des noms');
        $pieChart->getOptions()->setHeight(500);
        $pieChart->getOptions()->setWidth(500);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#07600');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(35);
 
 
         return $this->render('stock/stat-teste.html.twig', array(
                'piechart' => $pieChart,
            )
 
        );  */
   

   /*  } */

    


    
    /**
     * @Route("/barchart", name="barchart")
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function statBarchart(Stock2Repository $repo)
    {
       

 $data = $repo->findAll();
 $nom= [];
 $quantite=[];
 foreach ($data as $x) {
    //$dest[] = [$x->getDestination()] ; 
    $nom []=$x->getNomS();
 $quantite[]=$x->getQteS();
}

 
return $this->render('stock/stat.html.twig', [
    'nom' => json_encode($nom),
    'quantite' => json_encode($quantite)
    
]);
}
        
  

    
  

      /**
     * @Route("/qualite/{id}", name="qualite")
     */
     public function findBy(SendMailService $send,Stock2Repository $repo,PartenairesRepository $rep,$id)
    {
       
       $data=$rep->findAll();

         $c = $data->find($id);
         $mail= $this->getDoctrine()->getRepository(Partenaires::class)->findByquantite($c->getMailP());

      $to=$p->findByquantite($c->getId()); 
    
     $from='khanfirkhadija66@gmail.com';
     $subject='Besoin Produit';
     $text='Bonjour Monsieursvp jai besoin de produit!';

     $send->send ($from,$to,$subject,$text);
     
     $this->addFlash('notice' , 'Envoie avec succées ') ; 
        return $this->render('stock/affiche.html.twig'
            
        );
    } 
 

    
}

    

