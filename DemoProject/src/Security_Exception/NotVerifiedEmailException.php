<?php

namespace App\Security_Exception;

use Symfony\Component\Security\Core\Exception\CustomUserMessageAuthenticationException;

class NotVerifiedEmailException extends CustomUserMessageAuthenticationException
{

    public function __construct(

        string $message="email nom verifier",
        array $messageData = [],
        int $code = 0,
        \throwable $previous = null
        )
{ parent::__construct($message,$messageData,$code,$previous);
    }
}