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
     * @var \Publication
     *
     * @ORM\ManyToOne(targetEntity="Publication")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_publication_dislike", referencedColumnName="id_publication")
     * })
     */
    private $idPublicationDislike;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_client_dislike", referencedColumnName="id")
     * })
     */
    private $idClientDislike;

    public function getIdDislike(): ?int
    {
        return $this->idDislike;
    }

    public function getIdPublicationDislike(): ?Publication
    {
        return $this->idPublicationDislike;
    }

    public function setIdPublicationDislike(?Publication $idPublicationDislike): self
    {
        $this->idPublicationDislike = $idPublicationDislike;

        return $this;
    }

    public function getIdClientDislike(): ?User
    {
        return $this->idClientDislike;
    }

    public function setIdClientDislike(?User $idClientDislike): self
    {
        $this->idClientDislike = $idClientDislike;

        return $this;
    }


}
