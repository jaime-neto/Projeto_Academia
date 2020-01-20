package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import DB.DBEndereco;
import model.Endereco;

class DBEnderecoTest {
	DBEndereco db_end = null;
	Endereco end = null;
	
	@BeforeAll
	static void setMock() throws Exception {
		DBEndereco bdMock = new DBEndereco();
		Endereco endMock = new Endereco();
		endMock.setRua("dos bobos");
		endMock.setBairro("centro");
		endMock.setCidade("por ai");
		bdMock.cadEndereco(endMock);
	}
	
	@BeforeEach
	void setUp() throws Exception {
		db_end = new DBEndereco();
		end = new Endereco();
		end.setRua("Dos bobos");
		end.setBairro("centro");
		end.setCidade("Frutuoso Gomes");
		end.setIdEndereco(db_end.buscaUltimoEndereco().getIdEndereco());
	}

	@Test
	void cadEnd() {
		assertEquals(true, db_end.cadEndereco(end));
	}
	
	@Test
	void editEnd() {
		assertEquals(true, db_end.editEndereco(end));
	}
	
	@Test
	void delEnd() {
		assertEquals(true, db_end.deleteEndereco(end));
	}
	
	@Test
	void buscaUltimo() {
		assertTrue(db_end.buscaUltimoEndereco() instanceof Endereco);
	}
	
	@Test
	void buscaEnd() {
		assertTrue(db_end.buscaEndereco(end) instanceof Endereco);
	}
	
}
