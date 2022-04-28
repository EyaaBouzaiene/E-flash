<?php

namespace App\Entity;

use App\Repository\PartenairesRepository;
use Doctrine\ORM\Mapping as ORM;
use TimestampableEntity;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * @ORM\Entity(repositoryClass=PartenairesRepository::class)
 */
class Partenaires
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @Assert\NotBlank(message="Entrez quelques choses !")
     * @Assert\Positive(message="La Matricule doit etre positif.")
     * @Assert\Range(
     *      min = 1,
     *      max = 8000,
     *      notInRangeMessage = "la Matricule doit etre valid",
     *     )
     * @ORM\Column(type="integer")
     */
    private $MatriculeP;

    /**
     * @Assert\NotBlank(message="Le Matricule doit etre non vide")
     * @Assert\Type(type="alpha", message="Le nom ne doit pas contenir des chiffres .")
     * @Assert\Length(
     *      max = 8,
     *      maxMessage=" Très long !"
     *
     *     )
     * @ORM\Column(type="string", length=255)
     */
    private $nomMarqueP;

    /**
     * @Assert\NotBlank(message="Le Nom de la marque doit etre non vide")
     * @Assert\Type(type="alpha", message="Le nom ne doit pas contenir des chiffres .")
     * @Assert\Length(
     *      max = 15,
     *      maxMessage=" Très long !"
     *
     *     )
     * @ORM\Column(type="string", length=255)
     */
    private $nomP;

    /**
     * @Assert\NotBlank(message="Le Nom doit etre non vide")
     * @Assert\Type(type="alpha", message="Le nom ne doit pas contenir des chiffres .")
     * @Assert\Length(
     *      max = 10,
     *      maxMessage=" Très long !"
     *
     *     )
     * @ORM\Column(type="string", length=255)
     */
    private $prenomP;

    /**
     * * @Assert\NotBlank(message="Le Prénom doit etre non vide")
     * @Assert\Length(
     *      max = 30,
     *      maxMessage=" Très long !"
     *
     *     )
     * @ORM\Column(type="string", length=255)
     */
    private $mailP;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $categorieP;

    /**
     * @ORM\Column(type="date")
     */
    private $dateAjout;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $image;

    

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getMatriculeP(): ?int
    {
        return $this->MatriculeP;
    }

    public function setMatriculeP(int $MatriculeP): self
    {
        $this->MatriculeP = $MatriculeP;

        return $this;
    }

    public function getNomMarqueP(): ?string
    {
        return $this->nomMarqueP;
    }

    public function setNomMarqueP(string $nomMarqueP): self
    {
        $this->nomMarqueP = $nomMarqueP;

        return $this;
    }

    public function getNomP(): ?string
    {
        return $this->nomP;
    }

    public function setNomP(string $nomP): self
    {
        $this->nomP = $nomP;

        return $this;
    }

    public function getPrenomP(): ?string
    {
        return $this->prenomP;
    }

    public function setPrenomP(string $prenomP): self
    {
        $this->prenomP = $prenomP;

        return $this;
    }

    public function getMailP(): ?string
    {
        return $this->mailP;
    }

    public function setMailP(string $mailP): self
    {
        $this->mailP = $mailP;

        return $this;
    }

    public function getCategorieP(): ?string
    {
        return $this->categorieP;
    }

    public function setCategorieP(string $categorieP): self
    {
        $this->categorieP = $categorieP;

        return $this;
    }

    public function getDateAjout(): ?\DateTimeInterface
    {
        return $this->dateAjout;
    }

    public function setDateAjout(\DateTimeInterface $dateAjout): self
    {
        
        $this->dateAjout = new \DateTime('now');

        return $this;
    }

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(string $image): self
    {
        $this->image = $image;

        return $this;
    }

   
}
