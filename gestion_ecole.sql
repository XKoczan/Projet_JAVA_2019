-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  sam. 08 juin 2019 à 16:32
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
  `anneescolaire_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`anneescolaire_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tab_anneescolaire`
--

INSERT INTO `tab_anneescolaire` (`anneescolaire_id`) VALUES
(1);

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
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tab_bulletin`
--

INSERT INTO `tab_bulletin` (`bulletin_id`, `bulletin_trimestre_id`, `bulletin_inscription_id`, `bulletin_appreciation`) VALUES
(111, 1, 57, 'A renseigner'),
(112, 2, 57, 'A renseigner'),
(113, 3, 57, 'A renseigner'),
(114, 1, 58, 'A renseigner'),
(115, 2, 58, 'A renseigner'),
(116, 3, 58, 'A renseigner');

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
  KEY `classe_anneescolaire_id` (`classe_anneescolaire_id`),
  KEY `classe_niveau_id` (`classe_niveau_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tab_classe`
--

INSERT INTO `tab_classe` (`classe_id`, `classe_nom`, `classe_niveau_id`, `classe_anneescolaire_id`) VALUES
(2, 'TD2', 3, 1),
(4, 'TD3', 3, 1);

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
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tab_detailbulletin`
--

INSERT INTO `tab_detailbulletin` (`detailbulletin_id`, `detailbulletin_bulletin_id`, `detailbulletin_enseignement_id`, `detailbulletin_appreciation`) VALUES
(77, 111, 4, 'A renseigner'),
(78, 111, 5, 'A renseigner'),
(79, 112, 4, 'A renseigner'),
(80, 112, 5, 'A renseigner'),
(81, 113, 4, 'A renseigner'),
(82, 113, 5, 'A renseigner'),
(83, 114, 28, 'A renseigner'),
(84, 115, 28, 'A renseigner'),
(85, 116, 28, 'A renseigner');

-- --------------------------------------------------------

--
-- Structure de la table `tab_discipline`
--

DROP TABLE IF EXISTS `tab_discipline`;
CREATE TABLE IF NOT EXISTS `tab_discipline` (
  `discipline_id` int(11) NOT NULL AUTO_INCREMENT,
  `discipline_nom` varchar(255) NOT NULL,
  PRIMARY KEY (`discipline_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tab_discipline`
--

INSERT INTO `tab_discipline` (`discipline_id`, `discipline_nom`) VALUES
(2, 'Mathématiques'),
(3, 'Informatique'),
(6, 'WebDynamique');

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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tab_enseignement`
--

INSERT INTO `tab_enseignement` (`enseignement_id`, `enseignement_classe_id`, `enseignement_discipline_id`, `enseignement_personne_id`) VALUES
(28, 2, 3, 117),
(29, 2, 2, 119);

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
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tab_evaluation`
--

INSERT INTO `tab_evaluation` (`evaluation_id`, `evaluation_detailbulletin_id`, `evaluation_note`, `evaluation_appreciation`) VALUES
(90, 79, 15, 'A renseigner'),
(91, 80, 17, 'A renseigner'),
(92, 81, 18, 'A renseigner'),
(93, 82, 19, 'A renseigner'),
(100, 77, 20, 'A renseigner'),
(111, 77, 14, 'A renseigner'),
(112, 78, 10, 'A renseigner'),
(113, 77, 14, 'à renseigner'),
(114, 78, 11, 'A renseigner'),
(115, 78, 9, 'à renseigner'),
(122, 78, 14, 'A renseigner'),
(123, 83, NULL, 'A renseigner'),
(124, 84, NULL, 'A renseigner'),
(125, 85, NULL, 'A renseigner');

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
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tab_inscription`
--

INSERT INTO `tab_inscription` (`inscription_id`, `inscription_classe_id`, `inscription_personne_id`) VALUES
(57, 2, 75),
(58, 2, 118);

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
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tab_personne`
--

INSERT INTO `tab_personne` (`personne_id`, `personne_nom`, `personne_prenom`, `personne_type`) VALUES
(75, 'Nom', 'Prenom', 'etu'),
(117, 'Amrhein', 'Seb', 'prof'),
(118, 'Nom2', 'Prenom', 'etu'),
(119, 'Nom', 'Prenom', 'prof');

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
  PRIMARY KEY (`trimestre_id`),
  KEY `trimestre_annescolaire_id` (`trimestre_annescolaire_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tab_trimestre`
--

INSERT INTO `tab_trimestre` (`trimestre_id`, `trimestre_numero`, `trimestre_debut`, `trimestre_fin`, `trimestre_annescolaire_id`) VALUES
(2, 1, '2018-09-07', '2018-11-23', 1),
(3, 1, '2018-11-24', '2019-03-20', 1),
(4, 1, '2019-03-24', '2019-06-15', 1);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `tab_classe`
--
ALTER TABLE `tab_classe`
  ADD CONSTRAINT `tab_classe_ibfk_1` FOREIGN KEY (`classe_anneescolaire_id`) REFERENCES `tab_anneescolaire` (`anneescolaire_id`),
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

--
-- Contraintes pour la table `tab_trimestre`
--
ALTER TABLE `tab_trimestre`
  ADD CONSTRAINT `tab_trimestre_ibfk_1` FOREIGN KEY (`trimestre_annescolaire_id`) REFERENCES `tab_anneescolaire` (`anneescolaire_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
