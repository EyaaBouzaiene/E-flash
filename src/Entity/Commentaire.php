<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * Commentaire
 *
 * @ORM\Table(name="commentaire", indexes={@ORM\Index(name="FK_67F068BCDEF81A47", columns={"id_publication_commentaire"})})
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
     * @Assert\NotBlank(message="Votre Message  doit être non vide")
     * @Assert\Length(
     *      min = 7,
     *      max = 1000,
     *      minMessage = "Votre Message doit comporter au moins {{ limit }} caractères ",
     *      maxMessage = "Votre Message doit comporter au Plus {{ limit }} caractères" )
     * @Assert\NotEqualTo(
     * value = "mechant",
     * message = "Vous devez enlever le mot {{ value }} "
     * )
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
     * @ORM\Column(name="id_client", type="integer", nullable=false)
     */
    private $idClient;

    /**
     * @var \Publication
     *
     * @ORM\ManyToOne(targetEntity="Publication",inversedBy="commentaires")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_publication_commentaire", referencedColumnName="id_publication",nullable=false)
     * })
     */
    private $idPublicationCommentaire;


    public function getIdCommentaire(): ?int
    {
        return $this->idCommentaire;
    }

    public function getMessages(): ?string
    {
        return $this->messages;
    }

    public function setMessages(?string $messages): self
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

    public function getIdClient(): ?int
    {
        return $this->idClient;
    }

    public function setIdClient(int $idClient): self
    {
        $this->idClient = $idClient;

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

    public function __toString()
{
    return (string) $this->getIdPublicationCommentaire();
}


}
