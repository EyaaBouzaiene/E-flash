<?php

namespace App\Entity;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;

/**
 * Partenairesecours
 *
 * @ORM\Table(name="partenairesecours")
 * @ORM\Entity(repositoryClass=App\Repository\PartenairesecoursRepository::class)
 */
class Partenairesecours
{
    /**
     * @var int
     *
     * @ORM\Column(name="idPS", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idps;

    /**
     * @var int
     * @Assert\NotBlank(message="Entrez quelques choses !")
     * @Assert\Positive(message="La Matricule doit etre positif.")
     * @Assert\Range(
     *      min = 1,
     *      max = 8000,
     *      notInRangeMessage = "la Matricule doit etre valid",
     *     )
     * @ORM\Column(name="MatriculePS", type="integer", nullable=false)
     */
    private $matriculeps;

    /**
     * @var string
      * @Assert\NotBlank(message="Le Marque doit etre non vide")
     * @Assert\Type(type="alpha", message="Le nom ne doit pas contenir des chiffres .")
     * @Assert\Length(
     *      max = 8,
     *      maxMessage=" Très long !"
     *
     *     )
     * @ORM\Column(name="nomMarquePS", type="string", length=255, nullable=false)
     */
    private $nommarqueps;

    /**
     * @var string
     * @Assert\NotBlank(message="Le Nom de du Partenaires doit etre non vide")
     * @Assert\Type(type="alpha", message="Le nom ne doit pas contenir des chiffres .")
     * @Assert\Length(
     *      max = 15,
     *      maxMessage=" Très long !"
     *
     *     )
     * @ORM\Column(name="nomPS", type="string", length=50, nullable=false)
     */
    private $nomps;

    /**
     * @var string
       * @Assert\NotBlank(message="Le Prenom de du Partenaires doit etre non vide")
     * @Assert\Type(type="alpha", message="Le nom ne doit pas contenir des chiffres .")
     * @Assert\Length(
     *      max = 15,
     *      maxMessage=" Très long !"
     *
     *     )
     * @ORM\Column(name="prenomPS", type="string", length=50, nullable=false)
     */
    private $prenomps;

    /**
     * @var string
     * @ORM\Column(name="mailPS", type="string", length=100, nullable=false)
     */
    private $mailps;

    /**
     * @var string
     *
     * @ORM\Column(name="categoriePS", type="string", length=50, nullable=false)
     */
    private $categorieps;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="datePS", type="date", nullable=false)
     */
    private $dateps;

    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=255, nullable=false)
     */
    private $image;

    public function getIdps(): ?int
    {
        return $this->idps;
    }

    public function getMatriculeps(): ?int
    {
        return $this->matriculeps;
    }

    public function setMatriculeps(int $matriculeps): self
    {
        $this->matriculeps = $matriculeps;

        return $this;
    }

    public function getNommarqueps(): ?string
    {
        return $this->nommarqueps;
    }

    public function setNommarqueps(string $nommarqueps): self
    {
        $this->nommarqueps = $nommarqueps;

        return $this;
    }

    public function getNomps(): ?string
    {
        return $this->nomps;
    }

    public function setNomps(string $nomps): self
    {
        $this->nomps = $nomps;

        return $this;
    }

    public function getPrenomps(): ?string
    {
        return $this->prenomps;
    }

    public function setPrenomps(string $prenomps): self
    {
        $this->prenomps = $prenomps;

        return $this;
    }

    public function getMailps(): ?string
    {
        return $this->mailps;
    }

    public function setMailps(string $mailps): self
    {
        $this->mailps = $mailps;

        return $this;
    }

    public function getCategorieps(): ?string
    {
        return $this->categorieps;
    }

    public function setCategorieps(string $categorieps): self
    {
        $this->categorieps = $categorieps;

        return $this;
    }

    public function getDateps(): ?\DateTimeInterface
    {
        return $this->dateps;
    }

    public function setDateps(\DateTimeInterface $dateps): self
    {
        $this->dateps = $dateps;

        return $this;
    }

    public function getimage(): ?string
    {
        return $this->image;
    }

    public function setimage(string $image): self
    {
        $this->image = $image;

        return $this;
    }


}
