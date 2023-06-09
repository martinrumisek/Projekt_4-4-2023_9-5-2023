-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Počítač: 127.0.0.1
-- Vytvořeno: Pon 15. kvě 2023, 22:06
-- Verze serveru: 10.4.24-MariaDB
-- Verze PHP: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáze: `projektjava`
--
CREATE DATABASE IF NOT EXISTS `projektjava` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `projektjava`;

-- --------------------------------------------------------

--
-- Struktura tabulky `hodnoceni_zaci`
--

CREATE TABLE `hodnoceni_zaci` (
  `id` int(128) NOT NULL,
  `name` varchar(255) NOT NULL,
  `class` varchar(128) NOT NULL,
  `zkouseni1` varchar(128) NOT NULL,
  `zkouseni2` varchar(128) NOT NULL,
  `test1` varchar(128) NOT NULL,
  `test2` varchar(128) NOT NULL,
  `test3` varchar(128) NOT NULL,
  `test4` varchar(128) NOT NULL,
  `test5` varchar(128) NOT NULL,
  `aktivita1` varchar(128) NOT NULL,
  `aktivita2` varchar(128) NOT NULL,
  `aktivita3` varchar(128) NOT NULL,
  `aktivita4` varchar(128) NOT NULL,
  `aktivita5` varchar(128) NOT NULL,
  `bonus` varchar(128) NOT NULL,
  `prumer` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Vypisuji data pro tabulku `hodnoceni_zaci`
--

INSERT INTO `hodnoceni_zaci` (`id`, `name`, `class`, `zkouseni1`, `zkouseni2`, `test1`, `test2`, `test3`, `test4`, `test5`, `aktivita1`, `aktivita2`, `aktivita3`, `aktivita4`, `aktivita5`, `bonus`, `prumer`) VALUES
(1, 'Petr Novák', '1.A', '1', '', '2', '', '', '', '', '1', '5', '', '', '', '', '2,00');

-- --------------------------------------------------------

--
-- Struktura tabulky `poznamky`
--

CREATE TABLE `poznamky` (
  `id` int(11) NOT NULL,
  `text` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Vypisuji data pro tabulku `poznamky`
--

INSERT INTO `poznamky` (`id`, `text`) VALUES
(1, 'Můžeme si poznamenat všechny potřebné poznámky, které na nás do příště počkají a na nic nezapomeneme.');

-- --------------------------------------------------------

--
-- Struktura tabulky `zaci`
--

CREATE TABLE `zaci` (
  `id` int(128) NOT NULL,
  `name` varchar(255) NOT NULL,
  `class` varchar(128) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` int(128) NOT NULL,
  `address` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Vypisuji data pro tabulku `zaci`
--

INSERT INTO `zaci` (`id`, `name`, `class`, `email`, `phone`, `address`) VALUES
(1, 'Petr Novák', '1.A', 'novak_petr@seznam.cz', 720675665, 'Uherské Hradiště');

--
-- Indexy pro exportované tabulky
--

--
-- Indexy pro tabulku `hodnoceni_zaci`
--
ALTER TABLE `hodnoceni_zaci`
  ADD PRIMARY KEY (`id`);

--
-- Indexy pro tabulku `poznamky`
--
ALTER TABLE `poznamky`
  ADD PRIMARY KEY (`id`);

--
-- Indexy pro tabulku `zaci`
--
ALTER TABLE `zaci`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pro tabulky
--

--
-- AUTO_INCREMENT pro tabulku `hodnoceni_zaci`
--
ALTER TABLE `hodnoceni_zaci`
  MODIFY `id` int(128) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pro tabulku `poznamky`
--
ALTER TABLE `poznamky`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pro tabulku `zaci`
--
ALTER TABLE `zaci`
  MODIFY `id` int(128) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
