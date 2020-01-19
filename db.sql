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


create TABLE funcionario (
	id_func SERIAL, 
	nome VARCHAR(150),
	usuario VARCHAR(150),
	senha VARCHAR(150),
	cpf VARCHAR(12),
	salario FLOAT, 
	PRIMARY KEY (id_func)
);

create TABLE pagamento (
	id_pag SERIAL, 
	data DATE,
	id_func INT NOT NULL,
	id_cli INT NOT NULL,
	PRIMARY KEY (id_pag),
	FOREIGN KEY (id_func) REFERENCES funcionario (id_func),
	FOREIGN KEY (id_cli) REFERENCES cliente(id_cli)
);

