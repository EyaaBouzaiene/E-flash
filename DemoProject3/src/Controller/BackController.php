<?php

namespace App\Controller;

use App\Entity\Produit2;
use App\Entity\User;
use App\Repository\Categorie2Repository;
use App\Repository\Produit2Repository;
use App\Repository\ProduitsRepository;
use App\Repository\UserRepository;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\GeoChart;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\UX\Chartjs\Builder\ChartBuilderInterface;

use Doctrine\ORM\EntityManager;
class BackController extends AbstractController
{
    /**
     * @Route("/back", name="app_back")
     */
    public function index(ProduitsRepository $p,UserRepository $user): Response
    {
        $evenement = $p->findEntitiesByRate3();

        $nom = [];
        $rate = [];

        // On "démonte" les données pour les séparer tel qu'attendu par ChartJS
        foreach($evenement as $evenements){
            $nom[] = $evenements['nom'];
            $rate[] = $evenements['rate'];
        }

       
               $dataB = $this->getDoctrine()->getRepository(User::class)->findAll();
               $dest2 = array();
               foreach ($dataB as $x) {
                  //$dest[] = [$x->getDestination()] ; 
                  array_push($dest2, $x->getCOUNTRY());
               }

               $array_dest_occ2 = array_count_values($dest2);
               
              
                $stockB= [
                    ['Country','Users']
        
                ]  ; 
               
                    foreach ($array_dest_occ2 as $x => $x_value) {
                        
                        $stockB[] =  [$x, (int)$x_value];
                        
                    
                }
        $bar = new GeoChart();
        $bar->getData()->setArrayToDataTable($stockB) ; 
        
        $bar->getOptions()->getColorAxis()->setColors(['green', 'blue','yellow']);
        return $this->render('back/acceuil.html.twig', [
            'controller_name' => 'ProduitController','geochart' => $bar,
            'nom' => json_encode($nom),
            'rate' => json_encode($rate),
        ]);
    }


   
    }


//      /**
//      * @Route("/back2", name="app_back2")
//      */
//     public function afficheMAP(Categorie2Repository $classroomRepository,$id,Produit2Repository $p): Response
//     {
        
//         $produit=$p->findEntitiesByCOUNTRY();
//         return $this->render('back/acceuil.html.twig',['listProd3'=> $produit]);
//     }
 
