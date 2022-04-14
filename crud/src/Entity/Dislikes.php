<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Dislikes
 *
 * @ORM\Table(name="dislikes")
 * @ORM\Entity
 */
class Dislikes
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_dislike", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idDislike;

    /**
     * @var int
     *
     * @ORM\Column(name="id_publication_dislike", type="integer", nullable=false)
     */
    private $idPublicationDislike;

    /**
     * @var int
     *
     * @ORM\Column(name="id_client_dislike", type="integer", nullable=false)
     */
    private $idClientDislike;

    public function getIdDislike(): ?int
    {
        return $this->idDislike;
    }

    public function getIdPublicationDislike(): ?int
    {
        return $this->idPublicationDislike;
    }

    public function setIdPublicationDislike(int $idPublicationDislike): self
    {
        $this->idPublicationDislike = $idPublicationDislike;

        return $this;
    }

    public function getIdClientDislike(): ?int
    {
        return $this->idClientDislike;
    }

    public function setIdClientDislike(int $idClientDislike): self
    {
        $this->idClientDislike = $idClientDislike;

        return $this;
    }


}
