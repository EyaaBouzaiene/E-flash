<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Likes
 *
 * @ORM\Table(name="likes", indexes={@ORM\Index(name="FK_49CA4E7D65ED4E26", columns={"id_client_like"}), @ORM\Index(name="FK_49CA4E7D441194AF", columns={"id_publication_like"})})
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
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_client_like", referencedColumnName="id")
     * })
     */
    private $idClientLike;

    /**
     * @var \Publication
     *
     * @ORM\ManyToOne(targetEntity="Publication")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_publication_like", referencedColumnName="id_publication")
     * })
     */
    private $idPublicationLike;

    public function getIdLike(): ?int
    {
        return $this->idLike;
    }

    public function getIdClientLike(): ?User
    {
        return $this->idClientLike;
    }

    public function setIdClientLike(?User $idClientLike): self
    {
        $this->idClientLike = $idClientLike;

        return $this;
    }

    public function getIdPublicationLike(): ?Publication
    {
        return $this->idPublicationLike;
    }

    public function setIdPublicationLike(?Publication $idPublicationLike): self
    {
        $this->idPublicationLike = $idPublicationLike;

        return $this;
    }


}
