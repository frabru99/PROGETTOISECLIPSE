package Entity;

import java.util.ArrayList;

import Database.CalendarioDAO;
import Database.ClienteIscrittoDAO;
import Database.GiornoDAO;
import Database.RegistroClientiDAO;

public class RegistroClientiEntity {

	private ClienteIscrittoEntity clienteAttivo;
	private static RegistroClientiEntity uniqueInstance;
	private static ArrayList<ClienteIscrittoEntity> clienti;
	
	private RegistroClientiEntity() {
		
		clienti = new ArrayList<ClienteIscrittoEntity>();
        
        //Carico dal DAO
        RegistroClientiDAO rdao = new RegistroClientiDAO();
        ArrayList<ClienteIscrittoDAO>clientidao = rdao.getClienti();
        
        for (int i=0;i<clientidao.size();i++) {
            
            ClienteIscrittoEntity cliente = new ClienteIscrittoEntity(clientidao.get(i));
            clienti.add(cliente);
            
        }
        
		
	}
	
	
	public static RegistroClientiEntity getInstance() {
	        
	    if (uniqueInstance == null) {
	            
	    	uniqueInstance = new RegistroClientiEntity();
	        
	            
	    }
	        
	    return uniqueInstance;
	        
	}
	
	public void AccessoAlCentro(String codiceCliente) {
		
		for (int i=0;i<clienti.size();i++) {
            
            if(clienti.get(i).getCodiceCliente()==codiceCliente) {
            
            	clienteAttivo=clienti.get(i);
            	return;
            	
            }
            
            //se non ha trovato il cliente, significa che non Ã¨ iscritto o codice non valido
            System.out.println("codice cliente non valido");
            
        }
		
	}


	public ClienteIscrittoEntity getClienteAttivo() {
		return clienteAttivo;
	}


	public void setClienteAttivo(ClienteIscrittoEntity clienteAttivo) {
		this.clienteAttivo = clienteAttivo;
	}


	public static ArrayList<ClienteIscrittoEntity> getClienti() {
		return clienti;
	}


	public static void setClienti(ArrayList<ClienteIscrittoEntity> clienti) {
		RegistroClientiEntity.clienti = clienti;
	}
	 
	
	
	
}