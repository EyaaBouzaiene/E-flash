<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220407213615 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE stock2 ADD partenaire_id INT NOT NULL');
        $this->addSql('ALTER TABLE stock2 ADD CONSTRAINT FK_572CE90398DE13AC FOREIGN KEY (partenaire_id) REFERENCES partenaires (id)');
        $this->addSql('CREATE INDEX IDX_572CE90398DE13AC ON stock2 (partenaire_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE stock2 DROP FOREIGN KEY FK_572CE90398DE13AC');
        $this->addSql('DROP INDEX IDX_572CE90398DE13AC ON stock2');
        $this->addSql('ALTER TABLE stock2 DROP partenaire_id');
    }
}
