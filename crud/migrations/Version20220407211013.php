<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220407211013 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE clients DROP FOREIGN KEY Fk_clients');
        $this->addSql('ALTER TABLE clients CHANGE IDP IDP INT DEFAULT NULL');
        $this->addSql('ALTER TABLE clients ADD CONSTRAINT FK_C82E747F74B4A8 FOREIGN KEY (IDP) REFERENCES produits (REFERENCE)');
        $this->addSql('ALTER TABLE livraison DROP FOREIGN KEY fk_livreur');
        $this->addSql('ALTER TABLE livraison DROP FOREIGN KEY fk_commande');
        $this->addSql('ALTER TABLE livraison CHANGE idCommande idCommande INT DEFAULT NULL, CHANGE idLivreur idLivreur INT DEFAULT NULL');
        $this->addSql('ALTER TABLE livraison ADD CONSTRAINT FK_A60C9F1FFBC3259F FOREIGN KEY (idLivreur) REFERENCES user (id)');
        $this->addSql('ALTER TABLE livraison ADD CONSTRAINT FK_A60C9F1F3D498C26 FOREIGN KEY (idCommande) REFERENCES commande (idCommande)');
        $this->addSql('ALTER TABLE produits DROP FOREIGN KEY Fk_Produits');
        $this->addSql('ALTER TABLE produits CHANGE CATEGORIE CATEGORIE INT DEFAULT NULL');
        $this->addSql('ALTER TABLE produits ADD CONSTRAINT FK_BE2DDF8CDBFC8DB FOREIGN KEY (CATEGORIE) REFERENCES categories (CODE)');
        $this->addSql('ALTER TABLE rating DROP FOREIGN KEY fkrate');
        $this->addSql('ALTER TABLE rating CHANGE idLivreur idLivreur INT DEFAULT NULL');
        $this->addSql('ALTER TABLE rating ADD CONSTRAINT FK_D8892622FBC3259F FOREIGN KEY (idLivreur) REFERENCES user (id)');
        $this->addSql('ALTER TABLE stock DROP FOREIGN KEY PK1_stock');
        $this->addSql('ALTER TABLE stock CHANGE Partenaire Partenaire INT DEFAULT NULL, CHANGE nomS nomS VARCHAR(50) NOT NULL, CHANGE categorieS categorieS VARCHAR(50) NOT NULL');
        $this->addSql('ALTER TABLE stock ADD CONSTRAINT FK_4B3656607DA2A0A3 FOREIGN KEY (Partenaire) REFERENCES partenaires (idP)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE clients DROP FOREIGN KEY FK_C82E747F74B4A8');
        $this->addSql('ALTER TABLE clients CHANGE IDP IDP INT NOT NULL');
        $this->addSql('ALTER TABLE clients ADD CONSTRAINT Fk_clients FOREIGN KEY (IDP) REFERENCES produits (REFERENCE) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE livraison DROP FOREIGN KEY FK_A60C9F1FFBC3259F');
        $this->addSql('ALTER TABLE livraison DROP FOREIGN KEY FK_A60C9F1F3D498C26');
        $this->addSql('ALTER TABLE livraison CHANGE idLivreur idLivreur INT NOT NULL, CHANGE idCommande idCommande INT NOT NULL');
        $this->addSql('ALTER TABLE livraison ADD CONSTRAINT fk_livreur FOREIGN KEY (idLivreur) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE livraison ADD CONSTRAINT fk_commande FOREIGN KEY (idCommande) REFERENCES commande (idCommande) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE produits DROP FOREIGN KEY FK_BE2DDF8CDBFC8DB');
        $this->addSql('ALTER TABLE produits CHANGE CATEGORIE CATEGORIE INT NOT NULL');
        $this->addSql('ALTER TABLE produits ADD CONSTRAINT Fk_Produits FOREIGN KEY (CATEGORIE) REFERENCES categories (CODE) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE rating DROP FOREIGN KEY FK_D8892622FBC3259F');
        $this->addSql('ALTER TABLE rating CHANGE idLivreur idLivreur INT NOT NULL');
        $this->addSql('ALTER TABLE rating ADD CONSTRAINT fkrate FOREIGN KEY (idLivreur) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE stock DROP FOREIGN KEY FK_4B3656607DA2A0A3');
        $this->addSql('ALTER TABLE stock CHANGE nomS nomS VARCHAR(50) CHARACTER SET utf8 NOT NULL COLLATE `utf8_bin`, CHANGE categorieS categorieS VARCHAR(50) CHARACTER SET utf8 NOT NULL COLLATE `utf8_bin`, CHANGE Partenaire Partenaire INT NOT NULL');
        $this->addSql('ALTER TABLE stock ADD CONSTRAINT PK1_stock FOREIGN KEY (Partenaire) REFERENCES partenaires (idP) ON UPDATE CASCADE ON DELETE CASCADE');
    }
}
