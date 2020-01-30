create database gymapp;

create TABLE endereco (
	id_end SERIAl,
  	rua VARCHAR(150) NOT NULL,
  	bairro VARCHAR(120) NOT NULL,
  	cidade VARCHAR(120) NOT NULL,
  	PRIMARY KEY (id_end)
);

create table cliente (
	id_cli SERIAL,
  	nome VARCHAR(150) NOT NULL,
  	cpf VARCHAR(12) NOT NULL,
	telefone INTEGER NOT NULL,
  	id_end INT NOT NULL,
  	PRIMARY KEY (id_cli),
  	FOREIGN KEY (id_end) REFERENCES endereco (id_end)
);


create TABLE funcionario (
	id_func SERIAL, 
	nome VARCHAR(150) NOT NULL,
	usuario VARCHAR(150) NOT NULL,
	senha VARCHAR(150) NOT NULL,
	cpf VARCHAR(12) NOT NULL,
	salario FLOAT NOT NULL, 
	PRIMARY KEY (id_func)
);

create TABLE pagamento (
	id_pag SERIAL, 
	data DATE NOT NULL,
	tipo VARCHAR(150) NOT NULL,
	id_func INT NOT NULL,
	id_cli INT NOT NULL,
	valor FLOAT,
	PRIMARY KEY (id_pag),
	FOREIGN KEY (id_func) REFERENCES funcionario (id_func),
	FOREIGN KEY (id_cli) REFERENCES cliente(id_cli)
);

