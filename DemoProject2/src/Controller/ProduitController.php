<?php

namespace App\Controller;

use App\Entity\Categorie2;
use Dompdf\Dompdf;
use Dompdf\Options;
use App\Entity\Produit2;
use App\Form\AJOUTPRODUITSType;
use App\Form\AJOUTSCATEGORIEType;
use App\Form\ModifierProduitsType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use App\Repository\Produit2Repository;
use App\Service\SendMailService;
use DateTimeInterface;
use PHPUnit\Util\Json;
use Doctrine\Common\Persistence\EntityManagerInterface;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\GetSetMethodNormalizer;
use Symfony\Component\Serializer\Serializer;
use Doctrine\ORM\EntityManagertity;
class ProduitController extends AbstractController
{

    
    /**
     * @Route("/produit", name="app_produit")
     */
    public function index(): Response
    {
        return $this->render('produit/index.html.twig', [
            'controller_name' => 'ProduitController',
        ]);
    }
/**
     * @Route("/map", name="mapgeo")
     */
    public function productsAction()
    {
        $encoders = array(new JsonEncoder());
        $normalizers = array(new GetSetMethodNormalizer());

        $serializer = new Serializer($normalizers, $encoders);

        $em = $this->entityManager;

        $query = $em->createQuery(
            'SELECT prov.COUNTRY , count(prov) as items
            FROM App\Entity\Produit2 prov
            
            GROUP BY prov.COUNTRY'
        );

        $res = $query->getResult();

        foreach ($res as $val)
        {
            $data[] = array($val['prov.COUNTRY'], (int) $val['items']);
        }

        $data = $serializer->serialize($data, 'json');

        $response = new Response();
        $response->setContent($data);
        $response->headers->set('Content-Type', 'application/json');

        return $response;

    }
     /**
     *@Route("/list2", name="list2")
     */
    public function list(): Response
    {
        $classroom=$this->getDoctrine()->getRepository(Produit2::class)->findAll();
        return $this->render('produit/afficheP.html.twig',['list'=>$classroom]);
    }
/**
     *@Route("/list8/{id}", name="list8")
     */
    public function list8($id): Response
    {
        $classroom=$this->getDoctrine()->getRepository(Produit2::class)->findAll($id);
        return $this->render('produit/afficheP.html.twig',['list'=>$classroom]);
    }
  

     /**
     *@Route("/list3", name="list3")
     */
    public function list2(): Response
    {
        $classroom=$this->getDoctrine()->getRepository(Produit2::class)->findAll();
        return $this->render('produit/afficheP.html.twig',['list'=>$classroom]);
    }
    /**
     * @Route("/addproduit", name="addapp_produit")
     */
    public function create(Request $request,Request $request2)
    {
        $produit = new Produit2();
        $form = $this->createForm(AJOUTPRODUITSType::class, $produit);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid() ) {
            $produit->setRate(0);
            $produit->setDATEP(new \DateTime('now'));
            $produit->setEtat("ferme");
                $image = $form->get('IMAGE')->getData();
                $file = md5(uniqid()) . '.' . $image->guessExtension();
                $image->move(
                    $this->getParameter('images_directory'),
                    $file
                );
                $produit->setImage($file);

                $em = $this->getDoctrine()->getManager();
                $em->persist($produit);
                $em->flush();
                return $this->redirectToRoute('list2');
            
        }
        $produit2 = new Categorie2();
        $form2 = $this->createForm(AJOUTSCATEGORIEType::class, $produit2);

        $form2->handleRequest($request2);

        if ($form2->isSubmitted() && $form2->isValid() ) {
            

                $em = $this->getDoctrine()->getManager();
                $em->persist($produit2);
                $em->flush();
                 $this->addFlash('notice' , 'ajout avec Succes ') ;
                 return $this->redirectToRoute('addapp_produit');
                //return $this->redirectToRoute('list2');
            
        }


        return $this->render('produit/index.html.twig', ['formProduit' => $form->createView(),'formCategorie' => $form2->createView()]);
    }
   /**
     *@Route("/delclass2/{id}", name="dellclass")
     */
public function deleteclass($id) {
    $repos=$this->getDoctrine()->getRepository(Produit2::class);
    $result=$repos->find($id);
    $manager=$this->getDoctrine()->getManager();
    
    $manager->remove($result);
    $manager->flush();
    $this->addFlash('notice' , 'suppression avec Succes ') ;
    return $this->redirectToRoute('list2');
    
    }  


    /**
     *@Route("/updateclass2/{id}", name="updateclass")
     */
    public function updateclass(Request $request,$id): Response
    {
        $repos=$this->getDoctrine()->getRepository(Produit2::class);
        $classroom=$repos->find($id);
        
        $form= $this->createForm(ModifierProduitsType::class, $classroom); 
        $form->handleRequest($request);
        if($form->isSubmitted()) 
        {
            $manager=$this->getDoctrine()->getManager();
            
            $manager->flush();
            return $this->redirectToRoute('list2');
        }
        return $this->render('produit/update.html.twig',['form'=>$form->createView()]);
    }

/**
   * Creates a new ActionItem entity.
   *
   * @Route("/search", name="ajax_search")
  
   */
  public function searchAction(Request $request,Produit2Repository $p)
  {
      $em = $this->getDoctrine()->getManager();

      $requestString = $request->get('q');

      $entities =  $p->findEntitiesByString($requestString);

      return $this->render('produit/afficheP.html.twig', ['categorias' => $entities]);
  }

  public function getRealEntities($entities){

      foreach ($entities as $entity){
          $realEntities[$entity->getId()] = $entity->getFoo();
      }

      return $realEntities;
  }
     /**
     * @Route("/addcategorie", name="addcategorie")
     */
    public function create2(Request $request2)
    {
        

       // return $this->render('produit/index.html.twig', ['formCategorie' => $form2->createView()]);
    }
 /**
     * @Route("/downloadpdf", name="downloadpdf")
     */

    public function downloadpdf()
    {
        $data = $this->getDoctrine()->getRepository(Produit2::class)->findAll();
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
        $html = $this->renderView('produit/downloadpdf.html.twig', [
            'list' => $data
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


    /**
     * @Route("/contact", name="contact")
     */
    public function contact(SendMailService $send)
    {
       
       $send->send ('aderssadhia2000@gmail.com','aderssadhia@gmail.com','i love you','text');
            $this->addFlash('message', 'Mail de contact envoyé');
            return $this->redirectToRoute('list2');
        

        return $this->render('produit/afficheP.html.twig'
            
        );
    }
        
    
}
