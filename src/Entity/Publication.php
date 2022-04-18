<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Publication
 *
 * @ORM\Table(name="publication")
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
     * @Assert\NotBlank(message="Votre Titre doit être non vide")
     * @Assert\Length(
     *      min = 5,
     *      minMessage=" Le Titre d'une Publication doit comporter au moins {{ limit }} caractères"
     *
     *     )
     * @ORM\Column(name="titre", type="string", length=20, nullable=false)
     */
    private $titre;

    /**
     * @var string
     * @Assert\NotBlank(message="Votre Description  doit être non vide")
     * @Assert\Length(
     *      min = 7,
     *      max = 100,
     *      minMessage = " Le Description d'une Publication doit comporter au moins {{ limit }} caractères ",
     *      maxMessage = " Le Description d'une Publication doit comporter au plus  {{ limit }} caractères" )
     * @ORM\Column(name="description", type="text", length=65535, nullable=false)
     */
    private $description;

    /**
     * @var string
     * @ORM\Column(name="image_publication", type="string", length=255, nullable=false)
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

    /**
     * @ORM\OneToMany(targetEntity=Commentaire::class, mappedBy="idPublicationCommentaire", orphanRemoval=true)
     */
    private $commentaires;

    public function __construct()
    {
        $this->commentaires = new ArrayCollection();
    }

    public function getIdPublication(): ?int
    {
        return $this->idPublication;
    }

    public function getTitre(): ?string
    {
        return $this->titre;
    }

    public function setTitre(?string $titre): self
    {
        $this->titre = $titre;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(?string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getImagePublication()
    {
        return $this->imagePublication;
    }

    public function setImagePublication($imagePublication)
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

    /**
     * @return Collection<int, Commentaire>
     */
    public function getCommentaires(): Collection
    {
        return $this->commentaires;
    }

    public function addCommentaire(Commentaire $commentaire): self
    {
        if (!$this->commentaires->contains($commentaire)) {
            $this->commentaires[] = $commentaire;
            $commentaire->setPublication($this);
        }

        return $this;
    }

    public function removeCommentaire(Commentaire $commentaire): self
    {
        if ($this->commentaires->removeElement($commentaire)) {
            // set the owning side to null (unless already changed)
            if ($commentaire->getPublication() === $this) {
                $commentaire->setPublication(null);
            }
        }

        return $this;
    }


}
