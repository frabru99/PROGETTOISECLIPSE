package Entity;

import Database.SalaperCorsiDAO;

public class SalaperCorsiEntity {

		private int numeroPosti;
		private int idSalaCorsi;
		
		
		public SalaperCorsiEntity(int idSalaCorsi) {
			SalaperCorsiDAO db = new SalaperCorsiDAO(idSalaCorsi);
			this.numeroPosti = db.getNumeroPosti();
			this.idSalaCorsi = idSalaCorsi;
		}
		
		
		public SalaperCorsiEntity(SalaperCorsiDAO db) {
			this.numeroPosti = db.getNumeroPosti();
            this.idSalaCorsi = db.getIdSalaCorsi();
		}
		
		public int ScriviSuDB(int idSalaCorsi, int numeroPosti) {
			
			int ret=0;
			
			SalaperCorsiDAO sala= new SalaperCorsiDAO();
			
			//provo a scrivere sul DB
			
			ret = sala.scriviSala(idSalaCorsi, numeroPosti);
			
			if(ret!=-1) {			
				this.setIdSalaCorsi(idSalaCorsi);
				this.setNumeroPosti(numeroPosti);
			}
			
			return ret;
		}
		
		
		public SalaperCorsiEntity() {
			super();
		}


		public int getNumeroPosti() {
            return numeroPosti;
        }
		
		public void setNumeroPosti(int numeroPosti) {
            this.numeroPosti = numeroPosti;
        }
		
        public int getIdSalaCorsi() {
            return idSalaCorsi;
        }
        
        public void setIdSalaCorsi(int idSalaCorsi) {
            this.idSalaCorsi = idSalaCorsi;
        }


		@Override
		public String toString() {
			return "SalaperCorsiEntity [numeroPosti=" + numeroPosti + ", idSalaCorsi=" + idSalaCorsi + "]";
		}
        
        
        
        
}
