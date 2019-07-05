-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 13. Mai 2019 um 12:55
-- Server-Version: 10.1.19-MariaDB
-- PHP-Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `shopit`
--
CREATE DATABASE IF NOT EXISTS `shopit` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `shopit`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `article`
--

DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL,
  `creationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `changeDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(200) NOT NULL default ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Daten für Tabelle article
#

INSERT INTO article VALUES (1, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 'Äpfel');
INSERT INTO article VALUES (2, '2019-01-02 00:00:00', '2019-01-02 00:00:00', 'Brot');
INSERT INTO article VALUES (3, '2019-01-02 00:00:00', '2019-01-03 00:00:00', 'Mehl');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `team`
--

DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `id` int(11) NOT NULL,
  `creationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `changeDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(200) NOT NULL default ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Daten für Tabelle team
#

INSERT INTO team VALUES (1, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 'Superteam');
INSERT INTO team VALUES (2, '2019-01-02 00:00:00', '2019-01-02 00:00:00', 'Megateam');
INSERT INTO team VALUES (3, '2019-01-02 00:00:00', '2019-01-03 00:00:00', 'Ultrateam');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `item`
--

DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int(11) NOT NULL,
  `creationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `changeDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `unitId` int(11) NOT NULL,
  `articleId` int(11) NOT NULL,
  `teamId` int(11) NOT NULL,
  `listId` int(11) NOT NULL,
  `quantity` float(11) NOT NULL,
  `favorit` boolean,
  `status` boolean
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Daten für Tabelle item
#

INSERT INTO item VALUES (1, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 1, 1, 1, 1, '3.4', false, true);
INSERT INTO item VALUES (2, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 2, 2, 1, 1, '5.8', true, true);
INSERT INTO item VALUES (3, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 3, 3, 1, 1, '2.9', true, false);
INSERT INTO item VALUES (4, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 1, 1, 2, 2, '3.4', false, true);
INSERT INTO item VALUES (5, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 2, 2, 2, 2, '5.8', true, true);
INSERT INTO item VALUES (6, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 3, 3, 2, 2, '2.9', true, false);
INSERT INTO item VALUES (7, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 1, 1, 3, 3, '3.4', false, true);
INSERT INTO item VALUES (8, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 2, 2, 3, 3, '5.8', true, true);
INSERT INTO item VALUES (9, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 3, 3, 3, 3, '2.9', true, false);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `list`
--

DROP TABLE IF EXISTS `list`;
CREATE TABLE `list` (
  `id` int(11) NOT NULL,
  `creationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `changeDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(200) NOT NULL default '',
  `teamId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Daten für Tabelle list
#

INSERT INTO list VALUES (1, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 'Familie', 1);
INSERT INTO list VALUES (2, '2019-01-02 00:00:00', '2019-01-02 00:00:00', 'Freunde', 2);
INSERT INTO list VALUES (3, '2019-01-02 00:00:00', '2019-01-03 00:00:00', 'Feinde', 3);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `membership`
--	

DROP TABLE IF EXISTS `membership`;
CREATE TABLE `membership` (
  `id` int(11) NOT NULL,
  `creationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `changeDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `personId` int(11) NOT NULL,
  `teamId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Daten für Tabelle membership
#

INSERT INTO membership VALUES (1, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 1, 1);
INSERT INTO membership VALUES (2, '2019-01-02 00:00:00', '2019-01-02 00:00:00', 2, 1);
INSERT INTO membership VALUES (3, '2019-01-02 00:00:00', '2019-01-03 00:00:00', 3, 1);
INSERT INTO membership VALUES (4, '2019-01-02 00:00:00', '2019-01-02 00:00:00', 4, 2);
INSERT INTO membership VALUES (5, '2019-01-02 00:00:00', '2019-01-03 00:00:00', 5, 2);
INSERT INTO membership VALUES (6, '2019-01-02 00:00:00', '2019-01-02 00:00:00', 6, 3);
INSERT INTO membership VALUES (7, '2019-01-02 00:00:00', '2019-01-03 00:00:00', 7, 3);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `person`
--

DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(11) NOT NULL,
  `creationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `changeDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `firstName` varchar(200) NOT NULL default '',
  `lastName` varchar(200) NOT NULL default '',
  `email` varchar(200) NOT NULL default ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Daten für Tabelle person
#

INSERT INTO person VALUES (1, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 'Ulrike', 'Mustermann', 'ulrike@mustermann.org');
INSERT INTO person VALUES (2, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 'Max', 'Mustermann', 'max@mustermann.org');
INSERT INTO person VALUES (3, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 'Kim', 'Mustermann', 'kim@mustermann.org');
INSERT INTO person VALUES (4, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 'abc', 'def', 'abc@def.org');
INSERT INTO person VALUES (5, '2019-01-02 00:00:00', '2019-01-01 00:00:00', '123', '456', '123@456.org');
INSERT INTO person VALUES (6, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 'xyz', 'zyx', 'xyz@zyx.org');
INSERT INTO person VALUES (7, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 'alpha', 'beta', 'alpha@beta.org');


-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `responsibility`
--

DROP TABLE IF EXISTS `responsibility`;
CREATE TABLE `responsibility` (
  `id` int(11) NOT NULL,
  `creationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `changeDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `personId` int(11) DEFAULT NULL,
  `itemId` int(11) DEFAULT NULL,
  `shopId` int(11) DEFAULT NULL
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Daten für Tabelle responsibility
#

INSERT INTO responsibility VALUES (1, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 1, 1, 1);
INSERT INTO responsibility VALUES (2, '2019-01-02 00:00:00', '2019-01-02 00:00:00', 2, 2, 2);
INSERT INTO responsibility VALUES (3, '2019-01-02 00:00:00', '2019-01-03 00:00:00', 3, 3, 1);
INSERT INTO responsibility VALUES (4, '2019-01-02 00:00:00', '2019-01-02 00:00:00', 4, 4, 2);
INSERT INTO responsibility VALUES (5, '2019-01-02 00:00:00', '2019-01-03 00:00:00', 5, 5, 1);
INSERT INTO responsibility VALUES (6, '2019-01-02 00:00:00', '2019-01-02 00:00:00', 6, 6, 2);
INSERT INTO responsibility VALUES (7, '2019-01-02 00:00:00', '2019-01-03 00:00:00', 7, 7, 1);
INSERT INTO responsibility VALUES (8, '2019-01-02 00:00:00', '2019-01-03 00:00:00', 1, 8, 2);
INSERT INTO responsibility VALUES (9, '2019-01-02 00:00:00', '2019-01-03 00:00:00', 7, 9, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `shop`
--

DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `id` int(11) NOT NULL,
  `creationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `changeDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(200) NOT NULL default '',
  `street` varchar(200) NOT NULL default '',
  `postalCode` varchar(200) NOT NULL default '',
  `city` varchar(200) NOT NULL default ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Daten für Tabelle shop
#

INSERT INTO shop VALUES (1, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 'Lidl', 'Astraße', '00000', 'Stadt1');
INSERT INTO shop VALUES (2, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 'Aldi', 'Bstraße', '00001', 'Stadt2');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `unit`
--

DROP TABLE IF EXISTS `unit`;
CREATE TABLE `unit` (
  `id` int(11) NOT NULL,
  `creationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `changeDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `unit` varchar(200) NOT NULL default ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Daten für Tabelle unit
#

INSERT INTO unit VALUES (1, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 'Kilo');
INSERT INTO unit VALUES (2, '2019-01-02 00:00:00', '2019-01-02 00:00:00', 'Stück');
INSERT INTO unit VALUES (3, '2019-01-02 00:00:00', '2019-01-03 00:00:00', 'Packungen');

-- --------------------------------------------------------

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `team`
--
ALTER TABLE `team`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `list`
--
ALTER TABLE `list`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `membership`
--
ALTER TABLE `membership`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `responsibility`
--
ALTER TABLE `responsibility`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `shop`
--
ALTER TABLE `shop`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `unit`
--
ALTER TABLE `unit`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `article`
--
ALTER TABLE `article`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT für Tabelle `team`
--
ALTER TABLE `team`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT für Tabelle `item`
--
ALTER TABLE `item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT für Tabelle `membership`
--
ALTER TABLE `membership`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT für Tabelle `person`
--
ALTER TABLE `person`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT für Tabelle `responsibility`
--
ALTER TABLE `responsibility`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT für Tabelle `shop`
--
ALTER TABLE `shop`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT für Tabelle `unit`
--
ALTER TABLE `unit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
