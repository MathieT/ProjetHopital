package model;

import java.util.Queue;

public class Hopital {

		private Secretaire secretaire;
		private Medecin medecin1;
		private Medecin medecin2;
		private Queue<Patient> listeAttente;
		
		public Hopital(Secretaire secretaire, Medecin medecin1, Medecin medecin2, Queue<Patient> listeAttente) {
			super();
			this.secretaire = secretaire;
			this.medecin1 = medecin1;
			this.medecin2 = medecin2;
			this.listeAttente = listeAttente;
		}

		public Hopital() {
			super();
		}

		public Hopital(Secretaire secretaire, Medecin medecin1, Medecin medecin2) {
			super();
			this.secretaire = secretaire;
			this.medecin1 = medecin1;
			this.medecin2 = medecin2;
		}

		public Secretaire getSecretaire() {
			return secretaire;
		}

		public void setSecretaire(Secretaire secretaire) {
			this.secretaire = secretaire;
		}

		public Medecin getMedecin1() {
			return medecin1;
		}

		public void setMedecin1(Medecin medecin1) {
			this.medecin1 = medecin1;
		}

		public Medecin getMedecin2() {
			return medecin2;
		}

		public void setMedecin2(Medecin medecin2) {
			this.medecin2 = medecin2;
		}


		public Queue<Patient> getListeAttente() {
			return listeAttente;
		}

		public void setListeAttente(Queue<Patient> listeAttente) {
			this.listeAttente = listeAttente;
		}

		@Override
		public String toString() {
			return "Hopital [secretaire=" + secretaire + ", medecin1=" + medecin1 + ", medecin2=" + medecin2 + "]";
		}
		
		
}
