<?php

namespace App\Controller;

use App\data\SearchData;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Categorie2;
use App\Entity\Produit2;
use App\Form\SearchForm;
use App\Repository\Categorie2Repository;
use App\Repository\Produit2Repository;
use Symfony\Component\HttpFoundation\Request;
class FrontController extends AbstractController
{
    /**
     * @Route("/front2", name="app_front")
     */
    public function index(): Response
    {
        return $this->render('front/acceuilfront.html.twig', [
            'controller_name' => 'FrontController',
        ]);
    }

    /**
     *@Route("/front", name="listC")
     */
    public function list(Produit2Repository $p ,Categorie2Repository $classroomRepository): Response
    {
        $classroom=$this->getDoctrine()->getRepository(Categorie2::class)->findAll();
        $classroom3=$p->findAll();
        $produit=$p->findEntitiesByRate2();
        return $this->render('front/acceuilfront.html.twig',['listC'=>$classroom,'listProd'=> $produit,'listP'=>$classroom3]);
        
    }
   
    

    /**
     * Undocumented function
     *
     * @param Produit2Repository $p
     * @param Categorie2Repository $classroomRepository
     * @param [type] $id
     * @return void
     *@Route("/afficheP/{id}", name="afficheP")
     */
    public function list3(Categorie2Repository $classroomRepository,$id,Produit2Repository $p,Request $request): Response
    {
        $data = new SearchData();
        $data->page = $request->get('page', 1);
        $form = $this->createForm(SearchForm::class, $data);
        $form->handleRequest($request);
        $classroom=$classroomRepository->findAll();
        $classroom3=$classroomRepository->find($id);
        $produit=$p->listProduitByCat($classroom3->getId(),$data);
        return $this->render('produit/affichePfront.html.twig',['listP'=>$classroom, 'c'=> $classroom3 ,'listProd'=> $produit,'form' => $form->createView()]);
        
    }
    // public function list3(Categorie2Repository $classroomRepository,$id,Produit2Repository $p): Response
    // {
      
    //     $classroom=$classroomRepository->findAll();
    //     $classroom3=$classroomRepository->find($id);
    //     $produit=$p->listProduitByCat($classroom3->getId());
    //     return $this->render('produit/affichePfront.html.twig',['listP'=>$classroom, 'c'=> $classroom3 ,'listProd'=> $produit]);
        
    // }
    

/**
 * Undocumented function
     *
     * @param Produit2Repository $p
     * @param Categorie2Repository $classroomRepository
     * @param [type] $id
     * @return void
    
     *@Route("/readmore/{id}", name="readmore")
     */
    public function list4(Categorie2Repository $classroomRepository,$id,Produit2Repository $p): Response
    {
        $produit=$this->getDoctrine()->getRepository(Produit2::class)->findBy(['id'=>$id]);
        $classroom=$classroomRepository->findAll();
       
       
       
        return $this->render('produit/readmore.html.twig',['listP'=>$classroom,'listProd'=> $produit]);
        
    }

     

    
    
}
