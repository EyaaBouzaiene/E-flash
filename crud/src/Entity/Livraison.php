<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Livraison
 *
 * @ORM\Table(name="livraison", indexes={@ORM\Index(name="fk_liv", columns={"idl"}), @ORM\Index(name="FK_A60C9F1FFBC3259F", columns={"idLivreur"}), @ORM\Index(name="FK_A60C9F1F3D498C26", columns={"idCommande"})})
 * @ORM\Entity
 */
class Livraison
{
    /**
     * @var int
     *
     * @ORM\Column(name="idLivraison", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idlivraison;

    /**
     * @var int
     *
     * @ORM\Column(name="prix", type="integer", nullable=false)
     */
    private $prix;

    /**
     * @var string
     *
     * @ORM\Column(name="ville", type="string", length=80, nullable=false)
     */
    private $ville;

    /**
     * @var string
     *
     * @ORM\Column(name="etat", type="string", length=80, nullable=false, options={"default"="En cours"})
     */
    private $etat = 'En cours';

    /**
     * @var \Commande
     *
     * @ORM\ManyToOne(targetEntity="Commande")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idCommande", referencedColumnName="idCommande")
     * })
     */
    private $idcommande;

    /**
     * @var \Livreur
     *
     * @ORM\ManyToOne(targetEntity="Livreur")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idl", referencedColumnName="id")
     * })
     */
    private $idl;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idLivreur", referencedColumnName="id")
     * })
     */
    private $idlivreur;

    public function getIdlivraison(): ?int
    {
        return $this->idlivraison;
    }

    public function getPrix(): ?int
    {
        return $this->prix;
    }

    public function setPrix(int $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    public function getVille(): ?string
    {
        return $this->ville;
    }

    public function setVille(string $ville): self
    {
        $this->ville = $ville;

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

    public function getIdcommande(): ?Commande
    {
        return $this->idcommande;
    }

    public function setIdcommande(?Commande $idcommande): self
    {
        $this->idcommande = $idcommande;

        return $this;
    }

    public function getIdl(): ?Livreur
    {
        return $this->idl;
    }

    public function setIdl(?Livreur $idl): self
    {
        $this->idl = $idl;

        return $this;
    }

    public function getIdlivreur(): ?User
    {
        return $this->idlivreur;
    }

    public function setIdlivreur(?User $idlivreur): self
    {
        $this->idlivreur = $idlivreur;

        return $this;
    }


}
