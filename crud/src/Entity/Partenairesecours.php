<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Partenairesecours
 *
 * @ORM\Table(name="partenairesecours")
 * @ORM\Entity
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
     * @var string
     *
     * @ORM\Column(name="nomPS", type="string", length=50, nullable=false)
     */
    private $nomps;

    /**
     * @var string
     *
     * @ORM\Column(name="prenomPS", type="string", length=50, nullable=false)
     */
    private $prenomps;

    /**
     * @var string
     *
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

    public function getIdps(): ?int
    {
        return $this->idps;
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


}
