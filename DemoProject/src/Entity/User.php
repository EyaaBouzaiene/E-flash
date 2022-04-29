<?php

namespace App\Entity;

use App\Repository\UserRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Security\Core\User\UserInterface;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Validator\Constraints\ExpressionLanguageSyntax;


/**
 * @ORM\Entity(repositoryClass=UserRepository::class)
 *  @UniqueEntity(
 *  fields= {"email"},
 *  message= "l'email que vous avez indiqué déja utilisé !"
 * )
 */
class User implements UserInterface
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=180, unique=true)
     * @Assert\Email(message = "The email '{{ value }}' is not a valid email.")
     */
    private $email;

    /**
     * @ORM\Column(type="json")
     */
    private $role = [];

    /**
     * @var string The hashed password
     * @ORM\Column(type="string")
     * @Assert\Length (min="8", minMessage="Votre mot de passe doit faire minimum 8 caractéres")
     */
    private $password;

     /**
     * @Assert\EqualTo(propertyPath="password" ,message="tapez meme mot de passe ")
     */
    public $confirm_password ;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Nom is required")
     */
    private $nom;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="prenom is required")
     */
    private $prenom;

    /**
     * @ORM\Column(type="datetime")
     */
    private $date;

    /**
     * @ORM\Column(type="integer")
     * @Assert\Length (min="8", minMessage="Votre mot de passe doit faire 8 caractéres")
     */
    private $telf;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="telephone is required")
     */
    private $image;
    /**
     * @ORM\Column(type="string",length=255,nullable=true)
     */
    private $githubId;
    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $reset_token;
    /**
     * @ORM\Column(type="string", length=50 ,nullable=true)
     */
    private $activation_token;

    /**
     * @return mixed
     */
    public function getGithubId()
    {
        return $this->githubId;
    }

    /**
     * @param mixed $githubId
     */
    public function setGithubId($githubId)
    {
        $this->githubId = $githubId;
    }






    public function getId(): ?int
    {
        return $this->id;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }

    /**
     * A visual identifier that represents this user.
     *
     * @see UserInterface
     */
    public function getUsername(): string
    {
        return (string) $this->email;
    }

    /**
     * @see UserInterface
     */
    public function getRoles(): array
    {
        $role = $this->role;
        // guarantee every user at least has ROLE_USER
        if (empty($roles)) {
            $roles[] = 'ROLE_USER';
        }

       // $role[] = 'ROLE_USER';

        return array_unique($role);
    }

    public function setRoles(array $role): self
    {
        $this->role = $role;

        return $this;
    }

    /**
     * @see UserInterface
     */
    public function getPassword(): string
    {
        return (string) $this->password;
    }

    public function setPassword(string $password): self
    {
        $this->password = $password;

        return $this;
    }

    /**
     * Returning a salt is only needed, if you are not using a modern
     * hashing algorithm (e.g. bcrypt or sodium) in your security.yaml.
     *
     * @see UserInterface
     */
    public function getSalt(): ?string
    {
        return null;
    }

    /**
     * @see UserInterface
     */
    public function eraseCredentials()
    {
        // If you store any temporary, sensitive data on the user, clear it here
        // $this->plainPassword = null;
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

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(string $prenom): self
    {
        $this->prenom = $prenom;

        return $this;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }

    public function getTelf(): ?int
    {
        return $this->telf;
    }

    public function setTelf(int $telf): self
    {
        $this->telf = $telf;

        return $this;
    }

    public function getImage()
    {
        return $this->image;
    }

    public function setImage($image)
    {
        $this->image = $image;

        return $this;
    }
    protected $captchaCode;

    public function getCaptchaCode()
    {
        return $this->captchaCode;
    }

    public function setCaptchaCode($captchaCode)
    {
        $this->captchaCode = $captchaCode;
    }

    public function getActivationToken(): ?string
    {
        return $this->activation_token;
    }

    public function setActivationToken(string $activation_token): self
    {
        $this->activation_token = $activation_token;

        return $this;
    }

    public function getResetToken(): ?string
    {
        return $this->reset_token;
    }

    public function setResetToken(?string $reset_token): self
    {
        $this->reset_token = $reset_token;

        return $this;
    }

}
