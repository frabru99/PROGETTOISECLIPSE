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

/**
 * Classe del package Controller per l'interazione tra layer Boundary e layer Entity
 * @author Salvatore Cangiano
 * @author Giovanni Ciccarelli
 * @author Antonio Boccarossa
 * @author Francesco Brunello
 * @version 09/06/2023
 *
 */

public class Controller {
	
	/**
	 * Variabile per implementazione del pattern Singleton
	 */
	
	private static Controller uniqueInstance; 
	
	
	//Costruttore vuoto
	private Controller() {
		
		super();
		
	}
	
	
	/**
	 * Metodo getInstance per implementare il pattern Singleton - crea il Singleton se l'istanza è null oppure la ritorna
	 * @return uniqueInstance
	 */
	public static Controller getInstance() {
        
	    if (uniqueInstance == null) {
	            
	    	uniqueInstance = new Controller();
	        
	            
	    }
	        
	    return uniqueInstance;
	        
	}
	
	
	
	/**
	 * Metodo che permette o vieta l'accesso al centro ad un cliente
	 * @param codiceCliente
	 * @param email
	 * @return esito login
	 */
	public static boolean LoginCentroSportivo(String codiceCliente,String email) {
		
		boolean val = false;
		//Richiamo il singleton del registro clienti ed evoco il suo metodo AccessoAlCentro
		RegistroClientiEntity registro=RegistroClientiEntity.getInstance();
		val =registro.LoginCentroSportivo(codiceCliente,email);
		
		return val;
		
	}
	
	
	/**
	 * Metodo che ricerca i corsi con disponibilità di posti dato il nome di un giorno da un cliente e ritorna un array di stringhe
	 * @param giorno_scelto
	 * @return corsi prenotabili
	 */
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
	
	
	/**
	 * Metodo che permette di sottoscrivere un abbonamento mensile
	 * @param codiceCliente
	 * @param nomeMese
	 * @param dataSottoscrizione
	 * @param dataScadenza
	 * @return esito abbonamento
	 */
	public static int sottoscriviAbbonamentoMensile(String codiceCliente,String nomeMese,String dataSottoscrizione, String dataScadenza) {
		
		int ret = 0;
		
		//Controllo prima se il cliente ha già un abbonamento
		int value = AccessoAlCentro(codiceCliente); //Check abbonamento - riuso codice
		
		//Se non c'è un abbonamento presente posso crearlo
		if(value==0) {
		
		ClienteIscrittoEntity cliente=new ClienteIscrittoEntity(codiceCliente);
		ret=cliente.sottoscriviAbbonamentoMensile(nomeMese,dataSottoscrizione,dataScadenza);
		
		
		}
		else if (value ==1 || value ==2) {
			ret = 2;	//Cliente già abbonato
		}
		
		return ret;
	}
	
	
	/**
	 * Metodo che permette di sottoscrivere un abbonamento annuale
	 * @param codiceCliente
	 * @param nomeMese
	 * @param dataSottoscrizione
	 * @param dataScadenza
	 * @return esito abbonamento
	 */
	public static int sottoscriviAbbonamentoAnnuale(String codiceCliente,String dataSottoscrizione, String dataScadenza) {
		
		int ret =0;
		
		//Controllo prima se il cliente ha già un abbonamento
        int value = AccessoAlCentro(codiceCliente); //Check abbonamento - riuso codice
        
        //Se non c'è un abbonamento presente posso crearlo
        if(value==0) {
        
        ClienteIscrittoEntity cliente=new ClienteIscrittoEntity(codiceCliente);
        ret=cliente.sottoscriviAbbonamentoAnnuale(dataSottoscrizione,dataScadenza);
        
        
        } else if (value ==1 || value ==2) {
            ret = 2; //Cliente già abbonato
        }
        
        return ret;
		
	}
	

	/**
	 * Metodo DUMMY che permette di settare la data di sospensione e di ripresa di un abbonamento annuale
	 * @param codiceCliente
	 * @param dataSospensione
	 * @param dataRipresa
	 * @return esito sospensione
	 */
	public static int sospendiAbbonamentoAnnuale(String codiceCliente, String dataSospensione, String dataRipresa) {
		
		int ret;
		ClienteIscrittoEntity cliente = new ClienteIscrittoEntity(codiceCliente);
		ret=cliente.sospendiAbbonamentoAnnuale(dataSospensione, dataRipresa);
		return ret;
		
	}
	
	
	/**
	 * Metodo per la funzionalità effettuaPrenotazione - Permette di salvare una richiesta di prenotazione effettuata da un cliente per un corso
	 * @param codiceCliente
	 * @param codiceCorso
	 * @return esito prenotazione
	 */
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
	
	
	/**
	 * Funzione di utility che permette di controllare se, data una PK, esiste un abbonamento per un cliente
	 * @param codiceCliente
	 * @return permessi cliente
	 */
	public static int  AccessoAlCentro(String codiceCliente) {
		
        ClienteIscrittoEntity cliente = new ClienteIscrittoEntity(codiceCliente);
        int val = cliente.AccessoAlCentro();
        return val;
        
	}
	
	
	/**
	 * Funzione di utility che permette di controllare se, data una PK, esiste un corso ed ha disponibilità di posti
	 * @param idCorso
	 * @return disponibilità
	 */
	public static int checkDisponibilitaCorso(int idCorso) {
		
        CorsoEntity corso = new CorsoEntity();
        int val = corso.checkDisponibilitaCorso(idCorso);
        return val;
        
	}
	
	
	/**
	 * Funzione di utility che permette di controllare se, data una PK, esiste un giorno nel calendario
	 * @param idCorso
	 * @return disponibilità
	 */
	public static int checkGiorno(String nomeGiorno) {
		GiornoEntity giorno = new GiornoEntity();
		int ok = giorno.checkGiorno(nomeGiorno);
		return ok;
	}    
	
	
	/**
	 * Metodo che permette ad un amministratore di inserire gli orari del centro
	 * @param nomeGiorno
	 * @param oraApertura
	 * @param oraChiusura
	 * @return esito
	 */
	public static int inserisciOrariCentro(String nomeGiorno, String oraApertura, String oraChiusura) {
        GiornoEntity giorno = new GiornoEntity();
        int val=giorno.inserisciOrari(nomeGiorno,oraApertura,oraChiusura);
        return val;
    }
	
	/**
	 * Metodo che permette ad un amministratore di aggiornare gli orari del centro
	 * @param nomeGiorno
	 * @param oraApertura
	 * @param oraChiusura
	 * @return esito
	 */
	public static int aggiornaOrariGiorno(String nomeGiorno, String oraApertura, String oraChiusura) {
		GiornoEntity giorno = new GiornoEntity();
		int val=giorno.aggiornaOrari(nomeGiorno,oraApertura,oraChiusura);
		return val;
	}
	
	
}