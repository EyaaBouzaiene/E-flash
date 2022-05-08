<?php

namespace App\Controller;

use App\Entity\Produit2;
use App\Repository\Categorie2Repository;
use App\Repository\Produit2Repository;
use Stripe\Checkout\Session;
use Stripe\Stripe;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Routing\Generator\UrlGeneratorInterface;
/**
 * @Route("/payment")
 */
class PaymentController extends AbstractController
{
/**
     * @Route("/checkout2", name="stripe_checkout2")
     * @throws ApiErrorException
     */
   
    public function checkout($stripeSK,SessionInterface $session,Produit2Repository $productsRepository,Categorie2Repository $classroomRepository)
    {
        Stripe::setApiKey($stripeSK);

        $session = Session::create([
            'payment_method_types' => ['card'],
            
            'line_items'           => [
                [
                    'price_data' => [
                        'currency'     => 'usd',
                        'product_data' => [
                            'name' => 'Hroduit',
                            
                        ],
                        'unit_amount'  => 3000,
                    ],
                    'quantity'   => 1,
                ]
            ],
            'mode'                 => 'payment',
            'success_url'          => $this->generateUrl('success_url', [], UrlGeneratorInterface::ABSOLUTE_URL),
            'cancel_url'           => $this->generateUrl('cancel_url', [], UrlGeneratorInterface::ABSOLUTE_URL),
        ]);

        return $this->redirect($session->url, 303);
    }

/**
     * @Route("/success-url", name="success_url")
     */
    
    
    public function successUrl(): Response
    {
       /* $reservation = $this->getDoctrine()->getRepository(Produit2::class)->find($idReservation);

        $reservation->setPaid(true);
        
        $this->getDoctrine()->getManager()->persist($reservation);
        $this->getDoctrine()->getManager()->flush();*/

        
        return $this->render('payment/success.html.twig', []);
    }

/**
     * @Route("/cancel-url", name="cancel_url")
     */
    
   
    public function cancelUrl(): Response
    {
        return $this->render('payment/cancel.html.twig', []);
    }
}
