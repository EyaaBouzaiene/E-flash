<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Likes
 *
 * @ORM\Table(name="likes", indexes={@ORM\Index(name="fk_publication_like", columns={"id_publication_like"}), @ORM\Index(name="fk_client_like", columns={"id_client_like"})})
 * @ORM\Entity
 */
class Likes
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_like", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idLike;

    /**
     * @var int
     *
     * @ORM\Column(name="id_publication_like", type="integer", nullable=false)
     */
    private $idPublicationLike;

    /**
     * @var int
     *
     * @ORM\Column(name="id_client_like", type="integer", nullable=false)
     */
    private $idClientLike;

    public function getIdLike(): ?int
    {
        return $this->idLike;
    }

    public function getIdPublicationLike(): ?int
    {
        return $this->idPublicationLike;
    }

    public function setIdPublicationLike(int $idPublicationLike): self
    {
        $this->idPublicationLike = $idPublicationLike;

        return $this;
    }

    public function getIdClientLike(): ?int
    {
        return $this->idClientLike;
    }

    public function setIdClientLike(int $idClientLike): self
    {
        $this->idClientLike = $idClientLike;

        return $this;
    }


}
