package Controller;

import java.util.ArrayList;

import Entity.AbbonamentoAnnualeEntity;
import Entity.CalendarioEntity;
import Entity.CentroSportivoEntity;
import Entity.ClienteIscrittoEntity;
import Entity.CorsoEntity;
import Entity.GiornoEntity;
import Entity.PrenotazioneEntity;
import Entity.RegistroClientiEntity;
import Entity.SalaperCorsiEntity;

public class Controller {
	
	private static Controller uniqueInstance; //uniqueInstance - variabile per implementazione del pattern Singleton
	
	
	//Costruttore vuoto
	private Controller() {
		
		super();
		
	}
	
	
	//Metodo getInstance per implementare il pattern Singleton - crea il Singleton se l'istanza è null oppure la ritorna
	public static Controller getInstance() {
        
	    if (uniqueInstance == null) {
	            
	    	uniqueInstance = new Controller();
	        
	            
	    }
	        
	    return uniqueInstance;
	        
	}
	
	
	//Metodo che ricerca i corsi disponibili dato il nome di un giorno da un cliente
	public static ArrayList<CorsoEntity> ricercaCorsiDisponibili(String giorno_scelto){
		
		//Richiamo il singleton del centro sportivo ed evoco il suo metodo ricercaCorsiDisponibili
		CentroSportivoEntity centro=CentroSportivoEntity.getInstance();
		ArrayList<CorsoEntity> corsi=centro.ricercaCorsiDisponibili(giorno_scelto);
		
		return corsi;
		
	}
	
	
	//Metodo che permette o vieta l'accesso al centro ad un cliente
	public static boolean AccessoAlCentro(String codiceCliente,String email) {
		
		boolean val = false;
		//Richiamo il singleton del registro clienti ed evoco il suo metodo AccessoAlCentro
		RegistroClientiEntity registro=RegistroClientiEntity.getInstance();
		val =registro.AccessoAlCentro(codiceCliente,email);
		
		return val;
		
	}

	
	//Metodo che ricerca i corsi disponibili dato il nome di un giorno da un cliente -- CONTROLLARE
	public static ArrayList<CorsoEntity> ricercaCorsiOggetto(String giorno_scelto){
		
		GiornoEntity giorno = new GiornoEntity(giorno_scelto);
		ArrayList<CorsoEntity> corsi = new ArrayList<CorsoEntity>();
		corsi=giorno.getCorsi();
		return corsi;
		
	}
	
	//Metodo che ricerca i corsi disponibili dato il nome di un giorno da un cliente e ritorna una stringa
	public static String ricercaCorsiStringa(String giorno_scelto){
		
		GiornoEntity giorno = new GiornoEntity(giorno_scelto);
		
		//Due array per differenziare tutti i corsi, e quelli con disponibilità di posti
		ArrayList<CorsoEntity> corsi = new ArrayList<CorsoEntity>();
		ArrayList<CorsoEntity> corsidisp = new ArrayList<CorsoEntity>();
		corsi=giorno.getCorsi();
		
		for(int i =0; i < corsi.size(); i++){
			
			if(corsi.get(i).getPostiDisponibili()>0) {
				
				corsidisp.add(corsi.get(i)); 
				
			}
			
		}
		
		return corsidisp.toString();
		
	}
	
	
	//Metodo che ricerca i corsi disponibili dato il nome di un giorno da un cliente e ritorna un array di stringhe
	public static ArrayList<String> ricercaCorsiArrayStringa(String giorno_scelto){
		
		//GiornoEntity giorno = new GiornoEntity(giorno_scelto);
//
//		ArrayList<CorsoEntity> corsi = new ArrayList<CorsoEntity>();
//		
//		corsi = giorno.getCorsi();
		
		
		ArrayList<CorsoEntity> corsidisp = ricercaCorsiDisponibili(giorno_scelto);
		
		
		
		ArrayList<String> corsistr = new ArrayList<String>();
		
		for(int k=0;k<corsidisp.size();k++) {
			String corso = corsidisp.get(k).toString();
			System.out.println(corso);
			
			corsistr.add(corso);
		}
		
	
		
		return corsistr;
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
	
	
	public static int scriviPrenotazione(String codiceCliente, int codiceCorso) {
		
		ClienteIscrittoEntity cliente = new ClienteIscrittoEntity(codiceCliente);
		
	
		int val = cliente.controllerScriviPrenotazioneSuDB(codiceCorso);
		
		
		if(val!=-1) {
		
		ArrayList<GiornoEntity> giorni  = CalendarioEntity.getGiorni();
		
		
		for(int i=0; i<giorni.size(); i++) {
			for(int j=0; j<giorni.get(i).getCorsi().size(); j++) {
				if(giorni.get(i).getCorsi().get(j).getCodiceCorso()==codiceCorso) {
					giorni.get(i).getCorsi().get(j).decrementaPostiDisponibili();
					}
				}
			}
		
		}
		
		return val; //funzione di scrittura prenotazione da Boundary		
	}
	
	
	public static int checkAbbonamento (String codiceCliente) {
		
        ClienteIscrittoEntity cliente = new ClienteIscrittoEntity(codiceCliente);
        
        int val = cliente.checkAbbonamento();
        
        return val; //funzione di scrittura prenotazione da Boundary
	}
	
	public static int checkCorso(int idCorso) {
		
        CorsoEntity corso = new CorsoEntity();
        
        int val = corso.checkCorso(idCorso);
        
        return val; //funzione di check corso!
	}
	
	
}