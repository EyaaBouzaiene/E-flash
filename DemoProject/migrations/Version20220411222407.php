<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220411222407 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE commentaire DROP FOREIGN KEY fk_client_commentaire');
        $this->addSql('ALTER TABLE dislikes DROP FOREIGN KEY fk_client_dislike');
        $this->addSql('ALTER TABLE likes DROP FOREIGN KEY fk_client_like');
        $this->addSql('ALTER TABLE publication DROP FOREIGN KEY fk_client_publication');
        $this->addSql('ALTER TABLE rating DROP FOREIGN KEY fk_liv');
        $this->addSql('CREATE TABLE categorie2 (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE produit2 (id INT AUTO_INCREMENT NOT NULL, categorie_id INT NOT NULL, nom VARCHAR(255) NOT NULL, type VARCHAR(255) NOT NULL, datep DATE NOT NULL, descp VARCHAR(255) NOT NULL, image VARCHAR(255) NOT NULL, rate INT NOT NULL, quantite_r INT NOT NULL, prix VARCHAR(255) NOT NULL, etat VARCHAR(255) NOT NULL, quantite INT NOT NULL, INDEX IDX_BFF6AE8ABCF5E72D (categorie_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE user (id INT AUTO_INCREMENT NOT NULL, email VARCHAR(180) NOT NULL, roles LONGTEXT NOT NULL COMMENT \'(DC2Type:json)\', password VARCHAR(255) NOT NULL, nom VARCHAR(255) NOT NULL, prenom VARCHAR(255) NOT NULL, date DATETIME NOT NULL, telf INT NOT NULL, UNIQUE INDEX UNIQ_8D93D649E7927C74 (email), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE produit2 ADD CONSTRAINT FK_BFF6AE8ABCF5E72D FOREIGN KEY (categorie_id) REFERENCES categorie2 (id)');
        $this->addSql('DROP TABLE user1');
        $this->addSql('ALTER TABLE clients DROP FOREIGN KEY Fk_clients');
        $this->addSql('ALTER TABLE clients CHANGE IDP IDP INT DEFAULT NULL');
        $this->addSql('ALTER TABLE clients ADD CONSTRAINT FK_C82E747F74B4A8 FOREIGN KEY (IDP) REFERENCES produits (REFERENCE)');
        $this->addSql('ALTER TABLE commande DROP FOREIGN KEY Fk_client');
        $this->addSql('ALTER TABLE commande DROP FOREIGN KEY FK_Product');
        $this->addSql('ALTER TABLE commande CHANGE REFERENCEP REFERENCEP INT DEFAULT NULL, CHANGE IDClient IDClient INT DEFAULT NULL');
        $this->addSql('ALTER TABLE commande ADD CONSTRAINT FK_6EEAA67D929EF705 FOREIGN KEY (IDClient) REFERENCES clients (REfC)');
        $this->addSql('ALTER TABLE commande ADD CONSTRAINT FK_6EEAA67DD886119 FOREIGN KEY (REFERENCEP) REFERENCES produits (REFERENCE)');
        $this->addSql('ALTER TABLE commentaire DROP FOREIGN KEY fk_publication_commentaire');
        $this->addSql('ALTER TABLE commentaire CHANGE id_publication_commentaire id_publication_commentaire INT DEFAULT NULL, CHANGE id_client id_client INT DEFAULT NULL');
        $this->addSql('ALTER TABLE commentaire ADD CONSTRAINT FK_67F068BCDEF81A47 FOREIGN KEY (id_publication_commentaire) REFERENCES publication (id_publication)');
        $this->addSql('ALTER TABLE commentaire ADD CONSTRAINT FK_67F068BCE173B1B8 FOREIGN KEY (id_client) REFERENCES user (id)');
        $this->addSql('ALTER TABLE dislikes DROP FOREIGN KEY fk_publication_dislike');
        $this->addSql('ALTER TABLE dislikes CHANGE id_publication_dislike id_publication_dislike INT DEFAULT NULL, CHANGE id_client_dislike id_client_dislike INT DEFAULT NULL');
        $this->addSql('ALTER TABLE dislikes ADD CONSTRAINT FK_2DF3BE11B058A65D FOREIGN KEY (id_publication_dislike) REFERENCES publication (id_publication)');
        $this->addSql('ALTER TABLE dislikes ADD CONSTRAINT FK_2DF3BE11916015D1 FOREIGN KEY (id_client_dislike) REFERENCES user (id)');
        $this->addSql('ALTER TABLE likes DROP FOREIGN KEY fk_publication_like');
        $this->addSql('ALTER TABLE likes CHANGE id_publication_like id_publication_like INT DEFAULT NULL, CHANGE id_client_like id_client_like INT DEFAULT NULL');
        $this->addSql('ALTER TABLE likes ADD CONSTRAINT FK_49CA4E7D441194AF FOREIGN KEY (id_publication_like) REFERENCES publication (id_publication)');
        $this->addSql('ALTER TABLE likes ADD CONSTRAINT FK_49CA4E7D65ED4E26 FOREIGN KEY (id_client_like) REFERENCES user (id)');
        $this->addSql('ALTER TABLE partenaires CHANGE nomMarqueP nomMarqueP VARCHAR(255) NOT NULL, CHANGE prenomP prenomP VARCHAR(100) NOT NULL, CHANGE mailP mailP VARCHAR(100) NOT NULL, CHANGE categorieP categorieP VARCHAR(50) NOT NULL');
        $this->addSql('ALTER TABLE produits DROP FOREIGN KEY Fk_Produits');
        $this->addSql('ALTER TABLE produits CHANGE CATEGORIE CATEGORIE INT DEFAULT NULL');
        $this->addSql('ALTER TABLE produits ADD CONSTRAINT FK_BE2DDF8CDBFC8DB FOREIGN KEY (CATEGORIE) REFERENCES categories (CODE)');
        $this->addSql('ALTER TABLE publication CHANGE id_client_publication id_client_publication INT DEFAULT NULL');
        $this->addSql('ALTER TABLE publication ADD CONSTRAINT FK_AF3C67792D55F0ED FOREIGN KEY (id_client_publication) REFERENCES user (id)');
        $this->addSql('ALTER TABLE rating CHANGE idLivreur idLivreur INT DEFAULT NULL');
        $this->addSql('ALTER TABLE rating ADD CONSTRAINT FK_D8892622FBC3259F FOREIGN KEY (idLivreur) REFERENCES user (id)');
        $this->addSql('ALTER TABLE rating_event CHANGE id_event id_event INT DEFAULT NULL');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY fk2');
        $this->addSql('ALTER TABLE reservation CHANGE id_event id_event INT DEFAULT NULL');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C84955D52B4B97 FOREIGN KEY (id_event) REFERENCES evenement (id)');
        $this->addSql('ALTER TABLE stock DROP FOREIGN KEY stock_ibfk_1');
        $this->addSql('ALTER TABLE stock CHANGE nomPartenaireS nomPartenaireS VARCHAR(255) DEFAULT NULL, CHANGE nomS nomS VARCHAR(50) NOT NULL, CHANGE refS refS VARCHAR(255) NOT NULL, CHANGE categorieS categorieS VARCHAR(50) NOT NULL');
        $this->addSql('ALTER TABLE stock ADD CONSTRAINT FK_4B365660DC6CF3E FOREIGN KEY (nomPartenaireS) REFERENCES partenaires (nomMarqueP)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE produit2 DROP FOREIGN KEY FK_BFF6AE8ABCF5E72D');
        $this->addSql('ALTER TABLE commentaire DROP FOREIGN KEY FK_67F068BCE173B1B8');
        $this->addSql('ALTER TABLE dislikes DROP FOREIGN KEY FK_2DF3BE11916015D1');
        $this->addSql('ALTER TABLE likes DROP FOREIGN KEY FK_49CA4E7D65ED4E26');
        $this->addSql('ALTER TABLE publication DROP FOREIGN KEY FK_AF3C67792D55F0ED');
        $this->addSql('ALTER TABLE rating DROP FOREIGN KEY FK_D8892622FBC3259F');
        $this->addSql('CREATE TABLE user1 (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(30) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, prenom VARCHAR(30) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, email VARCHAR(30) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, password VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, role LONGTEXT CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci` COMMENT \'DC2Type:json\', telf INT NOT NULL, code VARCHAR(20) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_general_ci`, date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL, image VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('DROP TABLE categorie2');
        $this->addSql('DROP TABLE produit2');
        $this->addSql('DROP TABLE user');
        $this->addSql('ALTER TABLE clients DROP FOREIGN KEY FK_C82E747F74B4A8');
        $this->addSql('ALTER TABLE clients CHANGE IDP IDP INT NOT NULL');
        $this->addSql('ALTER TABLE clients ADD CONSTRAINT Fk_clients FOREIGN KEY (IDP) REFERENCES produits (REFERENCE) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE commande DROP FOREIGN KEY FK_6EEAA67D929EF705');
        $this->addSql('ALTER TABLE commande DROP FOREIGN KEY FK_6EEAA67DD886119');
        $this->addSql('ALTER TABLE commande CHANGE IDClient IDClient INT NOT NULL, CHANGE REFERENCEP REFERENCEP INT NOT NULL');
        $this->addSql('ALTER TABLE commande ADD CONSTRAINT Fk_client FOREIGN KEY (IDClient) REFERENCES clients (REfC) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE commande ADD CONSTRAINT FK_Product FOREIGN KEY (REFERENCEP) REFERENCES produits (REFERENCE) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE commentaire DROP FOREIGN KEY FK_67F068BCDEF81A47');
        $this->addSql('ALTER TABLE commentaire CHANGE id_publication_commentaire id_publication_commentaire INT NOT NULL, CHANGE id_client id_client INT NOT NULL');
        $this->addSql('ALTER TABLE commentaire ADD CONSTRAINT fk_publication_commentaire FOREIGN KEY (id_publication_commentaire) REFERENCES publication (id_publication) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE commentaire ADD CONSTRAINT fk_client_commentaire FOREIGN KEY (id_client) REFERENCES user1 (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE dislikes DROP FOREIGN KEY FK_2DF3BE11B058A65D');
        $this->addSql('ALTER TABLE dislikes CHANGE id_publication_dislike id_publication_dislike INT NOT NULL, CHANGE id_client_dislike id_client_dislike INT NOT NULL');
        $this->addSql('ALTER TABLE dislikes ADD CONSTRAINT fk_publication_dislike FOREIGN KEY (id_publication_dislike) REFERENCES publication (id_publication) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE dislikes ADD CONSTRAINT fk_client_dislike FOREIGN KEY (id_client_dislike) REFERENCES user1 (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE likes DROP FOREIGN KEY FK_49CA4E7D441194AF');
        $this->addSql('ALTER TABLE likes CHANGE id_publication_like id_publication_like INT NOT NULL, CHANGE id_client_like id_client_like INT NOT NULL');
        $this->addSql('ALTER TABLE likes ADD CONSTRAINT fk_publication_like FOREIGN KEY (id_publication_like) REFERENCES publication (id_publication) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE likes ADD CONSTRAINT fk_client_like FOREIGN KEY (id_client_like) REFERENCES user1 (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE partenaires CHANGE nomMarqueP nomMarqueP VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, CHANGE prenomP prenomP VARCHAR(100) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, CHANGE mailP mailP VARCHAR(100) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, CHANGE categorieP categorieP VARCHAR(50) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`');
        $this->addSql('ALTER TABLE produits DROP FOREIGN KEY FK_BE2DDF8CDBFC8DB');
        $this->addSql('ALTER TABLE produits CHANGE CATEGORIE CATEGORIE INT NOT NULL');
        $this->addSql('ALTER TABLE produits ADD CONSTRAINT Fk_Produits FOREIGN KEY (CATEGORIE) REFERENCES categories (CODE) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE publication CHANGE id_client_publication id_client_publication INT NOT NULL');
        $this->addSql('ALTER TABLE publication ADD CONSTRAINT fk_client_publication FOREIGN KEY (id_client_publication) REFERENCES user1 (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE rating CHANGE idLivreur idLivreur INT NOT NULL');
        $this->addSql('ALTER TABLE rating ADD CONSTRAINT fk_liv FOREIGN KEY (idLivreur) REFERENCES user1 (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('ALTER TABLE rating_event CHANGE id_event id_event INT NOT NULL');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C84955D52B4B97');
        $this->addSql('ALTER TABLE reservation CHANGE id_event id_event INT NOT NULL');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT fk2 FOREIGN KEY (id_event) REFERENCES evenement (id) ON UPDATE NO ACTION ON DELETE CASCADE');
        $this->addSql('ALTER TABLE stock DROP FOREIGN KEY FK_4B365660DC6CF3E');
        $this->addSql('ALTER TABLE stock CHANGE nomS nomS VARCHAR(50) CHARACTER SET utf8 NOT NULL COLLATE `utf8_bin`, CHANGE refS refS VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`, CHANGE categorieS categorieS VARCHAR(50) CHARACTER SET utf8 NOT NULL COLLATE `utf8_bin`, CHANGE nomPartenaireS nomPartenaireS VARCHAR(255) CHARACTER SET utf8 NOT NULL COLLATE `utf8_general_ci`');
        $this->addSql('ALTER TABLE stock ADD CONSTRAINT stock_ibfk_1 FOREIGN KEY (nomPartenaireS) REFERENCES partenaires (nomMarqueP) ON UPDATE CASCADE ON DELETE CASCADE');
    }
}
