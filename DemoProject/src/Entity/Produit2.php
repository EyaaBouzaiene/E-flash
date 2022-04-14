<?php

namespace App\Entity;

use App\Repository\Produit2Repository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=Produit2Repository::class)
 */
class Produit2
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $NOM;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $TYPE;

    

    /**
     * @ORM\Column(type="date")
     */
    private $DATEP;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $DESCP;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $IMAGE;

    /**
     * @ORM\Column(type="integer")
     */
    private $rate;

    /**
     * @ORM\Column(type="integer")
     */
    private $quantiteR;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $Prix;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $etat;

    /**
     * @ORM\Column(type="integer")
     */
    private $quantite;

    /**
     * @ORM\ManyToOne(targetEntity=Categorie2::class)
     * @ORM\JoinColumn(nullable=false)
     */
    private $Categorie;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNOM(): ?string
    {
        return $this->NOM;
    }

    public function setNOM(string $NOM): self
    {
        $this->NOM = $NOM;

        return $this;
    }

    public function getTYPE(): ?string
    {
        return $this->TYPE;
    }

    public function setTYPE(string $TYPE): self
    {
        $this->TYPE = $TYPE;

        return $this;
    }

    

    public function getDATEP(): ?\DateTimeInterface
    {
        return $this->DATEP;
    }

    public function setDATEP(\DateTimeInterface $DATEP): self
    {
        $this->DATEP = $DATEP;

        return $this;
    }

    public function getDESCP(): ?string
    {
        return $this->DESCP;
    }

    public function setDESCP(string $DESCP): self
    {
        $this->DESCP = $DESCP;

        return $this;
    }

    public function getIMAGE(): ?string
    {
        return $this->IMAGE;
    }

    public function setIMAGE(string $IMAGE): self
    {
        $this->IMAGE = $IMAGE;

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

    public function getQuantiteR(): ?int
    {
        return $this->quantiteR;
    }

    public function setQuantiteR(int $quantiteR): self
    {
        $this->quantiteR = $quantiteR;

        return $this;
    }

    public function getPrix(): ?string
    {
        return $this->Prix;
    }

    public function setPrix(string $Prix): self
    {
        $this->Prix = $Prix;

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

    public function getQuantite(): ?int
    {
        return $this->quantite;
    }

    public function setQuantite(int $quantite): self
    {
        $this->quantite = $quantite;

        return $this;
    }

    public function getCategorie(): ?Categorie2
    {
        return $this->Categorie;
    }

    public function setCategorie(?Categorie2 $Categorie): self
    {
        $this->Categorie = $Categorie;

        return $this;
    }
}
