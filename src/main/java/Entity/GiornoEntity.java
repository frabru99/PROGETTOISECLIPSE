package Entity;

import java.util.ArrayList;

import Database.GiornoDAO;

public class GiornoEntity {
	String nomeGiorno;
	String orarioAperturaCentro;
	String orarioChiusuraCentro;
	ArrayList<CorsoEntity> corsi;
	
	public GiornoEntity(String nomeGiorno, boolean disponibili) {
		
		
		
		GiornoDAO giorno=new GiornoDAO(nomeGiorno);
		
		this.nomeGiorno=nomeGiorno;
		this.orarioAperturaCentro=giorno.getOrarioAperturaCentro();
		this.orarioChiusuraCentro=giorno.getOrarioChiusuraCentro();
		
		this.corsi=new ArrayList<CorsoEntity>();
		
		if(disponibili == false) {
		giorno.caricaCorsiGiornoDaDB();
		}else if(disponibili == true) {
		giorno.caricaCorsiPostiDisponibili();
				
		
		}
		
		caricaCorsi(giorno);
		
	}
	
	public GiornoEntity(GiornoDAO giorno) {
		
		this.nomeGiorno=giorno.getNomeGiorno();
		this.orarioAperturaCentro=giorno.getOrarioAperturaCentro();
		this.orarioChiusuraCentro=giorno.getOrarioChiusuraCentro();
		
		this.corsi=new ArrayList<CorsoEntity>();
		
		
		giorno.caricaCorsiGiornoDaDB();
		caricaCorsi(giorno);
		
	}
	
	public GiornoEntity() {
		super();
		this.corsi=new ArrayList<CorsoEntity>();	
	}
	
	//fare funzione di insert!!!
	
	public int scriviSuDb(String nomeGiorno, String orarioApertura, String orarioChiusura) {
		int ret=0;
		
		GiornoDAO giorno = new GiornoDAO();
		//provo a scrivere sul DB
		
		ret = giorno.salvaInDB(nomeGiorno, orarioApertura, orarioChiusura);
		
		if(ret!=-1) {	
			this.setNomeGiorno(nomeGiorno);
			this.setOrarioAperturaCentro(orarioApertura);
			this.setOrarioChiusuraCentro(orarioChiusura);
			
		}
		
		return ret;
	}
	
	


	public void caricaCorsi(GiornoDAO giorno) {
		
		for(int i=0;i<giorno.getCorsi().size();i++) {
			CorsoEntity corso=new CorsoEntity(giorno.getCorsi().get(i)); //creare corso entity
			this.corsi.add(corso);	
		}
		
	}

	public String getNomeGiorno() {
		return nomeGiorno;
	}
	public void setNomeGiorno(String nomeGiorno) {
		this.nomeGiorno = nomeGiorno;
	}
	public String getOrarioAperturaCentro() {
		return orarioAperturaCentro;
	}
	public void setOrarioAperturaCentro(String orarioAperturaCentro) {
		this.orarioAperturaCentro = orarioAperturaCentro;
	}
	public String getOrarioChiusuraCentro() {
		return orarioChiusuraCentro;
	}
	public void setOrarioChiusuraCentro(String orarioChiusuraCentro) {
		this.orarioChiusuraCentro = orarioChiusuraCentro;
	}
	public ArrayList<CorsoEntity> getCorsi() {
		return corsi;
	}
	public void setCorsi(ArrayList<CorsoEntity> corsi) {
		this.corsi = corsi;
	}

	@Override
	public String toString() {
		return "GiornoEntity [nomeGiorno=" + nomeGiorno + ", orarioAperturaCentro=" + orarioAperturaCentro
				+ ", orarioChiusuraCentro=" + orarioChiusuraCentro + ", corsi=" + corsi + "]";
	}
	
	
	
}
