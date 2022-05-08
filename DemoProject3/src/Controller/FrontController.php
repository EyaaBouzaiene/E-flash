<?php

namespace App\Controller;

use App\data\Rate;
use App\data\SearchData;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Categorie2;
use App\Entity\Categories;
use App\Entity\Produit2;
use App\Entity\Produits;
use App\Entity\Review;
use App\Form\RateForm;
use App\Form\ReviewType;
use App\Form\SearchForm;
use App\Repository\Categorie2Repository;
use App\Repository\CategoriesRepository;
use App\Repository\Produit2Repository;
use App\Repository\ProduitsRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\HttpFoundation\Request;
class FrontController extends AbstractController
{
    /**
     * @Route("/front2", name="app_front")
     */
    public function index(): Response
    {
        return $this->render('produit/_rate.html.twig');
    }

    /**
     *@Route("/front", name="listC")
     */
    public function list(ProduitsRepository $p ,CategoriesRepository $classroomRepository): Response
    {
        $classroom=$this->getDoctrine()->getRepository(Categories::class)->findAll();
        $classroom3=$p->findAll();
        $produit=$p->findEntitiesByRate2();
        return $this->render('front/acceuilfront.html.twig',['listC'=>$classroom,'listProd'=> $produit,'listP'=>$classroom3]);
        
    }


     /**
     *@Route("/front3", name="listC2")
     */
    public function list2(ProduitsRepository $p ,CategoriesRepository $classroomRepository): Response
    {
        $classroom=$this->getDoctrine()->getRepository(Categories::class)->findAll();
        $classroom3=$p->findAll();
        $produit=$p->findEntitiesByRate2();
        return $this->render('front/acceuilfront.html.twig',['listC'=>$classroom,'listProd'=> $produit,'listP'=>$classroom3]);
        
    }
   
    

    /**
     * Undocumented function
     *
     * @param ProduitsRepository $p
     * @param CategoriesRepository $classroomRepository
     * @param [type] $id
     * @return void
     *@Route("/afficheP/{id}", name="afficheP")
     */
    public function list3(CategoriesRepository $classroomRepository,$id,ProduitsRepository $p,Request $request,Request $request2,EntityManagerInterface $entityManager): Response
    {
        $data = new SearchData();
        $review=new Review();
        $data->page = $request->get('page', 1);
        $form = $this->createForm(SearchForm::class, $data);
        $form2= $this->createForm(ReviewType::class, $review);
        $user = $this->getUser();
        $form->handleRequest($request);
        $form2->handleRequest($request2);
        $produits = $entityManager
            ->getRepository(Produits::class)
            ->findAll();
        if($form2->isSubmitted()) 
        {

           

            $review->setUser($user);
          //  $review->setProduits($defi);
            $time = new \DateTime();
            $time->format('Y-m-d H:i');
            $review->setDate($time);
            $entityManager->persist($review);
            $entityManager->flush();

            return $this->redirectToRoute('list2');
        }
        $classroom=$classroomRepository->findAll();
        $classroom3=$classroomRepository->find($id);
        $produit=$p->listProduitByCat($classroom3->getCode(),$data);
        return $this->render('produit/affichePfront.html.twig',['listP'=>$classroom, 'c'=> $classroom3 ,'listProd'=> $produit,'form' => $form->createView(),'form2' => $form2->createView()]);
        
    }

 /**
     *@Route("/afficheP/produits/{reference}", name="Rview")
     */

    public function Review(Produits $defi,$id,Request $request,EntityManagerInterface $entityManager): Response
    {
       
        $review=new Review();
    
      
        $form2= $this->createForm(ReviewType::class, $review);
        $user = $this->getUser();
        $form2->handleRequest($request);
        $produits = $entityManager
            ->getRepository(Produits::class)
            ->findAll();
              
        if($form2->isSubmitted()&& $form2->isValid()) 
        {

           

            $review->setUser($user);
            $review->setProduits($defi);
            $time = new \DateTime();
            $time->format('Y-m-d H:i');
           // $review->setDate($time);
            $entityManager->persist($review);
            $entityManager->flush();

            return $this->render('produit/affichePfront.html.twig',['defi' => $defi,'defis' => $produits,'form2' => $form2->createView(), 'user' => $user,]);
        }
      
        return $this->render('produit/affichePfront.html.twig',['defi' => $defi,'defis' => $produits,'form2' => $form2->createView(), 'user' => $user,]);
        
    }
    
  
/**
 * Undocumented function
     *
     * @param ProduitsRepository $p
     * @param CategoriesRepository $classroomRepository
     * @param [type] $id
     * @return void
    
     *@Route("/readmore/{id}", name="readmore")
     */
    public function list4(CategoriesRepository $classroomRepository,$id,ProduitsRepository $p): Response
    {
        $produit=$this->getDoctrine()->getRepository(Produits::class)->findBy(['reference'=>$id]);
        $classroom=$classroomRepository->findAll();
       
       
       
        return $this->render('produit/readmore.html.twig',['listP'=>$classroom,'listProd'=> $produit]);
        
    }

     

    
    
}
