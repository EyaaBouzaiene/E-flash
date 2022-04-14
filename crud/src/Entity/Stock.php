<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Stock
 *
 * @ORM\Table(name="stock", indexes={@ORM\Index(name="idPartenaire", columns={"Partenaire"})})
 * @ORM\Entity
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
     *
     * @ORM\Column(name="nomS", type="string", length=50, nullable=false)
     */
    private $noms;

    /**
     * @var int
     *
     * @ORM\Column(name="refS", type="integer", nullable=false)
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
     *
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
     * @var int
     *
     * @ORM\Column(name="qualiteS", type="integer", nullable=false)
     */
    private $qualites;

    /**
     * @var \Partenaires
     *
     * @ORM\ManyToOne(targetEntity="Partenaires")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="Partenaire", referencedColumnName="idP")
     * })
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

    public function getRefs(): ?int
    {
        return $this->refs;
    }

    public function setRefs(int $refs): self
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

    public function getDates(): ?\DateTimeInterface
    {
        return $this->dates;
    }

    public function setDates(\DateTimeInterface $dates): self
    {
        $this->dates = $dates;

        return $this;
    }

    public function getQualites(): ?int
    {
        return $this->qualites;
    }

    public function setQualites(int $qualites): self
    {
        $this->qualites = $qualites;

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


}
