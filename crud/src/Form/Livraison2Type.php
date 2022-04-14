<?php

namespace App\Form;

use App\Entity\Livraison2;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use App\Entity\Commande;
use App\Entity\Livreur ;



use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\ChoiceList\Choice;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;

class Livraison2Type extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('prix')
            ->add('ville')
            ->add('etat')
            ->add('idcommande',EntityType::class,
            [
            'class'=>Commande::class,
              'choice_label'=>'idCommande'])
              
            ->add('idl',EntityType::class,
            [
            'class'=>Livreur::class,
              'choice_label'=>'nomlivreur'])
            ->add('Ajouter',submitType::class)
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Livraison2::class,
        ]);
    }
}
