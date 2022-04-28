<?php

namespace App\Form;

use App\Entity\Evenement;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\DateTimeType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\DateType;

class Form1Type extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nom')
            ->add('description')
            ->add('duree')
            ->add('nombrePlace')
            ->add('dateDebut',DateTimeType::class,[
                'date_widget' => 'single_text'])
            ->add('end',DateTimeType::class,[
                'date_widget' => 'single_text'])
            ->add('lat')
            ->add('lon')
            ->add('lieu',TextType::class,[
                'attr' => [
                    'placeholder' => " lieu",
                    'class' => 'form-control'

                ]
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Evenement::class,
        ]);
    }
}
