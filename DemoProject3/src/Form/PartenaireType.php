<?php

namespace App\Form;

use App\Entity\Partenaires;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\read_exif_data;
use Symfony\Component\Validator\Constraints\NotBlank;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Validator\Constraints\Length;


class PartenaireType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('matriculep')
            ->add('nommarquep', TextType::class, [
                'constraints'=> new NotBlank]
            )
            ->add('nomp',TextType::class, [
                'constraints'=> new NotBlank
            ])
            ->add('prenomp',TextType::class, [
                'constraints'=> new NotBlank
            ])
            ->add('mailp', EmailType::class, [
                'constraints'=> new NotBlank
            ])
            ->add('categoriep', ChoiceType::class, [
                'choices'  => [
                    'Alimentaire' => 'Alimentaire',
            'Equipement' => 'Equipement',
            'Sécurite' => 'Sécurite'
                ],
                'placeholder' => 'select your categorie',
                'constraints'=> new NotBlank])
           
            ->add('image',FileType::class , [
                'label' => false,
                'multiple' => false,
                'mapped' => false,
                
            ])
           
            
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Partenaires::class,
        ]);
    }
}
