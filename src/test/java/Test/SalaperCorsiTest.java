package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Database.SalaperCorsiDAO;
import Entity.SalaperCorsiEntity;

public class SalaperCorsiTest {

	@Test
	public void testSalaperCorsiEntityInt() {
		
		SalaperCorsiEntity sala =  new SalaperCorsiEntity(11);
		
		
		assertEquals(11, sala.getIdSalaCorsi());
		
		System.out.println(sala);
		
	}

	@Test
	public void testSalaperCorsiEntitySalaperCorsiDAO() {
		SalaperCorsiDAO saladao = new SalaperCorsiDAO(12);
		
		SalaperCorsiEntity sala = new SalaperCorsiEntity(saladao);
		
		assertEquals(12, sala.getIdSalaCorsi());
		
		System.out.println(sala);
	}

	@Test
	public void testScriviSuDB() {
		SalaperCorsiEntity sala = new SalaperCorsiEntity();
		
		sala.ScriviSuDB(13, 27);
		
		SalaperCorsiEntity sala1 =  new SalaperCorsiEntity(13);
		
		
		assertEquals(13, sala1.getIdSalaCorsi());
		
		System.out.println(sala1);
		
		
	}

}
