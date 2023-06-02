package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Database.PrenotazioneDAO;
import Entity.ClienteIscrittoEntity;
import Entity.CorsoEntity;
import Entity.PrenotazioneEntity;

class PrenotazioneEntityTest {

	@Test
	void testPrenotazioneEntityClienteIscrittoEntityCorsoEntity() {
		ClienteIscrittoEntity cl = new ClienteIscrittoEntity("Cliente_3");
		
		CorsoEntity ce = new CorsoEntity(125);
		
		PrenotazioneEntity pe = new PrenotazioneEntity(cl, ce);
		
		System.out.println(pe); //parlare di falla della prenotazione!
		
		assertEquals("odiolajuve@gmail.com", pe.getEmailCliente());
	}

	@Test
	void testPrenotazioneEntityInt() {
		
		
		PrenotazioneEntity pe = new PrenotazioneEntity(568);
		
		
		System.out.println(pe);
		
		assertEquals(568, pe.getIdPrenotazione());
		
	}

	@Test
	void testPrenotazioneEntityPrenotazioneDAO() {
		
		
		PrenotazioneDAO p = new PrenotazioneDAO(567);
		
		PrenotazioneEntity pe = new PrenotazioneEntity(p);
		
		System.out.println(pe);
		
		assertEquals(567, pe.getIdPrenotazione());
	}


}
