package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import DB.DBEndereco;
import model.Endereco;

class DBEnderecoTest {
	DBEndereco db_end = null;
	Endereco end = null;
	
	@BeforeEach
	void setUp() throws Exception {
		db_end = new DBEndereco();
		end = new Endereco();
		end.setRua("Dos bobos");
		end.setBairro("centro");
		end.setCidade("Frutuoso Gomes");
		end.setIdEndereco(15);
	}

	@Test
	void cad_end() {
		assertEquals(true, db_end.cadEndereco(end));
	}
	
	@Test
	void edit_end() {
		//end.setIdEndereco(db_end.buscaUltimoEndereco().getIdEndereco());
		assertEquals(true, db_end.editEndereco(end));
	}
	
	@Test
	void del_end() {
		assertEquals(true, db_end.deleteEndereco(end));
	}
	
	@Test
	void buscaUltimo() {
		assertTrue(db_end.buscaUltimoEndereco() instanceof Endereco);
	}
	
}
