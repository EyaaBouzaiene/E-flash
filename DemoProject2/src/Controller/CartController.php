<?php

namespace App\Controller;
use App\Repository\Categorie2Repository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Produit2;
use App\Entity\Categorie2;
use App\Repository\Produit2Repository;
use App\Services\CartServices;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Generator\UrlGeneratorInterface;
use Stripe\Checkout\Session;
use Stripe\Stripe;

class CartController extends AbstractController
{
    /**
     * @Route("/cart", name="app_cart")
     */
    public function index(CartServices $cartServices): Response
    {

        $panier = $cartServices->getFullCart();

        // On "fabrique" les données
        
        $total =$cartServices->getTotal() ;
       
        

        return $this->render('cart/index.html.twig',['dataPanier'=>$panier,'total'=>$total]);
        
    }

     /**
     * @Route("/add/{id}", name="add")
     */
    public function add(Produit2 $product,CartServices $cartServices)
    {
        // On récupère le panier actuel
        
$cartServices->add($product);
        return $this->redirectToRoute("app_cart");
    }

    /**
     * @Route("/remove/{id}", name="remove")
     */
    public function remove(Produit2 $product,CartServices $cartServices )
    {
        // On récupère le panier actuel
        $cartServices->remove($product);


        return $this->redirectToRoute("app_cart");
    }

    /**
     * @Route("/delete/{id}", name="delete")
     */
    public function delete(Produit2 $product,CartServices $cartServices)
    {
        // On récupère le panier actuel
        $cartServices->removeall($product);

        return $this->redirectToRoute("app_cart");
    }

    /**
     * @Route("/delete", name="delete_all")
     */
    public function deleteAll(SessionInterface $session)
    {
        $session->remove("panier");

        return $this->redirectToRoute("app_cart");
    }
 /**
      * @Route("/checkout2", name="stripe")
      * @throws ApiErrorException
      */
   
     public function checkout($stripeSK,SessionInterface $session,Produit2Repository $productsRepository,Categorie2Repository $classroomRepository,CartServices $cartServices)
     {
         Stripe::setApiKey($stripeSK);
        
        
         $panier = $session->get("panier", []);
         $dataPanier = [];
         $total =$cartServices->getTotal2() ;
         $classroom=$classroomRepository->findAll();
    
            
          
            
             $session = Session::create([
                 'payment_method_types' => ['card'],
                
                
                 'line_items'           => [
                     [
                         'price_data' => [
                             'currency'     => 'eur',
                             'product_data' => [
                                 'name' => 'TOTAL',
                                
                             ],
                             'unit_amount'  =>$total*100,
                         ],
                         'quantity'   =>1,
                     ]
                 ],
              
                 'mode'                 => 'payment',
                 'allow_promotion_codes' => true,
                 'success_url'          => $this->generateUrl('success_url', [], UrlGeneratorInterface::ABSOLUTE_URL),
                 'cancel_url'           => $this->generateUrl('cancel_url', [], UrlGeneratorInterface::ABSOLUTE_URL),
             ]);
             

        
       return $this->redirect($session->url, 303);
     }

/**
     * @Route("/success-url", name="success_url")
     */
    
    
    public function successUrl(): Response
    {
       /* $reservation = $this->getDoctrine()->getRepository(Produit2::class)->find($idReservation);

        $reservation->setPaid(true);
        
        $this->getDoctrine()->getManager()->persist($reservation);
        $this->getDoctrine()->getManager()->flush();*/

        
        return $this->render('payment/success.html.twig', []);
    }

/**
     * @Route("/cancel-url", name="cancel_url")
     */
    
   
    public function cancelUrl(): Response
    {
        return $this->render('payment/cancel.html.twig', []);
    }



    }
   
    