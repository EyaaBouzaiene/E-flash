<?php

namespace App\Entity;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;
use App\Repository\EvenementRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Symfony\Component\Validator\Constraints\Length;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * Evenement
 *
 * @ORM\Table(name="evenement")
 * @ORM\Entity(repositoryClass=App\Repository\EvenementRepository::class)
 *  (
 *  fields= {"email"},
 *  message= "l'email que vous avez indiqué déja utilisé !"
 * )
 */
class Evenement
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     * @Assert\NotBlank(message="Votre Titre doit être non vide")
     * @Assert\Length(
     *      min = 5,
     *      minMessage=" Le Titre d'une Publication doit comporter au moins {{ limit }} caractères"
     *
     *     )
     * @ORM\Column(name="nom", type="string", length=20, nullable=false)
     */
    private $nom;

    /**
     * @var string
      * @Assert\NotBlank(message="Votre Description  doit être non vide")
     * @Assert\Length(
     *      min = 7,
     *      max = 100,
     *      minMessage = " La description d'une evenement doit comporter au moins {{ limit }} caractères ",
     *      maxMessage = " La description d'une evenement doit comporter au plus  {{ limit }} caractères" )
     * @ORM\Column(name="description", type="text", length=65535, nullable=false)
     */
    private $description;

    /**
     * @var int
     * @Assert\NotBlank(message="la durèe de votre evenement ne   doit être non vide")
     * @Assert\Positive
     * @ORM\Column(name="duree", type="integer", length=11, nullable=false)
     */
    private $duree;

     /**
     * @var int
     * @Assert\NotBlank(message="Votre Description  doit être non vide")
     * @Assert\Positive
     * @ORM\Column(name="nombre_place", type="integer", nullable=false)
     */
    private $nombrePlace;

      /**
     * @ORM\Column(type="datetime")
      * @Groups("post:read")
     */
    private $end;
  /**
     * @ORM\Column(type="datetime")
     * @Groups("post:read")
     */
    private $dateDebut;
    /**
     * @var string
     *
     * @ORM\Column(name="image2", type="string", length=255, nullable=false)
     */
    private $image2;
      /**
     * @ORM\Column(type="float")
     */
    private $lat;

    /**
     * @ORM\Column(type="float")
     */
    private $lon;
        /**
     * @ORM\Column(type="string", length=80)

     */

    private $lieu;

    /**
     * @ORM\OneToMany(targetEntity=PostLike::class, mappedBy="post")
     */
    private $Likes;

    public function __construct()
    {
        $this->Likes = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

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

    public function getDuree(): ?int
    {
        return $this->duree;
    }

    public function setDuree(int $duree): self
    {
        $this->duree = $duree;

        return $this;
    }

    public function getNombrePlace(): ?int
    {
        return $this->nombrePlace;
    }

    public function setNombrePlace(int $nombrePlace): self
    {
        $this->nombrePlace = $nombrePlace;

        return $this;
    }

    public function getDateDebut(): ?\DateTimeInterface
    {
        return $this->dateDebut;
    }

    public function setDateDebut(\DateTimeInterface $dateDebut): self
    {
        $this->dateDebut = $dateDebut;

        return $this;
    }
    public function getEnd(): ?\DateTimeInterface
    {
        return $this->end;
    }

    public function setEnd(\DateTimeInterface $end): self
    {
        $this->end = $end;

        return $this;
    }

    public function getImage2(): ?string
    {
        return $this->image2;
    }

    public function setImage2(string $image2): self
    {
        $this->image2 = $image2;

        return $this;
    }

    public function getLat(): ?float
    {
        return $this->lat;
    }

    public function setLat(float $lat): self
    {
        $this->lat = $lat;

        return $this;
    }
    public function getLon(): ?float
    {
        return $this->lon;
    }

    public function setLon(float $lon): self
    {
        $this->lon = $lon;

        return $this;
    }
    public function getLieu(): ?string
    {
        return $this->lieu;
    }

    public function setLieu(string $lieu): self
    {
        $this->lieu = $lieu;

        return $this;
    }

    /**
     * @return Collection<int, PostLike>
     */
    public function getLikes(): Collection
    {
        return $this->Likes;
    }

    public function addLike(PostLike $like): self
    {
        if (!$this->Likes->contains($like)) {
            $this->Likes[] = $like;
            $like->setPost($this);
        }

        return $this;
    }

    public function removeLike(PostLike $like): self
    {
        if ($this->Likes->removeElement($like)) {
            // set the owning side to null (unless already changed)
            if ($like->getPost() === $this) {
                $like->setPost(null);
            }
        }

        return $this;
    }
}
