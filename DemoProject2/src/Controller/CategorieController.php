<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Categorie2;
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
        $classroom=$this->getDoctrine()->getRepository(Categorie2::class)->findAll();
        return $this->render('front/acceuilfront.html.twig',['listC'=>$classroom]);
        
    }




   

/**
   * Creates a new ActionItem entity.
   *
   * @Route("/search", name="ajax_search")
  
   */
  public function searchAction(Request $request,Categorie2Repository $p)
  {
      $em = $this->getDoctrine()->getManager();

      $requestString = $request->get('q');

      $entities =  $p->findEntitiesByString($requestString);

      return $this->render('produit/afficheP.html.twig', ['categorias' => $entities]);
  }

  public function getRealEntities($entities){

      foreach ($entities as $entity){
          $realEntities[$entity->getId()] = $entity->getFoo();
      }

      return $realEntities;
  }

    
}
