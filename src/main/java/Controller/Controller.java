package Controller;

import java.util.ArrayList;

import Entity.AbbonamentoAnnualeEntity;
import Entity.CentroSportivoEntity;
import Entity.CorsoEntity;
import Entity.GiornoEntity;
import Entity.RegistroClientiEntity;

public class Controller {
	
	
	public Controller() {
		super();
	}
	
	public static ArrayList<CorsoEntity> ricercaCorsiDisponibili(String giorno_scelto){
		
		CentroSportivoEntity centro=CentroSportivoEntity.getInstance();
		
		ArrayList<CorsoEntity> corsi=centro.ricercaCorsiDisponibili(giorno_scelto);
		
		return corsi;
		
	}
	
	public static void AccessoAlCentro(String codiceCliente) {
		
		RegistroClientiEntity registro=RegistroClientiEntity.getInstance();
		registro.AccessoAlCentro(codiceCliente);
	}
	
	public static void EffetuaPrenotazione() {
		
		//Completato tutto quello che serviva per questa funzionalità
		//bisogna solo implementarla
		
		
	}
	
	//permette di ottenere la lista dei corsi, dato un giorno specifico
	public static ArrayList<CorsoEntity> listaCorsi(String giorno_scelto){
		GiornoEntity giorno = new GiornoEntity(giorno_scelto);
		ArrayList<CorsoEntity> corsi = new ArrayList<CorsoEntity>();
		corsi=giorno.getCorsi();
		return corsi;
	}
	
	//VARIANTE DELLA FUNZIONE PRECEDENTE: CHECKARE QUALE SIA LA PIU' CONVENIENTE
	public static String listaCorsi2(String giorno_scelto){
		GiornoEntity giorno = new GiornoEntity(giorno_scelto);
		ArrayList<CorsoEntity> corsi = new ArrayList<CorsoEntity>();
		corsi=giorno.getCorsi();
		return corsi.toString();
	}
	
	//permette di sottoscrivere un abbonamento annuale (CHECKARE)
	public static int sottoscriviAbbonamentoAnnuale() {
		AbbonamentoAnnualeEntity abbann = new AbbonamentoAnnualeEntity();
		int ret=abbann.scriviAbbsuDB();
		return ret;
	}
	
	//FARE NUOVO METODO DI INSERT IN ABBANNUALEDAO IN CUI SI FA L'UPDATE DELLA DATA DI SOSPENSIONE 
//	public static int sospendiAbbonamentoAnnuale() {
//		AbbonamentoAnnualeEntity abbann = new AbbonamentoAnnualeEntity();
//	}
	
}