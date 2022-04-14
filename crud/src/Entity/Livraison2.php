<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * Livraison2
 *
 * @ORM\Table(name="livraison2", indexes={@ORM\Index(name="livraison_livreur", columns={"idl"}), @ORM\Index(name="livraison_commande", columns={"idcommande"})})
 * @ORM\Entity(repositoryClass=App\Repository\Livraison2Repository::class)
 */
class Livraison2
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
     *@Assert\NotBlank(message= " champs prix est vide")
     * @ORM\Column(name="prix", type="integer", nullable=false)
     */
    private $prix;

    /**
     * @var string
     *@Assert\NotBlank(message= " champs ville est vide")
     * @ORM\Column(name="ville", type="string", length=80, nullable=false)
     */
    private $ville;

    /**
     * @var string
     *
     * @ORM\Column(name="etat", type="string", length=80, nullable=false, options={"default"="En Cours"})
     */
    private $etat = 'En Cours';

    /**
     * @var \Commande
     *
     * @ORM\ManyToOne(targetEntity="Commande" ,inversedBy="idcommande")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idcommande", referencedColumnName="idCommande")
     * })
     */
    private $idcommande;

    /**
     * @var \Livreur
     *
     * @ORM\ManyToOne(targetEntity="Livreur",inversedBy="id")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idl", referencedColumnName="id")
     * })
     */
    private $idl;

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

}
