<?php

namespace App\Controller;


use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use App\Form\Livraison2Type;
use Doctrine\ORM\EntityManagerInterface;
use Knp\Component\Pager\Pagination\PaginationInterface;
use Symfony\Component\HttpFoundation\Request;
use App\Repository\Livraison2Repository;
use App\Entity\Livraison2;
use App\Entity\Commande;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Dompdf\Dompdf;
use Dompdf\Options;

class Livraison2Controller extends AbstractController
{
    /**
     * @Route("/livraison2", name="app_livraison2")
     */
    public function index(): Response
    {
        return $this->render('livraison2/index.html.twig', [
            'controller_name' => 'Livraison2Controller',
        ]);
    }
     /**
     * @Route("/addLivraison", name="addLivraison")
     */
    public function AjouterLivraison(Request $request)
    {
        $livraison=new Livraison2();
        $form =  $this->createForm(Livraison2Type::class, $livraison);
        $form->handleRequest($request);
        
        if ($form->isSubmitted() && $form->isValid()) { 
            $em=$this->getDoctrine()->getManager();
            $em->persist($livraison);
            $em->flush(); 
            return $this->redirectToRoute('AfficherLivraison'); 
            
        }
        return $this->render('livraison2/addLivraison.html.twig', [
            'formL'=>$form->createView()
        ]);
    }
     /**
     * @Route("/ModifierLivraison/{idlivraison}", name="ModifierLivraison")
     */
    function ModifierLivraison(Livraison2Repository $rep, $idlivraison, Request $request){
        $livraison=$rep->find($idlivraison);
        $form=$this->createForm(Livraison2Type::class,$livraison);
        
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $em=$this->getDoctrine()->getManager();
            $em->flush(); 
            return $this->redirectToRoute('AfficherLivraison'); 
        }
        return $this->render('livraison2/modifierLivraison.html.twig', 
       ['form'=>$form->createView()]); 
    } 
     /**
     * @Route("/SupprimerLivraison/{idlivraison}", name="SupprimerLivraison")
     */
    public function SupprimerLivraison($idlivraison, Livraison2Repository $rep){
        $livraison=$rep->find($idlivraison);
        $em=$this->getDoctrine()->getManager();
        $em->remove($livraison);
        $em->flush();              
        return $this->redirectToRoute('AfficherLivraison'); 
     }
      /**
     * @Route("/AfficherLivraison", name="AfficherLivraison")
     */
    public function AfficheLivraison(){
        $rep=$this->getDoctrine()->getRepository(Livraison2::class); 
        $livraison=$rep->findAll();
        return $this->render('livraison2/afficherLivraison.html.twig', 
        ['livraison'=>$livraison]); 
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
