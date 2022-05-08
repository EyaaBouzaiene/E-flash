<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request ;  
use App\Entity\Partenaires ; 
use App\Entity\Stock2; 
use App\Form\PartenaireType;
use App\Form\PartenaireUpdateType;
use App\Form\StockUpdateType;
use Dompdf\Dompdf;
use Dompdf\Options;
use APP\Controller\PartenaireSecoursController;
use App\Entity\Partenairesecours ;  


use App\Form\ContactType;
use App\Form\SearchAnnonceType;
use App\Repository\PartenairesRepository;
use App\Service\SendMailService;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\SerializerInterface;
use Twilio\Rest\Client;

class PartenaireController extends AbstractController
{
  
################################################ CRUD ###########################################################################"" 
const  ATTRIBUTES_TO_SERIALIZE = ['idP', 'MatriculeP', 'nomMarqueP', 'nomP', 'prenomP', 'mailP','categorieP','dateAjout','image'];
    /**
     * @Route("/partenaire", name="app_partenaire")
     */
    public function index(): Response
    {
        return $this->render('partenaire/index.html.twig', [
            'controller_name' => 'PartenaireController',
        ]);
    }

    
    /**
     * @Route("/ajouterpartenaires", name="addpartenaires")
     */
    public function ajouter(Request $req)
    {
        $Partenaires = new Partenaires();
        $form = $this->createForm(PartenaireType::class,$Partenaires);
        $form->handleRequest($req) ; 

        if ($form->isSubmitted() && $form->isValid()) {
            $image = $form->get('image')->getData();
            $file = md5(uniqid()) . '.' . $image->guessExtension();
            $image->move(
                $this->getParameter('images_directory'),
                $file
            );
            $Partenaires->setImage($file);
            $em = $this->getDoctrine()->getManager() ; 
            $Partenaires->setDateAjout(new \DateTime('now'));
            
            $em->persist( $Partenaires) ; 
            $em->flush() ;
            $this->addFlash('notice' , 'Ajout Partenaire avec Succes ') ;
            return $this->redirectToRoute('displaypartenaires') ; 
        }

        return $this->render('partenaire/ajouter.html.twig' , ['form'=> $form->createView() ]) ; 
    }

    
    /**
     * @Route("/affichepartenaires", name="displaypartenaires")
     */

    public function affiche(): Response
    {
        

         $Partenaires=$this->getDoctrine()->getRepository(Partenaires::class)->findAll(); 
           return $this->render('partenaire/affiche.html.twig',['affichep'=>$Partenaires]);
     
    }

      /**
     * @Route("/updatepartenaires/{id}", name="updatepartenaires")
     */

    public function update(Request $requ, $id)
    {
        $repos=$this->getDoctrine()->getRepository(Partenaires::class);
        $partenaires=$repos->find($id);
   
        $form = $this->createForm(PartenaireUpdateType::class, $partenaires);

        $form->handleRequest($requ);

        if ($form->isSubmitted() && $form->isValid()) {

          

                $em = $this->getDoctrine()->getManager();
                $em->persist($partenaires);
                $em->flush();
                $this->addFlash('notice' , 'Modification avec Succes ') ;
                return $this->redirectToRoute('displaypartenaires');
        }
        

        return $this->render('partenaire/update.html.twig', ['form' => $form->createView()]);
    }

    
/**
     *@Route("/supprimerpartenaires/{id}", name="dellpartenaires")
     */
    public function deleteclass($id) {
        $repos=$this->getDoctrine()->getRepository(Partenaires::class);
        $result=$repos->find($id);
        $manager=$this->getDoctrine()->getManager();
        
        $manager->remove($result);
        $manager->flush();
        $this->addFlash('notice' , 'suppression avec Succes ') ;
        return $this->redirectToRoute('displaypartenaires');
        
        }


        /**
     *@Route("/ajouterpartenairesWITHID/{id}", name="ajouterpartenairesWITHID")
     */
    public function ajouterpartenairesWITHID($id) {
        $Partenaires = new Partenaires();
        $repos=$this->getDoctrine()->getRepository(Partenaires::class);
        $result=$repos->find($id);
        $manager=$this->getDoctrine()->getManager();
        
        $manager->persist( $Partenaires) ; 
        $manager->flush() ;
        dd( $manager);
        $this->addFlash('notice' , 'Ajout Partenaire avec Succes ') ;
        return $this->redirectToRoute('displaypartenaires') ; 
        
     
        
        }
        
################################################ PDF ###########################################################################"" 
          
    /**
     * @Route("/downloadpdfpartenaire", name="downloadpdfpartenaire")
     */

    public function downloadpdf()
    {
        $data = $this->getDoctrine()->getRepository(Partenaires::class)->findAll();
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
        $html = $this->renderView('partenaire/downloadpdfpartenaire.html.twig', [
            'affiche' => $data
        ]);

        $dompdf->loadHTML($html);
        $dompdf->setPaper('A2', 'portrait');
        $dompdf->render();
        $file = 'Partenaire.pdf';
        $dompdf->stream($file, [

            'Attachment' => false
        ]);

        return new Response();
    }

 ################################################ MAIL QUALITE PARTENAIRE ###########################################################################"" 

    
    /**
     * @Route("/mailpartenaire", name="mailpartenaire")
     */
    public function mailpartenaire(SendMailService $send, PartenairesRepository $r)
    {
      
    $marque=$r->selectMarque();
  
    foreach ($marque as $m) {
        
        $d=substr(json_encode($marque), 16);
        $pos=  strpos($d, '"'); 
        $mar=substr(json_encode($d), 1, $pos);


       /*    $marouque=substr($d, strlen($mar)+18);
        $pos2=  strpos($marouque, '"');
        $mar2=substr(json_encode($marouque), 1, $pos2); 
        #dd( $d,$mar, $marque,$marouque);  */

        $from='khanfirkhadija66@gmail.com';
        $to='khanfirkhadija66@gmail.com';
        $subject='Attention Partenaire';
        $text='Bonjour Veuillez vérifier la qualité du/des Partenaire ayant la Marque suivante(s):'. json_encode($mar);
       /*   $text2='Bonjour Veuillez vérifier la qualité du/des Partenaire ayant la Marque suivante(s):'. json_encode($mar2);  */

        $send->send($from, $to, $subject, $text);
        /*  $send->send($from, $to, $subject, $text2); */
   
        $this->addFlash('notice', 'Envoie avec succées ') ;
    }
     return $this->redirectToRoute('displaypartenaires'); 
          

}




    /**
     * @Route("/conventionPartenaire", name="conventionPartenaire")
     */
    public function conventionPartenaire(PartenairesRepository $r,SendMailService $send)
    {

        $categorie=$r->selectcategorie();

        $mail=$r->selectMailParcategorie($categorie);
 
    
              $extract=substr(json_encode($mail),10);
             
            $pos=  strpos($extract, '"'); 
            $mailExtract=substr(json_encode($extract), 1, $pos-2);
          
            $from='khanfirkhadija66@gmail.com';
            $subject='Nouvelle Convention';
            $text='Bonjour suite à votre meilleur qualité dans le marché nous vous prions de faire une convention avec notre Restaurant
            Merci pour votre attention!  
            à trés bientot.';
             $send->send($from, $mailExtract, $subject, $text); 
             $this->addFlash('notice', 'Convention envoyée avec succées') ;
           
            
        return $this->redirectToRoute('displaypartenaires'); 
    }

    
    
/**
     * @Route("/sms2", name="sms2")
     */
    public function sms( PartenairesRepository $r)
    {
        $account_sid = 'ACcf63c4badad22b557067799b9669ec57';
        $auth_token = '445fbe86523a0393ad346ecb9416d015';
        $twilio_number = '+19704897306';

        $client = new Client($account_sid, $auth_token);

        $client->messages->create(
            // Where to send a text message (your cell phone?)
            //'+216'.$
                '+21628429539',
                array(
                    'from' => $twilio_number,
                    'body' => 'Welcome '
                )
            );

        return $this->redirectToRoute('displaypartenaires');
    }


################################################ TRI desc par date ajout ###########################################################################"" 


/**
* @Route("/trip", name="trip")
*/
public function Tri(Request $request,PartenairesRepository $repo)
    {

        //trie
        $partenaire=$this->getDoctrine()->getRepository(Partenaires::class)->findAll();
      
       $partenaire= $repo->TRIEpart();
#dd($parteanire);

return $this->render('partenaire/affichetrie.html.twig', ['partenaire'=>$partenaire]);

    



}


/**
     * @Route("/ajoutePM" , name="ajoutePM" ,  methods={"GET", "POST"}, requirements={"id":"\d+"})
     */
    public function ajouterM(Request $request, SerializerInterface $serializer)
    {
        $partenaire = new Partenaires();
        $matricule_p=$request->query->get('MatriculeP');
        $nom_marque_p=$request->query->get('nomMarqueP');
        $nom_p=$request->query->get('nomP');
        $prenom_p=$request->query->get('prenomP');
  
        $mail_p=$request->query->get('mailP');
        $categorie_p=$request->query->get('categorieP');
        $image=$request->query->get('image');
        $em=$this->getDoctrine()->getManager();
        $partenaire->setMatriculeP($matricule_p);
        $partenaire->setNomMarqueP($nom_marque_p);
        $partenaire->setNomP($nom_p);
        $partenaire->setPrenomP($prenom_p);
       
        $partenaire->setMailP($mail_p);
        $partenaire->setCategorieP($categorie_p);
        $partenaire->setImage($image);


        $em->persist($partenaire);
        $em->flush();
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize($partenaire);
        return new JsonResponse($formatted);
    }

   

    /**
     * @Route("/modifierPM2" , name="modifierPartenaire" ,  methods={"GET", "POST"}, requirements={"id":"\d+"})
     */
    public function modifer(Request $request, SerializerInterface $serializer, PartenairesRepository $repo)
    {
        $partenaire = new Partenaires();
        $id=$request->query->get('idP');
        $partenaire=$repo->find($id);
        $matricule_p=$request->query->get('MatriculeP');
        $nom_marque_p=$request->query->get('nomMarqueP');
        $nom_p=$request->query->get('nomP');
        $prenom_p=$request->query->get('prenomP');
        $mail_p=$request->query->get('mailP');
   
        $em=$this->getDoctrine()->getManager();
        $partenaire->setMatriculeP($matricule_p);
        $partenaire->setNomMarqueP($nom_marque_p);
        $partenaire->setNomP($nom_p);
        $partenaire->setPrenomP($prenom_p);
        $partenaire->setMailP($mail_p);

        $em->persist($partenaire);
        $em->flush();
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize($partenaire);
        return new JsonResponse($formatted);
    }





    /**
      * @Route("/afficherPM")
      */
    public function getList()
    {
        $partenaire =$this->getDoctrine()->getRepository(Partenaires::class)->findAll();
        // $json = $serializer->serialize($categorie, 'json', ['groups' => ['categories']]);
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize($partenaire);
        return new JsonResponse($formatted);
    }
      
       
        /**
         * @Route("/deleteMobile", name="deletePM")
         */
        function deleteMobile(Request $request, PartenairesRepository $repo): Response
        {
            $id =$request->get("idP");
            $em=$this->getDoctrine()->getManager();

            $id=$repo->find($id);

            if ($id != null) {
                $em->remove($id);
                $em->flush();
                $serializer=new Serializer([new ObjectNormalizer()]);
                $formatted=$serializer->normalize("les informations ont ete supprimer ");
                return new JsonResponse($formatted);
            }

            return  new JsonResponse("Id Invalide");
        }




}