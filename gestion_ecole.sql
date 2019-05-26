-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 26 mai 2019 à 09:09
-- Version du serveur :  5.7.23
-- Version de PHP :  7.2.10

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  PRIMARY KEY (`bulletin_id`),
  KEY `bulletin_inscription_id` (`bulletin_inscription_id`),
  KEY `bulletin_trimestre_id` (`bulletin_trimestre_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tab_classe`
--

DROP TABLE IF EXISTS `tab_classe`;
CREATE TABLE IF NOT EXISTS `tab_classe` (
  `classe_id` int(11) NOT NULL AUTO_INCREMENT,
  `classe_nom` varchar(255) NOT NULL,
  `classe_ecole_id` int(11) NOT NULL,
  `classe_niveau_id` int(11) NOT NULL,
  `classe_anneescolaire_id` int(11) NOT NULL,
  PRIMARY KEY (`classe_id`),
  KEY `classe_anneescolaire_id` (`classe_anneescolaire_id`),
  KEY `classe_ecole_id` (`classe_ecole_id`),
  KEY `classe_niveau_id` (`classe_niveau_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  KEY `detailbulletin_bulletin_id` (`detailbulletin_bulletin_id`),
  KEY `detailbulletin_enseignement_id` (`detailbulletin_enseignement_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tab_discipline`
--

DROP TABLE IF EXISTS `tab_discipline`;
CREATE TABLE IF NOT EXISTS `tab_discipline` (
  `discipline_id` int(11) NOT NULL AUTO_INCREMENT,
  `discipline_nom` varchar(255) NOT NULL,
  PRIMARY KEY (`discipline_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tab_ecole`
--

DROP TABLE IF EXISTS `tab_ecole`;
CREATE TABLE IF NOT EXISTS `tab_ecole` (
  `ecole_id` int(11) NOT NULL AUTO_INCREMENT,
  `ecole_nom` varchar(255) NOT NULL,
  PRIMARY KEY (`ecole_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  KEY `enseignement_classe_id` (`enseignement_classe_id`),
  KEY `enseignement_discipline_id` (`enseignement_discipline_id`),
  KEY `enseignement_personne_id` (`enseignement_personne_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tab_evaluation`
--

DROP TABLE IF EXISTS `tab_evaluation`;
CREATE TABLE IF NOT EXISTS `tab_evaluation` (
  `evaluation_id` int(11) NOT NULL AUTO_INCREMENT,
  `evaluation_detailbulletin_id` int(11) NOT NULL,
  `evaluation_note` int(11) NOT NULL,
  `evaluation_appreciation` varchar(255) NOT NULL,
  PRIMARY KEY (`evaluation_id`),
  KEY `evaluation_detailbulletin_id` (`evaluation_detailbulletin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tab_niveau`
--

DROP TABLE IF EXISTS `tab_niveau`;
CREATE TABLE IF NOT EXISTS `tab_niveau` (
  `niveau_id` int(11) NOT NULL AUTO_INCREMENT,
  `niveau_nom` int(11) NOT NULL,
  PRIMARY KEY (`niveau_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tab_trimestre`
--

DROP TABLE IF EXISTS `tab_trimestre`;
CREATE TABLE IF NOT EXISTS `tab_trimestre` (
  `trimestre_id` int(11) NOT NULL AUTO_INCREMENT,
  `trimestre_numero` int(11) NOT NULL,
  `trimestre_debut` int(11) NOT NULL,
  `trimestre_fin` int(11) NOT NULL,
  `trimestre_annescolaire_id` int(11) NOT NULL,
  PRIMARY KEY (`trimestre_id`),
  KEY `trimestre_annescolaire_id` (`trimestre_annescolaire_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `tab_bulletin`
--
ALTER TABLE `tab_bulletin`
  ADD CONSTRAINT `tab_bulletin_ibfk_1` FOREIGN KEY (`bulletin_inscription_id`) REFERENCES `tab_inscription` (`inscription_id`),
  ADD CONSTRAINT `tab_bulletin_ibfk_2` FOREIGN KEY (`bulletin_trimestre_id`) REFERENCES `tab_trimestre` (`trimestre_id`);

--
-- Contraintes pour la table `tab_classe`
--
ALTER TABLE `tab_classe`
  ADD CONSTRAINT `tab_classe_ibfk_1` FOREIGN KEY (`classe_anneescolaire_id`) REFERENCES `tab_anneescolaire` (`anneescolaire_id`),
  ADD CONSTRAINT `tab_classe_ibfk_2` FOREIGN KEY (`classe_ecole_id`) REFERENCES `tab_ecole` (`ecole_id`),
  ADD CONSTRAINT `tab_classe_ibfk_3` FOREIGN KEY (`classe_niveau_id`) REFERENCES `tab_niveau` (`niveau_id`);

--
-- Contraintes pour la table `tab_detailbulletin`
--
ALTER TABLE `tab_detailbulletin`
  ADD CONSTRAINT `tab_detailbulletin_ibfk_1` FOREIGN KEY (`detailbulletin_bulletin_id`) REFERENCES `tab_bulletin` (`bulletin_id`),
  ADD CONSTRAINT `tab_detailbulletin_ibfk_2` FOREIGN KEY (`detailbulletin_enseignement_id`) REFERENCES `tab_enseignement` (`enseignement_id`);

--
-- Contraintes pour la table `tab_enseignement`
--
ALTER TABLE `tab_enseignement`
  ADD CONSTRAINT `tab_enseignement_ibfk_1` FOREIGN KEY (`enseignement_classe_id`) REFERENCES `tab_classe` (`classe_id`),
  ADD CONSTRAINT `tab_enseignement_ibfk_2` FOREIGN KEY (`enseignement_discipline_id`) REFERENCES `tab_discipline` (`discipline_id`),
  ADD CONSTRAINT `tab_enseignement_ibfk_3` FOREIGN KEY (`enseignement_personne_id`) REFERENCES `tab_personne` (`personne_id`);

--
-- Contraintes pour la table `tab_evaluation`
--
ALTER TABLE `tab_evaluation`
  ADD CONSTRAINT `tab_evaluation_ibfk_1` FOREIGN KEY (`evaluation_detailbulletin_id`) REFERENCES `tab_detailbulletin` (`detailbulletin_id`);

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
