package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import DB.DBPagamento;
import model.Cliente;
import model.Funcionario;
import model.Pagamento;

class DBPagamengoTest {
	DBPagamento db_pag = null;
	Pagamento pag = null;
	@BeforeEach
	void setUp() throws Exception {
		db_pag = new DBPagamento();
		pag = new Pagamento();
		pag.setTipo("mensal");
		Funcionario f = new Funcionario();
		Cliente c = new Cliente();
		c.setIdCliente(24);
		f.setIdFunc(10);
		pag.setIdFunc(f);
		pag.setIdCliente(c);
		pag.setIdPag(2);
	}

	@Test
	void realizar_pag() {
		assertEquals(true, db_pag.realizarPagamento(pag));
	}
	
	@Test
	void edit_pag() {
		assertEquals(true, db_pag.editPagamento(pag));
	}
	
	@Test
	void delete_pag() {
		assertEquals(true, db_pag.deletePagamento(pag));
	}

}
