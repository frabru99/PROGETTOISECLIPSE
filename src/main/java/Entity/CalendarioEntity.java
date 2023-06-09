package Entity;

import java.util.ArrayList;
import Database.CalendarioDAO;
import Database.GiornoDAO;

/**
 * Classe Singleton del package Entity che rappresenta l'oggetto del mondo reale definendone il comportamento e l'implementazione vera e propria dei metodi
 * @author Salvatore Cangiano
 * @author Giovanni Ciccarelli
 * @author Antonio Boccarossa
 * @author Francesco Brunello
 * @version 09/06/2023
 *
 */
public class CalendarioEntity {

    //Variabili membro.
    private static CalendarioEntity uniqueInstance;	//uniqueInstance - variabile per implementazione del pattern Singleton
    private static ArrayList<GiornoEntity> giorni;
    
    
    /**
     * Costruttore vuoto - recupera tutte le informazioni dei giorni dal DAO
     */
    private CalendarioEntity() {
        
    	//Inizializzo l'array di giorni
        giorni = new ArrayList<GiornoEntity>();
        
        //Creo un oggetto DAO per caricare gli attributi
        CalendarioDAO cdao = new CalendarioDAO();
        ArrayList<GiornoDAO>giornidao = cdao.getGiorni();
        
        //Carico i GiornoEntity dall'array di GiornoDAO
        for (int i=0;i<giornidao.size();i++) {
            
            GiornoEntity giorno = new GiornoEntity(giornidao.get(i));
            giorni.add(giorno);
            
        }
        
    }
    
    
    /**
     * Metodo getInstance per implementare il pattern Singleton - crea il Singleton se l'istanza è null oppure la ritorna
     * @return uniqueInstance
     */
    public static CalendarioEntity getInstance() {
        
        if (uniqueInstance == null) {
            
            uniqueInstance = new CalendarioEntity();
        
        }
        
        return uniqueInstance;
        
    }
    
    
    /**
     * Metodo per la ricerca del giorno all'interno del calendario
     * @param nomeGiorno
     * @return giorno
     */
    public GiornoEntity cercaGiorno(String nomeGiorno) {
        
        GiornoEntity giorno = new GiornoEntity();
        
        for (int i=0;i<giorni.size();i++) {
            
            if (giorni.get(i).getNomeGiorno().compareTo(nomeGiorno)==0) {
                
                giorno = giorni.get(i);
                return giorno;
                
            }
            
        }
        
        return giorno;
        
    }

    
    //GETTERS AND SETTERS
    
    /**
     * Getter
     * @return giorni
     */
    public static ArrayList<GiornoEntity> getGiorni() {
        return giorni;
    }

    /**
     * Setter
     * @param giorni
     */
    public static void setGiorni(ArrayList<GiornoEntity> giorni) {
        CalendarioEntity.giorni = giorni;
    }

    
    //TO - STRING
	@Override
	public String toString() {
		
		String gio = new String();
		
		for(int i=0; i<giorni.size(); i++) {
			
			gio += "  ##  "+ giorni.get(i).toString() + "  ##  \n";
			
		}
		
		return gio;
		
	}
    
}