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
		
		AbbonamentoMensileEntity abb = new AbbonamentoMensileEntity();
		
		abb.scrivisuDB("Gennaio");
		
		System.out.println("Scritto!");
		
		AbbonamentoMensileEntity abbon = new AbbonamentoMensileEntity(568);
		
		System.out.println(abbon.toString());
		
		assertEquals("2023-01-01", abb.getDataSottoscrizione());
	}

}
