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
     * @ORM\Column(name="REFERENCEP", type="integer", nullable=false)
     */
    private $referencep;

    /**
     * @var int
     *
     * @ORM\Column(name="IDClient", type="integer", nullable=false)
     */
    private $idclient;

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

    public function getReferencep(): ?int
    {
        return $this->referencep;
    }

    public function setReferencep(int $referencep): self
    {
        $this->referencep = $referencep;

        return $this;
    }

    public function getIdclient(): ?int
    {
        return $this->idclient;
    }

    public function setIdclient(int $idclient): self
    {
        $this->idclient = $idclient;

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


}
