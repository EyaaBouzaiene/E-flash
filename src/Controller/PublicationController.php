<?php

namespace App\Controller;

use App\Entity\Commentaire;
use App\Entity\Publication;
use App\Form\FormCommentaireType;
use App\Form\FormPublicationType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Knp\Component\Pager\PaginatorInterface;
class PublicationController extends AbstractController
{
    /**
     * @Route("/publication", name="app_publication")
     */
    public function index(): Response
    {
        return $this->render('publication/index.html.twig', [
            'controller_name' => 'PublicationController',
        ]);
    }


    /**
     * @Route("/Front", name="Front")
     */
    public function index_Front(): Response
    {
        return $this->render('base_Front.html.twig'
    );
    }

    /**
     * @Route("/AddPub", name="AddPub")
     */
    public function Ajouter_Publication(Request $req)
    {

         $Pub = new Publication();
         $form = $this->createForm(FormPublicationType::class,$Pub);
         $form->add('Ajouter',SubmitType::class);
         
         $form->handleRequest($req);

         if($form->isSubmitted() && $form->isValid())
         {
            $em=$this->getDoctrine()->getManager();

            $file=$Pub->getImagePublication();
           
            if($file != null){
            $filename= md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($this->getParameter('images_directory'), $filename);

            $Pub->setImagePublication($filename);

            $Pub->setIdClientPublication(1);


            $time = new \DateTime();
            $Pub->setDatePublication($time->format('Y-m-d H:i:s'));
            
            }
            
            else
            {
                $Pub->setImagePublication("vide");

                $Pub->setIdClientPublication(1);
    
                $time = new \DateTime();
                $Pub->setDatePublication($time->format('Y-m-d H:i:s'));
                
            }

            $em->persist($Pub);
            $em->flush();   

            return $this->redirectToRoute('ListPub');

         }


        return $this->render('publication/Page_Ajout_Publication.html.twig',[

            'form_Pub'=>$form->createView()

        ]);


    }



    /**
     * @Route("/ListPub", name="ListPub")
     */
    public function Afficher_Publication(PaginatorInterface $paginator,Request $request)
    {

    

        $Pub= $paginator->paginate($this->getDoctrine()->getRepository(Publication::class)->findAll(),
        $request->query->getInt('page', 1) ,
        4
        );

        return $this->render('publication/Page_Afficher_Publication.html.twig',[

            'Pubs'=>$Pub

        ]);
    }




    /**
     * @Route("/UpdatePub/{id}", name="UpdatePubid")
     */
    public function Modifier_Publication(Request $req,$id)
    {

        $ref = $req->headers->get('referer');

        $Pub= $this->getDoctrine()->getRepository(Publication::class)->find($id);

         $form = $this->createForm(FormPublicationType::class,$Pub);
         $form->add('Modifier',SubmitType::class);
         $form->handleRequest($req);


         if($form->isSubmitted() && $form->isValid())
         {

            $em=$this->getDoctrine()->getManager();

            $file=$Pub->getImagePublication();

            if($file != null){
            $filename= md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($this->getParameter('images_directory'), $filename);

            $Pub->setImagePublication($filename);
            }

            else{
                $Pub->setImagePublication("");

            }

            $em->persist($Pub);
            $em->flush();

            return $this->redirectToRoute('ListPub');


         }


        return $this->render('publication/Page_Modifier_Publication.html.twig',[

            'form_Pubm'=>$form->createView()

        ]);


    }


      /**
     * @Route("/DeletePub/{id}", name="DeletePubid")
     */

    public function Supprimer_Publication(Request $req,$id)
{       $ref = $req->headers->get('referer');
        $Pub = $this->getDoctrine()->getRepository(Publication::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($Pub);
        $em->flush();
        return $this->redirect($ref);
}


  /**
   * @Route("/DetailPub/{id}", name="DetailPub")
   */
  
public function Afficher_detail_publication($id,Request $req)
{

    $Com = new Commentaire();
    $form = $this->createForm(FormCommentaireType::class,$Com);
    $form->add('Share',SubmitType::class);
    $form->handleRequest($req);
    $Pub = $this->getDoctrine()->getRepository(Publication::class)->find($id);
    $ref = $req->headers->get('referer');

    if($form->isSubmitted() && $form->isValid())
       {
         
       $Com->setIdClient(1);
       $Com->setIdPublicationCommentaire($Pub);
       $time = new \DateTime();
       $Com->setDateCommentaire($time->format('Y-m-d H:i:s'));


       $em = $this->getDoctrine()->getManager();
       $em->persist($Com);
       $em->flush();
       
       return $this->redirect($ref);

       }
   

    
    return $this->render('publication/Page_Detail_Publication.html.twig',[

        'titre'=>$Pub->getTitre(),
        'description'=>$Pub->getDescription(),
        'image'=>$Pub->getImagePublication(),
        'Date'=>$Pub->getDatePublication(),
        'posts'=>$Pub,
        'comments'=>$Pub,
        'form_Com'=>$form->createView()
        
    ]);
}



/**
   * @Route("/EspaceCom/{id}", name="EspaceCom")
   */
  
  public function Espace_Commentaire($id,Request $req)
  {
      $Com = new Commentaire();
      $form = $this->createForm(FormCommentaireType::class,$Com);
      $form->add('Share',SubmitType::class);
      $form->handleRequest($req);

      if($form->isSubmitted() && $form->isValid())
         {
           
         $Pub1 = $this->getDoctrine()->getRepository(Publication::class)->find($id);

         $Com->setIdClient(1);
         $Com->setIdPublicationCommentaire($Pub1);
         $time = new \DateTime();
         $Com->setDateCommentaire($time->format('H:i:s \O\n Y-m-d'));
 
 
         $em = $this->getDoctrine()->getManager();
         $em->persist($Com);
         $em->flush();

        

         }
     

      $Pub = $this->getDoctrine()->getRepository(Publication::class)->find($id);
  
      return $this->render('commentaire/Page_Espace_Commentaire.html.twig',[
  
          'form_Com'=>$form->createView()
          
          
          
      ]);
  }


      /**
     * @Route("/UpdateCom/{id}", name="UpdateComid")
     */
    public function Modifier_Commentaire(Request $req,$id)
    {
         $Com= $this->getDoctrine()->getRepository(Commentaire::class)->find($id);

         $ref = $req->headers->get('referer');
         
         $form = $this->createForm(FormCommentaireType::class,$Com);
         $form->add('Modifier',SubmitType::class);
         $form->handleRequest($req);


         if($form->isSubmitted() && $form->isValid())
         {
            $em=$this->getDoctrine()->getManager(); 

            $em->persist($Com);
            $em->flush();

            return $this->redirectToRoute("ListPub");



         }


        return $this->render('publication/Page_Modifier_Commentaire.html.twig',[

            'form_Comm'=>$form->createView()

        ]);


    }


    /*----------------------------------------------------------------------------------------------------------------------- */


    /**
     * @Route("/Back", name="Back")
     */
    public function index_Back(): Response
    {
        return $this->render('base_Back.html.twig'
    );
    }




    /**
     * @Route("/ListPubBack", name="ListPubBack")
     */
    public function Afficher_BackEnd_Publication()
    {
        $Pub= $this->getDoctrine()->getRepository(Publication::class)->findAll();

        return $this->render('publication/Page_AffichageB_Publication.html.twig',[

            'Pubs'=>$Pub

        ]);
    }


    /**
     * @Route("/DeletePubBack/{id}", name="DeletePubBackid")
     */

    public function Supprimer_BackEnd_Publication(Request $req,$id)
{
        $Pub = $this->getDoctrine()->getRepository(Publication::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($Pub);
        $em->flush();
        return $this->redirectToRoute("ListPubBack");
}



}
