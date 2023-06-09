package Entity;

import java.util.ArrayList;

import Database.CalendarioDAO;
import Database.ClienteIscrittoDAO;
import Database.GiornoDAO;
import Database.RegistroClientiDAO;

/**
 * Classe Singleton del package Entity che rappresenta l'oggetto del mondo reale definendone il comportamento e l'implementazione vera e propria dei metodi
 * @author Salvatore Cangiano
 * @author Giovanni Ciccarelli
 * @author Antonio Boccarossa
 * @author Francesco Brunello
 * @version 09/06/2023
 *
 */
public class RegistroClientiEntity {

	//Variabili membro.
	private static RegistroClientiEntity uniqueInstance;
	private static ArrayList<ClienteIscrittoEntity> clienti;
	
	
	/**
	 * Costruttore vuoto - recupera tutti i clienti dal DB
	 */
	private RegistroClientiEntity() {
		
		clienti = new ArrayList<ClienteIscrittoEntity>();
        
		//Creo un oggetto DAO per caricare gli attributi
        RegistroClientiDAO rdao = new RegistroClientiDAO();
        ArrayList<ClienteIscrittoDAO>clientidao = rdao.getClienti();
        
        //Carico i ClienteIscrittoEntity dall'array di ClienteIscrittoDAO
        for (int i=0;i<clientidao.size();i++) {
            
            ClienteIscrittoEntity cliente = new ClienteIscrittoEntity(clientidao.get(i));
            clienti.add(cliente);
            
        }
        
	}
	
	
	/**
	 * Metodo getInstance per implementare il pattern Singleton - crea il Singleton se l'istanza è null oppure la ritorna
	 * @return uniqueInstance
	 */
	public static RegistroClientiEntity getInstance() {
	        
	    if (uniqueInstance == null) {
	            
	    	uniqueInstance = new RegistroClientiEntity();
	        
	            
	    }
	        
	    return uniqueInstance;
	        
	}
	
	
	/**
	 * Metodo che permette di controllare l'accesso al centro dei clienti tramite la loro PK
	 * @param codiceCliente
	 * @param email
	 * @return esito
	 */
	public boolean LoginCentroSportivo(String codiceCliente,String email) {
		
		//Scorro l'array di clienti
		for (int i=0;i<clienti.size(); i++) {
            
			//Se il codiceCliente e l'email sono corrette permetto l'accesso
            if((clienti.get(i).getCodiceCliente()).compareTo(codiceCliente)==0 && (clienti.get(i).getEmail()).compareTo(email)==0) {
            
            	return true;
            	
            }

        }
		
		System.out.println("Codice cliente o email non valida");
		return false;
		
	}
	
	
	/**
	 * Metodo DUMMY per la funzionalità registratiAlCentro - permette ad un cliente di registrarsi
	 * @param nome
	 * @param cognome
	 * @param email
	 * @return esito
	 */
	public boolean creaIscritto(String nome, String cognome, String email) {
		
		System.out.println("[REGISTRO-CLIENTI-ENTITY] Registrato con successo il cliente "+nome+" "+cognome+" "+email);
		return true;
		
	}
	

	//GETTERS AND SETTERS
	
	/**
	 * Getter
	 * @return clienti
	 */
	public static ArrayList<ClienteIscrittoEntity> getClienti() {
		return clienti;
	}

	/**
	 * Setter
	 * @param clienti
	 */
	public static void setClienti(ArrayList<ClienteIscrittoEntity> clienti) {
		RegistroClientiEntity.clienti = clienti;
	}
	 
}