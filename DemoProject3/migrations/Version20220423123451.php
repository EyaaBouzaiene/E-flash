<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220423123451 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE produit2 ADD favoris_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE produit2 ADD CONSTRAINT FK_BFF6AE8A51E8871B FOREIGN KEY (favoris_id) REFERENCES favoris (id)');
        $this->addSql('CREATE INDEX IDX_BFF6AE8A51E8871B ON produit2 (favoris_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE produit2 DROP FOREIGN KEY FK_BFF6AE8A51E8871B');
        $this->addSql('DROP INDEX IDX_BFF6AE8A51E8871B ON produit2');
        $this->addSql('ALTER TABLE produit2 DROP favoris_id');
    }
}
