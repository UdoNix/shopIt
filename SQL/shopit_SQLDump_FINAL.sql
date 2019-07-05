-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 05. Jul 2019 um 18:36
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

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `article`
--

CREATE TABLE `article` (
  `id` int(11) NOT NULL,
  `creationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `changeDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(200) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `article`
--

INSERT INTO `article` (`id`, `creationDate`, `changeDate`, `name`) VALUES
(1, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 'Äpfel'),
(2, '2019-01-02 00:00:00', '2019-01-02 00:00:00', 'Brot'),
(3, '2019-01-02 00:00:00', '2019-01-03 00:00:00', 'Mehl');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `item`
--

CREATE TABLE `item` (
  `id` int(11) NOT NULL,
  `creationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `changeDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `unitId` int(11) NOT NULL,
  `articleId` int(11) NOT NULL,
  `teamId` int(11) NOT NULL,
  `listId` int(11) NOT NULL,
  `quantity` float NOT NULL,
  `favorit` tinyint(1) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `item`
--

INSERT INTO `item` (`id`, `creationDate`, `changeDate`, `unitId`, `articleId`, `teamId`, `listId`, `quantity`, `favorit`, `status`) VALUES
(1, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 1, 1, 1, 1, 3.4, 0, 1),
(2, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 2, 2, 1, 1, 5.8, 1, 1),
(3, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 3, 3, 1, 1, 2.9, 1, 0),
(4, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 1, 1, 2, 2, 3.4, 0, 1),
(5, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 2, 2, 2, 2, 5.8, 1, 1),
(6, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 3, 3, 2, 2, 2.9, 1, 0),
(7, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 1, 1, 3, 3, 3.4, 0, 1),
(8, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 2, 2, 3, 3, 5.8, 1, 1),
(9, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 3, 3, 3, 3, 2.9, 1, 0);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `list`
--

CREATE TABLE `list` (
  `id` int(11) NOT NULL,
  `creationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `changeDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(200) NOT NULL DEFAULT '',
  `teamId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `list`
--

INSERT INTO `list` (`id`, `creationDate`, `changeDate`, `name`, `teamId`) VALUES
(1, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 'Familie', 1),
(2, '2019-01-02 00:00:00', '2019-01-02 00:00:00', 'Freunde', 2),
(3, '2019-01-02 00:00:00', '2019-01-03 00:00:00', 'Feinde', 3);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `membership`
--

CREATE TABLE `membership` (
  `id` int(11) NOT NULL,
  `creationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `changeDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `personId` int(11) NOT NULL,
  `teamId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `membership`
--

INSERT INTO `membership` (`id`, `creationDate`, `changeDate`, `personId`, `teamId`) VALUES
(1, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 1, 1),
(2, '2019-01-02 00:00:00', '2019-01-02 00:00:00', 2, 1),
(3, '2019-01-02 00:00:00', '2019-01-03 00:00:00', 3, 1),
(4, '2019-01-02 00:00:00', '2019-01-02 00:00:00', 4, 2),
(5, '2019-01-02 00:00:00', '2019-01-03 00:00:00', 5, 2),
(6, '2019-01-02 00:00:00', '2019-01-02 00:00:00', 6, 3),
(7, '2019-01-02 00:00:00', '2019-01-03 00:00:00', 7, 3);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `person`
--

CREATE TABLE `person` (
  `id` int(11) NOT NULL,
  `creationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `changeDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `firstName` varchar(200) NOT NULL DEFAULT '',
  `lastName` varchar(200) NOT NULL DEFAULT '',
  `email` varchar(200) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `person`
--

INSERT INTO `person` (`id`, `creationDate`, `changeDate`, `firstName`, `lastName`, `email`) VALUES
(1, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 'Ulrike', 'Mustermann', 'ulrike@mustermann.org'),
(2, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 'Max', 'Mustermann', 'max@mustermann.org'),
(3, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 'Kim', 'Mustermann', 'kim@mustermann.org'),
(4, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 'abc', 'def', 'abc@def.org'),
(5, '2019-01-02 00:00:00', '2019-01-01 00:00:00', '123', '456', '123@456.org'),
(6, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 'xyz', 'zyx', 'xyz@zyx.org'),
(7, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 'alpha', 'beta', 'alpha@beta.org');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `responsibility`
--

CREATE TABLE `responsibility` (
  `id` int(11) NOT NULL,
  `creationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `changeDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `personId` int(11) DEFAULT NULL,
  `itemId` int(11) DEFAULT NULL,
  `shopId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `responsibility`
--

INSERT INTO `responsibility` (`id`, `creationDate`, `changeDate`, `personId`, `itemId`, `shopId`) VALUES
(1, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 1, 1, 1),
(2, '2019-01-02 00:00:00', '2019-01-02 00:00:00', 2, 2, 2),
(3, '2019-01-02 00:00:00', '2019-01-03 00:00:00', 3, 3, 1),
(4, '2019-01-02 00:00:00', '2019-01-02 00:00:00', 4, 4, 2),
(5, '2019-01-02 00:00:00', '2019-01-03 00:00:00', 5, 5, 1),
(6, '2019-01-02 00:00:00', '2019-01-02 00:00:00', 6, 6, 2),
(7, '2019-01-02 00:00:00', '2019-01-03 00:00:00', 7, 7, 1),
(8, '2019-01-02 00:00:00', '2019-01-03 00:00:00', 1, 8, 2),
(9, '2019-01-02 00:00:00', '2019-01-03 00:00:00', 7, 9, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `shop`
--

CREATE TABLE `shop` (
  `id` int(11) NOT NULL,
  `creationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `changeDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(200) NOT NULL DEFAULT '',
  `street` varchar(200) NOT NULL DEFAULT '',
  `postalCode` varchar(200) NOT NULL DEFAULT '',
  `city` varchar(200) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `shop`
--

INSERT INTO `shop` (`id`, `creationDate`, `changeDate`, `name`, `street`, `postalCode`, `city`) VALUES
(1, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 'Lidl', 'Astraße', '00000', 'Stadt1'),
(2, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 'Aldi', 'Bstraße', '00001', 'Stadt2');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `team`
--

CREATE TABLE `team` (
  `id` int(11) NOT NULL,
  `creationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `changeDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(200) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `team`
--

INSERT INTO `team` (`id`, `creationDate`, `changeDate`, `name`) VALUES
(1, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 'Superteam'),
(2, '2019-01-02 00:00:00', '2019-01-02 00:00:00', 'Megateam'),
(3, '2019-01-02 00:00:00', '2019-01-03 00:00:00', 'Ultrateam');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `unit`
--

CREATE TABLE `unit` (
  `id` int(11) NOT NULL,
  `creationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `changeDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `unit` varchar(200) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `unit`
--

INSERT INTO `unit` (`id`, `creationDate`, `changeDate`, `unit`) VALUES
(1, '2019-01-02 00:00:00', '2019-01-01 00:00:00', 'Kilo'),
(2, '2019-01-02 00:00:00', '2019-01-02 00:00:00', 'Stück'),
(3, '2019-01-02 00:00:00', '2019-01-03 00:00:00', 'Packungen');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `article`
--
ALTER TABLE `article`
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
-- Indizes für die Tabelle `team`
--
ALTER TABLE `team`
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
-- AUTO_INCREMENT für Tabelle `team`
--
ALTER TABLE `team`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT für Tabelle `unit`
--
ALTER TABLE `unit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
