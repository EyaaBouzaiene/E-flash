<?php

namespace App\Entity;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;



/**
 * Categories
 *
 * @ORM\Table(name="categories")
 * @ORM\Entity(repositoryClass=App\Repository\CategoriesRepository::class)
 */

class Categories
{
    /**
     * @var int
     *
     * @ORM\Column(name="CODE", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue
    
     */
    private $code;

    /**
     * @var string
    *@Assert\NotBlank(message="Le nom doit etre non vide")
     * @Assert\Type(type="alpha", message="Le nom ne doit pas contenir des chiffres .")
     * @Assert\Length(
     *      max = 8,
     *      maxMessage=" Très long !"
     *
     *     )
     
     * @ORM\Column(name="NOMCAT", type="string", length=50, nullable=false)
     */
    private $nomcat;

    public function getCode(): ?int
    {
        return $this->code;
    }

    public function getNomcat(): ?string
    {
        return $this->nomcat;
    }

    public function setNomcat(string $nomcat): self
    {
        $this->nomcat = $nomcat;

        return $this;
    }



    /**
     * Set the value of code
     *
     * @param  int  $code
     *
     * @return  self
     */ 
    public function setCode(int $code)
    {
        $this->code = $code;

        return $this;
    }
}
