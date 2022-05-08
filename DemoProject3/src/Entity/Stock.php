<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * Stock
 *
 * @ORM\Table(name="stock", indexes={@ORM\Index(name="nomPartenaireS", columns={"nomPartenaireS"})})
 * @ORM\Entity(repositoryClass=App\Repository\StockRepository::class)
 */
class Stock
{
    /**
     * @var int
     *
     * @ORM\Column(name="idS", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $ids;

    /**
     * @var string
     * @Assert\NotBlank(message="Le NOM du Stock doit etre non vide")
     * @Assert\Type(type="alpha", message="Le nom ne doit pas contenir des chiffres .")
     * @Assert\Length(
     *      max = 8,
     *      maxMessage=" Très long !"
     *
     *     )
     * @ORM\Column(name="nomS", type="string", length=50, nullable=false)
     */
    private $noms;

    /**
     * @var string
     * @Assert\NotBlank(message="Entrez quelques choses !")
     * @Assert\Positive(message="La Matricule doit etre positif.")
     * @Assert\Range(
     *      min = 1,
     *      max = 8000,
     *      notInRangeMessage = "la Matricule doit etre valide",
     *     )
     * @ORM\Column(name="refS", type="string", length=255, nullable=false)
     */
    private $refs;

    /**
     * @var string
     *
     * @ORM\Column(name="categorieS", type="string", length=50, nullable=false)
     */
    private $categories;

    /**
     * @var int
     *  @Assert\NotBlank(message="Entrez quelques choses !")
     * @Assert\Positive(message="La Matricule doit etre positif.")
     * @Assert\Range(
     *      min = 1,
     *      max = 8000,
     *      notInRangeMessage = "la Matricule doit etre valide",
     *     )
     * @ORM\Column(name="qteS", type="integer", nullable=false)
     */
    private $qtes;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateS", type="date", nullable=false)
     */
    private $dates;

    /**
     * @var string
     *
     * @ORM\Column(name="qualiteS", type="string", length=50, nullable=false, options={"default"="1.0"})
     */
    private $qualites = '1.0';

    /**
     * @ORM\ManyToOne(targetEntity="Partenaires")
     * @ORM\JoinColumn(name="nomPartenaireS", referencedColumnName="nomMarqueP")
     
     */
    private $nompartenaires;

    /**
     * @ORM\ManyToOne(targetEntity=Partenaires::class)
     * @ORM\JoinColumn(nullable=false,referencedColumnName="idP")
     */
    private $partenaire;

    public function getIds(): ?int
    {
        return $this->ids;
    }

    public function getNoms(): ?string
    {
        return $this->noms;
    }

    public function setNoms(string $noms): self
    {
        $this->noms = $noms;

        return $this;
    }

    public function getRefs(): ?string
    {
        return $this->refs;
    }

    public function setRefs(string $refs): self
    {
        $this->refs = $refs;

        return $this;
    }

    public function getCategories(): ?string
    {
        return $this->categories;
    }

    public function setCategories(string $categories): self
    {
        $this->categories = $categories;

        return $this;
    }

    public function getQtes(): ?int
    {
        return $this->qtes;
    }

    public function setQtes(int $qtes): self
    {
        $this->qtes = $qtes;

        return $this;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->dates;
    }

    public function setDateS(\DateTimeInterface $dates): self
    {
        $this->dates = $dates;

        return $this;
    }

    public function getQualiteS(): ?string
    {
        return $this->qualites;
    }

    public function setQualiteS(string $qualites): self
    {
        $this->qualites = $qualites;

        return $this;
    }

    public function getNompartenaires(): ?Partenaires
    {
        return $this->nompartenaires;
    }

    public function setNompartenaires(?Partenaires $nompartenaires): self
    {
        $this->nompartenaires = $nompartenaires;

        return $this;
    }

    public function getPartenaire(): ?Partenaires
    {
        return $this->partenaire;
    }

    public function setPartenaire(?Partenaires $partenaire): self
    {
        $this->partenaire = $partenaire;

        return $this;
    }

    public function getDates(): ?\DateTimeInterface
    {
        return $this->dates;
    }


}
