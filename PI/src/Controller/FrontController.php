<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Partenaires ; 
use App\Entity\Stock2; 
use App\Repository\PartenairesRepository;
use App\Repository\Stock2Repository;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\BarChart;

use Symfony\Component\HttpFoundation\Request;


class FrontController extends AbstractController
{
    /**
     * @Route("/front", name="app_front")
     */
    public function index(): Response
    {
      
        return $this->render('front/acceuilfront.html.twig', [
            'controller_name' => 'FrontController'
        ]);
    }

    /**
     * @Route("/affichePfront", name="affichePfront")
     */
    public function affichePfront(): Response
    {
        $Partenaires=$this->getDoctrine()->getRepository(Partenaires::class)->findAll();
///bar chart Partenaire en fonction de la qualite
$partenaire= []  ; 
             
       $qualite=[];
$datac= $this->getDoctrine()->getRepository(Stock2::class)->selectNomMarque();
foreach ($datac as $xB) { 
    $partenaire[]=$xB['nomM'];
    $qualite[]=$xB['qualite'];
}
///line chart Mailleur chart



$partenaire1= []  ; 
             
       $qualite1=[];
$datac1= $this->getDoctrine()->getRepository(Stock2::class)->selectNomMarque();
foreach ($datac1 as $xB1) { 
    $partenaire1[]=$xB1['nomM'];
    $qualite1[]=$xB1['qualite'];


return $this->render('front/acceuilfront.html.twig',['afficheparte'=>$Partenaires, 'qualite' => json_encode($qualite),
'nomM' => json_encode($partenaire),]);
    }


    

}
}

