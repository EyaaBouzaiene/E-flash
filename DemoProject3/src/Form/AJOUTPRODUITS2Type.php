<?php

namespace App\Form;

use App\Entity\Categories;
use App\Entity\Produits;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\NotBlank;

class AJOUTPRODUITS2Type extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nom',TextType::class, [
                'constraints'=> new NotBlank,
                
                ] )
            ->add('type',TextType::class, [
                'constraints'=> new NotBlank])
            ->add('quantite')
            
            ->add('DESCP',TextType::class, [
                'constraints'=> new NotBlank])
           
            ->add('quantiter')
            ->add('prix')
            
            ->add('image2',FileType::class , [
                'label' => false,
                'multiple' => false,
                'mapped' => false,
            ])
            ->add('categorie',EntityType::class ,array(
                'class' => Categories::class ,
                'choice_label' =>'NOMCAT' , 
                'placeholder' => 'select your Categorie',
                'constraints'=> new NotBlank
            ))
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Produits::class,
        ]);
    }
}
