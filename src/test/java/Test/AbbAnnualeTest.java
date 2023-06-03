package Test;

import static org.junit.Assert.*;

import java.sql.Date;

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
		
		AbbonamentoAnnualeEntity abb = new AbbonamentoAnnualeEntity();
		
		abb.scriviAbbsuDB();
		
		System.out.println("Scritto su DB");
		
		
		AbbonamentoAnnualeEntity abbon = new AbbonamentoAnnualeEntity(214);
		
		
		System.out.println(abbon);
		
		
		assertEquals(214, abbon.getIdAbbonamentoAnnuale());
		
	}
	
	@Test
	public void testAbbonamentoAnnualeupdate() {
		
		AbbonamentoAnnualeEntity abb = new AbbonamentoAnnualeEntity();
		
		Date dataSospensione = new java.sql.Date(System.currentTimeMillis());
		
	
		Date dataRipresa = AbbonamentoAnnualeEntity.addDays(dataSospensione, 30);
		
		abb.sospendiAbbonamento(213, dataSospensione, dataRipresa);
		
		System.out.println("Updated!");
		
		
		AbbonamentoAnnualeEntity abbon = new AbbonamentoAnnualeEntity(213);
		
		System.out.println(abbon);
		
		
		assertEquals(213, abbon.getIdAbbonamentoAnnuale());
		
	}
	
	
	
	
	

}

