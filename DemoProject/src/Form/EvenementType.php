<?php

namespace App\Form;


use App\Entity\Evenement;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Doctrine\ORM\EntityRepository ;
use Symfony\Component\Form\Extension\Core\Type\{
    FileType,
    TextType,
    ButtonType,
    EmailType,
    HiddenType,
    PasswordType,
    TextareaType,
    SubmitType,
    NumberType,
    MoneyType,
    BirthdayType};
    use Symfony\Component\Form\Extension\Core\Type\DateTimeType;

class EvenementType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nom')
            ->add('description',TextareaType::class,
            array('attr' => array('cols' => '170', 'rows' => '5')))
            ->add('duree')
            ->add('nombrePlace')

            ->add('dateDebut',DateTimeType::class,[
                'date_widget' => 'single_text'])
                ->add('end',DateTimeType::class,[
                    'date_widget' => 'single_text'])
           
            ->add('image2',FileType::class , [
                'label' => false,
                'multiple' => false,
                'mapped' => false,
            ])
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
