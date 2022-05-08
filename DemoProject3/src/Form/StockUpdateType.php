<?php

namespace App\Form;

use App\Entity\Stock;
use App\Entity\Stock2;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;

class StockUpdateType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('noms')
            ->add('refs')
            ->add('categories', ChoiceType::class, [
                'choices'  => [
                    'Alimentaire' => 'Alimentaire',
            'Equipement' => 'Equipement',
            'Sécurite' => 'Sécurite'
                ]
                ])
            ->add('qtes')
            ->add('qualites')
           
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Stock::class,
        ]);
    }
}
