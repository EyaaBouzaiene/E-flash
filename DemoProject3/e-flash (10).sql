-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 04 mai 2022 à 19:41
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
-- Structure de la table `address`
--

CREATE TABLE `address` (
  `id` int(11) NOT NULL,
  `full_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `societe_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ville` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `postal` int(11) NOT NULL,
  `pays` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address_complement` longtext COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `address`
--

INSERT INTO `address` (`id`, `full_name`, `societe_name`, `address`, `phone`, `ville`, `postal`, `pays`, `address_complement`) VALUES
(1, 'Aderssa Dhia', 'HORIZON', 'rue 11 Raouad 2008', '24376729', 'Tunis', 8002, 'Tunisia', 'adqdsdsdscscq');

-- --------------------------------------------------------

--
-- Structure de la table `cart`
--

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `reference` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fullname` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `transport_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `transport_price` double NOT NULL,
  `livraison_adresse` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_paid` tinyint(1) NOT NULL,
  `more_informations` longtext COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `quantity` int(11) NOT NULL,
  `sub_total_ht` double NOT NULL,
  `taxe` double NOT NULL,
  `sub_total_ttc` double NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `cart`
--

INSERT INTO `cart` (`id`, `reference`, `fullname`, `transport_name`, `transport_price`, `livraison_adresse`, `is_paid`, `more_informations`, `created_at`, `quantity`, `sub_total_ht`, `taxe`, `sub_total_ttc`, `user_id`) VALUES
(51, 'EA445169-5435-23B1-6A6C-56ECAF1FD5B2', 'Dhia aderssa ', 'Ahmed', 2, 'Dhia aderssa ', 0, 'Dhia aderssa ', '2022-04-29 11:03:35', 1, 16, 3.2, 19.2, 107),
(52, 'A8EB724F-B7E0-5B49-1723-37E779EB8E2E', 'Dhia aderssa ', 'Ahmed', 2, 'Dhia aderssa ', 0, 'Dhia aderssa ', '2022-05-02 01:30:20', 3, 22.3, 4.46, 26.76, 107),
(53, '1FEBC0D0-1292-F51D-D00F-FA89F391B008', 'Dhia aderssa ', 'Ahmed', 2, 'Dhia aderssa ', 0, 'Dhia aderssa ', '2022-05-02 02:40:24', 1, 12.3, 2.46, 14.76, 107),
(54, '5AAE1E0B-6BBE-BAA9-826A-A954C9C13188', 'Dhia aderssa ', 'Ahmed', 2, 'Dhia aderssa ', 0, 'Dhia aderssa ', '2022-05-03 03:02:32', 2, 24.6, 4.92, 29.52, 107);

-- --------------------------------------------------------

--
-- Structure de la table `cart_details`
--

CREATE TABLE `cart_details` (
  `id` int(11) NOT NULL,
  `product_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `product_price` double NOT NULL,
  `product_quantity` int(11) NOT NULL,
  `subtotal_ht` double NOT NULL,
  `carts_id` int(11) NOT NULL,
  `taxe` double NOT NULL,
  `sub_total_ttc` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `cart_details`
--

INSERT INTO `cart_details` (`id`, `product_name`, `product_price`, `product_quantity`, `subtotal_ht`, `carts_id`, `taxe`, `sub_total_ttc`) VALUES
(76, 'Baby', 0.16, 1, 0.16, 51, 0, 0),
(77, 'Dhiaaaaa', 0.123, 1, 0.123, 52, 0, 0),
(78, 'Youssef', 0.05, 2, 0.1, 52, 0, 0),
(79, 'Dhiaaaaa', 0.123, 1, 0.123, 53, 0, 0),
(80, 'Dhiaaaaa', 0.123, 2, 0.246, 54, 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `categorie2`
--

CREATE TABLE `categorie2` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `categorie2`
--

INSERT INTO `categorie2` (`id`, `nom`) VALUES
(1, 'Alimentaire'),
(2, 'LIQUIDE'),
(3, 'Lait'),
(4, 'JUICE'),
(5, 'JOLIE'),
(6, 'YOYO');

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
(17, 'LOVE'),
(18, 'ABC'),
(20, 'Dhia'),
(22, 'Dhia'),
(23, 'Khaddouja');

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
(154, 'Dhia', 'Aderssa', 0, 90, '2022-03-01'),
(155, 'Dhia', 'Aderssa', 3, 89, '2022-04-26');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `idCommande` int(11) NOT NULL,
  `prix` varchar(8) NOT NULL,
  `date` date NOT NULL,
  `refcmd` int(10) NOT NULL,
  `REFERENCEP` int(11) NOT NULL,
  `IDClient` int(11) NOT NULL,
  `quantiteA` int(100) NOT NULL,
  `total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`idCommande`, `prix`, `date`, `refcmd`, `REFERENCEP`, `IDClient`, `quantiteA`, `total`) VALUES
(32, '25.0', '2022-03-09', 1234, 94, 154, 4, 100),
(33, '25.0', '2022-03-09', 1234, 94, 154, 4, 100),
(35, '50.0', '2022-03-09', 1234, 93, 154, 3, 150),
(36, '25.0', '2022-03-09', 1234, 94, 154, 3, 75),
(37, '50.0', '2022-03-09', 1234, 93, 154, 3, 150),
(38, '42.0', '2022-03-09', 1234, 91, 154, 10, 420),
(39, '24.0', '2022-03-09', 1234, 88, 154, 10, 240);

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

-- --------------------------------------------------------

--
-- Structure de la table `dislikes`
--

CREATE TABLE `dislikes` (
  `id_dislike` int(11) NOT NULL,
  `id_publication_dislike` int(11) NOT NULL,
  `id_client_dislike` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `doctrine_migration_versions`
--

CREATE TABLE `doctrine_migration_versions` (
  `version` varchar(191) COLLATE utf8_unicode_ci NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `execution_time` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

CREATE TABLE `evenement` (
  `id` int(11) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `description` text NOT NULL,
  `duree` varchar(11) NOT NULL,
  `nombre_place` varchar(20) NOT NULL,
  `date_debut` date NOT NULL,
  `image` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `evenement`
--

INSERT INTO `evenement` (`id`, `nom`, `description`, `duree`, `nombre_place`, `date_debut`, `image`) VALUES
(1, 'evenement', 'Christmas is celebrated on December 25 and is both a sacred religious holiday and a worldwide cultural and commercial phenomenon', '2h', '50', '2022-03-18', 0xffd8ffe000104a46494600010100000100010000ffdb00840009060713121215121312151615171716171515171515151515151516171615151515181d2820181a251b151521312125292b2e2e2e171f3338332c37282d2e2b010a0a0a0e0d0e1a10101a2d251f1f2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2d2dffc000110800a6013003012200021101031101ffc4001c0000020203010100000000000000000000050603040002070108ffc4003f1000010401030303020307020404070000010002031104051221063141135161227107328114235291a1b1c142d11516b2f04362829217243353637273ffc400190100030101010000000000000000000000020304010005ffc4003111000202010302040503040301000000000102001103122131044113225171618191c1f01432a142b1d1e10523f152ffda000c03010002110311003f0019d4387b12f6fa09ef5683d68038770122cc2b85ebe5505432f0778c20862a7b487d54ebd09d5cec276d75ba171fa9be5a7f899f3f1e52348a6c59579ee2a755cfa47275581f88f9c48d3118ddf503ee08af8778aef6be77d55ef73b736ec7208ee08e415bb667005a1c689b22cd5fbd7babfa7d16f2a5c8da45c662c566a00d4b5c9a5686bbe903f36db6977dff00d933e6ead1458c7d0735a5c008dad0dbddd858f0479b40f3f02cd8559da6b88fcaa774c4c17b0078fcfce7d63c66cb8cb5ee48abf4f697f54e9392289d31943dc06e90511f2e21d7cff9543a6b5918cf7173496bc006aac51b045fdca259f9194e83d279b6d004d7d440ec1c7ca5691b48f086cb8d932906fd3d3f3e917932a6370f84115ebebf5ffd8cd075146735b3bd94c0c2c06adcdbecf207dc8e3c152eababe24b9b8ce753a2613eab8b4d107f28208b2d0793f72a1774dc5fb37a9bddbf66fdd636f6baaaede104ff0081cc62f5b67d35bbb8ddb7df6fb25aaf4ec752922869e6b9b8fc8dd485d2ca0df9b8be275d8e5d35eeaffe51d26de3888feefefd921e9987852ea533406987930b7ff0dcefa7757b8077d0ed5f649941652ec7ff001e718601cee2bdbf98a6eb03b03a06c6ff00368ddab69988dd463886d6c440f51a0fd21ff5536fc5d378f9f95175de9b8f0ba3f480638dee6b79fa78a7578f3f74ad4bc29e9d3b2b21d64e915eff009f3e0406ea159597401a8dfb7c3f2a744eabe9cc28708cb1001c033d37ee24c8491c1e68d8b3c765e74be85832617a92069753bd47971063209e073c50a3f2b9e971a009343b0b343ec3c2c4bfd264f0f41ca6eeefedcffaf856d0ff00558c64d7e18aaaafbf1fefe37bc73e81c6c47b25f5446e78771ea57ff4eb8203be6eff00454f40761b7517eedbe85bc445fcb03ac6d26f8afcc013f0960b56261e9ad9c963e7155e9ed007534a8348f29bf7fcff0011b3ab3271066c4f89ac731a5a660c0363a9d742b826bbfe88875f750e2e440c8e276f7ee0e07691b1a01b1640e4dd52420b65cbd1a838c92494e37e7de73756c438a14f1d22eb56370841e93bd411fa5e3d3adbb771e6fb7354aa749f5bbf061743e9091a49732dc5bb49ef7c1b1c5ff34acb2910e8b0852b5b13660b757958824f02a15c2ea3c98b224c963c09242e2fe016bb71ba2d3e01ecab6a5a8c99123a595e5cf779f603b003c01eca9af42a571a06d400be2fe1e9105d88a27692be573a8b9ce75703712687b0bf0b6685a3548d54a0022c9b93c115a37a6e2979da10689f499ba47928b2d85b8cc64711874fd31ac1d95e2c561ad5e16af34927732b1b485ada20fb105756d3670f8dae1ec172c7845f49d79f0b76f70b01a81917547ad433d90b7738fd8792b97eb326e7bde7bbdc5df6bec3f9223aa6b9bcee79edd824bd6759b3f4f285816d8405017993cf2008466ea61be5577ba57fc2d5ba68eeee7eeb0201cc6162788dfd39901cd2c3f64b3d4383e9c878e3c2b7a2646c7846baa70fd48c3c2f7716f8ca1edbc0ca2c2b8f63f69cee55135d4ad4cca2a26c37ca9b2545d19bc191cd2318afe103f4a8f753c9945a143952c6d1b8db49de34f4ee94fca9db137b9e493d9ad1ddc574ec7e848d82b76e3ef55fd1732fc39ea866264ef9810c7b4b1ce02cb6c821d5e458feabb9e9da8c590cf52191af6fbb4ff423b83f751bf48990535dfc0cec990dedc4e6dd41a2884edae0f65ccfa874ba75b477f65dd7f10a463206b9d57be87b91449ff0917489a091db64da6fc3bb7c28135f48e472043c78bc51395493481be9973837f86cedfe48ab3a91e20f4b60dc1bb03efc5576f7a5d1357e8ec6c836db611c532aafc120f94afab7e1a64442e37b6537f947d0403e6c9afbab13ace9b356bd8f3bfafb88c387a8c27ca7915f2f63f69ae3ea78630030ecb11ed31d7d664ae48f92ee772a5374cc2317d50f76f11efdd7f49357555dbc7ba0d9fa1e442e2d7c2f142c903736bdf736c2a2721e5bb37bb67f0ee3b7f9764d4e9eb7c4fc9b3dfe5f9cce6ea41db3631b0a1dbf3ed0965f4ccd1c3ea92de05b9a09dcd1fd8a1f369d2b1824746e0d3d9c471cf6fb22195d4333e2f48edaa00babea701e0f857f56ea76cb018c46439c007135b45104edf7ec8d5ba85a0ca0d9edd84029d33025588a1f53158aca4eba0e56237176c9e9eefab78701b9c6cd5793c5552ae74bc6fd977f00edbde49bddeddfdf8a5bfaa01882a79af79c3a32ca0ab03b5fb4525949a99d371bcb18d7969710371360fbd0f7aba56f52e870c7303253b4deedc06e15fc3557dff45dfadc3744ff00130f439bd2fe7eb12a965229ae69271de1bbb7022c1aa3dea8856f03a62496212073413f95a41e7dacf8b4e39f1840e4ec62474f90b940371002f404cd1f47bc91fbd6d7fabe93f48f817cf35ecaee3f43124dbdce6f82d6807e775dff0044b3d6601cb468e8b39fe9fe47f989802dc04eb9bd08e6445cd24b877e3b8be4d57b7cadb4bd1a06006400f3f507d7e9fd170ebb169d4bbc21d0e5d54687ce25b4299b094d5af08780cda4fc781ff75c215b3e13f1f53ac58151793a6d0d57707b6229aba4184128346cf84c5d3ce00a764cc4a5405c42eee36ad4aa736a0d6f0abbf25ceeca5084c7132f4d30085cf96e71a6adc6393cb8ad450ec8a8099b994f231dc7bb95730b5aaccf31f743a4969633133820961cfe380a9ceff72ab666a1438427272c90a7de19a10e46eda4274c293d484b7e1299d2e51f99ae691e0820ff002298ba4f1e599e6389bb881679a0076e4af5f1e7506ef88217ca41895aac05af23e55100809e7aa74292396a4655f9ee3f9a0d268dc70979b2283cc4aa9222dc777ca97242233e90f1c8e551c985dec54cc419a01136d1e01239c0f8175ee9934dd55d86efddc8e69abe0f3f63ee9471f7b5d6382a7d8e73839dcfba9b22163b9da350802ab785f5cea59f25d7248e7570dbec07c01c231d170c72bea4f6f296666b7845b49c6b01ec753bdc293aa50719036b95600434ef583898ed6b446c8eab8a0db3f7f72a2d62063617ba8035c57167c0fd4ae1395ae4ed3b5ee254ba5f524b158e5cdbb0d71340fb8f658599b19071af1f9dbef16305383acfe7ce3fcb348185d263bc06f60da7970ef74d28564e1e1ca5ac92365f263616ec3c771e3c1edfecb687f10e3229f1385fe62288e3c01c2232f5760ca28c8d04d1a7b4b687916455fd979be1ba1bd247b4b86527620180a4e82c29012ddcd2ee3e87fd3191c703b7cd1413ff00872d756cc822bf317341ff00da011fdd38cb8589231de91635ae20feeced0e07b9706917faa250e86c145b23c50afce4823e41b17f28d7abcca3673f3dff00bdc16c5879651bfe7c27379ff0ce6165b3348f00821c7fc043b23a0b21a5acb6b9ce276817b78ef6e3db8e7b2ec0dd2650da13127bee21b7b7f868003b716a93f4a9ccf1bc48035a1c4b281dd628dbbfb576f94f5ff90ce3961f4ff513fa7e9cdedfc9fb99cc9fd0d98d7346e3f4f20eefca476d9f577567fe059ee903df2485cd0430fd20007bee6f61fe57539b0dee239a1dc7bdf9bf7f0bd8302c925c7ea347b71e6c7080f5f90f35f48c5c3840bdfea6727d4fa4726405efdce900f7606d0f01be07cab7d31a36636e37b47a601d96e692493d9a6fe4aeaeed3180f249afa7bf71fa7952478d8f1d7e5005d127b1fd561eb5ca68355edc7b40ff00a83eb4d57f0efefeb3996b5a06587b1ec958c60ef5cfd7cf04572291ae93d3270fdb2c9bdc08276b76b68d501ee3f453f55f54c2cb8d8e6976de5a0120b8dd591c0f74a5a1f536543336571dedece6d0048f83ee3c2d5f13263a2057b6e7bf2634b816dbea3daf61f2e04ed6748696d1e521f59b31636491b832f63a99c17efe7691e41bf28f693f8838f339b16c943dc4000b46d24fcdf08575874c517e517597b86e691daf815f1d939b1632cba49f8fdbda438b23a921fbed39262477f995d01b4a5d531b693484190f6b5e9a9bde01f2ed2dcf20f0ac74fbcb9c42140f28ff004ae9ef7b890c711e4804d7dd50582a1b8a165a1d8f1da0f2ac39e0765624d2a4efb4d7d94874727c9e0291ba851de58984982df3aa13487d9173a3baff0030ec48148465be912e40dc4d38f48de0ac87b8945fa4f1c4d23a321a5ddfeaed5e50e6c5b81da2ca39f8778b5397bf83db93c52f63a6c25183922e8edf29e3f5b9af1b28bedbfce2ef57e81263c8450da796904d01edc84af23483457d23aee0e3491132061e3824d2e01d5103192b847f97c51b0a5cb8832f8aa28771f1f87c3fb4dc5d46affadb73ea3d3ec6768eb3c88dd6e690681e7dd2e7e14eaed8f26463cd7a8d001f969bff00297e5d5dce616b8fba0f16506381079f0a0c0a55587733d328a57e13bbf594b19c67024171adbdaed737d33177cf1c6e7535ce009f604a1b8da9be43cb89e3ded45a9ea25a3bf3e0a26423dcc5a528ab9d7737a3719cca6b4b48afaac9bf7bb51c7d0987b69cd71bf3b885cb7a77aef2fd6632598be31e0ff4b3e5746ff9b8b402288f20ff0082a7c8e11fcc36afcf498ab90af95a2c755f43b31b6bd84b9ae245572df3cd774aa74c7134d69bfb2eb191aa7ed0d04b680ec3efe6d069a6640f0f73780478539cd64e8e25684e9f38de234bd1d94fa0d824b3dbe92853629f0e431cac734f90e041e57d05a76af0cacdcd78af3e125f5bc1fb5ca3d285f208dbb4bdac710493756078ff00298d91426ec0df68a46667fdb55dff00369cb653eabb752ea9d39d1d82fc2648f6ee73985cf93711b1de455d0aec925b1322243dbb48ee08208fe6a0c9cc0e05ac7900f7009a3f70b316617c588ccb8c91cd19475bc76b5f4c42350c491addf5c268d0b45134ad639c69c7bae9593f87903e32ddee048af0423d6757905fac0c8cbfd5b5ce1185a83d80ed357dfb1fee88e1753e5c5f95c481efbbfdd1ecae97106e63a8ed2458f34848c31edc26a7859413a6e258e4c754d0845f88f94000580ffea70b4f5d0593959c0cf2b7d2c70683aedd2381a219c70d1d89fd079ae6f068febcb1c4de0c8f632fdb7380bfd2ed771cedb8f132089b4d8d9f4b45f668a6f0dfabbf900fdb9443a4c07fa07f300f53978bfe04b52e7c718fa40edc38f6268d5b87cd7f35a7fc641b05b639ec5aee3eaaf3dc80de3ff384a9959e03cd3b96f340dc84308fe0702fb18afef19f3ec42a064d86de7f2f7dc1bd99b7711ba361b2315e6eff00d44f6aaad5001424cc617eade977648f5b1269229d81c047bcfa4ffe26d1e18781cf6e3f51c3f50c89b7b9b2ba4dc096bdae26ec1a21c3dc15dcb45d5435c1a4b7d8d18ffd21bb8f129e2df575df83dc1506bbf8790e6e6fed2e79646f8c191acdbb9d2b4edb0e3600dbb6fbf6f959a117b0fa4ef11cec49face2b11aa4d782c05838477ab3f0f23c62c742f79638d6d7d17348e7b802c7e8abe2e9243405e4f56e01aef3d1e9c58b90697b593c4f7701af6927e0105746eb0d5a138fb03c38b8b6803e01b24a4b8b437bdcd636adc4017db9573a8ba624c58849b9af04869a04104f6efdc70b3a7b60483b779d982ea5be6558fa2b232d9ea37631a7f2ef24177da8149474373257c728a731c5a47b10bae69bd750b20635f1c9bdad0ddad68a25a2b837c2e73ace63a596499c3697b8babdacf6572b00a34c9c6a2de6843a0b4189f9477804345807b5aecf044c8db4d01a3f40be7fd073e78e6b85a5dee0027fb276d3b5e9725e6291a5b43b7fba43bba396a076f5e3ef1be16b037af97e09d0353c88c33921236a9a8068e141d418e63a7071a15c5a5cd5b551f48f9495bc8c0d731f8d06353bcf737579038b4102fcd734509c9693dcaf24cadef0b7ca7d2b8792a854254f10124dc9743a0e23ba61d300b73281738f03ded237ed858edc131e85ab8f51af27b385fd951892c96ef5b092e6214010ecff87334cd34e636fdc9e126f53f40656137d4780f8ff8da6f6fff00b03d97d0b8af0e6023b52a9d410b5f8d2b1d5458e1cfd947e609af55f7fceffcc5f896da4813e667e59eeaa7af6564afe1418af1b85f6548337513b461e9b98fa94999ba636577d548268637c8046db3e6bc26fcfd39f1c65fd886dff452f5197cdcc245f2c5ad6f486442db57f08afe1f4b1bb263131b6dd53bb5d1db77e2e92b6767bde68956ba5b218dc98bd5fc9ea3777daffb5d26052528fe7bc592036d3bfeb01ad889a1c557f35cfbaaa7b6a7dd6a78840e739cdda077b1fd172bd4a72f6f1ca97a8423206ed1dd3302847c651c1d5a461dacec9f7a77ad71a381ac9de6391b608dae21dc93b8100f75cc7d6d8fe556d425ddca05251f52ca4e219169a317e20eb10e53cba21c10003544d7949304463e4fba9a194d5d134b49a5dd56285a2452a089cda4d576142753e97d3a17e3325b3b882770711b083f1ed4a377e244b107c6636bcb490d93711fa915cff35ce5b98f6376b5ce0d3dc07100fdc28726624500958f0bab13ab98390061444edbd2da8624f886494c45c7719b7edbb249f3cd555529a67e9ffb092df4b67a676deddfbeb81efbeffee971fc1c3708b7ee3645d78fb15560d646de55675a8fda389295563c99dbba2f3f18e3b1ac746d78e1edb68717df7e7937c2a5d4797f557bd12d344d0238db4ee49da2f61b1e6d71fc4d48b9d6db146c1fed49df55d4ce442d98035c7aece0ed7d105db0f05aebdc0fbf1dc04fe99afcadcc1c98ab70799e7adb8fa6e376288bb009d8d7bbd2dc68974f27fe101c761c5c62400723d32eafff0001b96ac3496c24baf29fc77bbff55843d99f62afe6ac8a3f591b58e0f63a9ce01b5dc827c29465b5829b4c1cd37ea6021b7cfee9c58f1f436c817e2bc2b24f509e1673af70739c0d11b5cf736dce7bdc46d99fe0b6c78ee38e0741d0f203dad24907eaa06ecb781c8773dc11cf90572264cec89044c166c5b9fb48680037717800d017cba9c3b7369a7ac4430c50b2393f78c69692c751dbdcd969e09759fd4fc29fa9c81136e6c434c459aa17ebc9dbeac4c0fb2012e65f6edb491e2f9fe4844190d61b776f7f00a4fc0d4980f279279b3cabb9dacb0b2ad7899c364c964733d3c2342d7a476d33a8b146e2658dae69bb7103ff00693dff00449dd47d7afca798801e935e76d020bab80e75fc78f94b4f89a584dfb9ee81634944fdd51d3e251a8293ed01f621986e6388d41be552d5b34114d412794d2d6396d578b1fac5e661c2c74fc31c8324eec70de5c376ff000037bdff0034ebae68af82413ee6969a61a1441e689f75ce3a2b2dd16431ec76d75d1f620f7053ef516a924be9ef700d0efc8050268f3efc7f95166f0c673ffd3557de391731c60d8d239f5947a9326d87ec83f456990e54f2099bbf635a5acb22ec9b3c117543f9a9ba825fa0fd921e56496beda483ee0907f9854e14b5201a8acad4234f5b60418d99e9c07e9d8d2e65eed8e376db3cf600d1f742a77da0f8d2173ecd9450b939bcb40c6f4cde432a4d15a9b09a77b5ad3c9e164c4570557c1ca2d78701641ec99898ded13d5013a362f52e5e046194d91be0389b03e0af59d51959ced84318d35f4b6ffa9f293b52cfc89aad8036a801cad74ed4e6c7707edb0a1643a4ff009dbe9c4e5abba898f7f0bdc08fd4786dd5df2bcc90a1c398b1c1c3b856ef5b49c7c674de83c42c94b402e045d8ff002ba3752060c67df14c3fd922fe12f5142d13099ec6bec104d36db5d85fcdff003433f107aa9d2cef8e075c540123b13e68af374b3672088f2d682a2d64ce2d5512f363bada6702df9f65981867b957dd2c52e32cd0ce6f506449106b8f1c5d5f348f68b96d31db8a57c870adaa3c5cb318a2784838f52cb852b4b9a9ea0d74bf0abe565823843cd48fb5bcd15708c22ad080723907d23162cb1b20e6ac0e7e4a138f4f6a862929b44f854dd3169b0562a6e675e9dcf1259642d348d69f3c623faaafcfdd2e6364ef95a0f647f58c6608c16d5a632d44abea248e24126b3c166e3b6fb7c2059f9167e954a47a80bca685a9333d98ebd3ec66d6dd7cfbda6d76508da0b7835dfe3e7dc2e5583a9b98426076aef95b4d052fc220dc60ca08a10b666b11b0fef2120ff144e001ff00d0ee01fb1038ed487cfd4b01b02298fc12c8c1f6b20b8959a2e14990ff004cb6cfdacfe8abc3d2d23bd72d07f74f2d3fa7bfb2a86524402ac00f8cd5fad17b4378632ef636c035db71eee3f7fe88c43197c7dca4f6c45aea70aa3ca77c19d9b0341f8a5365df78ec2fd8c5b9a370900168de0e95bc6e289cb82c0ddc863f566b5ae684b53a8d473a85172867462cb1a7b78427d120a2bd3b80fc891c476bee9ce1e8a6916ee4fba2fda6a05eb5062663c1bc52b0fd2760b47e3e9c744eabb0b356c32c6dae175b432a393176194466d6cdd5a47bc1b26bb5927f92bacd27d5209ec8d62682c6f3c2dd00ee441d7daf6956494bd9f57b253d4f1e9eba13e18eab8493d45096bfe9e479429e56a109c294de51c070dcae09039c5a833eda6d4dc81b82732d9b89c6e40a9732d85a681eea3c71b1c0bbdd41064389e795ae4e4171f6a5c97750b328d01c1f946a97566500296b8d98d79da7ca5e89b62e9645210eb52320172ac69e2015cc052496a20bc69b211418cda05564d4f3545cf74ad2dd2f63499b1f48dadaee7dd00c3d53d13411566b87b9094e1c98d428a3e32866b3d2772a48336f8f0a0d4727d4365533296f608c26dbce1968ec7686e668addfd157ca8f7b50dff88baaa8a8999efec014211a1b64c664da602d92bc221a9b688a51e870173b91cda6b6685bc5941932aab6f198901c75eb16e3d21f20b0ea4332b11cd7ec251ed5b25f8d6d6a5d196e7b8bdc998c92b710ea0369331b17a6f08be412f6f07c25fca9adcb78b2dc3c94656e898bd6012071237c14ea5bfecb6bd164d956e00b09373828d3f190c380ba1f4be8d196026ad2b42ce132f4be410eabe12cb131a881778c9064374edd94181db5a78fba44e98eb391b91900d16e548e73c7b175f23fb269ebbcd8db86f6927d47968601dbbd927f45cbf4dc863722274ad0181e0bf6f7dbdaff00caab0da8d8d5fe6f13d4306ab175bfd25bea7987aeedbd941a5e510e06fb225ac62c6f95ee8cdb0fe527b9084359b094b2b5e5f941d567546a9b5a1b36da12ec3eef3e4a092e43aeed171a96e600842508c390b73da3ef4be4451b40156539b24b16b9874dc5b9ed71e293d646a4194db58ea473188e099bea5900774b3ac6a4dd8794773f00cadb0ea5534ee8f8e4907a8491ed7c1428c282c6bb5026224bac3c346cfd517c6ea0b8f93cd233d67d2d0e3961886d06f737fb149b9908029a8cb51a8a4425754278d33a4b76e5ec8e69bdddd45a5405ace4aa790ee4f3e54ee379ebf4987c45b6971da2b646ee1dd50769effca02c8b5a2c3b519c7cd1dcd23b70244f8d4e460247a1e8d40990205ae4404db5a99a5d44ed240afb2536cbba705dee51e23bd9936522b4c63c5c36b62f940f22100a3f92ce057601276ad94779083c224dc68ea422d095351803790a5c471200b5572a5b14b78a4aa4dada4562f696e6828daf4c8b774fc72a9bdfca25bef35801c495f2ade01b8aa8e2ade9b2d1a288ec20a0d4d44c398f8adaedcabd89a7b0725a83bb3c46e055f3afb4800049a2656cc00d264f147b64b038b4d5a7e66f6d04bda7c2e9b91c0565c1f8bf5770b0e0c6e6da679c0bada1bff97e19afd56d937e6a925eada0b22ddb7b59afb22d2750870dc45203a9eafeaf0d5c1086a1c4c671a6cf316b262d8e56218772d336324dab38fc04e692a893b3194f1c0a363cab31da419481b4b31b556c8ce7c26daad46876aa2dc02e4e6664e26997aa4b92591bbb9700dfb9e02af9ba448c71ddc51a3fcd58822db3c047ff00719ff5046baa0fd6f3ff009bfcaa928ac495dacfaca58c380aa6a507957e1702d0abe449e14ea7784ca2a019235630f822d4f3454568426c58855daa9880dbc2d9dae3dc2c9e50395f74a57b6c05ad9588a30c28bb11823eb099a368e422fa5f52c8f17d8fdd25b5a896202c09631a98672b2f318b3f5192675127ef76a7874a15db95474bc373a9c0fe8986290edec8360480652a6c03074785e106d6f1432c84d2ec803c7ea826a8d121a4b0a6e53e330fda6a28e9d06f90f1d91c66238bbe07753e3c6c89e057752e7e496dd7909fb73da4809dfd66fa86a10b19b2c5d76f293dd903d407c2af9ed20971364aaad3698b8d46e24b9323134633656b236d34f84b3292e7f28c37006db3dd509b1761b4b1b43656e4cac314956a2c477b2f718907957e1c9af09a05c47136c5d30bfbad9da0bafbab18ba85144e49896db5032b03b711e871b0de037e82e03baacdc02c364a223507eea2154d52727cae1676304851b894f2a3dc510d2b4af52ad09648533f4f63bcd1ed6b320d221610723f1193031fd368016d910fa9c1eca69318edeeb5862da3948533d365f2d403ac60b5919a4a30380b4ff9f007b1cb9ee48dae70f94fc5b4f3fa84a3277496b78883c21ec9558c77d14c6f3093aec6138a15618c5ac520a5efa8a522563896814325612fb44e081cfecbd7e9ce5ca0898dbc1ee3fbe87ffe8cff00a8233d44cfaa4bf7557134f3eb467d9edfee11aea6c5bba1cdaa717ec3f08b6fee62ae138852cacbe56e71cb7c52d5c0a9c9370c26d2b3b9e1579594ad6ce57b247613018965833d2e558dab6311057a422220835352682f7f6c24515a3c70a2b0bb89a37e65dc5d6648fe96bb84c5a6751df0ee0fbf8497bb9521240b088630dbf799e215dbb47acdd4d805a06dd6c07f212e3b29c7bac8c940b88036239ba92cba6364babc6ee7caada8e7b4b38fd12e3e5a45742844a79ec13021a8af137da55834b967e4f01412e27a520695d0a38c35b40242d689f5d1e9a100f366196869010bd4dc3c785ac539b0d288bb19b5cf953d5732c2dad7683847f505929daff82a0d3326c9055b9a2dc6d306c64d5a86d3d202bf85250a5519170a485fcd2658332883349a4e4d2aa632f1654d39d86cad5d9e36d2150269f89900c61c271d3de238c24964e49e133be53e8f3ecb3a840408fe89f4b18c58da9348eea3ced4dad69e5224796e1dad6ce91eee392a6d3b4f4752931af17337b1c525e7c25d2968ee4a67846c8bf441b4ba39009f74d423891754a6c5cabff2fcd56184fd82ace85cce1c28afa23a7b0a3310fa4764abf885d30c2c2f6340239e153e1923690b503539a611b0ae4715a1fa6f7a299b0312c1f95194d4db4a95c049268ce1548e930b62739f7ea34820782df3fe504d331b638df92bcd7d8e7001879ff0ab5017989d5770d43d53a7c40b833ebddc0af1e50d8fa9a19652d6b09df757c0095f374091ad73f83c5d0eea9e884faeca1e794f1948374225c9650a6346b2c1d8217b295bcf0e74bf0a09d87b2f3326cd2d5dc485f1a8aa948015abad7033185cc7b41559cd538e0ac94268893b4153b8a85c55c95b6a131a2812a3472adb1c36f2abb072bc982256a3308da47338570b48e55a3d6a0a3812d318094d3d36d6b41a4b188cb45f4fc831715c2c077de108ce724514939eeb9894625cdb068a5e964b93f55acd348044bad8ede0fb2b59591e02d1cf0d6fcaacc8cb812904d9b940b51439816294b4d84734c949eebd588f27115d3fee84267552ab38a21c17ab162f31afde52d4e42e168635cbd58992568674cc7045a6e83003e2e4ac5880cbb028951d86c678b5a7aad1fe95e2c52997dd712bea59d6ca0290ed085ccdfbaf56262487392584edbd3b9043404675380491107d97ab17a188f121cc2703cac1d996f603c077f74dd89100d0178b162a80c7e732cd4b50400f297b59244ad00f16bc588320da35398774efccdffbf0879c76364716b403cf6fbac589a00804f97e728ccffa8a80bd62c5e53732eec269eafc28e47fc2f1621138c8242a3057ab13d24f92579946f2bc5899132a30f2b495cb162eef3bb4ad33785180b16238265889d42d3069843dbc85eac5ab37bca3a8425b745060efa962c5c66420d05de55e69dad58b10002538c9dccfffd9);

-- --------------------------------------------------------

--
-- Structure de la table `favoris`
--

CREATE TABLE `favoris` (
  `id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `IdProduits` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
  `idLivraison` int(11) NOT NULL,
  `idCommande` int(30) NOT NULL,
  `idLivreur` int(30) NOT NULL,
  `prix` int(250) NOT NULL,
  `ville` varchar(80) NOT NULL,
  `etat` varchar(80) NOT NULL DEFAULT 'En cours'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `livraison`
--

INSERT INTO `livraison` (`idLivraison`, `idCommande`, `idLivreur`, `prix`, `ville`, `etat`) VALUES
(84, 10, 73, 50, 'Nebeul', 'En cours'),
(85, 9, 72, 20, 'Ariana', 'validé'),
(86, 9, 72, 20, 'Ariana', 'validé'),
(89, 9, 73, 20, 'Bizerte', 'En cours'),
(90, 38, 73, 42, 'Tunis', 'en Cours');

-- --------------------------------------------------------

--
-- Structure de la table `partenaires`
--

CREATE TABLE `partenaires` (
  `idP` int(11) NOT NULL,
  `MatriculeP` int(11) NOT NULL,
  `nomMarqueP` varchar(255) NOT NULL,
  `nomP` varchar(255) NOT NULL,
  `prenomP` varchar(100) NOT NULL,
  `mailP` varchar(100) NOT NULL,
  `categorieP` varchar(50) NOT NULL,
  `dateAjout` date NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `partenairesecours`
--

CREATE TABLE `partenairesecours` (
  `idPS` int(11) NOT NULL,
  `MatriculePS` int(11) NOT NULL,
  `nomMarquePS` varchar(255) NOT NULL,
  `nomPS` varchar(50) NOT NULL,
  `prenomPS` varchar(50) NOT NULL,
  `mailPS` varchar(100) NOT NULL,
  `categoriePS` varchar(50) NOT NULL,
  `datePS` date NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `partenairesecours`
--

INSERT INTO `partenairesecours` (`idPS`, `MatriculePS`, `nomMarquePS`, `nomPS`, `prenomPS`, `mailPS`, `categoriePS`, `datePS`, `image`) VALUES
(7, 5400, 'Dhia', 'Aderssa', 'Dhia', 'sss@gmail.com\n', 'Alimentaire', '2022-05-03', '26ad90305924b1150ed489f02f947d0c.jpeg');

-- --------------------------------------------------------

--
-- Structure de la table `produit2`
--

CREATE TABLE `produit2` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `datep` date NOT NULL,
  `descp` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `rate` int(11) NOT NULL,
  `quantite_r` int(11) NOT NULL,
  `prix` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `etat` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `categorie_id` int(11) NOT NULL,
  `quantite` int(11) NOT NULL,
  `country` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `produit2`
--

INSERT INTO `produit2` (`id`, `nom`, `type`, `datep`, `descp`, `image`, `rate`, `quantite_r`, `prix`, `etat`, `categorie_id`, `quantite`, `country`) VALUES
(39, 'Dhia', 'Djej', '2022-04-22', 'ajshjqhsjqs', '4b34b8f6e4752f7f7a687cf91804e382.png', 5, 12, '12.3', 'ferme', 1, 12, 'Tunisia'),
(41, 'Baby', 'drink', '2022-04-23', 'This paragraph contains a lot of lines in the source code, but the browser ignores it', '87340173d3fbaf6dd56c0903d7aef52f.jpeg', 0, 12, '16', 'ferme', 1, 12, 'Canada'),
(43, 'Dhiaaaaa', 'Djej', '2022-04-23', 'This paragraph contains a lot of lines in the source code, but the browser ignores it', '52c286467626adf7aa6782d5d8764dfb.jpeg', 4, 12, '5', 'ferme', 1, 12, 'England'),
(44, 'Dhia', 'Djej', '2022-04-23', 'This paragraph contains a lot of lines in the source code, but the browser ignores it', 'fd7af3f0bdd732ba7be98ecbd5a9062d.jpeg', 5, 12, '23', 'ferme', 2, 12, 'France'),
(45, 'azerty', 'Djej', '2022-04-23', 'This paragraph contains a lot of lines in the source code, but the browser ignores it', 'b117501205ef461b291993a4e3342326.jpeg', 4, 12, '3', 'ferme', 6, 12, 'Russia'),
(46, 'khadija', 'ali', '2022-04-24', 'This paragraph contains a lot of lines in the source code, but the browser ignores it', '537b71a219b76fc37707104a3c9500c1.jpeg', 5, 12, '15', 'ferme', 1, 12, 'Canada'),
(47, 'Mayssa', 'drink', '2022-04-27', 'This paragraph contains a lot of lines in the source code, but the browser ignores it', 'd2cae465224ee5076b15f0487de3fd74.png', 4, 12, '13', 'ferme', 5, 12, 'Canada');

-- --------------------------------------------------------

--
-- Structure de la table `produits`
--

CREATE TABLE `produits` (
  `REFERENCE` int(11) NOT NULL,
  `CATEGORIE` int(11) DEFAULT NULL,
  `NOM` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `TYPE` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `quantite` int(11) NOT NULL,
  `DATEP` date NOT NULL,
  `IMAGE` longblob DEFAULT NULL,
  `rate` int(11) NOT NULL,
  `quantiteR` int(11) NOT NULL,
  `Prix` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `etat` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'achete',
  `IMAGE2` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DESCP` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `publication`
--

CREATE TABLE `publication` (
  `id_publication` int(11) NOT NULL,
  `titre` varchar(20) NOT NULL,
  `description` text NOT NULL,
  `image_publication` blob NOT NULL,
  `date_publication` varchar(20) NOT NULL,
  `id_client_publication` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `rating`
--

CREATE TABLE `rating` (
  `idl` int(11) NOT NULL,
  `idLivreur` int(30) NOT NULL,
  `rat` int(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `rating_event`
--

CREATE TABLE `rating_event` (
  `id_rate` int(11) NOT NULL,
  `id_event` int(70) NOT NULL,
  `rate` int(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `id_reservation` int(11) NOT NULL,
  `gmail` varchar(30) NOT NULL,
  `nombre_billet` int(11) NOT NULL,
  `id_event` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `stock`
--

CREATE TABLE `stock` (
  `idS` int(50) NOT NULL,
  `nomPartenaireS` varchar(255) DEFAULT NULL,
  `nomS` varchar(50) NOT NULL,
  `refS` varchar(255) NOT NULL,
  `categorieS` varchar(50) NOT NULL,
  `qteS` int(11) NOT NULL,
  `dateS` date NOT NULL,
  `qualiteS` varchar(50) NOT NULL DEFAULT '1.0',
  `partenaire_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `transport`
--

CREATE TABLE `transport` (
  `id` int(11) NOT NULL,
  `name_transport` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` double NOT NULL,
  `update_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `transport`
--

INSERT INTO `transport` (`id`, `name_transport`, `description`, `price`, `update_at`) VALUES
(1, 'Voiture', 'zqeeeeeeedsdzz', 23.5, '2022-04-20 15:02:59');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(8) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `email` varchar(180) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` longtext NOT NULL COMMENT '(DC2Type:json)',
  `telf` int(8) NOT NULL,
  `date` datetime NOT NULL,
  `image` varchar(255) NOT NULL,
  `github_id` varchar(255) DEFAULT NULL,
  `reset_token` varchar(255) DEFAULT NULL,
  `activation_token` varchar(50) DEFAULT NULL,
  `country` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `email`, `password`, `role`, `telf`, `date`, `image`, `github_id`, `reset_token`, `activation_token`, `country`) VALUES
(106, 'eflash', 'eprit', 'eflash.esprit@gmail.com', '$argon2id$v=19$m=65536,t=4,p=1$ZkJySUIucWVKRzlHdC94Qw$umiakB7L7ZjtTdXXV3SlMvrikJW0414NGpslWmfxgX4', '[\"ROLE_ADMIN\"]', 55443322, '2022-02-18 00:06:00', '054f33f94c418ded799ce4b4410e6892.png', '103887205', NULL, NULL, 'Russia'),
(107, 'hadil', 'khemissi', 'mayssa.bouzid@esprit.tn', '$argon2id$v=19$m=65536,t=4,p=1$cDVPYi40aDY4MTk5WUZaYQ$kaYWLedStD9ItZI3yi5fzqQEqim9hcYozr42bDJBq9I', '[\"ROLE_USER\"]', 77665544, '2022-04-26 21:33:20', '9796b113efe7527d0b471360492b8d56.jpeg', '61464347', NULL, NULL, 'United States'),
(108, 'hello', 'word', 'mayssa.bouzid@esprit.ee', '$argon2id$v=19$m=65536,t=4,p=1$NDBGa3Z2bGpzV296RDZoeQ$3FKCRUhc+DbbUOQSOWCqRPHcASnIxuGvbBxP4wlO098', '[\"ROLE_USER\"]', 22113344, '2022-04-21 23:49:46', 'dc5466315979dc51d85e832406d5ecd4.jpeg', NULL, NULL, NULL, 'United States'),
(110, 'dhia', 'aa', 'mayssa.bouzid@esprit.t', '$argon2id$v=19$m=65536,t=4,p=1$VDhyS01YbVU0MUlJUkhsLg$FxBMOz5dOWBBDorocdmI5sQ6I+UOU4x334MANk2EWGc', '[\"ROLE_USER\"]', 22334455, '2022-04-24 14:32:03', '3acacdb33e99761ba75440a4ca00435a.jpeg', NULL, NULL, NULL, 'Russia'),
(111, 'dhia72', 'Dhia', 'aderssadhia@gmail.com', '$argon2id$v=19$m=65536,t=4,p=1$dUU5R25FL01LdHpnZ1pmQw$DWem+JSrepxeEdIzz6R6Sh/7AvF1eFES51Dd+O+gQak', '[\"ROLE_ADMIN\"]', 24376729, '2022-04-27 03:46:43', 'a6ff7d12ef55fe5d946492bfe408151c.jpeg', NULL, NULL, NULL, 'Russia'),
(112, 'dhia72', 'Dhia', 'aderssadhia2000@gmail.com', '$argon2id$v=19$m=65536,t=4,p=1$YkVqUHIueFk4WFF0azBiQw$atlEK3ydNL0s9hNVZTRFW3Tzp/OUm7smmx1FYBxzgww', '[\"ROLE_USER\"]', 24376729, '2022-04-28 23:34:11', '3774337a6a55d6afa1e434d45138b7d8.jpeg', NULL, NULL, NULL, 'Germany');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_BA388B7A76ED395` (`user_id`);

--
-- Index pour la table `cart_details`
--
ALTER TABLE `cart_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_89FCC38DBCB5C6F5` (`carts_id`);

--
-- Index pour la table `categorie2`
--
ALTER TABLE `categorie2`
  ADD PRIMARY KEY (`id`);

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
  ADD PRIMARY KEY (`idCommande`),
  ADD KEY `REFERENCEP` (`REFERENCEP`),
  ADD KEY `IDClient` (`IDClient`);

--
-- Index pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD PRIMARY KEY (`id_commentaire`),
  ADD KEY `fk_publication_commentaire` (`id_publication_commentaire`),
  ADD KEY `fk_client_commentaire` (`id_client`);

--
-- Index pour la table `dislikes`
--
ALTER TABLE `dislikes`
  ADD PRIMARY KEY (`id_dislike`),
  ADD KEY `fk_publication_dislike` (`id_publication_dislike`),
  ADD KEY `fk_client_dislike` (`id_client_dislike`);

--
-- Index pour la table `doctrine_migration_versions`
--
ALTER TABLE `doctrine_migration_versions`
  ADD PRIMARY KEY (`version`);

--
-- Index pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `favoris`
--
ALTER TABLE `favoris`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_8933C432A76ED395` (`user_id`),
  ADD KEY `IDX_8933C432A2D3F607` (`IdProduits`);

--
-- Index pour la table `likes`
--
ALTER TABLE `likes`
  ADD PRIMARY KEY (`id_like`),
  ADD KEY `fk_publication_like` (`id_publication_like`),
  ADD KEY `fk_client_like` (`id_client_like`);

--
-- Index pour la table `livraison`
--
ALTER TABLE `livraison`
  ADD PRIMARY KEY (`idLivraison`),
  ADD KEY `fk_commande` (`idCommande`),
  ADD KEY `fk_livreur` (`idLivreur`);

--
-- Index pour la table `partenaires`
--
ALTER TABLE `partenaires`
  ADD PRIMARY KEY (`idP`),
  ADD KEY `nomMarqueP` (`nomMarqueP`);

--
-- Index pour la table `partenairesecours`
--
ALTER TABLE `partenairesecours`
  ADD PRIMARY KEY (`idPS`);

--
-- Index pour la table `produit2`
--
ALTER TABLE `produit2`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_BFF6AE8ABCF5E72D` (`categorie_id`);

--
-- Index pour la table `produits`
--
ALTER TABLE `produits`
  ADD PRIMARY KEY (`REFERENCE`),
  ADD KEY `IDX_BE2DDF8CDBFC8DB` (`CATEGORIE`);

--
-- Index pour la table `publication`
--
ALTER TABLE `publication`
  ADD PRIMARY KEY (`id_publication`),
  ADD KEY `fk_client_publication` (`id_client_publication`);

--
-- Index pour la table `rating`
--
ALTER TABLE `rating`
  ADD PRIMARY KEY (`idl`),
  ADD KEY `fk_liv` (`idLivreur`);

--
-- Index pour la table `rating_event`
--
ALTER TABLE `rating_event`
  ADD PRIMARY KEY (`id_rate`),
  ADD KEY `id_event` (`id_event`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id_reservation`),
  ADD KEY `id_event` (`id_event`);

--
-- Index pour la table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`idS`),
  ADD KEY `nomPartenaireS` (`nomPartenaireS`),
  ADD KEY `IDX_4B36566098DE13AC` (`partenaire_id`);

--
-- Index pour la table `transport`
--
ALTER TABLE `transport`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_8D93D649E7927C74` (`email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `address`
--
ALTER TABLE `address`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `cart`
--
ALTER TABLE `cart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT pour la table `cart_details`
--
ALTER TABLE `cart_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- AUTO_INCREMENT pour la table `categorie2`
--
ALTER TABLE `categorie2`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `categories`
--
ALTER TABLE `categories`
  MODIFY `CODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT pour la table `clients`
--
ALTER TABLE `clients`
  MODIFY `REfC` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=156;

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `idCommande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT pour la table `commentaire`
--
ALTER TABLE `commentaire`
  MODIFY `id_commentaire` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `dislikes`
--
ALTER TABLE `dislikes`
  MODIFY `id_dislike` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `favoris`
--
ALTER TABLE `favoris`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT pour la table `likes`
--
ALTER TABLE `likes`
  MODIFY `id_like` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `livraison`
--
ALTER TABLE `livraison`
  MODIFY `idLivraison` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=92;

--
-- AUTO_INCREMENT pour la table `partenaires`
--
ALTER TABLE `partenaires`
  MODIFY `idP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=84;

--
-- AUTO_INCREMENT pour la table `partenairesecours`
--
ALTER TABLE `partenairesecours`
  MODIFY `idPS` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `produit2`
--
ALTER TABLE `produit2`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT pour la table `produits`
--
ALTER TABLE `produits`
  MODIFY `REFERENCE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=129;

--
-- AUTO_INCREMENT pour la table `publication`
--
ALTER TABLE `publication`
  MODIFY `id_publication` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=116;

--
-- AUTO_INCREMENT pour la table `rating`
--
ALTER TABLE `rating`
  MODIFY `idl` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `rating_event`
--
ALTER TABLE `rating_event`
  MODIFY `id_rate` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id_reservation` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `stock`
--
ALTER TABLE `stock`
  MODIFY `idS` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=183;

--
-- AUTO_INCREMENT pour la table `transport`
--
ALTER TABLE `transport`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=113;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `FK_BA388B7A76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `cart_details`
--
ALTER TABLE `cart_details`
  ADD CONSTRAINT `FK_89FCC38DBCB5C6F5` FOREIGN KEY (`carts_id`) REFERENCES `cart` (`id`);

--
-- Contraintes pour la table `favoris`
--
ALTER TABLE `favoris`
  ADD CONSTRAINT `FK_8933C432A2D3F607` FOREIGN KEY (`IdProduits`) REFERENCES `produits` (`REFERENCE`),
  ADD CONSTRAINT `FK_8933C432A76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `produit2`
--
ALTER TABLE `produit2`
  ADD CONSTRAINT `FK_BFF6AE8ABCF5E72D` FOREIGN KEY (`categorie_id`) REFERENCES `categorie2` (`id`);

--
-- Contraintes pour la table `produits`
--
ALTER TABLE `produits`
  ADD CONSTRAINT `FK_BE2DDF8CDBFC8DB` FOREIGN KEY (`CATEGORIE`) REFERENCES `categories` (`CODE`);

--
-- Contraintes pour la table `stock`
--
ALTER TABLE `stock`
  ADD CONSTRAINT `FK_4B36566098DE13AC` FOREIGN KEY (`partenaire_id`) REFERENCES `partenaires` (`idP`),
  ADD CONSTRAINT `FK_4B365660DC6CF3E` FOREIGN KEY (`nomPartenaireS`) REFERENCES `partenaires` (`nomMarqueP`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
