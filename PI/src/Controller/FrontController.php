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
///bar chart



$dataB = $this->getDoctrine()->getRepository(Partenaires::class)->findAll();
$stock=$this->getDoctrine()->getRepository(Stock2::class)->findAll();
$d[]=$dataB | $stock;
$partenaire= [
    ['Nom Stock','Qualité']

]  ; 


 foreach ($dataB as $xB) { 

   
     foreach ($stock as $xS) { 
$partenaire[] = [$xB->getNomMarqueP(),(int)$xS->getQualiteS() ];

     

$bar = new BarChart();
$bar->getData()->setArrayToDataTable($partenaire) ; 



$bar->getOptions()->setTitle('La qualité de nos Partenaires ');
$bar->getOptions()->getHAxis()->setTitle('Qualité');
$bar->getOptions()->getHAxis()->setMinValue(0);
$bar->getOptions()->getVAxis()->setTitle('Partenaires');
$bar->getOptions()->setWidth(900);
$bar->getOptions()->setHeight(500); 
}}

return $this->render('front/acceuilfront.html.twig',['afficheparte'=>$Partenaires,'BarChart' => $bar , 'list' => $d]);
    }
/**
     * @Route("/statParetnaire", name="statParetnaire")
     */
    public function statParetnairet(): Response
    {
      


       $dataB = $this->getDoctrine()->getRepository(Partenaires::class)->findAll();
       $stock=$this->getDoctrine()->getRepository(Stock2::class)->findAll();
       $d[]=$dataB | $stock;
       $partenaire= [
           ['Nom Stock','Qualité']

       ]  ; 

   
        foreach ($dataB as $xB) { 
     
          
            foreach ($stock as $xS) { 
   $partenaire[] = [$xB->getNomMarqueP(),(int)$xS->getQualiteS() ];

            

$bar = new BarChart();
$bar->getData()->setArrayToDataTable($partenaire) ; 



$bar->getOptions()->setTitle('La qualité de nos Partenaires ');
$bar->getOptions()->getHAxis()->setTitle('Qualité');
$bar->getOptions()->getHAxis()->setMinValue(0);
$bar->getOptions()->getVAxis()->setTitle('Partenaires');
$bar->getOptions()->setWidth(900);
$bar->getOptions()->setHeight(500); 
}}
        return $this->render('front/acceuilfront.html.twig',['BarChart' => $bar , 'list' => $d]);
    }
    

    

}

