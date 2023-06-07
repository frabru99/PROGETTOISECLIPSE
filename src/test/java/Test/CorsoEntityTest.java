package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Database.CorsoDAO;
import Entity.CorsoEntity;

public class CorsoEntityTest {

	@Test
	public void testCorsoEntityInt() {
		
		CorsoEntity corso = new CorsoEntity(123);
		
		System.out.println(corso.toString());
		
		assertEquals("Zumba", corso.getNomeCorso());
		
	}

	@Test
	public void testCorsoEntityCorsoDAO() {
		
		CorsoDAO corsodb = new CorsoDAO(124);
		
		CorsoEntity corso = new CorsoEntity(corsodb);
		
		System.out.println(corso);
		
		
		assertEquals("Pilates", corso.getNomeCorso());
		
	}

	@Test
	public void testScriviSuDb() {
		
		
		CorsoEntity corso = new CorsoEntity();
		

		corso.scriviSuDB("Samba", "Antonio Boccarossa", "16:00", "2:30 h", 30 ,12);
		
		CorsoEntity cor = new CorsoEntity(126);
		
		System.out.println(cor.toString());
		
		assertEquals("Antonio Boccarossa", cor.getIstruttore());
		
		
		
	}

}
