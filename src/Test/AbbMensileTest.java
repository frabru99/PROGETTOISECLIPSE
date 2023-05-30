package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Database.AbbonamentoAnnualeDAO;
import Database.AbbonamentoMensileDAO;
import Entity.AbbonamentoAnnualeEntity;
import Entity.AbbonamentoMensileEntity;

public class AbbMensileTest {

	@Test
	public void testAbbonamentoMensileEntityInt() {
		
		AbbonamentoMensileEntity abb = new AbbonamentoMensileEntity(234);
		
		System.out.println(abb.toString());
		
		assertEquals("01/05/23", abb.getDataSottoscrizione());
		
	}

	@Test
	public void testAbbonamentoMensileEntityAbbonamentoMensileDAO() {
		
		AbbonamentoMensileDAO dbm = new AbbonamentoMensileDAO(567);
		
		AbbonamentoMensileEntity abb = new AbbonamentoMensileEntity(dbm);
		
		System.out.println(abb.toString());
		
		assertEquals("01/06/23", abb.getDataSottoscrizione());
	}
	
	@Test
	public void testAbbonamentoMensileinsert() {
		
		AbbonamentoMensileDAO db = new AbbonamentoMensileDAO();
		
		db.scriviAbbonamentoMensile(333, "01/01/23", "01/02/23", 40, "Gennaio");
		
		System.out.println("Scritto!");
		
		AbbonamentoMensileEntity abb = new AbbonamentoMensileEntity(333);
		
		System.out.println(abb.toString());
		
		assertEquals("01/01/23", abb.getDataSottoscrizione());
	}

}
