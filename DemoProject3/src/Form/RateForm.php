<?php

namespace App\Form;

use App\data\Rate;
use blackknight467\StarRatingBundle\Form\RatingType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;

use Symfony\Component\OptionsResolver\OptionsResolver;

class RateForm extends AbstractType

{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
        ->add('rating', RatingType::class, [
            //...
            'stars' => 4,
            //...
        ]);
            // ->add('promo', CheckboxType::class, [
            //     'label' => 'En promotion',
            //     'required' => false,
            // ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Rate::class,
            'method' => 'GET',
            'csrf_protection' => false
        ]);
    }

    public function getBlockPrefix()
    {
        return '';
    }
}
