<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Commande
 *
 * @ORM\Table(name="commande", indexes={@ORM\Index(name="REFERENCEP", columns={"REFERENCEP"}), @ORM\Index(name="IDClient", columns={"IDClient"})})
 * @ORM\Entity
 */
class Commande
{
    /**
     * @var int
     *
     * @ORM\Column(name="idCommande", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idcommande;

    /**
     * @var string
     *
     * @ORM\Column(name="prix", type="string", length=8, nullable=false)
     */
    private $prix;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=false)
     */
    private $date;

    /**
     * @var int
     *
     * @ORM\Column(name="refcmd", type="integer", nullable=false)
     */
    private $refcmd;

    /**
     * @var int
     *
     * @ORM\Column(name="quantiteA", type="integer", nullable=false)
     */
    private $quantitea;

    /**
     * @var float
     *
     * @ORM\Column(name="total", type="float", precision=10, scale=0, nullable=false)
     */
    private $total;

    /**
     * @var \Clients
     *
     * @ORM\ManyToOne(targetEntity="Clients")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="IDClient", referencedColumnName="REfC")
     * })
     */
    private $idclient;

    /**
     * @var \Produits
     *
     * @ORM\ManyToOne(targetEntity="Produits")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="REFERENCEP", referencedColumnName="REFERENCE")
     * })
     */
    private $referencep;

    public function getIdcommande(): ?int
    {
        return $this->idcommande;
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

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }

    public function getRefcmd(): ?int
    {
        return $this->refcmd;
    }

    public function setRefcmd(int $refcmd): self
    {
        $this->refcmd = $refcmd;

        return $this;
    }

    public function getQuantitea(): ?int
    {
        return $this->quantitea;
    }

    public function setQuantitea(int $quantitea): self
    {
        $this->quantitea = $quantitea;

        return $this;
    }

    public function getTotal(): ?float
    {
        return $this->total;
    }

    public function setTotal(float $total): self
    {
        $this->total = $total;

        return $this;
    }

    public function getIdclient(): ?Clients
    {
        return $this->idclient;
    }

    public function setIdclient(?Clients $idclient): self
    {
        $this->idclient = $idclient;

        return $this;
    }

    public function getReferencep(): ?Produits
    {
        return $this->referencep;
    }

    public function setReferencep(?Produits $referencep): self
    {
        $this->referencep = $referencep;

        return $this;
    }


}
