<?php

namespace App\Entity;

use App\Repository\Categorie2Repository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=Categorie2Repository::class)
 */
class Categorie2
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $NOM;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNOM(): ?string
    {
        return $this->NOM;
    }

    public function setNOM(string $NOM): self
    {
        $this->NOM = $NOM;

        return $this;
    }
}
