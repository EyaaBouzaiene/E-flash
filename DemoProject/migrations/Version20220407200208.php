<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220407200208 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE produit2 ADD categorie_id INT NOT NULL, ADD quantite INT NOT NULL');
        $this->addSql('ALTER TABLE produit2 ADD CONSTRAINT FK_BFF6AE8ABCF5E72D FOREIGN KEY (categorie_id) REFERENCES categorie2 (id)');
        $this->addSql('CREATE INDEX IDX_BFF6AE8ABCF5E72D ON produit2 (categorie_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE produit2 DROP FOREIGN KEY FK_BFF6AE8ABCF5E72D');
        $this->addSql('DROP INDEX IDX_BFF6AE8ABCF5E72D ON produit2');
        $this->addSql('ALTER TABLE produit2 DROP categorie_id, DROP quantite');
    }
}
