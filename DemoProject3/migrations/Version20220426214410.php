<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220426214410 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE cart_details CHANGE carts_id carts_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE favoris CHANGE produits_id produits_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE produit2 CHANGE categorie_id categorie_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE user DROP code, CHANGE nom nom VARCHAR(255) NOT NULL, CHANGE prenom prenom VARCHAR(255) NOT NULL, CHANGE email email VARCHAR(180) NOT NULL, CHANGE role role LONGTEXT NOT NULL COMMENT \'(DC2Type:json)\', CHANGE date date DATETIME NOT NULL, CHANGE activation_token activation_token VARCHAR(50) DEFAULT NULL');
        $this->addSql('CREATE UNIQUE INDEX UNIQ_8D93D649E7927C74 ON user (email)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE cart_details CHANGE carts_id carts_id INT NOT NULL');
        $this->addSql('ALTER TABLE favoris CHANGE produits_id produits_id INT NOT NULL');
        $this->addSql('ALTER TABLE produit2 CHANGE categorie_id categorie_id INT NOT NULL');
        $this->addSql('DROP INDEX UNIQ_8D93D649E7927C74 ON user');
        $this->addSql('ALTER TABLE user ADD code VARCHAR(20) DEFAULT NULL, CHANGE email email VARCHAR(30) NOT NULL, CHANGE role role LONGTEXT NOT NULL COMMENT \'DC2Type:json\', CHANGE nom nom VARCHAR(30) NOT NULL, CHANGE prenom prenom VARCHAR(30) NOT NULL, CHANGE date date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL, CHANGE activation_token activation_token VARCHAR(255) DEFAULT NULL');
    }
}
