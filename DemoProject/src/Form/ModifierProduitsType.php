<?php

namespace App\Form;

use App\Entity\Produit2;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ModifierProduitsType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('NOM')
            ->add('TYPE')
            ->add('DESCP')
            ->add('quantiteR')
            ->add('Prix')
            ->add('quantite')
            
        ;
        
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Produit2::class,
        ]);
    }
}
