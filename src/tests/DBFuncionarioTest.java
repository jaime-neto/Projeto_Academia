package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import DB.DBFuncionario;
import model.Endereco;
import model.Funcionario;

class DBFuncionarioTest {
	DBFuncionario db_func = null;
	Funcionario func = null;
	
	@BeforeEach
	void setUp() throws Exception {
		db_func = new DBFuncionario();
		func = new Funcionario();
		func.setNome("Joseph");
		func.setCpf("70318248878");
		func.setUsuario("josephFunc");
		func.setSenha("12345");
		func.setSalario(1000);
		func.setIdFunc(db_func.buscaUltimoFuncionario().getIdFunc());
	}

	@Test
	void cad_func() {
		assertEquals(true, db_func.cadFuncionario(func));
	}
	
	@Test
	void edit_func() {
		func.setNome("John");
		
		assertEquals(true, db_func.editFuncionario(func));
	}
	
	@Test
	void delete_func() {
		//func.setIdFunc(db_func.buscaUltimoFuncionario().getIdFunc());
		assertEquals(true, db_func.deleteFuncionario(func));
	}
	
	@Test
	void buscaUltimoFunc() {
		assertTrue(db_func.buscaUltimoFuncionario() instanceof Funcionario);
	}

}
