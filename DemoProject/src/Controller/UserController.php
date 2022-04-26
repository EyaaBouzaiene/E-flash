<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\UserType;
use App\Repository\UserRepository;
use Dompdf\Dompdf;
use Dompdf\Options;
use PhpOffice\PhpSpreadsheet\Spreadsheet;
use PhpOffice\PhpSpreadsheet\Writer\Xlsx;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\BinaryFileResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\ResponseHeaderBag;
use Symfony\Component\HttpFoundation\Session\Session;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Gedmo\Sluggable\Util\Urlizer;
use Symfony\Component\Security\Csrf\TokenGenerator\TokenGeneratorInterface;
use Symfony\UX\Chartjs\Builder\ChartBuilderInterface;
use Symfony\UX\Chartjs\Model\Chart;

class UserController extends AbstractController
{
    /**
     * @Route("/user", name="app_user")
     */
    public function index(): Response
    {
        return $this->render('user/index.html.twig', [
            'controller_name' => 'UserController',
        ]);
    }
    /**
     *@Route("/afficher_user", name="afficher_user")
     */
    public function list(): Response
    {
        $classroom=$this->getDoctrine()->getRepository(User::class)->findAll();
        return $this->render('user/afficher_user.html.twig',['list'=>$classroom]);
    }

    /**
     *@Route("/delete_user/{id}", name="deluser")
     */
public function deleteclass($id) {
    $repos=$this->getDoctrine()->getRepository(User::class);
    $result=$repos->find($id);
    $manager=$this->getDoctrine()->getManager();
    
    $manager->remove($result);
    $manager->flush();
    $this->addFlash('notice' , 'suppression avec Succes ') ;
    return $this->redirectToRoute('afficher_user');
    
    }  

      /**
     * @Route("/adduser", name="adduser")
     */
    public function create(Request $request)
    {
        $user = new User();
        $form = $this->createForm(UserType::class, $user);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid() ) {
         /*   $uploadedFile = $form['image']->getData();
            $destination = $this->getParameter('kernel.project_dir').'/public/images';
            $originalFilename = pathinfo($uploadedFile->getClientOriginalName(), PATHINFO_FILENAME);
            $newFilename = Urlizer::urlize($originalFilename).'-'.uniqid().'.'.$uploadedFile->guessExtension();
            $uploadedFile->move(
                $destination,
                $newFilename
            );
            $user->setImage($newFilename);*/
            //$user->setPassword($passwordEncoder->encodePassword($utilisateur, $utilisateur->getPassword()));
            $role = ['ROLE_USER'];
            $user->setRole($role);
                $em = $this->getDoctrine()->getManager();
                $em->persist($user);
                $em->flush();
              
            
        }


        return $this->render('user/adduser.html.twig', ['form' => $form->createView()]);
    }


    /**
     * @Route("/userlog", name="user_index", methods={"GET"})
     */
    public function index1(UserRepository $utilisateurRepository, Session $session,TokenGeneratorInterface $token , \Symfony\Component\Security\Core\Authentication\Token\Storage\TokenStorageInterface $tokenStorage): Response
    {
        //besoin de droits admin
        $utilisateur = $this->getUser();
        if(!$utilisateur)
        {
            $session->set("message", "Merci de vous connecter");
            return $this->redirectToRoute('app_login');
        }
        else if(in_array('ROLE_USER', $utilisateur->getRoles())){

                return  $this->redirectToRoute('app_front');
            }




        else if(in_array('ROLE_ADMIN', $utilisateur->getRoles())){

            return $this->redirectToRoute('app_back');

        }


        $utilisateur = $this->getUser();

        $token = $token->generateToken();
        //$utilisateur->setActivetoken($token);
        $em=$this->getDoctrine()->getManager();
        $em->flush();

        return $this->redirectToRoute('app_front');
    }

    public function getData() :array
    {
        /**
         * @var $User Resta[]
         */
        $list = [];

        $Use = $this->getDoctrine()->getRepository(User::class)->findAll();

        foreach ($Use as $Resta) {
            $list[] = [
                $Resta->getId(),
                $Resta->getEmail(),
               // $Resta->getRoles(),
                $Resta->getPassword(),
                $Resta->getNom(),
                $Resta->getPrenom(),
                $Resta->getTelf(),
                $Resta->getDate(),

            ];
        }
        return $list;
    }
    /**
     * @Route("/excel/export",  name="export")
     */
    public function export(Request  $request)
    {

        $spreadsheet = new Spreadsheet();

        $sheet = $spreadsheet->getActiveSheet();

        $sheet->setTitle("DataUser List");

        $sheet->getCell("A1")->setValue("ID");
        $sheet->getCell("B1")->setValue("EMAIl");
        $sheet->getCell("C1")->setValue("PASSWORD");
        $sheet->getCell("D1")->setValue("NOM");
        $sheet->getCell("E1")->setValue("PRENOM");
        $sheet->getCell("F1")->setValue("TELF");
        $sheet->getCell("G1")->setValue("DATE");



        $sheet->getColumnDimension("A")->setWidth(25);
        $sheet->getColumnDimension("B")->setWidth(25);
        $sheet->getColumnDimension("C")->setWidth(10);
        $sheet->getColumnDimension("D")->setWidth(10);
        $sheet->getColumnDimension("E")->setWidth(35);
        $sheet->getColumnDimension("F")->setWidth(50);
        $sheet->getColumnDimension("G")->setWidth(50);
        $sheet->getColumnDimension("H")->setWidth(20);


        $spreadsheet->getActiveSheet()->setAutoFilter(
            $spreadsheet->getActiveSheet()->calculateWorksheetDimension()
        );
        $sheet->fromArray($this->getData(), null, "A2", true);
        $writer = new Xlsx($spreadsheet);
        $writer->save("uploads/data.xlsx");

        $response = new BinaryFileResponse("uploads/data.xlsx");

        $disposition = $response->headers->makeDisposition(
            ResponseHeaderBag::DISPOSITION_ATTACHMENT,
            "data.xlsx"
        );

        $response->headers->set("Content-Disposition", $disposition);

        return $response;



    }

    /**
     * @Route("/stat", name="stat", methods={"GET"})
     */
    public function statistique(UserRepository $usersRepository): Response
    {
       // $nbrs[]=Array();

        // On va chercher le nombre d'annonces publiées par date
        $evenement = $usersRepository->countByDate();

        $dates = [];
        $userCount = [];

        // On "démonte" les données pour les séparer tel qu'attendu par ChartJS
        foreach($evenement as $evenements){
            $dates[] = $evenements['date'];
            $userCount[] = $evenements['count'];
        }

        return $this->render('user/stat.html.twig', [

            'dates' => json_encode($dates),
            'userCount' => json_encode($userCount),
        ]);
    }

    /**
     * @Route("/listU", name="listU", methods={"GET"})
     */
    public function pdf(UserRepository $rep) :Response
    {


        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        $reader=$rep->findAll();


        // Retrieve the HTML generated in our twig file

        $html = $this->renderView('user/pdf.html.twig', array(
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
