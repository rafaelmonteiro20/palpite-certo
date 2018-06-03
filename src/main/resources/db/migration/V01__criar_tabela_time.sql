CREATE TABLE time (
	id int PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	estado VARCHAR(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO time (nome, estado) values ('Fluminense', 'RJ');
INSERT INTO time (nome, estado) values ('Santos', 'SP');
INSERT INTO time (nome, estado) values ('Atlético Mineiro', 'MG');
INSERT INTO time (nome, estado) values ('Grêmio', 'RS');
INSERT INTO time (nome, estado) values ('Corinthians', 'SP');
INSERT INTO time (nome, estado) values ('Palmeiras', 'SP');
INSERT INTO time (nome, estado) values ('Internacional', 'RS');
INSERT INTO time (nome, estado) values ('Ceará', 'CE');
INSERT INTO time (nome, estado) values ('Flamengo', 'RJ');
INSERT INTO time (nome, estado) values ('São Paulo', 'SP');
