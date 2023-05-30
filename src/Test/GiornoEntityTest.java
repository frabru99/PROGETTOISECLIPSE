package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Database.GiornoDAO;
import Entity.GiornoEntity;

public class GiornoEntityTest {

	
	@Test
	public void testGiornoEntityString() {
		GiornoEntity giorno = new GiornoEntity("Lunedi");
		
		System.out.println(giorno);
		
		assertEquals("9:00", giorno.getOrarioAperturaCentro());
	}

	
	@Test
	public void testGiornoEntityGiornoDAO() {
		GiornoDAO giornodb = new GiornoDAO("Martedi");
		
		GiornoEntity giorno = new GiornoEntity(giornodb);
		
		System.out.println(giorno);
		
		assertEquals("8:00", giorno.getOrarioAperturaCentro());
		
	}

		
	@Test
	public void testScriviSuDb() {
		GiornoEntity giorno = new GiornoEntity();
		
		giorno.scriviSuDb("Mercoledi", "8:00", "23:00");
		
		System.out.println("Scritto!");
		
		GiornoEntity giorno2 = new GiornoEntity("Mercoledi");
		
		assertEquals("23:00", giorno2.getOrarioChiusuraCentro());
		
		System.out.println(giorno2);
		
		System.out.println("OKK!");
	}

}
