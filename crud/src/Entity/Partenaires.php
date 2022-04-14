<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Partenaires
 *
 * @ORM\Table(name="partenaires")
 * @ORM\Entity
 */
class Partenaires
{
    /**
     * @var int
     *
     * @ORM\Column(name="idP", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idp;

    /**
     * @var string
     *
     * @ORM\Column(name="nomP", type="string", length=50, nullable=false)
     */
    private $nomp;

    /**
     * @var string
     *
     * @ORM\Column(name="prenomP", type="string", length=100, nullable=false)
     */
    private $prenomp;

    /**
     * @var string
     *
     * @ORM\Column(name="mailP", type="string", length=100, nullable=false)
     */
    private $mailp;

    /**
     * @var string
     *
     * @ORM\Column(name="categorieP", type="string", length=50, nullable=false)
     */
    private $categoriep;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateAjout", type="date", nullable=false)
     */
    private $dateajout;

    public function getIdp(): ?int
    {
        return $this->idp;
    }

    public function getNomp(): ?string
    {
        return $this->nomp;
    }

    public function setNomp(string $nomp): self
    {
        $this->nomp = $nomp;

        return $this;
    }

    public function getPrenomp(): ?string
    {
        return $this->prenomp;
    }

    public function setPrenomp(string $prenomp): self
    {
        $this->prenomp = $prenomp;

        return $this;
    }

    public function getMailp(): ?string
    {
        return $this->mailp;
    }

    public function setMailp(string $mailp): self
    {
        $this->mailp = $mailp;

        return $this;
    }

    public function getCategoriep(): ?string
    {
        return $this->categoriep;
    }

    public function setCategoriep(string $categoriep): self
    {
        $this->categoriep = $categoriep;

        return $this;
    }

    public function getDateajout(): ?\DateTimeInterface
    {
        return $this->dateajout;
    }

    public function setDateajout(\DateTimeInterface $dateajout): self
    {
        $this->dateajout = $dateajout;

        return $this;
    }


}
