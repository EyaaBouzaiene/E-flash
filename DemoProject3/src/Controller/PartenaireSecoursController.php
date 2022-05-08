<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Form\PartenaireSecoursType;
use App\Form\PartenaireSecoursUpdateType;
use Symfony\Component\HttpFoundation\Request ; 
use App\Entity\Partenairesecours ;  

class PartenaireSecoursController extends AbstractController
{
    /**
     * @Route("/partenaire/secours", name="app_partenaire_secours")
     */
    public function index(): Response
    {
        return $this->render('partenaireSecours/index.html.twig', [
            'controller_name' => 'PartenaireSecoursController',
        ]);
    }


     /**
     * @Route("/ajouterpartenaireSecours", name="addpartenaireSecours")
     */
    public function ajouter(Request $req)
    {
        $Partenairesecours = new Partenairesecours();
        $form = $this->createForm(PartenaireSecoursType::class,$Partenairesecours);
        $form->handleRequest($req) ; 

        if ($form->isSubmitted() && $form->isValid()) {
            $image = $form->get('image')->getData();
            $file = md5(uniqid()) . '.' . $image->guessExtension();
            $image->move(
                $this->getParameter('images_directory'),
                $file
            );
            $Partenairesecours->setimage($file);
            $em = $this->getDoctrine()->getManager() ; 
            $Partenairesecours->setDatePS(new \DateTime('now'));
            
            $em->persist( $Partenairesecours) ; 
            $em->flush() ;
            $this->addFlash('notice' , 'Ajout Partenaire Secours avec Succés ') ;
            return $this->redirectToRoute('displaypartenaireSecours') ; 
        }

        return $this->render('partenaireSecours/ajouter.html.twig' , ['form'=> $form->createView() ]) ; 
    }

    
    /**
     * @Route("/affichepartenaireSecours", name="displaypartenaireSecours")
     */

    public function affiche(): Response
    {
        

         $PartenaireSec=$this->getDoctrine()->getRepository(Partenairesecours::class)->findAll(); 
           return $this->render('partenaireSecours/affiche.html.twig',['afficheps'=>$PartenaireSec]);
     
    }

 /**
     * @Route("/updatepartenaireSecours/{id}", name="updatepartenaireSecours")
     */

    public function update(Request $requ, $id)
    {
        $repos=$this->getDoctrine()->getRepository(Partenairesecours::class);
        $partenairesecours=$repos->find($id);
   
        $form = $this->createForm(PartenaireSecoursUpdateType::class, $partenairesecours);

        $form->handleRequest($requ);

        if ($form->isSubmitted() && $form->isValid()) {

          

                $em = $this->getDoctrine()->getManager();
                $em->persist($partenairesecours);
                $em->flush();
                $this->addFlash('notice' , 'Modification avec Succes ') ;
                return $this->redirectToRoute('displaypartenaireSecours');
        }
        

        return $this->render('Partenairesecours/update.html.twig', ['form' => $form->createView()]);
    }
   
/**
     *@Route("/supprimerpartenaireSecours/{id}", name="dellpartenaireSecours")
     */
    public function dellpartenaireSecours($id) {
        $repos=$this->getDoctrine()->getRepository(Partenairesecours::class);
        $resultPS=$repos->find($id);
        $manager=$this->getDoctrine()->getManager();
        
        $manager->remove($resultPS);
        $manager->flush();
        $this->addFlash('notice' , 'suppression avec Succes ') ;
        return $this->redirectToRoute('displaypartenaireSecours');
        
        }
        
   


}
