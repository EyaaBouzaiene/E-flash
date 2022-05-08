<?php

namespace App\Entity;

use App\Repository\FavorisRepository;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
/**
 * @ORM\Entity(repositoryClass=FavorisRepository::class)
 */
class Favoris
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

     /**
     * @ORM\Column(type="datetime")
     */
    private $date;

    

    /**
     * @ORM\ManyToOne(targetEntity=User::class, inversedBy="favoris")
     */
    private $user;

    /**
     * @ORM\ManyToOne(targetEntity=Produits::class, inversedBy="favoris")
     *  @ORM\JoinColumn(name="IdProduits",nullable=false,referencedColumnName="REFERENCE")
     */
    private $produits;

   
    

    
    public function __construct()
    {
       
        $this->date = new \DateTime();
    }
   

    public function getId(): ?int
    {
        return $this->id;
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

    
    

    public function getUser(): ?User
    {
        return $this->user;
    }

    public function setUser(?User $user): self
    {
        $this->user = $user;

        return $this;
    }

    public function getProduits(): ?Produits
    {
        return $this->produits;
    }

    public function setProduits(?Produits $produits): self
    {
        $this->produits = $produits;

        return $this;
    }
}