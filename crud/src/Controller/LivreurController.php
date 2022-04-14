<?php

namespace App\Controller;


use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use App\Form\LivreurType;
use Doctrine\ORM\EntityManagerInterface;
use Knp\Component\Pager\Pagination\PaginationInterface;
use Symfony\Component\HttpFoundation\Request;
use App\Repository\LivreurRepository;
use App\Entity\Livreur;
use App\Entity\Livraison2;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Dompdf\Dompdf;
use Dompdf\Options;
class LivreurController extends AbstractController
{
    /**
     * @Route("/livreur", name="app_livreur")
     */
    public function index(): Response
    {
        return $this->render('livreur/index.html.twig', [
            'controller_name' => 'LivreurController',
        ]);
    }
    /**
     * @Route("/addLivreur", name="addLivreur")
     */
    public function AjouterLivreur(Request $request)
    {
        $livreur=new Livreur();
        $form = $this->createForm(LivreurType::class, $livreur);
        $form->handleRequest($request);
        
        if ($form->isSubmitted() && $form->isValid()) { 
            $em=$this->getDoctrine()->getManager();
            $em->persist($livreur);
            $em->flush(); 
            return $this->redirectToRoute('AfficherLivreur'); 
            
        }
        return $this->render('livreur/addLivreur.html.twig', [
            'formL'=>$form->createView()
        ]);
    }
     /**
     * @Route("/ModifierLivreur/{id}", name="ModifierLivreur")
     */
    function ModifierLivreur(LivreurRepository $rep, $id, Request $request){
        $livreur=$rep->find($id);
        $form=$this->createForm(LivreurType::class,$livreur);
        
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $em=$this->getDoctrine()->getManager();
            $em->flush(); 
            return $this->redirectToRoute('AfficherLivreur'); 
        }
        return $this->render('livreur/modifierLivreur.html.twig', 
       ['form'=>$form->createView()]); 
    } 
     /**
     * @Route("/SupprimerLivreur/{id}", name="SupprimerLivreur")
     */
    public function SupprimerLivreur($id, LivreurRepository $rep){
        $livreur=$rep->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($livreur);
        $em->flush();              
        return $this->redirectToRoute('AfficherLivreur'); 
     }
      /**
     * @Route("/AfficherLivreur", name="AfficherLivreur")
     */
    public function AfficheLivreur(){
        $rep=$this->getDoctrine()->getRepository(Livreur::class); 
        $livreur=$rep->findAll();
        return $this->render('livreur/afficherLivreur.html.twig', 
        ['livreur'=>$livreur]); 
     }
     /**
     * @Route("/AfficherLivraisonL/{idl}", name="AfficherLivraisonL")
     */
    public function AfficheLivraisonLiv($idl){
        $rep=$this->getDoctrine()->getRepository(Livraison2::class); 
        $livraison=$rep->find($idl);
        return $this->render('livraison2/afficherLivraisonL.html.twig', 
        ['livraison'=>$livraison]); 
     }


}
