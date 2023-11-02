--
-- Database: `05provasql`
--
CREATE Database IF NOT EXISTS `05provasql`;
USE `05provasql`;
-- --------------------------------------------------------
--
-- Struttura della tabella `userdata`
--
CREATE TABLE IF NOT EXISTS `userdata` (
  `Id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `Nome` varchar(120) NOT NULL,
  `Cognome` varchar(120) NOT NULL,
  `Eta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
