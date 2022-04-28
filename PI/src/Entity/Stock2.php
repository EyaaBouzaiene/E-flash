<?php

namespace App\Entity;

use App\Repository\Stock2Repository;
use Doctrine\ORM\Mapping as ORM;
use TimestampableEntity;

/**
 * @ORM\Entity(repositoryClass=Stock2Repository::class)
 */
class Stock2
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
    
    private $nomS;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $refS;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $categorieS;

    /**
     * @ORM\Column(type="integer")
     */
    private $qteS;

    /**
     * @ORM\Column(type="date")
     */
    private $dateS;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $qualiteS;

    /**
     * @ORM\ManyToOne(targetEntity=Partenaires::class)
     * @ORM\JoinColumn(nullable=false)
     */
    private $partenaire;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNomPartenaireS(): ?string
    {
        return $this->nomPartenaireS;
    }

    public function setNomPartenaireS(string $nomPartenaireS): self
    {
        $this->nomPartenaireS = $nomPartenaireS;

        return $this;
    }

    public function getNomS(): ?string
    {
        return $this->nomS;
    }

    public function setNomS(string $nomS): self
    {
        $this->nomS = $nomS;

        return $this;
    }

    public function getRefS(): ?string
    {
        return $this->refS;
    }

    public function setRefS(string $refS): self
    {
        $this->refS = $refS;

        return $this;
    }

    public function getCategorieS(): ?string
    {
        return $this->categorieS;
    }

    public function setCategorieS(string $categorieS): self
    {
        $this->categorieS = $categorieS;

        return $this;
    }

    public function getQteS(): ?int
    {
        return $this->qteS;
    }

    public function setQteS(int $qteS): self
    {
        $this->qteS = $qteS;
        return $this;
    }

    public function getDateS(): ?\DateTimeInterface
    {
        return $this->dateS; 
      
      
    }

    public function setDateS(\DateTimeInterface $dateS): self
    {
         $this->dateS = $dateS;
      
        return $this;
    }

    public function getQualiteS(): ?string
    {
        return $this->qualiteS;
    }

    public function setQualiteS(string $qualiteS): self
    {
        $this->qualiteS = $qualiteS;
    
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
