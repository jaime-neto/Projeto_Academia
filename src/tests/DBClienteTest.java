package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import DB.DBCliente;
import DB.DBEndereco;
import model.Cliente;
import model.Endereco;

class DBClienteTest {
	DBCliente db_cli;
	DBEndereco db_end;
	Cliente cli;
	Endereco end;
	
	@BeforeEach
	void setUp() throws Exception {
		db_end = new DBEndereco();
		db_cli = new DBCliente();
		cli = new Cliente();
		cli.setNome("Bruno");
		cli.setTelefone("998785855");
		cli.setCpf("70318274426");
		end = new Endereco();
		end.setIdEndereco(db_end.buscaUltimoEndereco().getIdEndereco());
		cli.setEndereco(end);
		cli.setIdCliente(20);
	}

	@Test
	void cad_cli() {
		assertEquals(true, db_cli.cadCliente(cli));
	}
	
	@Test
	void del_cli() {
		assertEquals(true, db_cli.deleteCliente(cli));
	}
	
	@Test
	void edit_cli() {
		assertEquals(true, db_cli.editCliente(cli));
	}
}
