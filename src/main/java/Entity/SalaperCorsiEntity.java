package Entity;

import Database.SalaperCorsiDAO;

public class SalaperCorsiEntity {

		private int capienza;
		private int idSalaCorsi;
		
		
		public SalaperCorsiEntity(int idSalaCorsi) {
			SalaperCorsiDAO db = new SalaperCorsiDAO(idSalaCorsi);
			this.capienza = db.getCapienza();
			this.idSalaCorsi = idSalaCorsi;
		}
		
		
		public SalaperCorsiEntity(SalaperCorsiDAO db) {
			this.capienza = db.getCapienza();
            this.idSalaCorsi = db.getIdSalaCorsi();
		}
		
		public int ScriviSuDB(int idSalaCorsi, int capienza) {
			
			int ret=0;
			
			SalaperCorsiDAO sala= new SalaperCorsiDAO();
			
			//provo a scrivere sul DB
			
			ret = sala.scriviSala(idSalaCorsi, capienza);
			
			if(ret!=-1) {			
				this.setIdSalaCorsi(idSalaCorsi);
				this.setCapienza(capienza);
			}
			
			return ret;
		}
		
		
		public SalaperCorsiEntity() {
			super();
		}


		public int getCapienza() {
            return capienza;
        }
		
		public void setCapienza(int capienza) {
            this.capienza = capienza;
        }
		
        public int getIdSalaCorsi() {
            return idSalaCorsi;
        }
        
        public void setIdSalaCorsi(int idSalaCorsi) {
            this.idSalaCorsi = idSalaCorsi;
        }


		@Override
		public String toString() {
			return "SalaperCorsiEntity [Capienza=" + capienza+ ", idSalaCorsi=" + idSalaCorsi + "]";
		}
        
        
        
        
}
