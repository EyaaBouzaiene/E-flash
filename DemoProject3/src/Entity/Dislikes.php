<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Dislikes
 *
 * @ORM\Table(name="dislikes", indexes={@ORM\Index(name="fk_publication_dislike", columns={"id_publication_dislike"}), @ORM\Index(name="fk_client_dislike", columns={"id_client_dislike"})})
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
