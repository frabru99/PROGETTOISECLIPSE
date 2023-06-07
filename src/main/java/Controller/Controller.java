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
	
	
	
	//Metodo che permette o vieta l'accesso al centro ad un cliente
	public static boolean AccessoAlCentro(String codiceCliente,String email) {
		
		boolean val = false;
		//Richiamo il singleton del registro clienti ed evoco il suo metodo AccessoAlCentro
		RegistroClientiEntity registro=RegistroClientiEntity.getInstance();
		val =registro.AccessoAlCentro(codiceCliente,email);
		
		return val;
		
	}
	
	
	//Metodo che ricerca i corsi disponibili dato il nome di un giorno da un cliente e ritorna un array di stringhe
	public static ArrayList<String> ricercaCorsiConDisponibilita(String giorno_scelto){
			
		//Richiamo il singleton del centro sportivo ed evoco il suo metodo ricercaCorsiDisponibili
		CentroSportivoEntity centro=CentroSportivoEntity.getInstance();
		ArrayList<CorsoEntity> corsidisp = centro.ricercaCorsiConDisponibilita(giorno_scelto);
		ArrayList<String> corsistr = new ArrayList<String>();
		
		for(int k=0;k<corsidisp.size();k++) {
			
			String corso = corsidisp.get(k).toString();
			System.out.println(corso);
			corsistr.add(corso);
			
		}
		
		return corsistr;
		
	}
	
	
	//Permette di sottoscrivere un abbonamento annuale (CHECKARE)
	public static int sottoscriviAbbonamentoAnnuale() {
		AbbonamentoAnnualeEntity abbann = new AbbonamentoAnnualeEntity();
		int ret=abbann.scriviSuDB();
		return ret;
	}
	

	//Metodo DUMMY che permette di settare la data di sospensione e di ripresa di un abbonamento annuale
	public static int sospendiAbbonamentoAnnuale(String codiceCliente, String dataSospensione, String dataRipresa) {
		
		int ret;
		ClienteIscrittoEntity cliente = new ClienteIscrittoEntity(codiceCliente);
		ret=cliente.sospendiAbbonamentoAnnualeEntity(dataSospensione, dataRipresa);
		return ret;
		
	}
	
	
	//Metodo per la funzionalità effettuaPrenotazione - Permette di salvare una richiesta di prenotazione effettuata da un cliente per un corso
	public static int effettuaPrenotazione(String codiceCliente, int codiceCorso) {
		
		//Provo a scrivere la prenotazione sul DB
		ClienteIscrittoEntity cliente = new ClienteIscrittoEntity(codiceCliente);
		int val = cliente.controllerScriviPrenotazioneSuDB(codiceCorso);
		
		//In caso di esito positivo
		if(val!=-1) {
		
		//Recupero tutti i giorni dal calendario
		ArrayList<GiornoEntity> giorni  = CalendarioEntity.getGiorni();
		
		//Scorro l'array di giorni
		for(int i=0; i<giorni.size(); i++) {
			
			//Scorro l'array di corsi per ogni giorno
			for(int j=0; j<giorni.get(i).getCorsi().size(); j++) {
				
				//Se il corso desiderato è quello richiesto
				if(giorni.get(i).getCorsi().get(j).getCodiceCorso()==codiceCorso) {
					
					//Decremento i posti disponibili
					giorni.get(i).getCorsi().get(j).decrementaPostiDisponibili();
					
					}
				
				}
			
			}
		
		}
		
		return val;
		
	}
	
	
	//Funzione di utility che permette di controllare se, data una PK, esiste un abbonamento per un cliente
	public static int checkAbbonamento (String codiceCliente) {
		
        ClienteIscrittoEntity cliente = new ClienteIscrittoEntity(codiceCliente);
        int val = cliente.checkAbbonamento();
        return val;
        
	}
	
	
	//Funzione di utility che permette di controllare se, data una PK, esiste un corso ed ha disponibilità di posti
	public static int checkDisponibilitaCorso(int idCorso) {
		
        CorsoEntity corso = new CorsoEntity();
        int val = corso.checkDisponibilitaCorso(idCorso);
        return val;
        
	}
	
	
}