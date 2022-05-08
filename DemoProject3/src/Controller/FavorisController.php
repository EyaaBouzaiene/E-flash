<?php

namespace App\Controller;

use App\Entity\Produit2;
use App\Entity\Produits;
use App\Services\FavorisServices;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Session\SessionInterface;

class FavorisController extends AbstractController
{
    /**
     * @Route("/favoris", name="app_favoris")
     */
    public function index(FavorisServices $cartServices): Response
    {
  
        $panier = $cartServices->getFullFavoris();

        // On "fabrique" les données
        
       
       
        

        return $this->render('favoris/index.html.twig',['dataPanier'=>$panier]);
        
    }
    

      /**
     * @Route("/addf/{id}", name="addf")
     */
    public function add(Produits $product,FavorisServices $cartServices)
    {
        // On récupère le panier actuel
        $user = $this->getUser();
        $cartServices->add($product,$user);   
        return $this->redirectToRoute("app_favoris");
    }

      /**
     * @Route("/removef/{id}", name="removef")
     */
    public function remove(Produits $product,FavorisServices $cartServices )
    {
        // On récupère le panier actuel
        $cartServices->remove($product);


        return $this->redirectToRoute("app_favoris");
    }
/**
     * @Route("/deletef/{id}", name="deletef")
     */
    public function delete(Produits $product,FavorisServices $cartServices)
    {
        // On récupère le panier actuel
        $cartServices->removeall($product);

        return $this->redirectToRoute("app_favoris");
    }


}
