<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Livraison
 *
 * @ORM\Table(name="livraison", indexes={@ORM\Index(name="fk_commande", columns={"idCommande"}), @ORM\Index(name="fk_livreur", columns={"idLivreur"})})
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
     * @ORM\Column(name="idCommande", type="integer", nullable=false)
     */
    private $idcommande;

    /**
     * @var int
     *
     * @ORM\Column(name="idLivreur", type="integer", nullable=false)
     */
    private $idlivreur;

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

    public function getIdlivraison(): ?int
    {
        return $this->idlivraison;
    }

    public function getIdcommande(): ?int
    {
        return $this->idcommande;
    }

    public function setIdcommande(int $idcommande): self
    {
        $this->idcommande = $idcommande;

        return $this;
    }

    public function getIdlivreur(): ?int
    {
        return $this->idlivreur;
    }

    public function setIdlivreur(int $idlivreur): self
    {
        $this->idlivreur = $idlivreur;

        return $this;
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


}
