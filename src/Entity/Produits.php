<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Produits
 *
 * @ORM\Table(name="produits", indexes={@ORM\Index(name="CATEGORIE", columns={"CATEGORIE"})})
 * @ORM\Entity
 */
class Produits
{
    /**
     * @var int
     *
     * @ORM\Column(name="REFERENCE", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $reference;

    /**
     * @var string
     *
     * @ORM\Column(name="NOM", type="string", length=50, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="TYPE", type="string", length=50, nullable=false)
     */
    private $type;

    /**
     * @var int
     *
     * @ORM\Column(name="quantite", type="integer", nullable=false)
     */
    private $quantite;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="DATEP", type="date", nullable=false)
     */
    private $datep;

    /**
     * @var string
     *
     * @ORM\Column(name="DESC", type="string", length=50, nullable=false)
     */
    private $desc;

    /**
     * @var string
     *
     * @ORM\Column(name="IMAGE", type="blob", length=0, nullable=false)
     */
    private $image;

    /**
     * @var int
     *
     * @ORM\Column(name="rate", type="integer", nullable=false)
     */
    private $rate;

    /**
     * @var int
     *
     * @ORM\Column(name="quantiteR", type="integer", nullable=false)
     */
    private $quantiter;

    /**
     * @var string
     *
     * @ORM\Column(name="Prix", type="string", length=100, nullable=false)
     */
    private $prix;

    /**
     * @var string
     *
     * @ORM\Column(name="etat", type="string", length=100, nullable=false, options={"default"="achete"})
     */
    private $etat = 'achete';

    /**
     * @var \Categories
     *
     * @ORM\ManyToOne(targetEntity="Categories")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="CATEGORIE", referencedColumnName="CODE")
     * })
     */
    private $categorie;

    public function getReference(): ?int
    {
        return $this->reference;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(string $type): self
    {
        $this->type = $type;

        return $this;
    }

    public function getQuantite(): ?int
    {
        return $this->quantite;
    }

    public function setQuantite(int $quantite): self
    {
        $this->quantite = $quantite;

        return $this;
    }

    public function getDatep(): ?\DateTimeInterface
    {
        return $this->datep;
    }

    public function setDatep(\DateTimeInterface $datep): self
    {
        $this->datep = $datep;

        return $this;
    }

    public function getDesc(): ?string
    {
        return $this->desc;
    }

    public function setDesc(string $desc): self
    {
        $this->desc = $desc;

        return $this;
    }

    public function getImage()
    {
        return $this->image;
    }

    public function setImage($image): self
    {
        $this->image = $image;

        return $this;
    }

    public function getRate(): ?int
    {
        return $this->rate;
    }

    public function setRate(int $rate): self
    {
        $this->rate = $rate;

        return $this;
    }

    public function getQuantiter(): ?int
    {
        return $this->quantiter;
    }

    public function setQuantiter(int $quantiter): self
    {
        $this->quantiter = $quantiter;

        return $this;
    }

    public function getPrix(): ?string
    {
        return $this->prix;
    }

    public function setPrix(string $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    public function getEtat(): ?string
    {
        return $this->etat;
    }

    public function setEtat(string $etat): self
    {
        $this->etat = $etat;

        return $this;
    }

    public function getCategorie(): ?Categories
    {
        return $this->categorie;
    }

    public function setCategorie(?Categories $categorie): self
    {
        $this->categorie = $categorie;

        return $this;
    }


}
