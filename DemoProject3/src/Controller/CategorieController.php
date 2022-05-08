<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Categorie2;
use App\Entity\Categories;
use App\Repository\Categorie2Repository;
use Symfony\Component\HttpFoundation\Request;
class CategorieController extends AbstractController
{
    /**
     * @Route("/categorie", name="app_categorie")
     */
    public function index(): Response
    {
        return $this->render('categorie/index.html.twig', [
            'controller_name' => 'CategorieController',
        ]);
    }


     /**
     *@Route("/listC", name="listC")
     */
    public function list(): Response
    {
        $classroom=$this->getDoctrine()->getRepository(Categories::class)->findAll();
        return $this->render('front/acceuilfront.html.twig',['listC'=>$classroom]);
        
    }


    


   



    
}
