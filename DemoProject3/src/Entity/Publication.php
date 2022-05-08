<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Publication
 *
 * @ORM\Table(name="publication", indexes={@ORM\Index(name="fk_client_publication", columns={"id_client_publication"})})
 * @ORM\Entity
 */
class Publication
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_publication", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idPublication;

    /**
     * @var string
     *
     * @ORM\Column(name="titre", type="string", length=20, nullable=false)
     */
    private $titre;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="text", length=65535, nullable=false)
     */
    private $description;

    /**
     * @var string
     *
     * @ORM\Column(name="image_publication", type="blob", length=65535, nullable=false)
     */
    private $imagePublication;

    /**
     * @var string
     *
     * @ORM\Column(name="date_publication", type="string", length=20, nullable=false)
     */
    private $datePublication;

    /**
     * @var int
     *
     * @ORM\Column(name="id_client_publication", type="integer", nullable=false)
     */
    private $idClientPublication;

    public function getIdPublication(): ?int
    {
        return $this->idPublication;
    }

    public function getTitre(): ?string
    {
        return $this->titre;
    }

    public function setTitre(string $titre): self
    {
        $this->titre = $titre;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getImagePublication()
    {
        return $this->imagePublication;
    }

    public function setImagePublication($imagePublication): self
    {
        $this->imagePublication = $imagePublication;

        return $this;
    }

    public function getDatePublication(): ?string
    {
        return $this->datePublication;
    }

    public function setDatePublication(string $datePublication): self
    {
        $this->datePublication = $datePublication;

        return $this;
    }

    public function getIdClientPublication(): ?int
    {
        return $this->idClientPublication;
    }

    public function setIdClientPublication(int $idClientPublication): self
    {
        $this->idClientPublication = $idClientPublication;

        return $this;
    }


}
