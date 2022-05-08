<?php

namespace App\Entity;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;
use App\Entity\Categories;
/**
 * Produits
 * @ORM\Table(name="produits", indexes={@ORM\Index(name="IDX_BE2DDF8CDBFC8DB", columns={"CATEGORIE"})})
 * @ORM\Entity(repositoryClass=App\Repository\ProduitsRepository::class)
 */


class Produits
{
    /**
     * @var int
     *
     * @ORM\Column(name="REFERENCE", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $reference;

    /**
     * @var string
    * @Assert\NotBlank(message="Le nom doit etre non vide")
     * @Assert\Type(type="alpha", message="Le nom ne doit pas contenir des chiffres .")
     * @Assert\Length(
     *      max = 8,
     *      maxMessage=" Très long !"
     *
     *     )
     * @ORM\Column(name="NOM", type="string", length=255, nullable=false)
     */
    private $nom;

    /**
     * @var string
     * @Assert\NotBlank(message="Le type  doit etre non vide")
     * @Assert\Type(type="alpha", message="Le type ne doit pas contenir des chiffres .")
     * @Assert\Length(
     *      max = 15,
     *      maxMessage=" Très long !"
     *
     *     )
     * @ORM\Column(name="TYPE", type="string", length=255, nullable=false)
     */
    private $type;

    /**
     * @var int
    * @Assert\NotBlank(message="Entrez quelques choses !")
     * @Assert\Positive(message="La quntiteR doit etre positif.")
     * @Assert\Range(
     *      min = 1,
     *      max = 8000,
     *      notInRangeMessage = "le prix doit etre valid",
     *     )
     * @ORM\Column(name="quantite", type="integer", nullable=false)
     */
    private $quantite;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="DATEP", type="date", nullable=false)
     */
    private $datep;

    

    /**
     * @var blob
     *
     * @ORM\Column(name="IMAGE", type="blob", length=0, nullable=true)
     */
    private $image;

    /**
     * @var int
     *
     * @ORM\Column(name="rate", type="integer", nullable=false)
     */
    private $rate;

    /**
     * @var int
    * @Assert\NotBlank(message="Entrez quelques choses !")
     * @Assert\Positive(message="La quntiteR doit etre positif.")
     * @Assert\Range(
     *      min = 1,
     *      max = 8000,
     *      notInRangeMessage = "le prix doit etre valid",
     *     )
     * @ORM\Column(name="quantiteR", type="integer", nullable=false)
     */
    private $quantiter;

    /**
     * @var string
     *
     * @ORM\Column(name="Prix", type="string", length=255, nullable=false)
     */
    private $prix;

    /**
     * @var string
     *
     * @ORM\Column(name="etat", type="string", length=255, nullable=false, options={"default"="achete"})
     */
    private $etat = 'achete';

    /**
     * @var string
     *
     * @ORM\Column(name="IMAGE2", type="string", length=255, nullable=false)
     */
    private $image2;

    /**
     * @ORM\ManyToOne(targetEntity=Categories::class)
     * @ORM\JoinColumn(name="CATEGORIE",nullable=false,referencedColumnName="CODE")
     */
    private $categorie;

    /**
    * @Assert\NotBlank(message="Ecrivez quelques chose !")
     * @Assert\Length(
     *      min = 10,
     *      max = 1000,
     *      minMessage = "Description très courte ! ",
     *      maxMessage = "doit etre <=100" )
     * @ORM\Column(type="string", length=255,name="DESCP")
     */
    private $DESCP;

    /**
     * @ORM\OneToMany(targetEntity=Favoris::class, mappedBy="produits")
     */
    private $favoris;

    /**
     * @ORM\OneToMany(targetEntity=Review::class, mappedBy="produits")
     */
    private $reviews;

  

   
 

    public function __construct() {
        $this->datep = new \Datetime();
        $this->favoris = new ArrayCollection();
        $this->reviews = new ArrayCollection();
    }
    public function getReference(): ?int
    {
        return $this->reference;
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

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(string $type): self
    {
        $this->type = $type;

        return $this;
    }

    public function getQuantite(): ?int
    {
        return $this->quantite;
    }

    public function setQuantite(int $quantite): self
    {
        $this->quantite = $quantite;

        return $this;
    }
 /**
     * Set date
     *
     * @param \DateTime $date
     */
    public function setDate($datep) {
        $this->datep = $datep;

        return $this;
    }

    /**
     * Get date
     *
     * @return \DateTime 
     */
    public function getDate() {
        return $this->datep;
    }

    

    public function getImage()
    {
        return $this->image;
    }

    public function setImage($image): self
    {
        $this->image = $image;

        return $this;
    }

    public function getRate(): ?int
    {
        return $this->rate;
    }

    public function setRate(int $rate): self
    {
        $this->rate = $rate;

        return $this;
    }

    public function getQuantiter(): ?int
    {
        return $this->quantiter;
    }

    public function setQuantiter(int $quantiter): self
    {
        $this->quantiter = $quantiter;

        return $this;
    }

    public function getPrix(): ?string
    {
        return $this->prix;
    }

    public function setPrix(string $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    public function getEtat(): ?string
    {
        return $this->etat;
    }

    public function setEtat(string $etat): self
    {
        $this->etat = $etat;

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
   /**
     * @return Collection<int, Categories>
     */
    public function getCategorie(): ?Categories
    {
        return $this->categorie;
    }

    public function setCategorie(?Categories $categorie): self
    {
        $this->categorie = $categorie;

        return $this;
    }

    public function getDESCP(): ?string
    {
        return $this->DESCP;
    }

    public function setDESCP(string $DESCP): self
    {
        $this->DESCP = $DESCP;

        return $this;
    }

    public function getDatep(): ?\DateTimeInterface
    {
        return $this->datep;
    }

    public function setDatep(\DateTimeInterface $datep): self
    {
        $this->datep = $datep;

        return $this;
    }

    /**
     * @return Collection<int, Favoris>
     */
    public function getFavoris(): Collection
    {
        return $this->favoris;
    }

    public function addFavori(Favoris $favori): self
    {
        if (!$this->favoris->contains($favori)) {
            $this->favoris[] = $favori;
            $favori->setProduits($this);
        }

        return $this;
    }

    public function removeFavori(Favoris $favori): self
    {
        if ($this->favoris->removeElement($favori)) {
            // set the owning side to null (unless already changed)
            if ($favori->getProduits() === $this) {
                $favori->setProduits(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection<int, Review>
     */
    public function getReviews(): Collection
    {
        return $this->reviews;
    }

    public function addReview(Review $review): self
    {
        if (!$this->reviews->contains($review)) {
            $this->reviews[] = $review;
            $review->setProduits($this);
        }

        return $this;
    }

    public function removeReview(Review $review): self
    {
        if ($this->reviews->removeElement($review)) {
            // set the owning side to null (unless already changed)
            if ($review->getProduits() === $this) {
                $review->setProduits(null);
            }
        }

        return $this;
    }
    
  


}
