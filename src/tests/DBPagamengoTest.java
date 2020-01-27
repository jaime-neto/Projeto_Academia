package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import DB.DBCliente;
import DB.DBFuncionario;
import DB.DBPagamento;
import model.Cliente;
import model.Funcionario;
import model.Pagamento;

class DBPagamengoTest {
	DBPagamento db_pag = null;
	Pagamento pag = null;
	
	//Rodar TestsClientes e TestsFuncionarios primeiro 
	@BeforeAll
	static void setMock() throws Exception {
		DBPagamento bdMock = new DBPagamento();
		Pagamento pagMock = new Pagamento();
		pagMock.setTipo("mensal");

		DBCliente db_cliMock = new DBCliente();
		Cliente c = new Cliente();
		c.setIdCliente(db_cliMock.buscaUltimoCliente().getIdCliente());
		pagMock.setIdCliente(c);

		DBFuncionario db_funcMock = new DBFuncionario();
		Funcionario f = new Funcionario();
		f.setIdFunc(db_funcMock.buscaUltimoFuncionario().getIdFunc());
		pagMock.setIdFunc(f);
		
		Date data = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		pagMock.setData(dateFormat.format(data));
		
		bdMock.realizarPagamento(pagMock);
	}
	
	@BeforeEach
	void setUp() throws Exception {
		db_pag = new DBPagamento();
		pag = new Pagamento();
		pag.setTipo("mensal");
		
		DBCliente db_cli = new DBCliente();
		Cliente c = new Cliente();
		c.setIdCliente(db_cli.buscaUltimoCliente().getIdCliente());
		pag.setIdCliente(c);

		DBFuncionario db_func = new DBFuncionario();
		Funcionario f = new Funcionario();
		f.setIdFunc(db_func.buscaUltimoFuncionario().getIdFunc());
		pag.setIdFunc(f);
		pag.setIdPag(db_pag.buscaUltimoPagamento().getIdPag());
		Date data = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		pag.setData(dateFormat.format(data));
	}

	@Test
	void realizarPag() {
		assertEquals(true, db_pag.realizarPagamento(pag));
	}
	
	@Test
	void editPag() {
		assertEquals(true, db_pag.editPagamento(pag.getIdPag(), pag));
	}
	
	@Test
	void deletePag() {
		assertEquals(true, db_pag.deletePagamento(pag.getIdPag()));
	}
	
	@Test
	void buscaUltimoPag() {
		assertTrue(db_pag.buscaUltimoPagamento() instanceof Pagamento);
	}
	
	@Test
	void buscaTodosPag() {
		assertTrue(db_pag.buscarTodosPagamentos(pag.getCli().getIdCliente()) instanceof ArrayList<?>);
	}

}
