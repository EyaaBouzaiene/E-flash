<?php

namespace App\Entity;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;

/**
 * Partenaires
 *
 * @ORM\Table(name="partenaires", indexes={@ORM\Index(name="nomMarqueP", columns={"nomMarqueP"})})
 * @ORM\Entity(repositoryClass=App\Repository\PartenairesRepository::class)
 */
class Partenaires
{
    /**
     * @var int
     *
     * @ORM\Column(name="idP", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idp;

    /**
     * @var int
     * @Assert\NotBlank(message="Entrez quelques choses !")
     * @Assert\Positive(message="La Matricule doit etre positif.")
     * @Assert\Range(
     *      min = 1,
     *      max = 8000,
     *      notInRangeMessage = "la Matricule doit etre valide",
     *     )
     * @ORM\Column(name="MatriculeP", type="integer", nullable=false)
     */
    private $matriculep;

    /**
     * @var string
      * @Assert\NotBlank(message="Le Matricule doit etre non vide")
     * @Assert\Type(type="alpha", message="Le nom ne doit pas contenir des chiffres .")
     * @Assert\Length(
     *      max = 8,
     *      maxMessage=" Très long !"
     *
     *     )
     * @ORM\Column(name="nomMarqueP", type="string", length=255, nullable=false)
     */
    private $nommarquep;

    /**
     * @var string
     * @Assert\NotBlank(message="Le Nom de la marque doit etre non vide")
     * @Assert\Type(type="alpha", message="Le nom ne doit pas contenir des chiffres .")
     * @Assert\Length(
     *      max = 15,
     *      maxMessage=" Très long !"
     *
     *     )
     * @ORM\Column(name="nomP", type="string", length=255, nullable=false)
     */
    private $nomp;

    /**
     * @var string
    * @Assert\NotBlank(message="Le Prénom doit etre non vide")
     * @Assert\Type(type="alpha", message="Le Prénom ne doit pas contenir des chiffres .")
     * @Assert\Length(
     *      max = 10,
     *      maxMessage=" Très long !"
     *
     *     )
     * @ORM\Column(name="prenomP", type="string", length=100, nullable=false)
     */
    private $prenomp;

    /**
     * @var string
         * @Assert\NotBlank(message="Le mail ne doit pas etre non vide")
     * @Assert\Length(
     *      max = 30,
     *      maxMessage=" Très long !"
     *
     *     )
     * @ORM\Column(name="mailP", type="string", length=100, nullable=false)
     */
    private $mailp;

    /**
     * @var string
     *
     * @ORM\Column(name="categorieP", type="string", length=50, nullable=false)
     */
    private $categoriep;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateAjout", type="date", nullable=false)
     */
    private $dateajout;

    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=255, nullable=false)
     */
    private $image;

    public function getIdp(): ?int
    {
        return $this->idp;
    }

    public function getMatriculep(): ?int
    {
        return $this->matriculep;
    }

    public function setMatriculep(int $matriculep): self
    {
        $this->matriculep = $matriculep;

        return $this;
    }

    public function getNommarquep(): ?string
    {
        return $this->nommarquep;
    }

    public function setNommarquep(string $nommarquep): self
    {
        $this->nommarquep = $nommarquep;

        return $this;
    }

    public function getNomp(): ?string
    {
        return $this->nomp;
    }

    public function setNomp(string $nomp): self
    {
        $this->nomp = $nomp;

        return $this;
    }

    public function getPrenomp(): ?string
    {
        return $this->prenomp;
    }

    public function setPrenomp(string $prenomp): self
    {
        $this->prenomp = $prenomp;

        return $this;
    }

    public function getMailp(): ?string
    {
        return $this->mailp;
    }

    public function setMailp(string $mailp): self
    {
        $this->mailp = $mailp;

        return $this;
    }

    public function getCategoriep(): ?string
    {
        return $this->categoriep;
    }

    public function setCategoriep(string $categoriep): self
    {
        $this->categoriep = $categoriep;

        return $this;
    }

    public function getDateajout(): ?\DateTimeInterface
    {
        return $this->dateajout;
    }

    public function setDateajout(\DateTimeInterface $dateajout): self
    {
        $this->dateajout = $dateajout;

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
