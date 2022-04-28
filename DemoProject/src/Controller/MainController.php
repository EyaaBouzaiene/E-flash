<?php

namespace App\Controller;
use App\Repository\EvenementRepository;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use App\Entity\Evenement;
class MainController extends AbstractController
{
    /**
     * @Route("/calendar", name="calendar_event")
     */
    public function index12(Request $request, EvenementRepository $calendar): Response
    {

        $events = $calendar->findAll();
        $rdvs = [];
        foreach ($events as $events) {
            $rdvs[] = ['id' => $events->getId(),
                'start' => $events->getDateDebut()->format('Y-m-d H:i:s'),
                'end' => $events->getEnd()->format('Y-m-d H:i:s'),
                'image' => $events->getImage2(),
                
                'title' => $events->getNom(),
                'duree' => $events->getDuree(),
                'nombre de place' => $events->getNombrePlace(),
                'description' => $events->getDescription(),


            ];


        }
        $data = json_encode($rdvs);


        return $this->render('evenement/index2.html.twig', compact('data'));


    }
    /**
     * @Route("/listU", name="listU", methods={"GET"})
     */
    public function pdf(EvenementRepository  $rep) :Response
    {


        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        $reader=$rep->findAll();


        // Retrieve the HTML generated in our twig file

        $html = $this->renderView('evenement/pdf.html.twig', array(
            'users'=>$reader
        ));

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A3', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("UsersList.pdf", [
            "Attachment" => true
        ]);

        // Send some text response
        return new Response("The PDF file has been succesfully generated !");

    }
}

