create database gymapp;

create TABLE endereco (
	id_end SERIAl,
  	rua VARCHAR(150),
  	bairro VARCHAR(120),
  	cidade VARCHAR(120),
  	PRIMARY KEY (id_end)
);

create table cliente (
	id_cli SERIAL,
  	nome VARCHAR(150),
  	cpf VARCHAR(12),
	telefone VARCHAR(12),
  	id_end INT NOT NULL,
  	PRIMARY KEY (id_cli),
  	FOREIGN KEY (id_end) REFERENCES endereco (id_end)
);

