CREATE TABLE usuario (
	id int PRIMARY KEY AUTO_INCREMENT,
	login VARCHAR(50) NOT NULL,
	senha VARCHAR(32) NOT NULL,
	perfil VARCHAR(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario (login, senha, perfil) values ('admin', '202cb962ac59075b964b07152d234b70', 'ADMINISTRADOR');
