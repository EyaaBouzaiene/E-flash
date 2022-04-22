<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220417171904 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE cart_details ADD carts_id INT NOT NULL');
        $this->addSql('ALTER TABLE cart_details ADD CONSTRAINT FK_89FCC38DBCB5C6F5 FOREIGN KEY (carts_id) REFERENCES cart (id)');
        $this->addSql('CREATE INDEX IDX_89FCC38DBCB5C6F5 ON cart_details (carts_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE cart_details DROP FOREIGN KEY FK_89FCC38DBCB5C6F5');
        $this->addSql('DROP INDEX IDX_89FCC38DBCB5C6F5 ON cart_details');
        $this->addSql('ALTER TABLE cart_details DROP carts_id');
    }
}
