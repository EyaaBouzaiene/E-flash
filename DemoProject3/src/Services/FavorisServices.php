<?php

namespace App\Services;
use App\Security\LoginAuthenticator;
use App\Entity\Favoris;
use App\Entity\Produit2;
use App\Entity\Produits;
use App\Entity\User;
use App\Repository\FavorisRepository;
use App\Repository\Produit2Repository;
use App\Repository\ProduitsRepository;
use App\Repository\UserRepository;
use Doctrine\ORM\EntityManagerInterface;
use  Symfony\Component\HttpFoundation\Session\SessionInterface;
class FavorisServices
{
    private $session;
    private $repoProduct;
    public function __construct(SessionInterface $session, ProduitsRepository $repoProduct,EntityManagerInterface $manager,FavorisRepository $repofav,UserRepository $repouser){
        $this->session = $session;
        $this->repoProduct = $repoProduct;
        $this->repofav = $repofav;
        $this->manager = $manager;
        $this->repouser = $repouser;
    }


    public function add(Produits $product,$user){
        $panier = $this->getFavoris();
        $id = $product->getReference();
        
        if(empty($panier[$id])){
           
            $panier[$id] = 1;
            $favoris= new Favoris();
            $favoris->setProduits($product)
                    ->setDate(new \DateTime())
                    ->setUser($user);

$this->manager->persist($favoris);
$this->manager->flush();
        }else{
           
        }

        // On sauvegarde dans la session
        $this->updateFavoris($panier);
    }

    public function removeall(Produits $product){
        $panier = $this->getFavoris();
        $id = $product->getReference();

        if(!empty($panier[$id])){
            unset($panier[$id]);
           
            
           
            $this->updateFavoris($panier);
        }

        // On sauvegarde dans la session
        
    }
    public function getFavoris(){
        return $this->session->get('panier',[]);
    }
    public function updateFavoris($panier){
        $this->session->set('panier', $panier);
        $this->session->set('panierData', $this->getFullFavoris());
    }

    public function remove(Produits $product){
        $panier = $this->getFavoris();
        $id = $product->getReference();

      
        if(!empty($panier[$id])){
            
                unset($panier[$id]);
               
                $this->updateFavoris($panier);
            
        }

        // On sauvegarde dans la session
      
    }

    public function getFullFavoris(){
     
        $cart = $this->getFavoris();
        $fullCart = [];
        
      
        foreach ($cart as $id => $quantity) {
            $product = $this->repoProduct->find($id);

            
           // $user = $this->repouser->find($id);
            if($product){
                //produit récupéré avec succès
                $fullCart['products'][] = [
                    "quantity" => $quantity,
                    "product" => $product,
                   
                ];
               
            }else{
                //identifiant incorrect
                $this->remove($id);
            }
        }
        $fullCart['data'] = [
           
        ];
        return $fullCart;




    }

    public function saveOffre(Produits $product)
    {

        $panier = $this->getFavoris();
        $id = $product->getReference();    
$favoris= new Favoris();

$cart_details_array = [];

$favoris->setProduits($product)
->setDate(new \DateTime());
$this->manager->persist($favoris);
$this->manager->flush();
$this->updateFavoris($panier);

    }



}
