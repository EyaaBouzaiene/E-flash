<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Stock
 *
 * @ORM\Table(name="stock", indexes={@ORM\Index(name="nomPartenaireS", columns={"nomPartenaireS"})})
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
     * @var string
     *
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
     * @var string
     *
     * @ORM\Column(name="qualiteS", type="string", length=50, nullable=false, options={"default"="1.0"})
     */
    private $qualites = '1.0';

    /**
     * @var \Partenaires
     *
     * @ORM\ManyToOne(targetEntity="Partenaires")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="nomPartenaireS", referencedColumnName="nomMarqueP")
     * })
     */
    private $nompartenaires;

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

    public function getDates(): ?\DateTimeInterface
    {
        return $this->dates;
    }

    public function setDates(\DateTimeInterface $dates): self
    {
        $this->dates = $dates;

        return $this;
    }

    public function getQualites(): ?string
    {
        return $this->qualites;
    }

    public function setQualites(string $qualites): self
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


}
