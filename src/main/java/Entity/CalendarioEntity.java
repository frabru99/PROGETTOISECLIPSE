package Entity;

import java.util.ArrayList;
import Database.CalendarioDAO;
import Database.GiornoDAO;

public class CalendarioEntity {

    //Variabili membro.
    private static CalendarioEntity uniqueInstance;	//uniqueInstance - variabile per implementazione del pattern Singleton
    private static ArrayList<GiornoEntity> giorni;
    
    
    //Costruttore vuoto - recupera tutte le informazioni dei giorni
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
    
    
    //Metodo getInstance per implementare il pattern Singleton - crea il Singleton se l'istanza è null oppure la ritorna
    public static CalendarioEntity getInstance() {
        
        if (uniqueInstance == null) {
            
            uniqueInstance = new CalendarioEntity();
        
        }
        
        return uniqueInstance;
        
    }
    
    
    //Metodo per la ricerca del giorno all'interno del calendario
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

    public static ArrayList<GiornoEntity> getGiorni() {
        return giorni;
    }

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