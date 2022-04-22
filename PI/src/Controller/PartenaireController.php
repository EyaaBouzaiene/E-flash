<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request ;  
use App\Entity\Partenaires ; 
use App\Form\PartenaireType;
use App\Form\PartenaireUpdateType;
use App\Form\StockUpdateType;
use Dompdf\Dompdf;
use Dompdf\Options;



use App\Form\ContactType;
use App\Form\SearchAnnonceType;
use App\Repository\AnnoncesRepository;
use App\Service\SendMailService;



class PartenaireController extends AbstractController
{
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

    



}
