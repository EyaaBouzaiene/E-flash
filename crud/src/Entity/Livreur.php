<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Livreur
 *
 * @ORM\Table(name="livreur")
 * @ORM\Entity(repositoryClass=App\Repository\LivreurRepository::class)
 */
class Livreur
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *@Assert\NotBlank(message= " champs nom est vide")
     * @ORM\Column(name="nomLivreur", type="string", length=30, nullable=false)
     */
    private $nomlivreur;

    /**
     * @var string
     *@Assert\NotBlank(message= " champs prenom est vide")
     * @ORM\Column(name="prenomLivreur", type="string", length=30, nullable=false)
     */
    private $prenomlivreur;

    /**
     * @var string
     *@Assert\NotBlank(message= " champs mail est vide")
     
     * @Assert\Email(message="l adresse  n est pas valide")
     * @ORM\Column(name="email", type="string", length=30, nullable=false)
     */
    private $email;

    /**
     * @var string
     * @Assert\Length(
     *      min = 4,
     *      max = 4,minMessage = "mdp name doit etre {{ limit }} chiffre",)
     *@Assert\NotBlank(message= " champs telf est vide")
     
     * @ORM\Column(name="password", type="string", length=80, nullable=false)
     */
    private $password;

    /**
     * @var int
    *  @Assert\NotBlank(message= " champs tel est vide")
     * @ORM\Column(name="telf", type="integer", nullable=false)
     */
    private $telf;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNomlivreur(): ?string
    {
        return $this->nomlivreur;
    }

    public function setNomlivreur(string $nomlivreur): self
    {
        $this->nomlivreur = $nomlivreur;

        return $this;
    }

    public function getPrenomlivreur(): ?string
    {
        return $this->prenomlivreur;
    }

    public function setPrenomlivreur(string $prenomlivreur): self
    {
        $this->prenomlivreur = $prenomlivreur;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }

    public function getPassword(): ?string
    {
        return $this->password;
    }

    public function setPassword(string $password): self
    {
        $this->password = $password;

        return $this;
    }

    public function getTelf(): ?int
    {
        return $this->telf;
    }

    public function setTelf(int $telf): self
    {
        $this->telf = $telf;

        return $this;
    }


}
