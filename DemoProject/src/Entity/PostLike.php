<?php

namespace App\Entity;

use App\Repository\PostLikeRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=PostLikeRepository::class)
 */
class PostLike
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\ManyToOne(targetEntity=Evenement::class, inversedBy="Likes")
     */
    private $post;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getPost(): ?Evenement
    {
        return $this->post;
    }

    public function setPost(?Evenement $post): self
    {
        $this->post = $post;

        return $this;
    }
}
