<?php

namespace App\Form;

use App\Entity\Categorie2;
use App\Entity\Produit2;

use Symfony\Component\Form\AbstractType;
use blackknight467\StarRatingBundle\Form\RatingType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Doctrine\ORM\EntityRepository ;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Validator\Constraints\NotBlank;
use Symfony\Component\Form\Extension\Core\Type\TextType;
class AJOUTPRODUITSType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('NOM' ,TextType::class, [
                'constraints'=> new NotBlank,
                
                ] 
                )
            ->add('TYPE',TextType::class, [
                'constraints'=> new NotBlank])
            ->add('DESCP',TextType::class, [
                'constraints'=> new NotBlank])
            ->add('IMAGE',FileType::class , [
                'label' => false,
                'multiple' => false,
                'mapped' => false,
            ])
           
            ->add('quantiteR')
            ->add('Prix')
           
            ->add('quantite')
            ->add('Categorie',EntityType::class ,array(
                'class' => Categorie2::class ,
                'choice_label' =>'NOM' , 
                'placeholder' => 'select your Categorie',
                'constraints'=> new NotBlank
            ))
            
        ;
    }


    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Produit2::class,
        ]);
    }
}
