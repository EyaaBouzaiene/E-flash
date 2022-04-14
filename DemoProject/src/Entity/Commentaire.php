<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Commentaire
 *
 * @ORM\Table(name="commentaire", indexes={@ORM\Index(name="fk_publication_commentaire", columns={"id_publication_commentaire"}), @ORM\Index(name="fk_client_commentaire", columns={"id_client"})})
 * @ORM\Entity
 */
class Commentaire
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_commentaire", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idCommentaire;

    /**
     * @var string
     *
     * @ORM\Column(name="messages", type="text", length=65535, nullable=false)
     */
    private $messages;

    /**
     * @var string
     *
     * @ORM\Column(name="date_commentaire", type="string", length=20, nullable=false)
     */
    private $dateCommentaire;

    /**
     * @var \Publication
     *
     * @ORM\ManyToOne(targetEntity="Publication")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_publication_commentaire", referencedColumnName="id_publication")
     * })
     */
    private $idPublicationCommentaire;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_client", referencedColumnName="id")
     * })
     */
    private $idClient;

    public function getIdCommentaire(): ?int
    {
        return $this->idCommentaire;
    }

    public function getMessages(): ?string
    {
        return $this->messages;
    }

    public function setMessages(string $messages): self
    {
        $this->messages = $messages;

        return $this;
    }

    public function getDateCommentaire(): ?string
    {
        return $this->dateCommentaire;
    }

    public function setDateCommentaire(string $dateCommentaire): self
    {
        $this->dateCommentaire = $dateCommentaire;

        return $this;
    }

    public function getIdPublicationCommentaire(): ?Publication
    {
        return $this->idPublicationCommentaire;
    }

    public function setIdPublicationCommentaire(?Publication $idPublicationCommentaire): self
    {
        $this->idPublicationCommentaire = $idPublicationCommentaire;

        return $this;
    }

    public function getIdClient(): ?User
    {
        return $this->idClient;
    }

    public function setIdClient(?User $idClient): self
    {
        $this->idClient = $idClient;

        return $this;
    }


}
