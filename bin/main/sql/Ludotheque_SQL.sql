-- Pour créer la BDD sur un autre poste :

--DROP TABLE IF EXISTS Client;
--DROP TABLE IF EXISTS Jeu;
--DROP TABLE IF EXISTS Genre;
--DROP TABLE IF EXISTS Jeu_Genre;
--DROP TABLE IF EXISTS Exemplaires_jeu;

--CREATE TABLE Client (
--	id_client SERIAL PRIMARY KEY,
--	nom VARCHAR(30) NOT NULL,
--	prenom VARCHAR(30) NOT NULL,
--	email VARCHAR NOT NULL UNIQUE,
--	telephone VARCHAR(10),
--	rue VARCHAR NOT NULL,
--	codePostal VARCHAR(5) NOT NULL,
--	ville VARCHAR NOT NULL
--)

-- CREATE TABLE Jeu (
-- 	id_jeu SERIAL PRIMARY KEY,
-- 	titre VARCHAR(30) NOT NULL,
-- 	reference NUMERIC(13, 0) UNIQUE NOT NULL,
-- 	description VARCHAR(255) NOT NULL,
-- 	tarifJournee NUMERIC(5, 2) NOT NULL,
-- 	ageMin NUMERIC(2, 0) CHECK (ageMin >= 0 AND ageMin <= 99) NOT NULL,
-- 	duree NUMERIC(5, 2) NOT NULL
-- );

-- CREATE TABLE Genre (
-- 	id_genre SERIAL PRIMARY KEY,
-- 	nom VARCHAR(100) NOT NULL
-- );

-- CREATE TABLE Jeu_Genre (
-- 	id_jeu INT,
-- 	id_genre INT,
-- 	PRIMARY KEY (id_jeu, id_genre),
-- 	CONSTRAINT fk_Jeu FOREIGN KEY (id_jeu) REFERENCES Jeu(id_jeu) ON DELETE CASCADE,
-- 	CONSTRAINT fk_Genre FOREIGN KEY (id_genre) REFERENCES Genre(id_genre) ON DELETE CASCADE
-- );

-- CREATE INDEX idx_Jeu_Genre_jeu ON Jeu_Genre(id_jeu);
-- CREATE INDEX idx_Jeu_Genre_genre ON Jeu_Genre(id_genre);

-- INSERT INTO Genre (nom) VALUES 
-- 	('Aventure'),
-- 	('Jeu de Cartes'),
-- 	('Stratégie'),
-- 	('Simulation'),
-- 	('Jeu de Rôle'),
-- 	('Puzzle');
-- INSERT INTO Jeu (titre, reference, description, tarifJournee, ageMin, duree) VALUES
-- ('The Legend of Heroes', 1234567890123, 'Un jeu épique où vous sauvez le royaume.', 9.99, 12, 40.5),
-- ('Puzzle Mania', 9876543210123, 'Un casse-tête addictif pour tous les âges.', 5.50, 3, 2.0),
-- ('Farm Simulator 2024', 1122334455667, 'Cultivez et gérez votre ferme moderne.', 8.75, 7, 25.0),
-- ('Epic Battles', 3322110099887, 'Affrontez vos ennemis dans des batailles épiques.', 12.00, 16, 50.0),
-- ('Kingdoms & Castles', 9988776655443, 'Construisez votre royaume et gérez vos ressources.', 7.25, 10, 35.0),
-- ('Brain Teasers', 6677889900112, 'Des puzzles stimulants pour entraîner votre esprit.', 6.50, 5, 3.5);


-- -- Relation Jeu et Genre (many-to-many)
-- INSERT INTO Jeu_Genre (id_jeu, id_genre) VALUES
-- -- 'The Legend of Heroes' (id_jeu=1)
-- (1, 1), -- Aventure
-- (1, 5), -- Jeu de Rôle
-- -- 'Puzzle Mania' (id_jeu=2)
-- (2, 6), -- Puzzle
-- -- 'Farm Simulator 2024' (id_jeu=3)
-- (3, 4), -- Simulation
-- -- 'Epic Battles' (id_jeu=4)
-- (4, 1), -- Aventure
-- (4, 3), -- Stratégie
-- (4, 5), -- Jeu de Rôle
-- -- 'Kingdoms & Castles' (id_jeu=5)
-- (5, 1), -- Aventure
-- (5, 3), -- Stratégie
-- -- 'Brain Teasers' (id_jeu=6)
-- (6, 6); -- Puzzle

CREATE TABLE Exemplaire_jeu (
	id_exemplaire SERIAL PRIMARY KEY,
	code_barre VARCHAR(13),
	louable BOOLEAN, 
	id_jeu INTEGER, 
	CONSTRAINT fk_Exemplaire_Jeu FOREIGN KEY (id_jeu) REFERENCES Jeu(id_jeu) ON DELETE CASCADE
);