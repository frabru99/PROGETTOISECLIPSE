package Controller;

import java.util.ArrayList;

import Entity.AbbonamentoAnnualeEntity;
import Entity.CorsoEntity;
import Entity.GiornoEntity;

public class Controller {
	
	
	public Controller() {
		super();
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
		int ret=abbann.ControllerScrivisuDB();
		return ret;
	}
	
	//FARE NUOVO METODO DI INSERT IN ABBANNUALEDAO IN CUI SI FA L'UPDATE DELLA DATA DI SOSPENSIONE 
	public static int sospendiAbbonamentoAnnuale() {
		AbbonamentoAnnualeEntity abbann = new AbbonamentoAnnualeEntity();
	}
	
}
