package model;

public enum Salle {
		
		Salle1("Salle1"), Salle2("Salle2");
		
		private String nomSalle;

		private Salle(String nomSalle) {
			this.nomSalle = nomSalle;
		}

		public String getNomSalle() {
			return nomSalle;
		}

		public void setNomSalle(String nomSalle) {
			this.nomSalle = nomSalle;
		}
		
		
}
