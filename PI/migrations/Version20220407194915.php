<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220407194915 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE produits DROP FOREIGN KEY Fk_Produits');
        $this->addSql('ALTER TABLE commande DROP FOREIGN KEY Fk_client22');
        $this->addSql('ALTER TABLE livraison DROP FOREIGN KEY fk_commande');
        $this->addSql('ALTER TABLE rating_event DROP FOREIGN KEY linkevent');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY fk2');
        $this->addSql('ALTER TABLE clients DROP FOREIGN KEY Fk_clients');
        $this->addSql('ALTER TABLE commande DROP FOREIGN KEY FK_Product');
        $this->addSql('ALTER TABLE commentaire DROP FOREIGN KEY fk_publication_commentaire');
        $this->addSql('ALTER TABLE dislikes DROP FOREIGN KEY fk_publication_dislike');
        $this->addSql('ALTER TABLE likes DROP FOREIGN KEY fk_publication_like');
        $this->addSql('ALTER TABLE commentaire DROP FOREIGN KEY fk_client_commentaire');
        $this->addSql('ALTER TABLE dislikes DROP FOREIGN KEY fk_client_dislike');
        $this->addSql('ALTER TABLE likes DROP FOREIGN KEY fk_client_like');
        $this->addSql('ALTER TABLE livraison DROP FOREIGN KEY fk_livreur');
        $this->addSql('ALTER TABLE publication DROP FOREIGN KEY fk_client_publication');
        $this->addSql('ALTER TABLE rating DROP FOREIGN KEY fk_liv');
        $this->addSql('CREATE TABLE produit2 (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(255) NOT NULL, type VARCHAR(255) NOT NULL, quantite VARCHAR(255) NOT NULL, datep DATE NOT NULL, descp VARCHAR(255) NOT NULL, image VARCHAR(255) NOT NULL, rate INT NOT NULL, quantite_r INT NOT NULL, prix VARCHAR(255) NOT NULL, etat VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('DROP TABLE categories');
        $this->addSql('DROP TABLE clients');
        $this->addSql('DROP TABLE commande');
        $this->addSql('DROP TABLE commentaire');
        $this->addSql('DROP TABLE dislikes');
        $this->addSql('DROP TABLE evenement');
        $this->addSql('DROP TABLE likes');
        $this->addSql('DROP TABLE livraison');
        $this->addSql('DROP TABLE partenaires');
        $this->addSql('DROP TABLE partenairesecours');
        $this->addSql('DROP TABLE produits');
        $this->addSql('DROP TABLE publication');
        $this->addSql('DROP TABLE rating');
        $this->addSql('DROP TABLE rating_event');
        $this->addSql('DROP TABLE reservation');
        $this->addSql('DROP TABLE stock');
        $this->addSql('DROP TABLE user');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE categories (CODE INT AUTO_INCREMENT NOT NULL, NOMCAT VARCHAR(50) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, PRIMARY KEY(CODE)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE clients (REfC INT AUTO_INCREMENT NOT NULL, NOM VARCHAR(50) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, Prenom VARCHAR(50) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, QTA INT NOT NULL, IDP INT NOT NULL, DATEA DATE NOT NULL, COUPON LONGTEXT CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, INDEX IDP (IDP), PRIMARY KEY(REfC)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE commande (idCommande INT AUTO_INCREMENT NOT NULL, prix VARCHAR(8) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, date DATE NOT NULL, refcmd INT NOT NULL, REFERENCEP INT NOT NULL, IDClient INT NOT NULL, quantiteA INT NOT NULL, total DOUBLE PRECISION NOT NULL, INDEX REFERENCEP (REFERENCEP), INDEX IDClient (IDClient), PRIMARY KEY(idCommande)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE commentaire (id_commentaire INT AUTO_INCREMENT NOT NULL, id_publication_commentaire INT NOT NULL, id_client INT NOT NULL, messages TEXT CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, date_commentaire VARCHAR(20) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, INDEX fk_publication_commentaire (id_publication_commentaire), INDEX fk_client_commentaire (id_client), PRIMARY KEY(id_commentaire)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE dislikes (id_dislike INT AUTO_INCREMENT NOT NULL, id_publication_dislike INT NOT NULL, id_client_dislike INT NOT NULL, INDEX fk_publication_dislike (id_publication_dislike), INDEX fk_client_dislike (id_client_dislike), PRIMARY KEY(id_dislike)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE evenement (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(20) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, description TEXT CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, duree VARCHAR(11) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, nombre_place VARCHAR(20) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, date_debut DATE NOT NULL, image LONGBLOB NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET latin1 COLLATE `latin1_swedish_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE likes (id_like INT AUTO_INCREMENT NOT NULL, id_publication_like INT NOT NULL, id_client_like INT NOT NULL, INDEX fk_publication_like (id_publication_like), INDEX fk_client_like (id_client_like), PRIMARY KEY(id_like)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE livraison (idLivraison INT AUTO_INCREMENT NOT NULL, idCommande INT NOT NULL, idLivreur INT NOT NULL, prix INT NOT NULL, ville VARCHAR(80) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, etat VARCHAR(80) CHARACTER SET utf8mb4 DEFAULT \'En cours\' NOT NULL COLLATE `utf8mb4_general_ci`, INDEX fk_commande (idCommande), INDEX fk_livreur (idLivreur), PRIMARY KEY(idLivraison)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE partenaires (idP INT AUTO_INCREMENT NOT NULL, MatriculeP INT NOT NULL, nomMarqueP VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, nomP VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, prenomP VARCHAR(100) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, mailP VARCHAR(100) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, categorieP VARCHAR(50) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, dateAjout DATE NOT NULL, INDEX nomMarqueP (nomMarqueP), PRIMARY KEY(idP)) DEFAULT CHARACTER SET latin1 COLLATE `latin1_swedish_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE partenairesecours (idPS INT AUTO_INCREMENT NOT NULL, MatriculePS INT NOT NULL, nomMarquePS VARCHAR(255) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, nomPS VARCHAR(50) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, prenomPS VARCHAR(50) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, mailPS VARCHAR(100) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, categoriePS VARCHAR(50) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, datePS DATE NOT NULL, PRIMARY KEY(idPS)) DEFAULT CHARACTER SET latin1 COLLATE `latin1_swedish_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE produits (REFERENCE INT AUTO_INCREMENT NOT NULL, NOM VARCHAR(50) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, TYPE VARCHAR(50) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, quantite INT NOT NULL, DATEP DATE NOT NULL, `DESC` VARCHAR(50) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, IMAGE LONGBLOB NOT NULL, CATEGORIE INT NOT NULL, rate INT NOT NULL, quantiteR INT NOT NULL, Prix VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, etat VARCHAR(100) CHARACTER SET utf8mb4 DEFAULT \'achete\' NOT NULL COLLATE `utf8mb4_general_ci`, INDEX CATEGORIE (CATEGORIE), PRIMARY KEY(REFERENCE)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE publication (id_publication INT AUTO_INCREMENT NOT NULL, id_client_publication INT NOT NULL, titre VARCHAR(20) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, description TEXT CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, image_publication BLOB NOT NULL, date_publication VARCHAR(20) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, INDEX fk_client_publication (id_client_publication), PRIMARY KEY(id_publication)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE rating (idl INT AUTO_INCREMENT NOT NULL, idLivreur INT NOT NULL, rat INT DEFAULT NULL, INDEX fk_liv (idLivreur), PRIMARY KEY(idl)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE rating_event (id_rate INT AUTO_INCREMENT NOT NULL, id_event INT NOT NULL, rate INT NOT NULL, INDEX id_event (id_event), PRIMARY KEY(id_rate)) DEFAULT CHARACTER SET latin1 COLLATE `latin1_swedish_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE reservation (id_reservation INT AUTO_INCREMENT NOT NULL, id_event INT NOT NULL, gmail VARCHAR(30) CHARACTER SET latin1 NOT NULL COLLATE `latin1_swedish_ci`, nombre_billet INT NOT NULL, INDEX id_event (id_event), PRIMARY KEY(id_reservation)) DEFAULT CHARACTER SET latin1 COLLATE `latin1_swedish_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE stock (idS INT AUTO_INCREMENT NOT NULL, nomPartenaireS VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, nomS VARCHAR(50) CHARACTER SET utf8 NOT NULL COLLATE `utf8_bin`, refS VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, categorieS VARCHAR(50) CHARACTER SET utf8 NOT NULL COLLATE `utf8_bin`, qteS INT NOT NULL, dateS DATE NOT NULL, qualiteS VARCHAR(50) CHARACTER SET latin1 DEFAULT \'1.0\' NOT NULL COLLATE `latin1_swedish_ci`, INDEX nomPartenaireS (nomPartenaireS), PRIMARY KEY(idS)) DEFAULT CHARACTER SET latin1 COLLATE `latin1_swedish_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE user (id INT NOT NULL, nom VARCHAR(30) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, prenom VARCHAR(30) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, email VARCHAR(30) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, password VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, role VARCHAR(30) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, telf INT NOT NULL, code VARCHAR(20) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_general_ci`, date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('ALTER TABLE clients ADD CONSTRAINT Fk_clients FOREIGN KEY (IDP) REFERENCES produits (REFERENCE) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE commande ADD CONSTRAINT Fk_client22 FOREIGN KEY (IDClient) REFERENCES clients (REfC) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE commande ADD CONSTRAINT FK_Product FOREIGN KEY (REFERENCEP) REFERENCES produits (REFERENCE) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE commentaire ADD CONSTRAINT fk_publication_commentaire FOREIGN KEY (id_publication_commentaire) REFERENCES publication (id_publication) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE commentaire ADD CONSTRAINT fk_client_commentaire FOREIGN KEY (id_client) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE dislikes ADD CONSTRAINT fk_publication_dislike FOREIGN KEY (id_publication_dislike) REFERENCES publication (id_publication) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE dislikes ADD CONSTRAINT fk_client_dislike FOREIGN KEY (id_client_dislike) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE likes ADD CONSTRAINT fk_publication_like FOREIGN KEY (id_publication_like) REFERENCES publication (id_publication) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE likes ADD CONSTRAINT fk_client_like FOREIGN KEY (id_client_like) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE livraison ADD CONSTRAINT fk_livreur FOREIGN KEY (idLivreur) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE livraison ADD CONSTRAINT fk_commande FOREIGN KEY (idCommande) REFERENCES commande (idCommande) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE produits ADD CONSTRAINT Fk_Produits FOREIGN KEY (CATEGORIE) REFERENCES categories (CODE) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE publication ADD CONSTRAINT fk_client_publication FOREIGN KEY (id_client_publication) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE rating ADD CONSTRAINT fk_liv FOREIGN KEY (idLivreur) REFERENCES user (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE rating_event ADD CONSTRAINT linkevent FOREIGN KEY (id_event) REFERENCES evenement (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT fk2 FOREIGN KEY (id_event) REFERENCES evenement (id) ON UPDATE NO ACTION ON DELETE CASCADE');
        $this->addSql('DROP TABLE produit2');
    }
}
