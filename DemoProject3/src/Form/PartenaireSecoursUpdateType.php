<?php

namespace App\Form;

use App\Entity\Partenairesecours;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class PartenaireSecoursUpdateType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('matriculeps')
            ->add('nommarqueps')
            ->add('nomps')
            ->add('prenomps')
            ->add('mailps')
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Partenairesecours::class,
        ]);
    }
}
