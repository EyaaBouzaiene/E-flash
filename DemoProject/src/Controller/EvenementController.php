<?php

namespace App\Controller;
use App\Entity\PostLike;
use App\Repository\EvenementRepository;

use App\Entity\Evenement;
use App\Form\EvenementType;
use App\Form\Form1Type;
use Doctrine\ORM\EntityManagerInterface;
use Doctrine\Common\Persistence\ObjectManager;

use FontLib\Table\Type\post;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\JsonResponse;
;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Component\HttpKernel\Exception\NotFoundHttpException;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Contracts\Cache\CacheInterface;
use Symfony\Contracts\Cache\ItemInterface;
use App\Repository\PostLikeRepository;
use Twilio\Rest\Client;

/**
 * @Route("/event")
 */
class EvenementController extends AbstractController
{

    /**
     * @Route("/back", name="afficher", methods={"GET"})
     */
    public function index(Request $request, EntityManagerInterface $entityManager, PaginatorInterface $paginator): Response
    {
        $evenement = $entityManager
            ->getRepository(Evenement::class)
            ->findAll();
        $evenements = $paginator->paginate(
            $evenement,
            // Define the page parameter
            $request->query->getInt('page', 1),
            // Items per page
            3
        );
        return $this->render('evenement/index.html.twig', [
            'evenements' => $evenements,
        ]);
    }


    /**
     * @Route("/front", name="app_evenement_front", methods={"GET"})
     */
    public function index1(EntityManagerInterface $entityManager): Response
    {
        $evenements = $entityManager
            ->getRepository(Evenement::class)
            ->findAll();

        return $this->render('evenement/acceuilfront.html.twig', [
            'evenements' => $evenements,
        ]);
    }

    /**
     * @Route("/new", name="app_evenement_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager, \Swift_Mailer $mailer): Response
    {
        $evenement = new Evenement();
        $form = $this->createForm(EvenementType::class, $evenement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $image = $form->get('image2')->getData();
            $file = md5(uniqid()) . '.' . $image->guessExtension();
            $image->move(
                $this->getParameter('images_directory'),
                $file
            );
            $evenement->setImage2($file);
            $entityManager->persist($evenement);
            $entityManager->flush();
            // On génère l'e-mail
            $message = (new \Swift_Message('New event'))
                ->setFrom('eflash.esprit@gmail.com')
                ->setTo('hadil.khemissi@esprit.tn')
                ->setBody(
                    "A new event was added"
                );

            // On envoie l'e-mail
            $mailer->send($message);




            return $this->redirectToRoute('app_evenement_index');
        }

        return $this->render('evenement/new.html.twig', [
            'evenement' => $evenement,
            'form' => $form->createView(),
        ]);

    }


    /**
     * @Route("/{id}/show", name="app_evenement_show", methods={"GET"})
     */
    public function show(Evenement $evenement): Response
    {
        return $this->render('evenement/show.html.twig', [
            'evenement' => $evenement,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="app_evenement_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Evenement $evenement, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(Form1Type::class, $evenement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_evenement_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('evenement/edit.html.twig', [
            'evenement' => $evenement,
            'form' => $form->createView(),
        ]);
    }


    /**
     * @Route("/delclass2/{id}", name="dellclass12")
     */
    public function deleteclass($id)
    {
        $repos = $this->getDoctrine()->getRepository(evenement::class);
        $result = $repos->find($id);
        $manager = $this->getDoctrine()->getManager();

        $manager->remove($result);
        $manager->flush();
        $this->addFlash('notice', 'suppression avec Succes ');
        return $this->redirectToRoute('afficher');

    }



    /**
     * @Route("/map", name="map")
     */
    public function index2(EntityManagerInterface $entityManager): Response
    {
        $e = $entityManager
            ->getRepository(Evenement::class)
            ->findAll();

        return $this->render('evenement/map.html.twig', [
            'evenements' => $e,
        ]);
    }

    /**
     * @Route("/MAPPED2/{id}", name="MAPPED2")
     */
    public function MAPPED2($id)
    {
        $repos = $this->getDoctrine()->getRepository(Evenement::class)->find($id);

        return $this->render('evenement/mapindiv.html.twig', array("evenement" => $repos));
    }

    /**
     * @Route("/MAPPED3/{id}", name="MAPPED3")
     */
    public function MAPPED3($id)
    {
        $repos = $this->getDoctrine()->getRepository(Evenement::class)->find($id);

        return $this->render('evenement/mapindivfront.html.twig', array("evenement" => $repos));
    }

    /**
     * @Route("/stats", name="stats")
     */
    public function statistiquess(EvenementRepository $evRepo)
    {
        // On va chercher le nombre d'annonces publiées par date
        $evenement = $evRepo->countByDate();

        $dates = [];
        $evenementCount = [];

        // On "démonte" les données pour les séparer tel qu'attendu par ChartJS
        foreach ($evenement as $evenements) {
            $dates[] = $evenements['dateevent'];
            $evenementCount[] = $evenements['count'];
        }

        return $this->render('evenement/stat.html.twig', [

            'dates' => json_encode($dates),
            'evenementCount' => json_encode($evenementCount),
        ]);
    }

  /**
     *@route ("/evenement/{id}/like",name="post_like")
     *@param App\Entity\Evenement $evenement
    * @param Doctrine\ORM\EntityManagerInterface $entityManager
     *@param App\Repository\PostLikeRepository $likeRepo
     *@return Symfony\Component\HttpFoundation\Response
   *
     */
    public function like( Evenement $evenement,EntityManagerInterface $entityManager, PostLikeRepository $likeRepo): Response
    {
$like = new PostLike();
$like ->setPost($evenement);
        $entityManager->persist($like);
        $entityManager->flush();

        return $this->json(['code'=> 200 ,'message' => 'ca marche bien' , 'likes'=> $likeRepo->count(['post' =>$evenement])] , 200);
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