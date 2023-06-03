package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Database.ClienteIscrittoDAO;
import Entity.ClienteIscrittoEntity;

public class ClienteIscrittoTest {

	@Test
	public void testClienteIscrittoEntity() {
		
		ClienteIscrittoEntity cl = new ClienteIscrittoEntity("Cliente_4");
		
		System.out.println(cl);
		
		
		assertEquals("cicciopasticcio@gmail.com", cl.getEmail());
		
	}

	@Test
	public void testClienteIscrittoEntityClienteIscrittoDAO() {
		
		
		ClienteIscrittoDAO cldb = new ClienteIscrittoDAO("Cliente_3");
		
		ClienteIscrittoEntity cl = new ClienteIscrittoEntity(cldb);
		
		
		System.out.println(cl);
		
		
		assertEquals(213, cl.getIdAbbonamentoAnnuale());
		
		
		
	}

	@Test
	public void testScriviSuDb() {
		
		ClienteIscrittoEntity dao  = new ClienteIscrittoEntity();
		
		dao.scriviSuDb("Antonio", "de Gasperi","deGasp@gmail.com");
		
		
		ClienteIscrittoEntity cl = new ClienteIscrittoEntity("Cliente_6");
		
		System.out.println(cl);
		
		assertEquals("Antonio", cl.getNome());
		
	}

}
