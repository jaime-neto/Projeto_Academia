package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import DB.DBFuncionario;
import model.Endereco;
import model.Funcionario;

class DBFuncionarioTest {
	DBFuncionario db_func = null;
	Funcionario func = null;
	
	@BeforeAll
	static void setMock() {
		DBFuncionario db_funcMock = new DBFuncionario();
		Funcionario funcMock = new Funcionario();
		db_funcMock.cadFuncionario("Joseph","70318248878",1000, "josephFunc", "12345");
	}
	
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
	void cadFunc() {
		assertEquals(true, db_func.cadFuncionario(func.getNome(), func.getCpf(), func.getSalario(), func.getUsuario(), func.getSenha()));
	}
	
	@Test
	void editFunc() {
		func.setNome("John");
		assertEquals(true, db_func.editFuncionario(func.getIdFunc(),func));
	}
	
	@Test
	void deleteFunc() {
		assertEquals(true, db_func.deleteFuncionario(func.getIdFunc()));
	}
	
	@Test
	void buscaUltimoFunc() {
		assertTrue(db_func.buscaUltimoFuncionario() instanceof Funcionario);
	}
	
	@Test
	void buscarTodosFunc() {
		assertTrue(db_func.buscarTodosFuncionarios() instanceof ArrayList<?>);
		assertTrue(db_func.buscarTodosFuncionarios().size() > 0);
	}
	
	@Test
	void buscaFuncionario() {
		assertTrue(db_func.buscaFuncionario(func.getIdFunc()) instanceof Funcionario);
	}

}
