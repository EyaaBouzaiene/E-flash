<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Commentaire
 *
 * @ORM\Table(name="commentaire")
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
     * @var int
     *
     * @ORM\Column(name="id_publication_commentaire", type="integer", nullable=false)
     */
    private $idPublicationCommentaire;

    /**
     * @var int
     *
     * @ORM\Column(name="id_client", type="integer", nullable=false)
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

    public function getIdPublicationCommentaire(): ?int
    {
        return $this->idPublicationCommentaire;
    }

    public function setIdPublicationCommentaire(int $idPublicationCommentaire): self
    {
        $this->idPublicationCommentaire = $idPublicationCommentaire;

        return $this;
    }

    public function getIdClient(): ?int
    {
        return $this->idClient;
    }

    public function setIdClient(int $idClient): self
    {
        $this->idClient = $idClient;

        return $this;
    }


}
