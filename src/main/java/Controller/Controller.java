package Controller;

import java.util.ArrayList;

import Entity.AbbonamentoAnnualeEntity;
import Entity.CentroSportivoEntity;
import Entity.CorsoEntity;
import Entity.GiornoEntity;
import Entity.PrenotazioneEntity;
import Entity.RegistroClientiEntity;

public class Controller {
	
	private static Controller uniqueInstance;
	
	//Costruttore
	private Controller() {
		
		super();
		
	}
	
	//getInstance singleton
	public static Controller getInstance() {
        
	    if (uniqueInstance == null) {
	            
	    	uniqueInstance = new Controller();
	        
	            
	    }
	        
	    return uniqueInstance;
	        
	}
	
	public static ArrayList<CorsoEntity> ricercaCorsiDisponibili(String giorno_scelto){
		
		CentroSportivoEntity centro=CentroSportivoEntity.getInstance();
		
		ArrayList<CorsoEntity> corsi=centro.ricercaCorsiDisponibili(giorno_scelto);
		
		return corsi;
		
	}
	
	public static boolean AccessoAlCentro(String codiceCliente,String email) {
		
		boolean val = false;
		RegistroClientiEntity registro=RegistroClientiEntity.getInstance();
		val =registro.AccessoAlCentro(codiceCliente,email);
		
		return val;
		
	}
	
	public static void EffettuaPrenotazione() {
		
		//Completato tutto quello che serviva per questa funzionalità
		//bisogna solo implementarla
		
		
		
	}
	
	//permette di ottenere la lista dei corsi, dato un giorno specifico
	public static ArrayList<CorsoEntity> ricercaCorsiOggetto(String giorno_scelto){
		GiornoEntity giorno = new GiornoEntity(giorno_scelto, false);
		ArrayList<CorsoEntity> corsi = new ArrayList<CorsoEntity>();
		corsi=giorno.getCorsi();
		return corsi;
	}
	
	//VARIANTE DELLA FUNZIONE PRECEDENTE: CHECKARE QUALE SIA LA PIU' CONVENIENTE
	public static String ricercaCorsiStringa(String giorno_scelto){
		GiornoEntity giorno = new GiornoEntity(giorno_scelto, false);
		ArrayList<CorsoEntity> corsi = new ArrayList<CorsoEntity>();
		corsi=giorno.getCorsi();
		return corsi.toString();
	}
	
	public static ArrayList<String> ricercaCorsiArrayStringa(String giorno_scelto){
		GiornoEntity giorno = new GiornoEntity(giorno_scelto, true);
		ArrayList<String> corsi = new ArrayList<String>();
		for(int i=0;i<giorno.getCorsi().size();i++) {
			String corso = giorno.getCorsi().get(i).toString();
			System.out.println(corso);
			
			corsi.add(corso);
		}
		return corsi;
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
	
	
	public static int scriviPrenotazione(String codiceCliente, String email, int codiceCorso) {
		
		PrenotazioneEntity pren = new PrenotazioneEntity();
		
		int val = pren.controllerScriviSuDB(codiceCliente, email, codiceCorso);
		
		return val; //funzione di scrittura prenotazione da Boundary		
	}
	
}