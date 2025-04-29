-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 07 avr. 2025 à 15:19
-- Version du serveur : 10.4.24-MariaDB
-- Version de PHP : 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `marieteam_bdd`
--

-- --------------------------------------------------------

--
-- Structure de la table `bateau`
--

CREATE TABLE `bateau` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `url_image` varchar(255) DEFAULT NULL,
  `longueur` decimal(5,2) DEFAULT NULL,
  `largeur` decimal(5,2) DEFAULT NULL,
  `vitesse` decimal(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `bateau`
--

INSERT INTO `bateau` (`id`, `nom`, `url_image`, `longueur`, `largeur`, `vitesse`) VALUES
(1, 'Kor Ant', '/images/kor_ant.png', '25.50', '6.20', '18.00'),
(2, 'Ar Solen', '/images/ar_solen.jpg', '30.00', '7.50', '20.50'),
(3, 'Alixi', '/images/alixi.jpg', '22.80', '5.90', '16.80'),
(4, 'Luce Isle', '/images/luce_isle.jpg', '28.40', '6.80', '19.20'),
(5, 'Maellus', '/images/maellus.jpg', '32.10', '8.00', '21.00');

-- --------------------------------------------------------

--
-- Structure de la table `bateau_equipement`
--

CREATE TABLE `bateau_equipement` (
  `id_bateau` int(11) NOT NULL,
  `id_equipement` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `bateau_equipement`
--

INSERT INTO `bateau_equipement` (`id_bateau`, `id_equipement`) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 4),
(3, 2),
(3, 5),
(4, 1),
(4, 3),
(5, 4),
(5, 5);

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `lettre` char(1) NOT NULL,
  `libelle` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`lettre`, `libelle`) VALUES
('A', 'Passager'),
('B', 'Véhicule inf. 2m'),
('C', 'Véhicule sup. 2m');

-- --------------------------------------------------------

--
-- Structure de la table `contenir`
--

CREATE TABLE `contenir` (
  `id_bateau` int(11) NOT NULL,
  `lettre_categorie` char(1) NOT NULL,
  `capaciteMax` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `contenir`
--

INSERT INTO `contenir` (`id_bateau`, `lettre_categorie`, `capaciteMax`) VALUES
(1, 'A', 238),
(1, 'B', 11),
(1, 'C', 2),
(2, 'A', 276),
(2, 'B', 5),
(2, 'C', 1),
(3, 'A', 250),
(3, 'B', 3),
(3, 'C', 0);

-- --------------------------------------------------------

--
-- Structure de la table `enregistrer`
--

CREATE TABLE `enregistrer` (
  `id_reservation` int(11) NOT NULL,
  `id_type` int(11) NOT NULL,
  `quantite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `enregistrer`
--

INSERT INTO `enregistrer` (`id_reservation`, `id_type`, `quantite`) VALUES
(1, 1, 2),
(1, 2, 2),
(1, 3, 1),
(1, 5, 1),
(28, 1, 1),
(29, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `equipement`
--

CREATE TABLE `equipement` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `equipement`
--

INSERT INTO `equipement` (`id`, `nom`, `description`) VALUES
(1, 'Radar', 'Système de détection radar pour la navigation'),
(2, 'GPS', 'Système de positionnement global'),
(3, 'Sonar', 'Système de détection sous-marine'),
(4, 'Wi-Fi', 'Connexion internet à bord'),
(5, 'Bar', 'Espace bar pour les passagers');

-- --------------------------------------------------------

--
-- Structure de la table `liaison`
--

CREATE TABLE `liaison` (
  `code` int(11) NOT NULL,
  `distance` int(11) NOT NULL,
  `id_secteur` int(11) NOT NULL,
  `port_depart` int(11) NOT NULL,
  `port_arrivee` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `liaison`
--

INSERT INTO `liaison` (`code`, `distance`, `id_secteur`, `port_depart`, `port_arrivee`) VALUES
(11, 225, 2, 2, 5),
(15, 348, 1, 1, 2),
(16, 219, 1, 2, 1),
(17, 410, 1, 3, 1),
(18, 119, 1, 1, 3),
(19, 523, 1, 5, 2),
(22, 297, 3, 6, 7),
(30, 468, 2, 1, 4);

-- --------------------------------------------------------

--
-- Structure de la table `periode`
--

CREATE TABLE `periode` (
  `dateDeb` date NOT NULL,
  `dateFin` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `periode`
--

INSERT INTO `periode` (`dateDeb`, `dateFin`) VALUES
('2023-09-01', '2024-06-15'),
('2024-06-16', '2024-09-15'),
('2024-09-16', '2025-05-31');

-- --------------------------------------------------------

--
-- Structure de la table `port`
--

CREATE TABLE `port` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `image_path` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `port`
--

INSERT INTO `port` (`id`, `nom`, `image_path`) VALUES
(1, 'Quiberon', 'image/quiberon.jpg'),
(2, 'Le Palais', 'image/le_palais.jpg'),
(3, 'Sauzon', 'image/sauzon.jpg'),
(4, 'Port St Gildas', 'image/st_gildas.jpg'),
(5, 'Vannes', 'image/vannes.jpg'),
(6, 'Port-Tudy', 'image/port_tudy.jpg'),
(7, 'Lorient', 'image/lorient.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `num` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `adr` varchar(200) NOT NULL,
  `cp` varchar(10) NOT NULL,
  `ville` varchar(100) NOT NULL,
  `id_utilisateur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`num`, `nom`, `adr`, `cp`, `ville`, `id_utilisateur`) VALUES
(1, 'TIPREZ', '15 rue de l industrie', '19290', 'PEYRELEVADE', 1),
(2, 'DUPONT', '10 rue des Lilas', '75010', 'Paris', 2),
(3, 'MARTIN', '5 avenue des Champs-Élysées', '75008', 'Paris', 3),
(4, 'LEROY', '20 rue de la Paix', '69001', 'Lyon', 4),
(5, 'TESSIER', '15 boulevard Saint-Germain', '75005', 'Paris', 5),
(25, 'GIRAUD', '7 rue de la République', '13001', 'Marseille', 6),
(26, 'Noah Verin', '4 Rue De L\'Abbé Lemire', '59223', 'Roncq', 0),
(27, 'Noah Verin', '4 Rue De L\'Abbé Lemire', '59223', 'Roncq', 0),
(28, 'Noah Verin', '4 Rue De L\'Abbé Lemire', '59223', 'Roncq', 0),
(29, 'Noah Verin', '4 Rue De L\'Abbé Lemire', '59223', 'Roncq', 0);

-- --------------------------------------------------------

--
-- Structure de la table `secteur`
--

CREATE TABLE `secteur` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `secteur`
--

INSERT INTO `secteur` (`id`, `nom`) VALUES
(1, 'Belle-Île-en-Mer'),
(2, 'Houat'),
(3, 'Île de Groix');

-- --------------------------------------------------------

--
-- Structure de la table `tarifer`
--

CREATE TABLE `tarifer` (
  `id_type` int(11) NOT NULL,
  `id_periode` date NOT NULL,
  `tarif` decimal(10,2) NOT NULL,
  `code_liaison` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `tarifer`
--

INSERT INTO `tarifer` (`id_type`, `id_periode`, `tarif`, `code_liaison`) VALUES
(1, '2024-09-16', '23.50', 11),
(1, '2024-09-16', '20.00', 15),
(1, '2024-09-16', '21.00', 16),
(1, '2024-09-16', '22.00', 17),
(1, '2024-09-16', '21.50', 18),
(1, '2024-09-16', '24.00', 19),
(1, '2024-09-16', '26.00', 22),
(1, '2024-09-16', '25.00', 30),
(2, '2024-09-16', '15.80', 11),
(2, '2024-09-16', '12.80', 15),
(2, '2024-09-16', '13.20', 16),
(2, '2024-09-16', '14.20', 17),
(2, '2024-09-16', '13.70', 18),
(2, '2024-09-16', '16.20', 19),
(2, '2024-09-16', '18.20', 22),
(2, '2024-09-16', '17.20', 30),
(3, '2024-09-16', '9.00', 11),
(3, '2024-09-16', '7.00', 15),
(3, '2024-09-16', '7.50', 16),
(3, '2024-09-16', '8.50', 17),
(3, '2024-09-16', '8.00', 18),
(3, '2024-09-16', '9.50', 19),
(3, '2024-09-16', '11.50', 22),
(3, '2024-09-16', '10.50', 30),
(4, '2024-06-16', '100.00', 11),
(4, '2024-06-16', '93.00', 15),
(4, '2024-06-16', '95.00', 16),
(4, '2024-06-16', '97.00', 17),
(4, '2024-06-16', '94.50', 18),
(4, '2024-06-16', '102.00', 19),
(4, '2024-06-16', '112.00', 22),
(4, '2024-06-16', '107.00', 30),
(5, '2024-06-16', '148.00', 11),
(5, '2024-06-16', '138.00', 15),
(5, '2024-06-16', '140.00', 16),
(5, '2024-06-16', '145.00', 17),
(5, '2024-06-16', '141.50', 18),
(5, '2024-06-16', '150.00', 19),
(5, '2024-06-16', '160.00', 22),
(5, '2024-06-16', '155.00', 30),
(6, '2024-06-16', '213.00', 11),
(6, '2024-06-16', '202.00', 15),
(6, '2024-06-16', '205.00', 16),
(6, '2024-06-16', '210.00', 17),
(6, '2024-06-16', '204.50', 18),
(6, '2024-06-16', '215.00', 19),
(6, '2024-06-16', '225.00', 22),
(6, '2024-06-16', '220.00', 30),
(7, '2023-09-01', '243.00', 11),
(7, '2023-09-01', '230.00', 15),
(7, '2023-09-01', '235.00', 16),
(7, '2023-09-01', '240.00', 17),
(7, '2023-09-01', '232.50', 18),
(7, '2023-09-01', '245.00', 19),
(7, '2023-09-01', '255.00', 22),
(8, '2024-06-16', '303.00', 11),
(8, '2024-06-16', '290.00', 15),
(8, '2024-06-16', '295.00', 16),
(8, '2024-06-16', '300.00', 17),
(8, '2024-06-16', '292.50', 18),
(8, '2024-06-16', '305.00', 19),
(8, '2024-06-16', '315.00', 22),
(8, '2024-06-16', '310.00', 30);

-- --------------------------------------------------------

--
-- Structure de la table `traversee`
--

CREATE TABLE `traversee` (
  `num` int(11) NOT NULL,
  `date` date NOT NULL,
  `heure` time NOT NULL,
  `id_liaison` int(11) NOT NULL,
  `id_bateau` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `traversee`
--

INSERT INTO `traversee` (`num`, `date`, `heure`, `id_liaison`, `id_bateau`) VALUES
(541202, '2024-07-10', '16:00:00', 15, 1),
(541203, '2024-07-10', '17:30:00', 15, 2),
(541204, '2024-07-10', '19:00:00', 15, 3),
(541205, '2024-07-10', '20:30:00', 15, 4),
(541206, '2024-07-10', '22:00:00', 16, 1),
(541207, '2024-07-10', '23:30:00', 16, 2),
(541208, '2024-07-10', '01:00:00', 16, 3),
(541209, '2024-07-10', '02:30:00', 16, 4),
(541210, '2024-07-10', '04:00:00', 17, 1),
(541211, '2024-07-10', '05:30:00', 17, 2),
(541212, '2024-07-10', '07:00:00', 17, 3),
(541213, '2024-07-10', '08:30:00', 17, 4),
(541214, '2024-07-10', '10:00:00', 18, 1),
(541215, '2024-07-10', '11:30:00', 18, 2),
(541216, '2024-07-10', '13:00:00', 18, 3),
(541217, '2024-07-10', '14:30:00', 18, 4),
(541218, '2024-07-10', '16:00:00', 19, 1),
(541219, '2024-07-10', '17:30:00', 19, 2),
(541220, '2024-07-10', '19:00:00', 19, 3),
(541221, '2024-07-10', '20:30:00', 19, 4),
(541222, '2024-07-10', '22:00:00', 22, 1),
(541223, '2024-07-10', '23:30:00', 22, 2),
(541224, '2024-07-10', '01:00:00', 22, 3),
(541225, '2024-07-10', '02:30:00', 22, 4),
(541226, '2024-07-10', '04:00:00', 30, 1),
(541227, '2024-07-10', '05:30:00', 30, 2),
(541228, '2024-07-10', '07:00:00', 30, 3),
(541229, '2024-07-10', '08:30:00', 30, 4);

-- --------------------------------------------------------

--
-- Structure de la table `type`
--

CREATE TABLE `type` (
  `num` int(11) NOT NULL,
  `libelle` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `type`
--

INSERT INTO `type` (`num`, `libelle`) VALUES
(1, 'Adulte'),
(2, 'Junior 8 à 18 ans'),
(3, 'Enfant 0 à 7 ans'),
(4, 'Voiture long.inf.4m'),
(5, 'Voiture long.inf.5m'),
(6, 'Fourgon'),
(7, 'Camping Car'),
(8, 'Camion');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `role` enum('admin','client') DEFAULT 'client'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`, `email`, `mot_de_passe`, `role`) VALUES
(1, 'TIPREZ', 'tiprez@example.com', 'password123', 'client'),
(26, '', '', '', 'client');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `bateau`
--
ALTER TABLE `bateau`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `bateau_equipement`
--
ALTER TABLE `bateau_equipement`
  ADD PRIMARY KEY (`id_bateau`,`id_equipement`),
  ADD KEY `fk_bateau_equipement_equipement` (`id_equipement`);

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`lettre`);

--
-- Index pour la table `contenir`
--
ALTER TABLE `contenir`
  ADD PRIMARY KEY (`id_bateau`,`lettre_categorie`),
  ADD KEY `lettre_categorie` (`lettre_categorie`);

--
-- Index pour la table `enregistrer`
--
ALTER TABLE `enregistrer`
  ADD PRIMARY KEY (`id_reservation`,`id_type`),
  ADD KEY `id_type` (`id_type`);

--
-- Index pour la table `equipement`
--
ALTER TABLE `equipement`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `liaison`
--
ALTER TABLE `liaison`
  ADD PRIMARY KEY (`code`),
  ADD KEY `id_secteur` (`id_secteur`),
  ADD KEY `port_depart` (`port_depart`),
  ADD KEY `port_arrivee` (`port_arrivee`);

--
-- Index pour la table `periode`
--
ALTER TABLE `periode`
  ADD PRIMARY KEY (`dateDeb`,`dateFin`);

--
-- Index pour la table `port`
--
ALTER TABLE `port`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`num`),
  ADD KEY `id_utilisateur` (`id_utilisateur`);

--
-- Index pour la table `secteur`
--
ALTER TABLE `secteur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `tarifer`
--
ALTER TABLE `tarifer`
  ADD PRIMARY KEY (`id_type`,`id_periode`,`code_liaison`),
  ADD KEY `id_periode` (`id_periode`),
  ADD KEY `code_liaison` (`code_liaison`);

--
-- Index pour la table `traversee`
--
ALTER TABLE `traversee`
  ADD PRIMARY KEY (`num`),
  ADD KEY `id_liaison` (`id_liaison`),
  ADD KEY `id_bateau` (`id_bateau`);

--
-- Index pour la table `type`
--
ALTER TABLE `type`
  ADD PRIMARY KEY (`num`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `bateau`
--
ALTER TABLE `bateau`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `equipement`
--
ALTER TABLE `equipement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `liaison`
--
ALTER TABLE `liaison`
  MODIFY `code` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `bateau_equipement`
--
ALTER TABLE `bateau_equipement`
  ADD CONSTRAINT `fk_bateau_equipement_bateau` FOREIGN KEY (`id_bateau`) REFERENCES `bateau` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_bateau_equipement_equipement` FOREIGN KEY (`id_equipement`) REFERENCES `equipement` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
