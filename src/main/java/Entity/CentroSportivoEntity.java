package Entity;

import java.util.ArrayList;

import Database.CalendarioDAO;
import Database.CentroSportivoDAO;
import Database.GiornoDAO;
import Database.SalaperCorsiDAO;

public class CentroSportivoEntity {
	private static CentroSportivoEntity uniqueInstance;
	private static RegistroClientiEntity registro;
	private static CalendarioEntity calendario;
	private static ArrayList<SalaperCorsiEntity> sale;
	
	private CentroSportivoEntity() {
        
		//vedo se prenderle le istanze solo quando mi servono
		registro=RegistroClientiEntity.getInstance();
		calendario=CalendarioEntity.getInstance();
        sale = new ArrayList<SalaperCorsiEntity>();
        
        //Carico dal DAO
        CentroSportivoDAO centroSportivoDao = new CentroSportivoDAO();
        ArrayList<SalaperCorsiDAO>saledao = centroSportivoDao.getSale();
        
        for (int i=0;i<saledao.size();i++) {
            
            SalaperCorsiEntity sala = new SalaperCorsiEntity(saledao.get(i));
            sale.add(sala);
            
        }
        
        
    }

	public static CentroSportivoEntity getInstance() {
	
		 if (uniqueInstance == null) {
	            
	            uniqueInstance = new CentroSportivoEntity();
	            
	        }
	        
	        return uniqueInstance;
	
	}
	
	public ArrayList<CorsoEntity> ricercaCorsiDisponibili(String nomeGiorno){
		
		ArrayList<GiornoEntity> giorni=CalendarioEntity.getGiorni();
		ArrayList<CorsoEntity> corsi=new ArrayList<CorsoEntity>();
		ArrayList<CorsoEntity> corsiDisponibili=new ArrayList<CorsoEntity>();
		
		
		
		//prima mi cerco i corsi di quel giorno
		for(int i=0;i<giorni.size();i++) {
			
			if((giorni.get(i).getNomeGiorno()).compareTo(nomeGiorno)==0) {
				
				corsi=giorni.get(i).getCorsi();
				
				//poi tra i corsi di quel giorno mi cerco quelli disponibili
				for(int j=0;j<corsi.size();j++) {
					
					if(corsi.get(j).getPostiDisponibili() > 0) {
						
						corsiDisponibili.add(corsi.get(j));
						
					}
					
				}		
				
			}
			
		}
		
		return corsiDisponibili;
		
	}

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
