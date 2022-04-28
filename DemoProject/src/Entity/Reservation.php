<?php

namespace App\Entity;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;

/**
 * Reservation
 *
 * @ORM\Table(name="reservation", indexes={@ORM\Index(name="id_event", columns={"id_event"})})
 * @ORM\Entity
 */
class Reservation
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_reservation", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idReservation;

    /**
     * @var string
     *
     * @Assert\Email(message = "The email '{{ value }}' is not a valid email.")
     * @ORM\Column(name="gmail", type="string", length=30, nullable=false)
     */
    private $gmail;

    /**
     * @var int
     * @Assert\NotBlank(message="Votre Description  doit être non vide")
     * @Assert\Positive
     * @ORM\Column(name="nombre_billet", type="integer", nullable=false)
     */
    private $nombreBillet;

    /**
     * @var \Evenement
     *
     * @ORM\ManyToOne(targetEntity="Evenement")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_event", referencedColumnName="id")
     * })
     */
    private $idEvent;

    public function getIdReservation(): ?int
    {
        return $this->idReservation;
    }

    public function getGmail(): ?string
    {
        return $this->gmail;
    }

    public function setGmail(string $gmail): self
    {
        $this->gmail = $gmail;

        return $this;
    }

    public function getNombreBillet(): ?int
    {
        return $this->nombreBillet;
    }

    public function setNombreBillet(int $nombreBillet): self
    {
        $this->nombreBillet = $nombreBillet;

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
