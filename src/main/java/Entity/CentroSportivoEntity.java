package Entity;

import java.util.ArrayList;

import Database.CalendarioDAO;
import Database.CentroSportivoDAO;
import Database.GiornoDAO;
import Database.SalaperCorsiDAO;

public class CentroSportivoEntity {
	
	//Variabili membro.
	private static CentroSportivoEntity uniqueInstance;
	private static RegistroClientiEntity registro;
	private static CalendarioEntity calendario;
	private static ArrayList<SalaperCorsiEntity> sale;
	
	
	//Costruttore vuoto - recupera il registroClienti ed il calendario
	private CentroSportivoEntity() {
        
		registro=RegistroClientiEntity.getInstance();
		calendario=CalendarioEntity.getInstance();
        sale = new ArrayList<SalaperCorsiEntity>();
        
        //Creo un oggetto DAO per caricare gli attributi
        CentroSportivoDAO centroSportivoDao = new CentroSportivoDAO();
        ArrayList<SalaperCorsiDAO>saledao = centroSportivoDao.getSale();
        
        //Carico le SalaperCorsiEntity dall'array di SalaperCorsiDAO
        for (int i=0;i<saledao.size();i++) {
            
            SalaperCorsiEntity sala = new SalaperCorsiEntity(saledao.get(i));
            sale.add(sala);
            
        }
        
    }

	
	//Metodo getInstance per implementare il pattern Singleton - crea il Singleton se l'istanza è null oppure la ritorna
	public static CentroSportivoEntity getInstance() {
	
		 if (uniqueInstance == null) {
	            
	            uniqueInstance = new CentroSportivoEntity();
	            
	        }
	        
	        return uniqueInstance;
	
	}
	
	
	//Metodo per la ricerca dei corsi disponibili all'interno 
	public ArrayList<CorsoEntity> ricercaCorsiDisponibili(String nomeGiorno){
		
		//Recupero dal calendario i giorni, al fine di recuperarne successivamente i corsi
		ArrayList<GiornoEntity> giorni=CalendarioEntity.getGiorni();
		
		//Inizializzo due array - uno per tutti i corsi, ed uno per tutti i corsi con disponibilità di posti
		ArrayList<CorsoEntity> corsi=new ArrayList<CorsoEntity>();
		ArrayList<CorsoEntity> corsiDisponibili=new ArrayList<CorsoEntity>();
		
		//Scorro l'array di giorni
		for(int i=0;i<giorni.size();i++) {
			
			//Se trovo il giorno richiesto
			if((giorni.get(i).getNomeGiorno()).compareTo(nomeGiorno)==0) {
				
				//Recupero tutti i corsi di quel giorno
				corsi=giorni.get(i).getCorsi();
				
				//Filtro tra tutti i corsi di quel giorno, quelli con disponibilità di posti
				for(int j=0;j<corsi.size();j++) {
					
					//Se c'è almeno un posto disponibile, aggiungo il corso all'array di corsi con disponibilità
					if(corsi.get(j).getPostiDisponibili() > 0) {
						
						corsiDisponibili.add(corsi.get(j));
						
					}
					
				}		
				
			}
			
		}
		
		return corsiDisponibili;
		
	}

	
	//GETTERS AND SETTERS
	public static RegistroClientiEntity getRegistro() {
		return registro;
	}

	public static void setRegistro(RegistroClientiEntity registro) {
		CentroSportivoEntity.registro = registro;
	}

	public static CalendarioEntity getCalendario() {
		return calendario;
	}

	public static void setCalendario(CalendarioEntity calendario) {
		CentroSportivoEntity.calendario = calendario;
	}

	public static ArrayList<SalaperCorsiEntity> getSale() {
		return sale;
	}

	public static void setSale(ArrayList<SalaperCorsiEntity> sale) {
		CentroSportivoEntity.sale = sale;
	}
	
	
}
