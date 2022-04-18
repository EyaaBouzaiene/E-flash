<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220412101351 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE stock DROP FOREIGN KEY stock_ibfk_1');
        $this->addSql('DROP TABLE partenaires');
        $this->addSql('DROP TABLE partenairesecours');
        $this->addSql('DROP TABLE stock');
        $this->addSql('ALTER TABLE produits DROP FOREIGN KEY Fk_Produits');
        $this->addSql('ALTER TABLE produits CHANGE CATEGORIE CATEGORIE INT DEFAULT NULL');
        $this->addSql('ALTER TABLE produits ADD CONSTRAINT FK_BE2DDF8CDBFC8DB FOREIGN KEY (CATEGORIE) REFERENCES categories (CODE)');
        $this->addSql('ALTER TABLE rating DROP FOREIGN KEY fk_liv');
        $this->addSql('ALTER TABLE rating CHANGE idLivreur idLivreur INT DEFAULT NULL');
        $this->addSql('ALTER TABLE rating ADD CONSTRAINT FK_D8892622FBC3259F FOREIGN KEY (idLivreur) REFERENCES user (id)');
        $this->addSql('ALTER TABLE rating_event CHANGE id_event id_event INT DEFAULT NULL');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY fk2');
        $this->addSql('ALTER TABLE reservation CHANGE id_event id_event INT DEFAULT NULL');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C84955D52B4B97 FOREIGN KEY (id_event) REFERENCES evenement (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE partenaires (idP INT AUTO_INCREMENT NOT NULL, MatriculeP INT NOT NULL, nomMarqueP VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, nomP VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, prenomP VARCHAR(100) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, mailP VARCHAR(100) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, categorieP VARCHAR(50) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, dateAjout DATE NOT NULL, INDEX nomMarqueP (nomMarqueP), PRIMARY KEY(idP)) DEFAULT CHARACTER SET latin1 COLLATE `latin1_swedish_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE partenairesecours (idPS INT AUTO_INCREMENT NOT NULL, MatriculePS INT NOT NULL, nomMarquePS VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, nomPS VARCHAR(50) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, prenomPS VARCHAR(50) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, mailPS VARCHAR(100) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, categoriePS VARCHAR(50) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, datePS DATE NOT NULL, PRIMARY KEY(idPS)) DEFAULT CHARACTER SET latin1 COLLATE `latin1_swedish_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE stock (idS INT AUTO_INCREMENT NOT NULL, nomPartenaireS VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, nomS VARCHAR(50) CHARACTER SET utf8 NOT NULL COLLATE `utf8_bin`, refS VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, categorieS VARCHAR(50) CHARACTER SET utf8 NOT NULL COLLATE `utf8_bin`, qteS INT NOT NULL, dateS DATE NOT NULL, qualiteS VARCHAR(50) CHARACTER SET latin1 DEFAULT \'1.0\' NOT NULL COLLATE `latin1_swedish_ci`, INDEX nomPartenaireS (nomPartenaireS), PRIMARY KEY(idS)) DEFAULT CHARACTER SET latin1 COLLATE `latin1_swedish_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('ALTER TABLE stock ADD CONSTRAINT stock_ibfk_1 FOREIGN KEY (nomPartenaireS) REFERENCES partenaires (nomMarqueP) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE produits DROP FOREIGN KEY FK_BE2DDF8CDBFC8DB');
        $this->addSql('ALTER TABLE produits CHANGE CATEGORIE CATEGORIE INT NOT NULL');
        $this->addSql('ALTER TABLE produits ADD CONSTRAINT Fk_Produits FOREIGN KEY (CATEGORIE) REFERENCES categories (CODE) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE rating DROP FOREIGN KEY FK_D8892622FBC3259F');
        $this->addSql('ALTER TABLE rating CHANGE idLivreur idLivreur INT NOT NULL');
        $this->addSql('ALTER TABLE rating ADD CONSTRAINT fk_liv FOREIGN KEY (idLivreur) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE rating_event CHANGE id_event id_event INT NOT NULL');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C84955D52B4B97');
        $this->addSql('ALTER TABLE reservation CHANGE id_event id_event INT NOT NULL');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT fk2 FOREIGN KEY (id_event) REFERENCES evenement (id) ON UPDATE NO ACTION ON DELETE CASCADE');
    }
}
