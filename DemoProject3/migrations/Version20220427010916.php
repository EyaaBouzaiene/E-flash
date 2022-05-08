<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220427010916 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE favoris ADD produits_id INT NOT NULL, ADD date DATETIME NOT NULL');
        $this->addSql('ALTER TABLE favoris ADD CONSTRAINT FK_8933C432CD11A2CF FOREIGN KEY (produits_id) REFERENCES produit2 (id)');
        $this->addSql('CREATE INDEX IDX_8933C432CD11A2CF ON favoris (produits_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE favoris DROP FOREIGN KEY FK_8933C432CD11A2CF');
        $this->addSql('DROP INDEX IDX_8933C432CD11A2CF ON favoris');
        $this->addSql('ALTER TABLE favoris DROP produits_id, DROP date');
    }
}