<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Clients
 *
 * @ORM\Table(name="clients", indexes={@ORM\Index(name="IDP", columns={"IDP"})})
 * @ORM\Entity
 */
class Clients
{
    /**
     * @var int
     *
     * @ORM\Column(name="REfC", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $refc;

    /**
     * @var string
     *
     * @ORM\Column(name="NOM", type="string", length=50, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="Prenom", type="string", length=50, nullable=false)
     */
    private $prenom;

    /**
     * @var int
     *
     * @ORM\Column(name="QTA", type="integer", nullable=false)
     */
    private $qta;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="DATEA", type="date", nullable=false)
     */
    private $datea;

    /**
     * @var \Produits
     *
     * @ORM\ManyToOne(targetEntity="Produits")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="IDP", referencedColumnName="REFERENCE")
     * })
     */
    private $idp;

    public function getRefc(): ?int
    {
        return $this->refc;
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

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(string $prenom): self
    {
        $this->prenom = $prenom;

        return $this;
    }

    public function getQta(): ?int
    {
        return $this->qta;
    }

    public function setQta(int $qta): self
    {
        $this->qta = $qta;

        return $this;
    }

    public function getDatea(): ?\DateTimeInterface
    {
        return $this->datea;
    }

    public function setDatea(\DateTimeInterface $datea): self
    {
        $this->datea = $datea;

        return $this;
    }

    public function getIdp(): ?Produits
    {
        return $this->idp;
    }

    public function setIdp(?Produits $idp): self
    {
        $this->idp = $idp;

        return $this;
    }


}
