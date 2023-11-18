-- --------------------------------------------------------
-- Hôte:                         127.0.0.1
-- Version du serveur:           8.0.30 - MySQL Community Server - GPL
-- SE du serveur:                Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Listage de la structure de la base pour alimentations
CREATE DATABASE IF NOT EXISTS `alimentations` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `alimentations`;

-- Listage de la structure de table alimentations. aliments
CREATE TABLE IF NOT EXISTS `aliments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `poids_moyen` float NOT NULL,
  `calories` int NOT NULL,
  `vitamines_C` float NOT NULL,
  `type_id` int DEFAULT NULL,
  `couleur_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `type_id` (`type_id`),
  KEY `couleur_id` (`couleur_id`),
  CONSTRAINT `aliments_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `type_aliment` (`id`),
  CONSTRAINT `aliments_ibfk_2` FOREIGN KEY (`type_id`) REFERENCES `type_aliment` (`id`),
  CONSTRAINT `aliments_ibfk_3` FOREIGN KEY (`couleur_id`) REFERENCES `couleur` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table alimentations.aliments : ~78 rows (environ)
INSERT IGNORE INTO `aliments` (`id`, `nom`, `poids_moyen`, `calories`, `vitamines_C`, `type_id`, `couleur_id`) VALUES
	(1, 'Bigaradier', 0.1, 66, 50.5, 1, 6),
	(2, 'Banane', 0.15, 89, 0, 1, 6),
	(3, 'Orange', 0.2, 47, 0, 1, 9),
	(4, 'Fraise', 0.02, 33, 0, 1, 3),
	(5, 'Abricot', 0.05, 48, 0, 1, 2),
	(6, 'Ananas', 1, 50, 0, 1, 4),
	(7, 'Avocat', 0.2, 160, 0, 1, 13),
	(8, 'Cassis', 0.01, 63, 0, 1, 1),
	(9, 'Cerise', 0.01, 50, 0, 1, 3),
	(10, 'Citron', 0.15, 29, 0, 1, 6),
	(11, 'Clémentine', 0.08, 47, 0, 1, 4),
	(12, 'Coing', 0.3, 57, 0, 1, 6),
	(13, 'Datte', 0.02, 282, 0, 1, 4),
	(14, 'Figue', 0.05, 74, 0, 1, 14),
	(15, 'Framboise', 0.01, 53, 0, 1, 3),
	(16, 'Grenade', 0.2, 83, 0, 1, 3),
	(17, 'Groseille', 0.01, 56, 0, 1, 3),
	(18, 'Kiwi', 0.07, 61, 0, 1, 20),
	(19, 'Litchi', 0.02, 66, 0, 1, 5),
	(20, 'Mandarine', 0.08, 53, 0, 1, 7),
	(21, 'Mangue', 0.3, 60, 0, 1, 8),
	(22, 'Melon', 1, 34, 0, 1, 13),
	(23, 'Mirabelle', 0.02, 67, 0, 1, 6),
	(24, 'Mûre', 0.01, 43, 0, 1, 1),
	(25, 'Myrtille', 0.01, 57, 0, 1, 5),
	(26, 'Nectarine', 0.1, 44, 0, 1, 21),
	(27, 'Noix de coco', 1, 354, 0, 1, 11),
	(28, 'Olive', 0.02, 115, 0, 1, 13),
	(29, 'Pamplemousse', 0.3, 42, 0, 1, 6),
	(30, 'Papaye', 1, 43, 0, 1, 4),
	(31, 'Pastèque', 2, 30, 0, 1, 13),
	(32, 'Pêche', 0.15, 39, 0, 1, 4),
	(33, 'Poire', 0.2, 57, 0, 1, 13),
	(34, 'Pomelo', 0.25, 38, 0, 1, 6),
	(35, 'Prune', 0.04, 46, 0, 1, 14),
	(36, 'Raisin', 0.02, 0, 0, 1, 14),
	(64, 'Carotte', 0.1, 41, 0, 4, 5),
	(65, 'Brocoli', 0.3, 34, 0, 4, 5),
	(66, 'Pomme de terre', 0.2, 0, 0, 4, 11),
	(67, 'Tomate', 0.15, 0, 0, 4, 8),
	(68, 'Artichaut', 0.5, 47, 0, 4, 1),
	(69, 'Asperge', 0.1, 20, 0, 4, 45),
	(70, 'Aubergine', 0.2, 25, 0, 4, 6),
	(71, 'Betterave', 0.3, 43, 0, 4, 4),
	(72, 'Blette', 0.3, 19, 0, 4, 6),
	(73, 'Brocoli', 0.3, 34, 0, 4, 8),
	(74, 'Carotte', 0.1, 41, 0, 4, 22),
	(75, 'Céleri', 0.4, 16, 0, 4, 6),
	(76, 'Champignon', 0.05, 22, 0, 4, 2),
	(77, 'Chou-fleur', 1, 25, 0, 4, 1),
	(78, 'Chou de Bruxelles', 0.02, 43, 0, 4, 16),
	(79, 'Concombre', 0.2, 15, 0, 4, 20),
	(80, 'Courgette', 0.3, 17, 0, 4, 12),
	(81, 'Échalote', 0.05, 72, 0, 4, 5),
	(82, 'Endive', 0.2, 17, 0, 4, 16),
	(83, 'Épinard', 0.2, 23, 0, 4, 12),
	(84, 'Fenouil', 0.4, 31, 0, 4, 4),
	(85, 'Haricot vert', 0.05, 31, 0, 4, 5),
	(86, 'Laitue', 0.3, 15, 0, 4, 5),
	(87, 'Maïs', 1, 86, 0, 4, 21),
	(88, 'Navet', 0.2, 28, 0, 4, 8),
	(89, 'Oignon', 0.1, 40, 0, 4, 1),
	(90, 'Panais', 0.3, 75, 0, 4, 18),
	(91, 'Petit pois', 0.01, 81, 0, 4, 21),
	(92, 'Poireau', 0.3, 0, 0, 4, 9),
	(93, 'Poivron', 0.2, 0, 0, 4, 12),
	(94, 'Pomme de terre', 0.2, 0, 0, 4, 6),
	(95, 'Potiron', 2, 0, 0, 4, 5),
	(96, 'Radis', 0.02, 0, 0, 4, 20),
	(97, 'Tomate', 0.15, 0, 0, 4, 22),
	(127, 'Riz', 0.001, 1, 0, 2, 2),
	(129, 'Litchi Mur', 0.02, 66, 0, 1, 6),
	(130, 'Litchi Mur', 0.02, 66, 0, 1, 6),
	(131, 'Litchi Mur', 0.02, 66, 0, 1, 6),
	(132, 'Litchi Mur', 0.02, 66, 0, 1, 6),
	(133, 'Litchi Mur', 0.02, 66, 0, 1, 6),
	(134, 'Litchi pas beau', 0.02, 66, 0, 1, 6),
	(135, 'fgdsgdfgdg', 0.02, 5000, 50.5, 2, 2);

-- Listage de la structure de table alimentations. couleur
CREATE TABLE IF NOT EXISTS `couleur` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `hexadecimal_rvb` char(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '#FFFFFF',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table alimentations.couleur : ~45 rows (environ)
INSERT IGNORE INTO `couleur` (`id`, `nom`, `hexadecimal_rvb`) VALUES
	(1, 'toto', '#FFFFFF'),
	(2, 'blanc', '#FFFFFF'),
	(3, 'Rouge', '#FF0000'),
	(4, 'Citron vert', '#00FF00'),
	(5, 'Bleu', '#0000FF'),
	(6, 'Jaune', '#FFFF00'),
	(7, 'Cyan / Aqua', '#00FFFF'),
	(8, 'Magenta / Fuchsia', '#FF00FF'),
	(9, 'argent', '#C0C0C0'),
	(11, 'Bordeaux', '#800000'),
	(12, 'olive', '#808000'),
	(13, 'vert', '#008000'),
	(14, 'Violet', '#800080'),
	(15, 'Sarcelle', '#008080'),
	(16, 'Marine', '#000080'),
	(18, 'Red', '#FF0000'),
	(20, 'Crème', '#FF5555'),
	(21, 'Blanc', '#FFFFFF'),
	(22, 'Noir', '#000000'),
	(23, 'test', '#000000'),
	(24, 'test2', '#000000'),
	(25, 'test3', '#000000'),
	(26, 'test4', '#000000'),
	(27, 'test4', '#000000'),
	(28, 'test4', '#000000'),
	(29, 'test4', '#000000'),
	(30, 'test4', '#000000'),
	(31, 'test6', '#000000'),
	(32, 'test6', '#000000'),
	(33, 'test6', '#000000'),
	(34, 'test6', '#000000'),
	(35, 'test8', '#000000'),
	(36, 'test9', '#000000'),
	(37, 'test9', '#000000'),
	(38, 'test9', '#000000'),
	(39, 'test9', '#000000'),
	(40, 'test10', '#000000'),
	(41, 'test10', '#000000'),
	(42, 'test10', '#000000'),
	(43, 'test10', '#000000'),
	(44, 'test10', '#000000'),
	(45, 'test10', '#000000'),
	(46, 'test11', '#000000'),
	(47, 'test12', '#000000'),
	(48, 'test13', '#000000'),
	(49, 'titi', '#000000');

-- Listage de la structure de table alimentations. fruits
CREATE TABLE IF NOT EXISTS `fruits` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  `couleur` varchar(255) DEFAULT NULL,
  `poids_moyen` float DEFAULT NULL,
  `calories` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table alimentations.fruits : ~36 rows (environ)
INSERT IGNORE INTO `fruits` (`id`, `nom`, `couleur`, `poids_moyen`, `calories`) VALUES
	(1, 'Pomme', 'Rouge', 0.1, 52),
	(2, 'Banane', 'Jaune', 0.15, 89),
	(3, 'Orange', 'Orange', 0.2, 47),
	(4, 'Fraise', 'Rouge', 0.02, 33),
	(5, 'Abricot', 'Orange', 0.05, 48),
	(6, 'Ananas', 'Marron', 1, 50),
	(7, 'Avocat', 'Vert', 0.2, 160),
	(8, 'Cassis', 'Noir', 0.01, 63),
	(9, 'Cerise', 'Rouge', 0.01, 50),
	(10, 'Citron', 'Jaune', 0.15, 29),
	(11, 'Clémentine', 'Orange', 0.08, 47),
	(12, 'Coing', 'Jaune', 0.3, 57),
	(13, 'Datte', 'Marron', 0.02, 282),
	(14, 'Figue', 'Violet', 0.05, 74),
	(15, 'Framboise', 'Rouge', 0.01, 53),
	(16, 'Grenade', 'Rouge', 0.2, 83),
	(17, 'Groseille', 'Rouge', 0.01, 56),
	(18, 'Kiwi', 'Marron', 0.07, 61),
	(19, 'Litchi', 'Rose', 0.02, 66),
	(20, 'Mandarine', 'Orange', 0.08, 53),
	(21, 'Mangue', 'Orange', 0.3, 60),
	(22, 'Melon', 'Vert', 1, 34),
	(23, 'Mirabelle', 'Jaune', 0.02, 67),
	(24, 'Mûre', 'Noir', 0.01, 43),
	(25, 'Myrtille', 'Bleu', 0.01, 57),
	(26, 'Nectarine', 'Orange', 0.1, 44),
	(27, 'Noix de coco', 'Marron', 1, 354),
	(28, 'Olive', 'Vert', 0.02, 115),
	(29, 'Pamplemousse', 'Jaune', 0.3, 42),
	(30, 'Papaye', 'Orange', 1, 43),
	(31, 'Pastèque', 'Vert', 2, 30),
	(32, 'Pêche', 'Orange', 0.15, 39),
	(33, 'Poire', 'Vert', 0.2, 57),
	(34, 'Pomelo', 'Jaune', 0.25, 38),
	(35, 'Prune', 'Violet', 0.04, 46),
	(36, 'Raisin', 'Violet', 0.02, NULL);

-- Listage de la structure de table alimentations. legumes
CREATE TABLE IF NOT EXISTS `legumes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  `couleur` varchar(255) DEFAULT NULL,
  `poids_moyen` float DEFAULT NULL,
  `calories` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table alimentations.legumes : ~27 rows (environ)
INSERT IGNORE INTO `legumes` (`id`, `nom`, `couleur`, `poids_moyen`, `calories`) VALUES
	(1, 'Carotte', 'Orange', 0.1, 41),
	(2, 'Brocoli', 'Vert', 0.3, 34),
	(3, 'Pomme de terre', 'Marron', 0.2, NULL),
	(4, 'Tomate', 'Rouge', 0.15, NULL),
	(5, 'Artichaut', 'Vert', 0.5, 47),
	(6, 'Asperge', 'Vert', 0.1, 20),
	(7, 'Aubergine', 'Violet', 0.2, 25),
	(8, 'Betterave', 'Rouge', 0.3, 43),
	(9, 'Blette', 'Vert', 0.3, 19),
	(10, 'Brocoli', 'Vert', 0.3, 34),
	(11, 'Carotte', 'Orange', 0.1, 41),
	(12, 'Céleri', 'Vert', 0.4, 16),
	(13, 'Champignon', 'Marron', 0.05, 22),
	(21, 'Fenouil', 'Vert', 0.4, 31),
	(22, 'Haricot vert', 'Vert', 0.05, 31),
	(23, 'Laitue', 'Vert', 0.3, 15),
	(24, 'Maïs', 'Jaune', 1, 86),
	(25, 'Navet', 'Blanc', 0.2, 28),
	(26, 'Oignon', 'Marron', 0.1, 40),
	(27, 'Panais', 'Blanc', 0.3, 75),
	(28, 'Petit pois', 'Vert', 0.01, 81),
	(29, 'Poireau', 'Vert', 0.3, NULL),
	(30, 'Poivron', 'Rouge', 0.2, NULL),
	(31, 'Pomme de terre', 'Marron', 0.2, NULL),
	(32, 'Potiron', 'Orange', 2, NULL),
	(33, 'Radis', 'Rouge', 0.02, NULL),
	(34, 'Tomate', 'Rouge', 0.15, NULL);

-- Listage de la structure de table alimentations. type_aliment
CREATE TABLE IF NOT EXISTS `type_aliment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Listage des données de la table alimentations.type_aliment : ~4 rows (environ)
INSERT IGNORE INTO `type_aliment` (`id`, `nom`) VALUES
	(1, 'fruit'),
	(2, 'céréale'),
	(3, 'condiment'),
	(4, 'légume');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
