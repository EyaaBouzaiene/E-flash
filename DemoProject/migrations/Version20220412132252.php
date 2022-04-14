<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220412132252 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE categorie2 (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE produit2 (id INT AUTO_INCREMENT NOT NULL, categorie_id INT DEFAULT NULL, nom VARCHAR(255) NOT NULL, type VARCHAR(255) NOT NULL, datep DATE NOT NULL, descp VARCHAR(255) NOT NULL, image VARCHAR(255) NOT NULL, rate INT NOT NULL, quantite_r INT NOT NULL, prix VARCHAR(255) NOT NULL, etat VARCHAR(255) NOT NULL, quantite INT NOT NULL, INDEX IDX_BFF6AE8ABCF5E72D (categorie_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE produit2 ADD CONSTRAINT FK_BFF6AE8ABCF5E72D FOREIGN KEY (categorie_id) REFERENCES categorie2 (id)');
        $this->addSql('ALTER TABLE evenement ADD image2 VARCHAR(255) NOT NULL');
        $this->addSql('ALTER TABLE rating_event CHANGE id_event id_event INT DEFAULT NULL');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY fk2');
        $this->addSql('ALTER TABLE reservation CHANGE id_event id_event INT DEFAULT NULL');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C84955D52B4B97 FOREIGN KEY (id_event) REFERENCES evenement (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE produit2 DROP FOREIGN KEY FK_BFF6AE8ABCF5E72D');
        $this->addSql('DROP TABLE categorie2');
        $this->addSql('DROP TABLE produit2');
        $this->addSql('ALTER TABLE evenement DROP image2');
        $this->addSql('ALTER TABLE rating_event CHANGE id_event id_event INT NOT NULL');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C84955D52B4B97');
        $this->addSql('ALTER TABLE reservation CHANGE id_event id_event INT NOT NULL');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT fk2 FOREIGN KEY (id_event) REFERENCES evenement (id) ON UPDATE NO ACTION ON DELETE CASCADE');
    }
}
