package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Database.AbbonamentoAnnualeDAO;
import Database.AbbonamentoMensileDAO;
import Entity.AbbonamentoAnnualeEntity;
import Entity.AbbonamentoMensileEntity;

public class AbbAnnualeTest {

	@Test
	public void testAbbonamentoAnnualeEntityInt() {
		AbbonamentoAnnualeEntity abb = new AbbonamentoAnnualeEntity(213);
		
		System.out.println(abb.toString());
		
		assertEquals("01/06/23", abb.getDataSottoscrizione());
	}

	@Test
	public void testAbbonamentoAnnualeEntityAbbonamentoAnnualeDAO() {
		AbbonamentoAnnualeDAO dbm = new AbbonamentoAnnualeDAO(213);
		
		AbbonamentoAnnualeEntity abb = new AbbonamentoAnnualeEntity(dbm);
		
		System.out.println(abb.toString());
		
		assertEquals("01/06/24", abb.getDataScadenza());
	}
	
	
	@Test
	public void testAbbonamentoAnnualeinsert() {
		
		AbbonamentoAnnualeDAO db = new AbbonamentoAnnualeDAO();
		
		db.scriviAbbonamentoAnnuale(111, "01/07/23", "01/07/24", 250);
		
		System.out.println("Scritto!");
		
		AbbonamentoAnnualeEntity abb = new AbbonamentoAnnualeEntity(111);
		
		System.out.println(abb.toString());
		
		assertEquals("01/07/23", abb.getDataSottoscrizione());
	}

}

