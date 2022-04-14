<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * RatingEvent
 *
 * @ORM\Table(name="rating_event", indexes={@ORM\Index(name="id_event", columns={"id_event"})})
 * @ORM\Entity
 */
class RatingEvent
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_rate", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idRate;

    /**
     * @var int
     *
     * @ORM\Column(name="rate", type="integer", nullable=false)
     */
    private $rate;

    /**
     * @var \Evenement
     *
     * @ORM\ManyToOne(targetEntity="Evenement")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_event", referencedColumnName="id")
     * })
     */
    private $idEvent;

    public function getIdRate(): ?int
    {
        return $this->idRate;
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

    public function getIdEvent(): ?Evenement
    {
        return $this->idEvent;
    }

    public function setIdEvent(?Evenement $idEvent): self
    {
        $this->idEvent = $idEvent;

        return $this;
    }


}
