<?php

namespace App\Services;

use App\Entity\Produit2;
use App\Entity\Produits;
use App\Repository\Produit2Repository;
use App\Repository\ProduitsRepository;
use Doctrine\ORM\EntityManagerInterface;
use  Symfony\Component\HttpFoundation\Session\SessionInterface;
class CartServices
{
    private $session;
    private $repoProduct;
    private $tva = 0.2;
    private $manager;
    public function __construct(SessionInterface $session, ProduitsRepository $repoProduct,EntityManagerInterface $manager){
        $this->session = $session;
        $this->repoProduct = $repoProduct;
        $this->manager = $manager;
    }


    public function add(Produits $product){
        $panier = $this->getCart();
        $id = $product->getReference();
        
        if(!empty($panier[$id])){
            $panier[$id]++;
        }else{
            $panier[$id] = 1;
        }

        // On sauvegarde dans la session
        $this->updateCart($panier);
    }

    public function getCart(){
        return $this->session->get('panier',[]);
    }
    public function updateCart($panier){
        $this->session->set('panier', $panier);
        $this->session->set('panierData', $this->getFullCart());
    }

    public function remove(Produits $product){
        $panier = $this->getCart();
        $id = $product->getReference();

      
        if(!empty($panier[$id])){
            if($panier[$id] > 1){
                $panier[$id]--;
            }else{
                unset($panier[$id]);
                $this->updateCart($panier);
            }
        }

        // On sauvegarde dans la session
        $this->updateCart($panier);
      
    }


    public function removeall(Produits $product){
        $panier = $this->getCart();
        $id = $product->getReference();

        if(!empty($panier[$id])){
            unset($panier[$id]);
            $this->updateCart($panier);
        }

        // On sauvegarde dans la session
        
    }


    public function getFullCart(){
     
        $cart = $this->getCart();
        $fullCart = [];
        $quantity_cart = 0;
        $subTotal = 0;
        
        foreach ($cart as $id => $quantity) {
            $product = $this->repoProduct->find($id);
            if($product){
                //produit récupéré avec succès
                $fullCart['products'][] = [
                    "quantity" => $quantity,
                    "product" => $product
                ];
                $quantity_cart += $quantity;
                $subTotal += $quantity * $product->getPrix();
            }else{
                //identifiant incorrect
                $this->remove($id);
            }
        }
        $fullCart['data'] = [
            "quantity_cart" => $quantity_cart,
            "subTotalHT" => $subTotal,
            "Taxe" => round($subTotal*$this->tva,2),
            "subTotalTTC" => round(($subTotal + ($subTotal*$this->tva)),2)
        ];
        return $fullCart;




    }

    public function getFullCart2(){
         $panier = $this->getCart();
         $dataPanier = [];
         foreach ($panier as $id => $quantite) {
            $product =  $this->repoProduct->find($id);
           $dataPanier[] = [
               "products" => $product,
               "quantity" => $quantite
           ];
            
         }

        
         return $dataPanier;
        




    }

     public function getTotal(){
       $total = 0;
     $fullCart=$this->getFullCart2();
    foreach ( $fullCart as $item) {
    $totalItem=$item['products']->getPrix() * $item['quantity'];
         $total+= $totalItem;
        }



         return $total;
     }


     public function getTotal2(){
        $total = 0;
      $fullCart=$this->getFullCart2();
     foreach ( $fullCart as $item) {
     $totalItem=$item['products']->getPrix() * $item['quantity'];
          $total+= round(($totalItem + ($totalItem*$this->tva)),2);
         }
 
 
 
          return $total;
      }

}
