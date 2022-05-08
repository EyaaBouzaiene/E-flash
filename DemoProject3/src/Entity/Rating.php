<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Rating
 *
 * @ORM\Table(name="rating", indexes={@ORM\Index(name="fk_liv", columns={"idLivreur"})})
 * @ORM\Entity
 */
class Rating
{
    /**
     * @var int
     *
     * @ORM\Column(name="idl", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idl;

    /**
     * @var int
     *
     * @ORM\Column(name="idLivreur", type="integer", nullable=false)
     */
    private $idlivreur;

    /**
     * @var int|null
     *
     * @ORM\Column(name="rat", type="integer", nullable=true)
     */
    private $rat;

    public function getIdl(): ?int
    {
        return $this->idl;
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

    public function getRat(): ?int
    {
        return $this->rat;
    }

    public function setRat(?int $rat): self
    {
        $this->rat = $rat;

        return $this;
    }


}
