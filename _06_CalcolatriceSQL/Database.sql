--
-- Database: `06CalcolatriceSQL`
--
CREATE Database IF NOT EXISTS `06CalcolatriceSQL`;
USE `06CalcolatriceSQL`;
-- --------------------------------------------------------
--
-- Struttura della tabella `userdata`
--
CREATE TABLE IF NOT EXISTS `userdata` (
  `Username` varchar(120) PRIMARY KEY NOT NULL,
  `Password` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
--
-- Struttura della tabella `history`
--
CREATE TABLE IF NOT EXISTS `history` (
  `Id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `Operation` varchar(120) NOT NULL,
  `Username` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
