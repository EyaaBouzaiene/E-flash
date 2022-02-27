-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : dim. 27 fév. 2022 à 14:09
-- Version du serveur : 10.4.22-MariaDB
-- Version de PHP : 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `e-flash`
--

-- --------------------------------------------------------

--
-- Structure de la table `categories`
--

CREATE TABLE `categories` (
  `CODE` int(11) NOT NULL,
  `NOMCAT` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `categories`
--

INSERT INTO `categories` (`CODE`, `NOMCAT`) VALUES
(1, 'Alimentaire'),
(4, 'Liquide'),
(5, 'Liquide'),
(6, 'Liquide'),
(7, 'Liquide'),
(8, 'Liquide'),
(9, 'Liquide'),
(10, 'Liquide'),
(11, 'Liquide'),
(12, 'Liquide'),
(13, 'Liquide'),
(14, 'Liquide'),
(15, 'Liquide'),
(16, 'Liquide');

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE `clients` (
  `REfC` int(11) NOT NULL,
  `NOM` varchar(50) NOT NULL,
  `Prenom` varchar(50) NOT NULL,
  `QTA` int(11) NOT NULL,
  `IDP` int(11) NOT NULL,
  `DATEA` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `clients`
--

INSERT INTO `clients` (`REfC`, `NOM`, `Prenom`, `QTA`, `IDP`, `DATEA`) VALUES
(152, 'Dhia', 'Aderssa', 13, 26, '2022-02-26'),
(153, 'Dhia', 'Aderssa', 14, 26, '2022-02-27');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `idCom` int(8) NOT NULL,
  `etat` varchar(30) NOT NULL,
  `prix` int(8) NOT NULL,
  `date` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`idCom`, `etat`, `prix`, `date`) VALUES
(2, 'mayssa', 22334455, 'ff'),
(5, 'mayssa', 22334455, '22-03-2002');

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

CREATE TABLE `commentaire` (
  `id_commentaire` int(11) NOT NULL,
  `messages` text NOT NULL,
  `date_commentaire` varchar(20) NOT NULL,
  `id_publication_commentaire` int(11) NOT NULL,
  `id_client` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `commentaire`
--

INSERT INTO `commentaire` (`id_commentaire`, `messages`, `date_commentaire`, `id_publication_commentaire`, `id_client`) VALUES
(51, 'java', '2022/02/24 19:41:26', 1, 5),
(52, 'ff', '2022/02/24 20:37:00', 1, 5),
(53, '0', '2022/02/24 20:37:15', 1, 5),
(54, 'blid', '2022/02/24 20:41:07', 1, 5),
(55, 'blid', '2022/02/24 20:41:07', 1, 5),
(56, 'blid', '2022/02/24 20:41:58', 1, 5),
(57, 'aaz', '2022/02/24 20:46:42', 1, 5),
(58, 'bonjour', '2022/02/25 00:17:59', 1, 5);

-- --------------------------------------------------------

--
-- Structure de la table `dislikes`
--

CREATE TABLE `dislikes` (
  `id_dislike` int(11) NOT NULL,
  `id_publication_dislike` int(11) NOT NULL,
  `id_client_dislike` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `dislikes`
--

INSERT INTO `dislikes` (`id_dislike`, `id_publication_dislike`, `id_client_dislike`) VALUES
(15, 1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

CREATE TABLE `evenement` (
  `id` int(11) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `description` text NOT NULL,
  `duree` int(11) NOT NULL,
  `nombre_place` int(11) NOT NULL,
  `date_debut` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `evenement`
--

INSERT INTO `evenement` (`id`, `nom`, `description`, `duree`, `nombre_place`, `date_debut`) VALUES
(56, 'happy hour', 'happy hour', 5, 6, '2022-06-11'),
(57, 'happy hour', 'happy hour', 5, 6, '2022-06-11'),
(58, 'happy hour', 'happy hour', 5, 6, '2022-06-11'),
(59, 'happy hour', 'happy hour', 5, 6, '2022-06-11'),
(61, 'happy Djeja', 'happy hour', 5, 6, '2022-06-11');

-- --------------------------------------------------------

--
-- Structure de la table `likes`
--

CREATE TABLE `likes` (
  `id_like` int(11) NOT NULL,
  `id_publication_like` int(11) NOT NULL,
  `id_client_like` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `livraison`
--

CREATE TABLE `livraison` (
  `idLivraison` int(30) NOT NULL,
  `idCommande` int(30) NOT NULL,
  `idLivreur` int(30) NOT NULL,
  `prix` int(250) NOT NULL,
  `ville` varchar(80) NOT NULL,
  `etat` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `livraison`
--

INSERT INTO `livraison` (`idLivraison`, `idCommande`, `idLivreur`, `prix`, `ville`, `etat`) VALUES
(22, 456, 123, 20, 'zaghouen', 'NonValide'),
(23, 111, 222, 150, 'Tunis', 'NonValide'),
(24, 222, 333, 200, 'bizerte', 'NonValide'),
(25, 333, 444, 90, 'nebeul', 'NonValide'),
(26, 456, 123, 20, 'zaghouen', 'NonValide'),
(27, 111, 222, 150, 'Tunis', 'NonValide'),
(29, 333, 444, 90, 'nebeul', 'NonValide'),
(30, 456, 123, 20, 'zaghouen', 'NonValide'),
(31, 111, 222, 150, 'Tunis', 'NonValide'),
(32, 222, 333, 200, 'bizerte', 'NonValide'),
(33, 333, 444, 90, 'nebeul', 'NonValide'),
(34, 456, 123, 20, 'zaghouen', 'NonValide'),
(35, 111, 222, 150, 'Tunis', 'NonValide'),
(36, 222, 333, 200, 'bizerte', 'NonValide'),
(37, 333, 444, 90, 'nebeul', 'NonValide'),
(38, 456, 123, 20, 'zaghouen', 'NonValide'),
(39, 111, 222, 150, 'Tunis', 'NonValide'),
(40, 222, 333, 200, 'bizerte', 'NonValide'),
(41, 333, 444, 90, 'nebeul', 'NonValide'),
(42, 456, 123, 20, 'zaghouen', 'NonValide'),
(43, 111, 222, 150, 'Tunis', 'NonValide'),
(44, 222, 333, 200, 'bizerte', 'NonValide'),
(45, 333, 444, 90, 'nebeul', 'NonValide'),
(46, 456, 123, 20, 'zaghouen', 'NonValide'),
(47, 111, 222, 150, 'Tunis', 'NonValide'),
(48, 222, 333, 200, 'bizerte', 'NonValide'),
(49, 333, 444, 90, 'nebeul', 'NonValide');

-- --------------------------------------------------------

--
-- Structure de la table `partenaires`
--

CREATE TABLE `partenaires` (
  `idP` int(100) NOT NULL,
  `nomP` varchar(50) NOT NULL,
  `prenomP` varchar(100) NOT NULL,
  `mailP` varchar(100) NOT NULL,
  `categorieP` varchar(50) NOT NULL,
  `dateAjout` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `partenaires`
--

INSERT INTO `partenaires` (`idP`, `nomP`, `prenomP`, `mailP`, `categorieP`, `dateAjout`) VALUES
(48, 'boumendil', 'ahmed', 'flashelectro06@gmail.com', 'alimentaire', '2022-02-27'),
(50, 'benyahmed', 'ahmed', 'flashelectro06@gmail.com\r\n', 'djej', '2022-02-27'),
(52, 'm1', 'p1', 'null', 'abdelkader.bouali@gmail.com', '2022-02-26');

-- --------------------------------------------------------

--
-- Structure de la table `partenairesecours`
--

CREATE TABLE `partenairesecours` (
  `idPS` int(11) NOT NULL,
  `nomPS` varchar(50) NOT NULL,
  `prenomPS` varchar(50) NOT NULL,
  `mailPS` varchar(100) NOT NULL,
  `categoriePS` varchar(50) NOT NULL,
  `datePS` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `partenairesecours`
--

INSERT INTO `partenairesecours` (`idPS`, `nomPS`, `prenomPS`, `mailPS`, `categoriePS`, `datePS`) VALUES
(2, 'benyahmed', 'ahmed', 'flashelectro06@gmail.com\r\n', 'djej', '2022-02-07');

-- --------------------------------------------------------

--
-- Structure de la table `produits`
--

CREATE TABLE `produits` (
  `REFERENCE` int(11) NOT NULL,
  `NOM` varchar(50) NOT NULL,
  `TYPE` varchar(50) NOT NULL,
  `quantite` int(11) NOT NULL,
  `DATEP` date NOT NULL,
  `DESC` varchar(50) NOT NULL,
  `IMAGE` varchar(50) NOT NULL,
  `CATEGORIE` int(50) NOT NULL,
  `rate` int(11) NOT NULL,
  `quantiteR` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `produits`
--

INSERT INTO `produits` (`REFERENCE`, `NOM`, `TYPE`, `quantite`, `DATEP`, `DESC`, `IMAGE`, `CATEGORIE`, `rate`, `quantiteR`) VALUES
(26, 'Vitalait2', 'lait2', 13, '2022-02-25', 'Djeja1', 'hmahama', 4, 0, 13),
(27, 'Vitalait8', 'lait5', 13, '2022-02-25', 'Djeja8', 'love me ', 4, 0, 13);

-- --------------------------------------------------------

--
-- Structure de la table `publication`
--

CREATE TABLE `publication` (
  `id_publication` int(11) NOT NULL,
  `titre` varchar(20) NOT NULL,
  `description` text NOT NULL,
  `image_publication` varchar(255) NOT NULL,
  `date_publication` varchar(20) NOT NULL,
  `id_client_publication` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `publication`
--

INSERT INTO `publication` (`id_publication`, `titre`, `description`, `image_publication`, `date_publication`, `id_client_publication`) VALUES
(66, 'domingo', 'bonjour le plat est tre bons', '/c/projet.jpg', '2022/02/23 11:27:31', 1),
(67, 'domingo', 'bonjour le plat est tre bons', '/c/projet.jpg', '2022/02/23 11:27:45', 1),
(68, 'domingo', 'bonjour le plat est tre bons', '/c/projet.jpg', '2022/02/23 11:44:20', 1),
(69, 'domingo', 'bonjour le plat est tre bons', '/c/projet.jpg', '2022/02/23 11:44:34', 1),
(70, 'domingo', 'bonjour le plat est tre bons', '/c/projet.jpg', '2022/02/23 11:46:10', 1),
(71, 'domingo', 'bonjour le plat est tre bons', '/c/projet.jpg', '2022/02/23 11:46:16', 1),
(72, 'domingo', 'bonjour le plat est tre bons', '/c/projet.jpg', '2022/02/23 11:46:23', 1),
(73, 'domingo', 'bonjour le plat est tre bons', '/c/projet.jpg', '2022/02/23 11:47:12', 1),
(74, 'domingo', 'bonjour le plat est tre bons', '/c/projet.jpg', '2022/02/23 11:47:23', 1),
(75, 'domingo', 'bonjour le plat est tre bons', '/c/projet.jpg', '2022/02/23 11:47:33', 1),
(76, 'domingo', 'bonjour le plat est tre bons', '/c/projet.jpg', '2022/02/24 19:47:45', 1),
(77, 'domingo', 'bonjour le plat est tre bons', '/c/projet.jpg', '2022/02/24 19:49:12', 3),
(78, 'domingo', 'bonjour le plat est tre bons', '/c/projet.jpg', '2022/02/24 19:50:28', 3),
(79, 'domingo', 'bonjour le plat est tre bons', '/c/projet.jpg', '2022/02/24 19:50:59', 3),
(80, 'domingo', 'bonjour le plat est tre bons', '/c/projet.jpg', '2022/02/24 19:55:15', 3),
(81, 'domingo', 'bonjour le plat est tre bons', '/c/projet.jpg', '2022/02/24 19:58:20', 3),
(82, 'Dimanche', 'bonjour le plat est tre bons', '/c/projet.jpg', '2022/02/24 19:58:36', 3),
(83, 'domingo', 'bonjour le plat est tre bons', '/c/projet.jpg', '2022/02/25 00:13:12', 3);

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `id_reservation` int(11) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `gmail` varchar(30) NOT NULL,
  `nombre_billet` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id_reservation`, `nom`, `prenom`, `gmail`, `nombre_billet`) VALUES
(1, 'nom', 'prenom', 'gmail', 7),
(2, 'nom', 'prenom', 'gmail', 7),
(3, 'nom', 'prenom', 'gmail', 7),
(6, 'bbbb5', 'prenom', 'gmail', 7),
(7, 'bbbb5', 'prenom', 'gmail', 7);

-- --------------------------------------------------------

--
-- Structure de la table `stock`
--

CREATE TABLE `stock` (
  `idS` int(50) NOT NULL,
  `Partenaire` int(11) NOT NULL,
  `nomS` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `refS` int(50) NOT NULL,
  `categorieS` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `qteS` int(11) NOT NULL,
  `dateS` date NOT NULL,
  `qualiteS` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `stock`
--

INSERT INTO `stock` (`idS`, `Partenaire`, `nomS`, `refS`, `categorieS`, `qteS`, `dateS`, `qualiteS`) VALUES
(107, 48, 'lait', 5, 'alimentaire', 10, '2022-02-23', 2),
(108, 48, 'tables', 123, 'equipement', 5, '2022-02-23', 5),
(109, 48, 'chaise', 8, 'djej', 8, '2022-02-07', 1);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(8) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(30) NOT NULL,
  `telf` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `email`, `password`, `role`, `telf`) VALUES
(67, 'mayssa', 'bouzid', 'ddd.bb@esprit.tn', '5fdcf829d818c8d33dbb3cbf7d271e6', 'admin', 22334455),
(68, 'hadil', 'hhd', 'hadil.khemissi@esprit.tn', 'b886f22d38891533fc529de65ac28e9e', 'client', 112),
(69, 'nour', 'hedg', 'nour.bouzid@esprit.tn', 'b820168e4e43a69d315d3fce1c7122b', 'admin', 22334455),
(71, 'nour', 'hedg', 'nour.bouzid@esprit.tn', 'b820168e4e43a69d315d3fce1c7122b', 'admin', 22334455);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`CODE`);

--
-- Index pour la table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`REfC`),
  ADD KEY `IDP` (`IDP`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`idCom`);

--
-- Index pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD PRIMARY KEY (`id_commentaire`);

--
-- Index pour la table `dislikes`
--
ALTER TABLE `dislikes`
  ADD PRIMARY KEY (`id_dislike`);

--
-- Index pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `likes`
--
ALTER TABLE `likes`
  ADD PRIMARY KEY (`id_like`);

--
-- Index pour la table `livraison`
--
ALTER TABLE `livraison`
  ADD PRIMARY KEY (`idLivraison`);

--
-- Index pour la table `partenaires`
--
ALTER TABLE `partenaires`
  ADD PRIMARY KEY (`idP`);

--
-- Index pour la table `partenairesecours`
--
ALTER TABLE `partenairesecours`
  ADD PRIMARY KEY (`idPS`);

--
-- Index pour la table `produits`
--
ALTER TABLE `produits`
  ADD PRIMARY KEY (`REFERENCE`),
  ADD KEY `CATEGORIE` (`CATEGORIE`);

--
-- Index pour la table `publication`
--
ALTER TABLE `publication`
  ADD PRIMARY KEY (`id_publication`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id_reservation`);

--
-- Index pour la table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`idS`),
  ADD KEY `idPartenaire` (`Partenaire`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `categories`
--
ALTER TABLE `categories`
  MODIFY `CODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `clients`
--
ALTER TABLE `clients`
  MODIFY `REfC` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=154;

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `idCom` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `commentaire`
--
ALTER TABLE `commentaire`
  MODIFY `id_commentaire` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- AUTO_INCREMENT pour la table `dislikes`
--
ALTER TABLE `dislikes`
  MODIFY `id_dislike` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT pour la table `likes`
--
ALTER TABLE `likes`
  MODIFY `id_like` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT pour la table `livraison`
--
ALTER TABLE `livraison`
  MODIFY `idLivraison` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT pour la table `partenaires`
--
ALTER TABLE `partenaires`
  MODIFY `idP` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT pour la table `partenairesecours`
--
ALTER TABLE `partenairesecours`
  MODIFY `idPS` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `produits`
--
ALTER TABLE `produits`
  MODIFY `REFERENCE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT pour la table `publication`
--
ALTER TABLE `publication`
  MODIFY `id_publication` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=84;

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id_reservation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `stock`
--
ALTER TABLE `stock`
  MODIFY `idS` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=113;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `clients`
--
ALTER TABLE `clients`
  ADD CONSTRAINT `Fk_clients` FOREIGN KEY (`IDP`) REFERENCES `produits` (`REFERENCE`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `produits`
--
ALTER TABLE `produits`
  ADD CONSTRAINT `Fk_Produits` FOREIGN KEY (`CATEGORIE`) REFERENCES `categories` (`CODE`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `stock`
--
ALTER TABLE `stock`
  ADD CONSTRAINT `PK1_stock` FOREIGN KEY (`Partenaire`) REFERENCES `partenaires` (`idP`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
