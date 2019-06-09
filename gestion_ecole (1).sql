-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 09 juin 2019 à 16:31
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gestion_ecole`
--

-- --------------------------------------------------------

--
-- Structure de la table `tab_anneescolaire`
--

DROP TABLE IF EXISTS `tab_anneescolaire`;
CREATE TABLE IF NOT EXISTS `tab_anneescolaire` (
  `anneescolaire_id` int(11) NOT NULL,
  PRIMARY KEY (`anneescolaire_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tab_anneescolaire`
--

INSERT INTO `tab_anneescolaire` (`anneescolaire_id`) VALUES
(2018),
(2019);

-- --------------------------------------------------------

--
-- Structure de la table `tab_bulletin`
--

DROP TABLE IF EXISTS `tab_bulletin`;
CREATE TABLE IF NOT EXISTS `tab_bulletin` (
  `bulletin_id` int(11) NOT NULL AUTO_INCREMENT,
  `bulletin_trimestre_id` int(11) NOT NULL,
  `bulletin_inscription_id` int(11) NOT NULL,
  `bulletin_appreciation` varchar(255) NOT NULL,
  PRIMARY KEY (`bulletin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tab_bulletin`
--

INSERT INTO `tab_bulletin` (`bulletin_id`, `bulletin_trimestre_id`, `bulletin_inscription_id`, `bulletin_appreciation`) VALUES
(1, 1, 1, 'A renseigner'),
(2, 2, 1, 'A renseigner'),
(3, 3, 1, 'A renseigner'),
(4, 1, 2, 'A renseigner'),
(5, 2, 2, 'A renseigner'),
(6, 3, 2, 'A renseigner'),
(7, 1, 3, 'A renseigner'),
(8, 2, 3, 'A renseigner'),
(9, 3, 3, 'A renseigner'),
(10, 1, 4, 'A renseigner'),
(11, 2, 4, 'A renseigner'),
(12, 3, 4, 'A renseigner'),
(13, 1, 5, 'A renseigner'),
(14, 2, 5, 'A renseigner'),
(15, 3, 5, 'A renseigner'),
(16, 1, 6, 'A renseigner'),
(17, 2, 6, 'A renseigner'),
(18, 3, 6, 'A renseigner'),
(19, 1, 7, 'A renseigner'),
(20, 2, 7, 'A renseigner'),
(21, 3, 7, 'A renseigner'),
(22, 1, 8, 'A renseigner'),
(23, 2, 8, 'A renseigner'),
(24, 3, 8, 'A renseigner'),
(25, 1, 9, 'A renseigner'),
(26, 2, 9, 'A renseigner'),
(27, 3, 9, 'A renseigner'),
(28, 1, 10, 'A renseigner'),
(29, 2, 10, 'A renseigner'),
(30, 3, 10, 'A renseigner'),
(31, 1, 11, 'A renseigner'),
(32, 2, 11, 'A renseigner'),
(33, 3, 11, 'A renseigner'),
(34, 1, 12, 'A renseigner'),
(35, 2, 12, 'A renseigner'),
(36, 3, 12, 'A renseigner'),
(37, 1, 13, 'A renseigner'),
(38, 2, 13, 'A renseigner'),
(39, 3, 13, 'A renseigner'),
(40, 1, 14, 'A renseigner'),
(41, 2, 14, 'A renseigner'),
(42, 3, 14, 'A renseigner'),
(43, 1, 15, 'A renseigner'),
(44, 2, 15, 'A renseigner'),
(45, 3, 15, 'A renseigner'),
(46, 1, 16, 'A renseigner'),
(47, 2, 16, 'A renseigner'),
(48, 3, 16, 'A renseigner'),
(49, 1, 17, 'A renseigner'),
(50, 2, 17, 'A renseigner'),
(51, 3, 17, 'A renseigner'),
(52, 1, 18, 'A renseigner'),
(53, 2, 18, 'A renseigner'),
(54, 3, 18, 'A renseigner'),
(55, 1, 19, 'A renseigner'),
(56, 2, 19, 'A renseigner'),
(57, 3, 19, 'A renseigner'),
(58, 1, 20, 'A renseigner'),
(59, 2, 20, 'A renseigner'),
(60, 3, 20, 'A renseigner'),
(61, 1, 21, 'A renseigner'),
(62, 2, 21, 'A renseigner'),
(63, 3, 21, 'A renseigner'),
(64, 1, 22, 'A renseigner'),
(65, 2, 22, 'A renseigner'),
(66, 3, 22, 'A renseigner'),
(67, 1, 23, 'A renseigner'),
(68, 2, 23, 'A renseigner'),
(69, 3, 23, 'A renseigner'),
(70, 1, 24, 'A renseigner'),
(71, 2, 24, 'A renseigner'),
(72, 3, 24, 'A renseigner'),
(73, 1, 25, 'A renseigner'),
(74, 2, 25, 'A renseigner'),
(75, 3, 25, 'A renseigner'),
(76, 1, 26, 'A renseigner'),
(77, 2, 26, 'A renseigner'),
(78, 3, 26, 'A renseigner'),
(79, 1, 27, 'A renseigner'),
(80, 2, 27, 'A renseigner'),
(81, 3, 27, 'A renseigner'),
(82, 1, 28, 'A renseigner'),
(83, 2, 28, 'A renseigner'),
(84, 3, 28, 'A renseigner'),
(85, 1, 29, 'A renseigner'),
(86, 2, 29, 'A renseigner'),
(87, 3, 29, 'A renseigner'),
(88, 1, 30, 'A renseigner'),
(89, 2, 30, 'A renseigner'),
(90, 3, 30, 'A renseigner'),
(91, 1, 31, 'A renseigner'),
(92, 2, 31, 'A renseigner'),
(93, 3, 31, 'A renseigner'),
(94, 1, 32, 'A renseigner'),
(95, 2, 32, 'A renseigner'),
(96, 3, 32, 'A renseigner'),
(97, 1, 33, 'A renseigner'),
(98, 2, 33, 'A renseigner'),
(99, 3, 33, 'A renseigner');

-- --------------------------------------------------------

--
-- Structure de la table `tab_classe`
--

DROP TABLE IF EXISTS `tab_classe`;
CREATE TABLE IF NOT EXISTS `tab_classe` (
  `classe_id` int(11) NOT NULL AUTO_INCREMENT,
  `classe_nom` varchar(255) NOT NULL,
  `classe_niveau_id` int(11) NOT NULL,
  `classe_anneescolaire_id` int(11) NOT NULL,
  PRIMARY KEY (`classe_id`),
  KEY `classe_niveau_id` (`classe_niveau_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tab_classe`
--

INSERT INTO `tab_classe` (`classe_id`, `classe_nom`, `classe_niveau_id`, `classe_anneescolaire_id`) VALUES
(1, 'TD1', 1, 2019),
(2, 'TD2', 1, 2019),
(3, 'TD3', 1, 2019),
(4, 'TD4', 1, 2019),
(5, 'TD5', 1, 2019),
(6, 'TD1', 2, 2019),
(7, 'TD2', 2, 2019),
(8, 'TD3', 2, 2019),
(9, 'TD4', 2, 2019),
(10, 'TD5', 2, 2019),
(11, 'TD1', 3, 2019),
(12, 'TD2', 3, 2019),
(13, 'TD3', 3, 2019),
(14, 'TD4', 3, 2019),
(15, 'TD5', 3, 2019),
(16, 'TD1', 4, 2019),
(17, 'TD2', 4, 2019),
(18, 'TD3', 4, 2019),
(19, 'TD4', 4, 2019),
(20, 'TD5', 4, 2019),
(21, 'TD1', 5, 2019),
(22, 'TD2', 5, 2019),
(23, 'TD3', 5, 2019),
(24, 'TD4', 5, 2019),
(25, 'TD5', 5, 2019),
(26, 'TD5', 5, 2018),
(27, 'TD5', 1, 2018);

-- --------------------------------------------------------

--
-- Structure de la table `tab_detailbulletin`
--

DROP TABLE IF EXISTS `tab_detailbulletin`;
CREATE TABLE IF NOT EXISTS `tab_detailbulletin` (
  `detailbulletin_id` int(11) NOT NULL AUTO_INCREMENT,
  `detailbulletin_bulletin_id` int(11) NOT NULL,
  `detailbulletin_enseignement_id` int(11) NOT NULL,
  `detailbulletin_appreciation` varchar(255) NOT NULL,
  PRIMARY KEY (`detailbulletin_id`),
  KEY `detailbulletin_bulletin_id` (`detailbulletin_bulletin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tab_detailbulletin`
--

INSERT INTO `tab_detailbulletin` (`detailbulletin_id`, `detailbulletin_bulletin_id`, `detailbulletin_enseignement_id`, `detailbulletin_appreciation`) VALUES
(1, 1, 33, 'A renseigner'),
(2, 1, 34, 'A renseigner'),
(3, 1, 35, 'A renseigner'),
(4, 2, 33, 'A renseigner'),
(5, 2, 34, 'A renseigner'),
(6, 2, 35, 'A renseigner'),
(7, 3, 33, 'A renseigner'),
(8, 3, 34, 'A renseigner'),
(9, 3, 35, 'A renseigner'),
(10, 4, 33, 'A renseigner'),
(11, 4, 34, 'A renseigner'),
(12, 4, 35, 'A renseigner'),
(13, 5, 33, 'A renseigner'),
(14, 5, 34, 'A renseigner'),
(15, 5, 35, 'A renseigner'),
(16, 6, 33, 'A renseigner'),
(17, 6, 34, 'A renseigner'),
(18, 6, 35, 'A renseigner'),
(19, 7, 39, 'A renseigner'),
(20, 8, 39, 'A renseigner'),
(21, 9, 39, 'A renseigner'),
(22, 10, 40, 'A renseigner'),
(23, 11, 40, 'A renseigner'),
(24, 12, 40, 'A renseigner'),
(25, 13, 41, 'A renseigner'),
(26, 14, 41, 'A renseigner'),
(27, 15, 41, 'A renseigner'),
(28, 16, 42, 'A renseigner'),
(29, 17, 42, 'A renseigner'),
(30, 18, 42, 'A renseigner'),
(31, 19, 43, 'A renseigner'),
(32, 20, 43, 'A renseigner'),
(33, 21, 43, 'A renseigner'),
(34, 22, 44, 'A renseigner'),
(35, 23, 44, 'A renseigner'),
(36, 24, 44, 'A renseigner'),
(37, 25, 45, 'A renseigner'),
(38, 26, 45, 'A renseigner'),
(39, 27, 45, 'A renseigner'),
(40, 28, 46, 'A renseigner'),
(41, 29, 46, 'A renseigner'),
(42, 30, 46, 'A renseigner'),
(43, 31, 47, 'A renseigner'),
(44, 32, 47, 'A renseigner'),
(45, 33, 47, 'A renseigner'),
(46, 34, 48, 'A renseigner'),
(47, 35, 48, 'A renseigner'),
(48, 36, 48, 'A renseigner'),
(49, 37, 49, 'A renseigner'),
(50, 38, 49, 'A renseigner'),
(51, 39, 49, 'A renseigner'),
(52, 40, 50, 'A renseigner'),
(53, 41, 50, 'A renseigner'),
(54, 42, 50, 'A renseigner'),
(55, 43, 51, 'A renseigner'),
(56, 44, 51, 'A renseigner'),
(57, 45, 51, 'A renseigner'),
(58, 46, 52, 'A renseigner'),
(59, 47, 52, 'A renseigner'),
(60, 48, 52, 'A renseigner'),
(61, 49, 53, 'A renseigner'),
(62, 50, 53, 'A renseigner'),
(63, 51, 53, 'A renseigner'),
(64, 52, 54, 'A renseigner'),
(65, 53, 54, 'A renseigner'),
(66, 54, 54, 'A renseigner'),
(67, 55, 55, 'A renseigner'),
(68, 56, 55, 'A renseigner'),
(69, 57, 55, 'A renseigner'),
(70, 58, 56, 'A renseigner'),
(71, 59, 56, 'A renseigner'),
(72, 60, 56, 'A renseigner'),
(73, 61, 57, 'A renseigner'),
(74, 62, 57, 'A renseigner'),
(75, 63, 57, 'A renseigner'),
(76, 64, 58, 'A renseigner'),
(77, 65, 58, 'A renseigner'),
(78, 66, 58, 'A renseigner'),
(79, 67, 59, 'A renseigner'),
(80, 67, 60, 'A renseigner'),
(81, 68, 59, 'A renseigner'),
(82, 68, 60, 'A renseigner'),
(83, 69, 59, 'A renseigner'),
(84, 69, 60, 'A renseigner'),
(85, 70, 61, 'A renseigner'),
(86, 71, 61, 'A renseigner'),
(87, 72, 61, 'A renseigner'),
(88, 73, 62, 'A renseigner'),
(89, 74, 62, 'A renseigner'),
(90, 75, 62, 'A renseigner'),
(91, 76, 63, 'A renseigner'),
(92, 77, 63, 'A renseigner'),
(93, 78, 63, 'A renseigner'),
(94, 79, 37, 'A renseigner'),
(95, 80, 37, 'A renseigner'),
(96, 81, 37, 'A renseigner'),
(97, 82, 40, 'A renseigner'),
(98, 83, 40, 'A renseigner'),
(99, 84, 40, 'A renseigner'),
(100, 85, 49, 'A renseigner'),
(101, 86, 49, 'A renseigner'),
(102, 87, 49, 'A renseigner'),
(103, 88, 61, 'A renseigner'),
(104, 89, 61, 'A renseigner'),
(105, 90, 61, 'A renseigner'),
(106, 91, 53, 'A renseigner'),
(107, 92, 53, 'A renseigner'),
(108, 93, 53, 'A renseigner'),
(109, 94, 49, 'A renseigner'),
(110, 95, 49, 'A renseigner'),
(111, 96, 49, 'A renseigner'),
(112, 97, 43, 'A renseigner'),
(113, 98, 43, 'A renseigner'),
(114, 99, 43, 'A renseigner');

-- --------------------------------------------------------

--
-- Structure de la table `tab_discipline`
--

DROP TABLE IF EXISTS `tab_discipline`;
CREATE TABLE IF NOT EXISTS `tab_discipline` (
  `discipline_id` int(11) NOT NULL AUTO_INCREMENT,
  `discipline_nom` varchar(255) NOT NULL,
  PRIMARY KEY (`discipline_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tab_discipline`
--

INSERT INTO `tab_discipline` (`discipline_id`, `discipline_nom`) VALUES
(8, 'POO JAVA'),
(9, 'Mathematiques'),
(10, 'Anglais');

-- --------------------------------------------------------

--
-- Structure de la table `tab_enseignement`
--

DROP TABLE IF EXISTS `tab_enseignement`;
CREATE TABLE IF NOT EXISTS `tab_enseignement` (
  `enseignement_id` int(11) NOT NULL AUTO_INCREMENT,
  `enseignement_classe_id` int(11) NOT NULL,
  `enseignement_discipline_id` int(11) NOT NULL,
  `enseignement_personne_id` int(11) NOT NULL,
  PRIMARY KEY (`enseignement_id`),
  KEY `enseignement_personne_id` (`enseignement_personne_id`),
  KEY `enseignement_discipline_id` (`enseignement_discipline_id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tab_enseignement`
--

INSERT INTO `tab_enseignement` (`enseignement_id`, `enseignement_classe_id`, `enseignement_discipline_id`, `enseignement_personne_id`) VALUES
(33, 1, 8, 140),
(34, 1, 9, 141),
(35, 1, 10, 142),
(37, 26, 9, 181),
(38, 27, 10, 183),
(39, 2, 8, 185),
(40, 3, 9, 186),
(41, 4, 10, 187),
(42, 5, 8, 188),
(43, 6, 10, 189),
(44, 7, 8, 190),
(45, 8, 8, 191),
(46, 9, 9, 192),
(47, 10, 10, 193),
(48, 11, 8, 194),
(49, 12, 8, 195),
(50, 13, 8, 196),
(51, 14, 9, 197),
(52, 15, 9, 198),
(53, 16, 8, 199),
(54, 17, 10, 200),
(55, 18, 8, 201),
(56, 19, 9, 202),
(57, 20, 9, 203),
(58, 21, 10, 204),
(59, 22, 8, 205),
(60, 22, 8, 206),
(61, 23, 8, 207),
(62, 24, 9, 208),
(63, 25, 9, 209);

-- --------------------------------------------------------

--
-- Structure de la table `tab_evaluation`
--

DROP TABLE IF EXISTS `tab_evaluation`;
CREATE TABLE IF NOT EXISTS `tab_evaluation` (
  `evaluation_id` int(11) NOT NULL AUTO_INCREMENT,
  `evaluation_detailbulletin_id` int(11) NOT NULL,
  `evaluation_note` int(11) DEFAULT NULL,
  `evaluation_appreciation` varchar(255) NOT NULL,
  PRIMARY KEY (`evaluation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tab_evaluation`
--

INSERT INTO `tab_evaluation` (`evaluation_id`, `evaluation_detailbulletin_id`, `evaluation_note`, `evaluation_appreciation`) VALUES
(1, 1, NULL, 'A renseigner'),
(2, 2, NULL, 'A renseigner'),
(3, 3, NULL, 'A renseigner'),
(4, 4, NULL, 'A renseigner'),
(5, 5, NULL, 'A renseigner'),
(6, 6, NULL, 'A renseigner'),
(7, 7, NULL, 'A renseigner'),
(8, 8, NULL, 'A renseigner'),
(9, 9, NULL, 'A renseigner'),
(10, 10, NULL, 'A renseigner'),
(11, 11, NULL, 'A renseigner'),
(12, 12, NULL, 'A renseigner'),
(13, 13, NULL, 'A renseigner'),
(14, 14, NULL, 'A renseigner'),
(15, 15, NULL, 'A renseigner'),
(16, 16, NULL, 'A renseigner'),
(17, 17, NULL, 'A renseigner'),
(18, 18, NULL, 'A renseigner'),
(19, 19, NULL, 'A renseigner'),
(20, 20, NULL, 'A renseigner'),
(21, 21, NULL, 'A renseigner'),
(22, 22, NULL, 'A renseigner'),
(23, 23, NULL, 'A renseigner'),
(24, 24, NULL, 'A renseigner'),
(25, 25, NULL, 'A renseigner'),
(26, 26, NULL, 'A renseigner'),
(27, 27, NULL, 'A renseigner'),
(28, 28, NULL, 'A renseigner'),
(29, 29, NULL, 'A renseigner'),
(30, 30, NULL, 'A renseigner'),
(31, 31, NULL, 'A renseigner'),
(32, 32, NULL, 'A renseigner'),
(33, 33, NULL, 'A renseigner'),
(34, 34, NULL, 'A renseigner'),
(35, 35, NULL, 'A renseigner'),
(36, 36, NULL, 'A renseigner'),
(37, 37, NULL, 'A renseigner'),
(38, 38, NULL, 'A renseigner'),
(39, 39, NULL, 'A renseigner'),
(40, 40, NULL, 'A renseigner'),
(41, 41, NULL, 'A renseigner'),
(42, 42, NULL, 'A renseigner'),
(43, 43, NULL, 'A renseigner'),
(44, 44, NULL, 'A renseigner'),
(45, 45, NULL, 'A renseigner'),
(46, 46, NULL, 'A renseigner'),
(47, 47, NULL, 'A renseigner'),
(48, 48, NULL, 'A renseigner'),
(49, 49, NULL, 'A renseigner'),
(50, 50, NULL, 'A renseigner'),
(51, 51, NULL, 'A renseigner'),
(52, 52, NULL, 'A renseigner'),
(53, 53, NULL, 'A renseigner'),
(54, 54, NULL, 'A renseigner'),
(55, 55, NULL, 'A renseigner'),
(56, 56, NULL, 'A renseigner'),
(57, 57, NULL, 'A renseigner'),
(58, 58, NULL, 'A renseigner'),
(59, 59, NULL, 'A renseigner'),
(60, 60, NULL, 'A renseigner'),
(61, 61, NULL, 'A renseigner'),
(62, 62, NULL, 'A renseigner'),
(63, 63, NULL, 'A renseigner'),
(64, 64, NULL, 'A renseigner'),
(65, 65, NULL, 'A renseigner'),
(66, 66, NULL, 'A renseigner'),
(67, 67, NULL, 'A renseigner'),
(68, 68, NULL, 'A renseigner'),
(69, 69, NULL, 'A renseigner'),
(70, 70, NULL, 'A renseigner'),
(71, 71, NULL, 'A renseigner'),
(72, 72, NULL, 'A renseigner'),
(73, 73, NULL, 'A renseigner'),
(74, 74, NULL, 'A renseigner'),
(75, 75, NULL, 'A renseigner'),
(76, 76, NULL, 'A renseigner'),
(77, 77, NULL, 'A renseigner'),
(78, 78, NULL, 'A renseigner'),
(79, 79, NULL, 'A renseigner'),
(80, 80, NULL, 'A renseigner'),
(81, 81, NULL, 'A renseigner'),
(82, 82, NULL, 'A renseigner'),
(83, 83, NULL, 'A renseigner'),
(84, 84, NULL, 'A renseigner'),
(85, 85, NULL, 'A renseigner'),
(86, 86, NULL, 'A renseigner'),
(87, 87, NULL, 'A renseigner'),
(88, 88, NULL, 'A renseigner'),
(89, 89, NULL, 'A renseigner'),
(90, 90, NULL, 'A renseigner'),
(91, 91, NULL, 'A renseigner'),
(92, 92, NULL, 'A renseigner'),
(93, 93, NULL, 'A renseigner'),
(94, 94, NULL, 'A renseigner'),
(95, 95, NULL, 'A renseigner'),
(96, 96, NULL, 'A renseigner'),
(97, 97, NULL, 'A renseigner'),
(98, 98, NULL, 'A renseigner'),
(99, 99, NULL, 'A renseigner'),
(100, 100, NULL, 'A renseigner'),
(101, 101, NULL, 'A renseigner'),
(102, 102, NULL, 'A renseigner'),
(103, 103, NULL, 'A renseigner'),
(104, 104, NULL, 'A renseigner'),
(105, 105, NULL, 'A renseigner'),
(106, 106, NULL, 'A renseigner'),
(107, 107, NULL, 'A renseigner'),
(108, 108, NULL, 'A renseigner'),
(109, 109, NULL, 'A renseigner'),
(110, 110, NULL, 'A renseigner'),
(111, 111, NULL, 'A renseigner'),
(112, 112, NULL, 'A renseigner'),
(113, 113, NULL, 'A renseigner'),
(114, 114, NULL, 'A renseigner');

-- --------------------------------------------------------

--
-- Structure de la table `tab_inscription`
--

DROP TABLE IF EXISTS `tab_inscription`;
CREATE TABLE IF NOT EXISTS `tab_inscription` (
  `inscription_id` int(11) NOT NULL AUTO_INCREMENT,
  `inscription_classe_id` int(11) NOT NULL,
  `inscription_personne_id` int(11) NOT NULL,
  PRIMARY KEY (`inscription_id`),
  KEY `inscription_classe_id` (`inscription_classe_id`),
  KEY `inscription_personne_id` (`inscription_personne_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tab_inscription`
--

INSERT INTO `tab_inscription` (`inscription_id`, `inscription_classe_id`, `inscription_personne_id`) VALUES
(1, 1, 210),
(2, 1, 211),
(3, 2, 212),
(4, 3, 213),
(5, 4, 214),
(6, 5, 215),
(7, 6, 216),
(8, 7, 217),
(9, 8, 218),
(10, 9, 219),
(11, 10, 220),
(12, 11, 221),
(13, 12, 222),
(14, 13, 223),
(15, 14, 224),
(16, 15, 225),
(17, 16, 226),
(18, 17, 227),
(19, 18, 228),
(20, 19, 229),
(21, 20, 230),
(22, 21, 231),
(23, 22, 232),
(24, 23, 233),
(25, 24, 234),
(26, 25, 235),
(27, 26, 236),
(28, 3, 237),
(29, 12, 238),
(30, 23, 239),
(31, 16, 240),
(32, 12, 241),
(33, 6, 242);

-- --------------------------------------------------------

--
-- Structure de la table `tab_niveau`
--

DROP TABLE IF EXISTS `tab_niveau`;
CREATE TABLE IF NOT EXISTS `tab_niveau` (
  `niveau_id` int(11) NOT NULL AUTO_INCREMENT,
  `niveau_nom` int(11) NOT NULL,
  PRIMARY KEY (`niveau_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tab_niveau`
--

INSERT INTO `tab_niveau` (`niveau_id`, `niveau_nom`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 0);

-- --------------------------------------------------------

--
-- Structure de la table `tab_personne`
--

DROP TABLE IF EXISTS `tab_personne`;
CREATE TABLE IF NOT EXISTS `tab_personne` (
  `personne_id` int(11) NOT NULL AUTO_INCREMENT,
  `personne_nom` varchar(255) NOT NULL,
  `personne_prenom` varchar(255) NOT NULL,
  `personne_type` varchar(255) NOT NULL,
  PRIMARY KEY (`personne_id`)
) ENGINE=InnoDB AUTO_INCREMENT=243 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tab_personne`
--

INSERT INTO `tab_personne` (`personne_id`, `personne_nom`, `personne_prenom`, `personne_type`) VALUES
(140, 'Segado', 'Jean Pierre', 'prof'),
(141, 'Le Cor', 'Luc', 'prof'),
(142, 'Des Neiges', 'Marie', 'prof'),
(181, 'Naulin', 'David', 'prof'),
(183, 'Maoni', 'Brice', 'prof'),
(185, 'Lupin', 'Serges', 'prof'),
(186, 'Sorox', 'Luc', 'prof'),
(187, 'Rox', 'Raoul', 'prof'),
(188, 'Outan', 'Laurent', 'prof'),
(189, 'Carlos', 'Melanie', 'prof'),
(190, 'Sheriff', 'Carl', 'prof'),
(191, 'Timbal', 'Sylvie', 'prof'),
(192, 'Timbre', 'Thimote', 'prof'),
(193, 'Lieutenant', 'Georges', 'prof'),
(194, 'Ludibel', 'Ludivine', 'prof'),
(195, 'Poivre', 'Vera', 'prof'),
(196, 'Botrin', 'Vincent', 'prof'),
(197, 'Le Monier', 'Gauthier', 'prof'),
(198, 'Homsi', 'Paul', 'prof'),
(199, 'Dupont', 'Jean Luc', 'prof'),
(200, 'Lariviere', 'Jean Baptiste', 'prof'),
(201, 'Salibot', 'Alice', 'prof'),
(202, 'Virondro', 'Manuel', 'prof'),
(203, 'Potter', 'Harry', 'prof'),
(204, 'Granger', 'Jessica', 'prof'),
(205, 'Elerte', 'Jorris', 'prof'),
(206, 'Nanori', 'Holaris', 'prof'),
(207, 'Salbert', 'Jean Francois', 'prof'),
(208, 'Leclerc', 'Rock', 'prof'),
(209, 'Firlingert', 'Julie', 'prof'),
(210, 'Le Loher', 'Guillaume', 'etu'),
(211, 'Koczan', 'Xavier', 'etu'),
(212, 'Benistand', 'Sylvain', 'etu'),
(213, 'Lancman', 'Nathan', 'etu'),
(214, 'Do Barrero', 'Jordan', 'etu'),
(215, 'Bouvier', 'Lucie', 'etu'),
(216, 'Irlinger', 'Margot', 'etu'),
(217, 'Hu', 'Thomas', 'etu'),
(218, 'Laurent', 'Thomas', 'etu'),
(219, 'Mystere', 'Martin', 'etu'),
(220, 'Johns', 'Indiana', 'etu'),
(221, 'Marie', 'Sebastien', 'etu'),
(222, 'Mesnard', 'Vincent', 'etu'),
(223, 'Inocencio', 'Valentin', 'etu'),
(224, 'Legrandois', 'Lucie', 'etu'),
(225, 'Comtesse', 'Noemie', 'etu'),
(226, 'Sabot', 'Alice', 'etu'),
(227, 'Sartoris', 'Louis', 'etu'),
(228, 'Meunier', 'Helene', 'etu'),
(229, 'Rathery', 'Amandine', 'etu'),
(230, 'Leponge', 'Bob', 'etu'),
(231, 'Lapin', 'Jean', 'etu'),
(232, 'Lepge', 'Pierre', 'etu'),
(233, 'Lagarde', 'Lorie', 'etu'),
(234, 'Courtis', 'Curtis', 'etu'),
(235, 'Napolis', 'Noe', 'etu'),
(236, 'Larmoire', 'Paul', 'etu'),
(237, 'Roger', 'Michel', 'etu'),
(238, 'Fenetre', 'Louis', 'etu'),
(239, 'Milou', 'Mylene', 'etu'),
(240, 'Merguez', 'Christine', 'etu'),
(241, 'Poulet', 'Dorris', 'etu'),
(242, 'Ladive', 'Carlos', 'etu');

-- --------------------------------------------------------

--
-- Structure de la table `tab_trimestre`
--

DROP TABLE IF EXISTS `tab_trimestre`;
CREATE TABLE IF NOT EXISTS `tab_trimestre` (
  `trimestre_id` int(11) NOT NULL AUTO_INCREMENT,
  `trimestre_numero` int(11) NOT NULL,
  `trimestre_debut` date NOT NULL,
  `trimestre_fin` date NOT NULL,
  `trimestre_annescolaire_id` int(11) NOT NULL,
  PRIMARY KEY (`trimestre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tab_trimestre`
--

INSERT INTO `tab_trimestre` (`trimestre_id`, `trimestre_numero`, `trimestre_debut`, `trimestre_fin`, `trimestre_annescolaire_id`) VALUES
(1, 1, '2019-01-01', '2019-02-28', 2019),
(2, 2, '2019-03-01', '2019-04-30', 2019),
(3, 3, '2019-05-01', '2019-06-30', 2019);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `tab_classe`
--
ALTER TABLE `tab_classe`
  ADD CONSTRAINT `tab_classe_ibfk_3` FOREIGN KEY (`classe_niveau_id`) REFERENCES `tab_niveau` (`niveau_id`);

--
-- Contraintes pour la table `tab_detailbulletin`
--
ALTER TABLE `tab_detailbulletin`
  ADD CONSTRAINT `tab_detailbulletin_ibfk_1` FOREIGN KEY (`detailbulletin_bulletin_id`) REFERENCES `tab_bulletin` (`bulletin_id`);

--
-- Contraintes pour la table `tab_enseignement`
--
ALTER TABLE `tab_enseignement`
  ADD CONSTRAINT `tab_enseignement_ibfk_3` FOREIGN KEY (`enseignement_personne_id`) REFERENCES `tab_personne` (`personne_id`),
  ADD CONSTRAINT `tab_enseignement_ibfk_4` FOREIGN KEY (`enseignement_discipline_id`) REFERENCES `tab_discipline` (`discipline_id`);

--
-- Contraintes pour la table `tab_inscription`
--
ALTER TABLE `tab_inscription`
  ADD CONSTRAINT `tab_inscription_ibfk_1` FOREIGN KEY (`inscription_classe_id`) REFERENCES `tab_classe` (`classe_id`),
  ADD CONSTRAINT `tab_inscription_ibfk_2` FOREIGN KEY (`inscription_personne_id`) REFERENCES `tab_personne` (`personne_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
