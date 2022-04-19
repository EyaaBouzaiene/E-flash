<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\UserType;
use App\Repository\UserRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\Session;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Gedmo\Sluggable\Util\Urlizer;
use Symfony\Component\Security\Csrf\TokenGenerator\TokenGeneratorInterface;

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
}
