<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Produit2
 *
 * @ORM\Table(name="produit2", indexes={@ORM\Index(name="IDX_BFF6AE8ABCF5E72D", columns={"categorie_id"})})
 * @ORM\Entity
 */
class Produit2
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
     *
     * @ORM\Column(name="nom", type="string", length=255, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=255, nullable=false)
     */
    private $type;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="datep", type="date", nullable=false)
     */
    private $datep;

    /**
     * @var string
     *
     * @ORM\Column(name="descp", type="string", length=255, nullable=false)
     */
    private $descp;

    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=255, nullable=false)
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
     *
     * @ORM\Column(name="quantite_r", type="integer", nullable=false)
     */
    private $quantiteR;

    /**
     * @var string
     *
     * @ORM\Column(name="prix", type="string", length=255, nullable=false)
     */
    private $prix;

    /**
     * @var string
     *
     * @ORM\Column(name="etat", type="string", length=255, nullable=false)
     */
    private $etat;

    /**
     * @var int
     *
     * @ORM\Column(name="quantite", type="integer", nullable=false)
     */
    private $quantite;

    /**
     * @var \Categorie2
     *
     * @ORM\ManyToOne(targetEntity="Categorie2")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="categorie_id", referencedColumnName="id")
     * })
     */
    private $categorie;


}
