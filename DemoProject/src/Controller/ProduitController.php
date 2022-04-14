<?php

namespace App\Controller;

use App\Entity\Produit2;
use App\Form\AJOUTPRODUITSType;
use App\Form\ModifierProduitsType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use App\Repository\Produit2Repository;
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
     *@Route("/list2", name="list2")
     */
    public function list(): Response
    {
        $classroom=$this->getDoctrine()->getRepository(Produit2::class)->findAll();
        return $this->render('produit/afficheP.html.twig',['list'=>$classroom]);
    }
    /**
     * @Route("/addproduit", name="addapp_produit")
     */
    public function create(Request $request)
    {
        $produit = new Produit2();
        $form = $this->createForm(AJOUTPRODUITSType::class, $produit);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid() ) {
            $produit->setRate(0);
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


        return $this->render('produit/index.html.twig', ['formProduit' => $form->createView()]);
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
}
