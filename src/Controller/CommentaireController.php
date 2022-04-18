<?php

namespace App\Controller;

use App\Entity\Commentaire;
use App\Entity\Publication;
use App\Form\FormCommentaireType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;

class CommentaireController extends AbstractController
{
    /**
     * @Route("/commentaire", name="app_commentaire")
     */
    public function index(): Response
    {
        return $this->render('commentaire/index.html.twig', [
            'controller_name' => 'CommentaireController',
        ]);
    }



   /**
     * @Route("/AddCom/{id}", name="AddCom")
     */
    public function Ajouter_Commentaire(Request $req,int $id)
    {

    }





    /**
     * @Route("/DeleteCom/{id}", name="DeleteComid")
     */

    public function Supprimer_Commentaire(Request $req,$id)
{
        $Com = $this->getDoctrine()->getRepository(Commentaire::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($Com);
        $em->flush();
        $ref = $req->headers->get('referer');
        return $this->redirect($ref);
}


    /*----------------------------------------------------------------------------------------------------------------------- */


    /**
     * @Route("/ListComBack", name="ListComBack")
     */
    public function Afficher_BackEnd_Commentaire()
    {
        $Com= $this->getDoctrine()->getRepository(Commentaire::class)->findAll();

        return $this->render('commentaire/Page_AffichageB_Commentaire.html.twig',[

            'Coms'=>$Com

        ]);
    }


     /**
     * @Route("/DeleteComBack/{id}", name="DeleteComBackid")
     */

    public function Supprimer_BackEnd_Commentaire(Request $req,$id)
{
        $Com = $this->getDoctrine()->getRepository(Commentaire::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($Com);
        $em->flush();
        return $this->redirectToRoute("ListComBack");
}


   

}
