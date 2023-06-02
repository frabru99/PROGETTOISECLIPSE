package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Entity.CalendarioEntity;
import Entity.GiornoEntity;

class CalendarioEntityTest {

	@Test
	void testGetInstance() {
		CalendarioEntity cal = CalendarioEntity.getInstance();
		
		System.out.println(cal);
		
		assertNotNull(cal);
		
	}

	@Test
	void testCercaGiorno() {
		
		CalendarioEntity cal = CalendarioEntity.getInstance();
		
		GiornoEntity giorno = cal.cercaGiorno("Martedi");
		
		
		System.out.println(giorno);

		assertEquals("Martedi", giorno.getNomeGiorno());
	}

}
