<?php

namespace App\Controller;

use App\Entity\Categorie2;
use App\Entity\Categories;
use Dompdf\Dompdf;
use Dompdf\Options;
use App\Entity\Produit2;
use App\Entity\Produits;
use App\Form\AJOUTPRODUITS2Type;
use App\Form\AJOUTPRODUITSType;
use App\Form\AJOUTSCATEGORIE2Type;
use App\Form\AJOUTSCATEGORIEType;
use App\Form\ModifierProduits2Type;
use App\Form\ModifierProduitsType;
use App\Repository\CategoriesRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use App\Repository\Produit2Repository;
use App\Repository\ProduitsRepository;
use App\Repository\UserRepository;
use App\Service\SendMailService;
use DateTimeInterface;
use PHPUnit\Util\Json;
use Doctrine\Common\Persistence\EntityManagerInterface;
use Twilio\Rest\Client;

use Symfony\Component\Serializer\Normalizer\GetSetMethodNormalizer;

use Doctrine\ORM\EntityManagertity;
use PhpOffice\PhpSpreadsheet\Spreadsheet;
use PhpOffice\PhpSpreadsheet\Writer\Xlsx;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Encoder\XmlEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\SerializerInterface;
class ProduitController extends AbstractController
{

    const  ATTRIBUTES_TO_SERIALIZE = ['REFERENCE', 'NOM', 'TYPE', 'quantite', 'DATEP', 'IMAGE','rate','quantiteR','Prix','etat','IMAGE2','DESCP'];
    const  ATTRIBUTES_TO_SERIALIZE2 = ['CODE','NOMCAT'];
    
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
     *@Route("/list2", name="list2")
     */
    public function list(): Response
    {
        $classroom=$this->getDoctrine()->getRepository(Produits::class)->findAll();
        return $this->render('produit/afficheP.html.twig',['list'=>$classroom]);
    }
  

/**
     *@Route("/list8/{id}", name="list8")
     */
    public function list8($id): Response
    {
        $classroom=$this->getDoctrine()->getRepository(Produits::class)->findAll($id);
        return $this->render('produit/afficheP.html.twig',['list'=>$classroom]);
    }
  

     /**
     *@Route("/list3", name="list3")
     */
    public function list2(): Response
    {
        $classroom=$this->getDoctrine()->getRepository(Produits::class)->findAll();
        return $this->render('produit/afficheP.html.twig',['list'=>$classroom]);
    }
    /**
     * @Route("/addproduit", name="addapp_produit")
     */
    public function create(Request $request,Request $request2)
    {
        $produit = new Produits();
        $form = $this->createForm(AJOUTPRODUITS2Type::class, $produit);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid() ) {
            $produit->setRate(0);
      
           
                $image = $form->get('image2')->getData();
                $file = md5(uniqid()) . '.' . $image->guessExtension();
                $image->move(
                    $this->getParameter('images_directory'),
                    $file
                );
                $produit->setImage2($file);

                $em = $this->getDoctrine()->getManager();
                $em->persist($produit);
                $em->flush();
                return $this->redirectToRoute('list2');
            
        }
        $produit2 = new Categories();
        $form2 = $this->createForm(AJOUTSCATEGORIE2Type::class, $produit2);

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
     * @Route("/addproduit2" , name="addapp_produit2" ,  methods={"GET", "POST"}, requirements={"id":"\d+"})
     */
    public function ajouter(Request $request, SerializerInterface $serializer)
    {

        $produit = new Produits();
        $nom = $request->query->get('NOM');
        $quantite = $request->query->get('quantite');
        $type = $request->query->get('TYPE');
        $rate = $request->query->get('rate');
       
       
        
        $quantiter = $request->query->get('quantiteR');
        $prix = $request->query->get('Prix');
        $descp = $request->query->get('DESCP');
        $categorie = $request->query->get('CATEGORIE');
        $image2 = $request->query->get('IMAGE2');
        


        $em = $this->getDoctrine()->getManager();

        $produit->setNom($nom);
        $produit->setQuantite($quantite);
        $produit->setType($type);
        
        $produit->setRate($rate);
        $produit->setQuantiter($quantiter);
        $produit->setPrix($prix);
     $produit->setDESCP($descp);
     $produit->setCategorie($this->getDoctrine()->getManager()->getRepository(Categories::class)->find($categorie));
     $produit->setImage2($image2);
       

        $em->persist($produit);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($produit);
        return new JsonResponse($formatted);
    }

    /**
     * @Route("/addcat" , name="addapp_cat" ,  methods={"GET", "POST"}, requirements={"id":"\d+"})
     */
    public function ajouter2(Request $request, SerializerInterface $serializer)
    {

        $categorie = new Categories();
        $nom = $request->query->get('NOMCAT');
        
        


        $em = $this->getDoctrine()->getManager();

        $categorie->setNomcat($nom);
        

        $em->persist($categorie);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($categorie);
        return new JsonResponse($formatted);
    }
/**
     * @Route("/affichercat")
     */
    public function getList()
    {

        $categorie =$this->getDoctrine()->getRepository(Categories::class)->findAll();
       // $json = $serializer->serialize($categorie, 'json', ['groups' => ['categories']]);
       $serializer=new Serializer([new ObjectNormalizer()]);
       $formatted=$serializer->normalize($categorie);
       return new JsonResponse ($formatted);


        // return $this->json(['Categories' => $categorie], Response::HTTP_OK, [], [
        //     'attributes' => self::ATTRIBUTES_TO_SERIALIZE2
        // ]);
    }

    /**
     * @Route("/afficherprodmobile")
     */
    public function getListProd()
    {

        $categorie =$this->getDoctrine()->getRepository(Produits::class)->findAll();
       // $json = $serializer->serialize($categorie, 'json', ['groups' => ['categories']]);
       $serializer=new Serializer([new ObjectNormalizer()]);
       $formatted=$serializer->normalize($categorie);
       return new JsonResponse ($formatted);


        // return $this->json(['Categories' => $categorie], Response::HTTP_OK, [], [
        //     'attributes' => self::ATTRIBUTES_TO_SERIALIZE2
        // ]);
    }


/**
     * @Route("/delete5", name="supprimer_categorie")
     */
    public function supprimerCategorie(Request $request, CategoriesRepository $repo): Response
    {

        $id =$request->get("CODE");
        $em=$this->getDoctrine()->getManager();

        $id=   $repo->find($id);

        if($id != null){
            $em->remove($id);
            $em->flush();
            $serializer=new Serializer([new ObjectNormalizer()]);
            $formatted=$serializer->normalize("les informations ont ete supprimer ");
            return new JsonResponse($formatted);
        }

        return  new JsonResponse("Id Invalide");
    }
    /**
     * @Route("/deleteproducts", name="supprimer_produits")
     */
    public function supprimerProduits(Request $request, ProduitsRepository $repo): Response
    {

        $id =$request->get("REFERENCE");
        $em=$this->getDoctrine()->getManager();

        $id=   $repo->find($id);

        if($id != null){
            $em->remove($id);
            $em->flush();
            $serializer=new Serializer([new ObjectNormalizer()]);
            $formatted=$serializer->normalize("les informations ont ete supprimer ");
            return new JsonResponse($formatted);
        }

        return  new JsonResponse("Id Invalide");
    }

/**
     * @Route("/modifiercat" , name="categorie_modifier" ,  methods={"GET", "POST"}, requirements={"id":"\d+"})
     */
    public function modifier(Request $request, SerializerInterface $serializer, CategoriesRepository $repo)
    {

        $categorie = new Categories();
        $id = $request->get('CODE');
        $categorie = $repo->find($id);
        $nom = $request->query->get('NOMCAT');
       

        $em = $this->getDoctrine()->getManager();

        $categorie->setNomcat($nom);
        


        $em->persist($categorie);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize("les informations ont ete modifier ");
        return new JsonResponse($formatted);
    }

  /**
     * @Route("/modifierSM" , name="modifierPM" ,  methods={"GET", "POST"}, requirements={"id":"\d+"})
     */
    public function modiferProduitsMobile(Request $request, SerializerInterface $serializer, ProduitsRepository $repo)
    {
        $produit = new Produits();
        $id=$request->get('REFERENCE');
        $produit=$repo->find($id);

        $nom = $request->query->get('NOM');
        $quantite = $request->query->get('quantite');
        $type = $request->query->get('TYPE');
       $categorie=$request->query->get("CATEGORIE");
       
        
        $quantiter = $request->query->get('quantiteR');
        $prix = $request->query->get('Prix');
        $descp = $request->query->get('DESCP');
        $categorie = $request->query->get('CATEGORIE');
        $image2 = $request->query->get('IMAGE2');
        $rate = $request->query->get('rate');
   
        $em=$this->getDoctrine()->getManager();
        $produit->setNom($nom);
        $produit->setQuantite($quantite);
        $produit->setType($type);
        
        
        $produit->setRate($rate);
        $produit->setQuantiter($quantiter);
        $produit->setPrix($prix);
     $produit->setDESCP($descp);
    // $produit->setCategorie($categorie);
        $produit->setImage2($image2);

        $em->persist($produit);
        $em->flush();
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize($produit);
        return new JsonResponse($formatted);
    }


/**
     * @Route("/affichercat2" )
     */

    public function afficher(Request $request, SerializerInterface $serializer, CategoriesRepository $repo)
    {

        $hebergements = $repo->findOneById( $request->query->get('CODE'));
        $json = $serializer->serialize($hebergements, 'json', ['groups' => ['hebergement']]);
        //tbadel lite hebergement badlou forme jsn


        return $this->json(['hebergement' => $hebergements], Response::HTTP_OK, [], [
            'attributes' => self::ATTRIBUTES_TO_SERIALIZE2
        ]);


    }



    
  


     /**
         *@Route("/delclass5/{id}", name="dellprod")
         */
        public function deleteclass($id)
        {
            $repos=$this->getDoctrine()->getRepository(Produits::class);
            $result=$repos->find($id);
            $manager=$this->getDoctrine()->getManager();
            
            $manager->remove($result);
            $manager->flush();
            $this->addFlash('notice', 'suppression avec Succes ') ;
            return $this->redirectToRoute('list2');
        }


    /**
     *@Route("/updateclass2/{id}", name="updateclass")
     */
    public function updateclass(Request $request,$id): Response
    {
        $repos=$this->getDoctrine()->getRepository(Produits::class);
        $classroom=$repos->find($id);
        
        $form= $this->createForm(ModifierProduits2Type::class, $classroom); 
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
     * @Route("/addcategorie", name="addcategorie")
     */
    public function create2(Request $request2)
    {
        

       // return $this->render('produit/index.html.twig', ['formCategorie' => $form2->createView()]);
    }
 /**
     * @Route("/downloadpdfProduits", name="downloadpdfProduits")
     */

    public function downloadpdf()
    {
        $data = $this->getDoctrine()->getRepository(Produits::class)->findAll();
        $pdfoptions = new Options();
        $pdfoptions->set('defaultFont', 'Arial');
        $pdfoptions->set('isRemoteEnabled', TRUE);


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
        
 
    
/**
     * @Route("/sms", name="sms")
     */
    public function sms( ProduitsRepository $r)
    {
        $account_sid = 'AC9a5729631fb20a28678f51ea6e674347';
        $auth_token = 'c44928a15e67ca68fa87b2d113c1b5d3';
        $twilio_number = '+19704897391';

        $client = new Client($account_sid, $auth_token);

        $client->messages->create(
            // Where to send a text message (your cell phone?)
            //'+216'.$
                '+21624376729',
                array(
                    'from' => $twilio_number,
                    'body' => 'Welcome '
                )
            );

        return $this->redirectToRoute('list2');
    }


     /**
     * @Route("/exportExel",  name="export3")
     */
    public function export()
    {
        $spreadsheet = new Spreadsheet();

        $sheet = $spreadsheet->getActiveSheet();

        $sheet->setTitle('stock');

        $sheet->getCell('A1')->setValue('REFERENCE');
        $sheet->getCell('B1')->setValue('NOM');
        $sheet->getCell('C1')->setValue('TYPE');
        $sheet->getCell('D1')->setValue('DATE AJOUT');
        $sheet->getCell('E1')->setValue('Prix');
        $sheet->getCell('F1')->setValue('Description');
        $sheet->getCell('G1')->setValue('Categorie');
        $sheet->getCell('H1')->setValue('Quantite');
        $sheet->getCell('I1')->setValue('QuantiteR');


        // Increase row cursor after header write
        $sheet->fromArray($this->getData(), null, 'A2', true);
        $writer = new Xlsx($spreadsheet);
        // $writer->save('ss.xlsx');
        $writer->save('StockExcel' . date('m-d-Y_his') . '.xlsx');
        $this->addFlash('notice' , 'The PDF file has been succesfully generated !'); 
    
     return $this->redirectToRoute('list2'); 
       
    }

 /**
         * @var $produit typ[]
         */
    public function getData()
    {
       
        $list = []; 
        $typerec = $this->getDoctrine()->getRepository(Produits::class)->findAll();

        foreach ($typerec as $typ) {
            $list[] = [
              
                $typ->getReference(),
                $typ->getNom(),
                $typ->getType(),
                $typ->getDate(),
                $typ->getPrix(),
                $typ->getDESCP(),
               $typ->getCategorie()->getNomcat(),
               $typ->getQuantite(),
               $typ->getQuantiter(),
               
            ];
        }
        return $list;
    }
    
}
