<?php

namespace App\Controller;

use App\Repository\Categorie2Repository;
use App\Repository\Produit2Repository;
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
    public function index(Produit2Repository $p): Response
    {
        $data = $p->findAll();
        $dest = array();
        foreach ($data as $x) {
           //$dest[] = [$x->getDestination()] ; 
           array_push($dest, $x->getNOM());
        }
        
        $pieChart = new PieChart();
        $array_dest_occ = array_count_values($dest);
        $final = [
           ['nom ', 'Quantite']
        
        ];
        foreach ($array_dest_occ as $x => $x_value) {
           $final[] = [$x, (int)$x_value];
        }
        
            
                  
                   $pieChart->getData()->setArrayToDataTable( $final);
        
               $pieChart->getOptions()->setTitle('produit en fonction des noms');
               $pieChart->getOptions()->setHeight(500);
               $pieChart->getOptions()->setWidth(500);
               $pieChart->getOptions()->getTitleTextStyle()->setColor('#07600');
               $pieChart->getOptions()->getTitleTextStyle()->setFontSize(35);
        

       
        

        return $this->render('back/acceuil.html.twig', [
            'controller_name' => 'ProduitController','piechart' => $pieChart
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
 
