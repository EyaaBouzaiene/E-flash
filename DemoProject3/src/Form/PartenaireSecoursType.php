<?php

namespace App\Form;

use App\Entity\Partenairesecours;
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


class PartenaireSecoursType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('matriculeps')
            ->add('nommarqueps', TextType::class, [
                'constraints'=> new NotBlank]
            )
            ->add('nomps',TextType::class, [
                'constraints'=> new NotBlank
            ])
            ->add('prenomps',TextType::class, [
                'constraints'=> new NotBlank
            ])
            ->add('mailps', EmailType::class, [
                'constraints'=> new NotBlank
            ])
            ->add('categorieps', ChoiceType::class, [
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
            'data_class' => Partenairesecours::class,
        ]);
    }
}
