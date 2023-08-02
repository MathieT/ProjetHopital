package application;

import java.time.LocalDate;
import java.util.Scanner;

import dao.DaoCompte;
import dao.DaoPatient;
import dao.DaoVisite;
import dao.JdbcContext;
import model.Compte;
import model.ExceptionLoginFail;
import model.Medecin;
import model.Patient;
import model.Salle;
import model.Secretaire;
import model.TypeCompte;
import model.Visite;

public class main {
	
	static String SaisieString(String msg) {
		String a;
		Scanner sc=new Scanner(System.in);
		System.out.println(msg);
		a = sc.nextLine();
		return a;
	}
	
	static int SaisieInt(String msg) {
		int a;
		Scanner sc=new Scanner(System.in);
		System.out.println(msg);
		a = sc.nextInt();
		return a;
	}
	
	static Long SaisieLong(String msg) {
		Long a;
		Scanner sc=new Scanner(System.in);
		System.out.println(msg);
		a = sc.nextLong();
		return a;
	}
	
	static boolean identification() throws ExceptionLoginFail {
		boolean continuProgramme=true;
		System.out.println("Veuillez vous identifier");
		DaoCompte daoCompte = JdbcContext.getDaoCompte();
		String login = SaisieString("Login :");
		String password = SaisieString("Password :");
		if(daoCompte.findByIdentifiant(login,password)==null) {
			throw new ExceptionLoginFail("Erreur : ce login et/ou password ne correspondent pas à un compte existant");
			//System.err.println("Erreur : ce login et/ou password ne correspondent pas à un compte existant");
			//identification();
		}
		Compte compte = daoCompte.findByIdentifiant(login,password);
		if(compte!= null) {
			System.out.println("Bienvenue "+ compte.getLogin());
			if(compte.getTypeCompte()==TypeCompte.Medecin) {
				String nomsalle = SaisieString("Choisissez une salle (Tapez Salle1 ou Salle2) :");
				Medecin medecin = new Medecin(compte.getId(),compte.getLogin(),compte.getPassword(),Salle.valueOf(nomsalle));
				continuProgramme=menuPrincipalMedecin(medecin);
			}
			else {
				Secretaire secretaire = new Secretaire(compte.getId(),compte.getLogin(),compte.getPassword());
				continuProgramme=menuPrincipalSecretaire(secretaire);
			}
		}
		return continuProgramme;
	}
	

	static void menuprincipal(boolean continuProgramme) {
		while(continuProgramme) {
			try {
					continuProgramme=identification();
				}catch(ExceptionLoginFail e) {
					//e.printStackTrace();
					System.err.println(e.toString());
				}
		}
		System.out.println("Fermeture de l'applicaction");
		System.out.println("A bientôt");
	}
	
	static boolean menuPrincipalMedecin(Medecin medecin) throws ExceptionLoginFail {
		boolean continuProgramme=true;
		int reponse = 0;
		while(medecin!=null) {
			System.out.println();
			System.out.println("Menu :");
			System.out.println();
			System.out.println("Pour rendre votre salle disponible, vous pouvez cliquer sur 1 ");
			System.out.println("Pour voir la file d'attente, vous pouvez cliquer sur 2 ");
			System.out.println("Pour voir le prochain patient, vous pouvez cliquer sur 3 ");
			System.out.println("Pour sauvegarder la liste des visites, vous pouvez cliquer sur 4 ");
			System.out.println("Pour retourner au menu précédent (déconnexion), vous pouvez cliquer sur 5 ");
			System.out.println("Pour fermer l'application, vous pouvez cliquer sur 6 ");
			reponse = SaisieInt("Tapez votre réponse :");
			switch(reponse) {
				case 1:
					medecin.salleDisponible();
					break;
				case 2 :
					medecin.afficherFileAttente();
					break;
				case 3 :
					medecin.visualiserProchainPatient();
					break;
				case 4:
					medecin.sauvegarderVisites();
					break;
				case 5:
					medecin = null;
					System.out.println("Déconnexion");
					break;
				case 6:
					medecin = null;
					continuProgramme=false;
					break;
			}
		}
		return continuProgramme;
	}
	
	static boolean menuPrincipalSecretaire(Secretaire secretaire) throws ExceptionLoginFail {
		boolean continuProgramme=true;
		int reponse = 0;
		while(secretaire!=null) {
			System.out.println();
			System.out.println("Menu :");
			System.out.println();
			System.out.println("Pour ajouter un patient en liste d'attente, vous pouvez cliquer sur 1 ");
			System.out.println("Pour voir la file d'attente, vous pouvez cliquer sur 2 ");
			System.out.println("Pour partir en pause, vous pouvez cliquer sur 3 ");
			System.out.println("Pour afficher les visite d'un patient, vous pouvez cliquer sur 4");
			System.out.println("Pour retourner au menu précédent (déconnexion), vous pouvez cliquer sur 5 ");
			System.out.println("Pour fermer l'application, vous pouvez cliquer sur 6 ");
			
			reponse = SaisieInt("Tapez votre réponse :");
			switch(reponse) {
				case 1:
					String patientConnu;
					patientConnu = SaisieString("Le patient est-il dans la base de données (Y/N)?");
					if(patientConnu.equals("N")) {
					}
					Long idPatient;
					idPatient = SaisieLong("Rentrez l'ID du patient : ");
					DaoPatient daoPatient = JdbcContext.getDaoPatient();
					secretaire.ajoutFileAttente(daoPatient.findByKey(idPatient));
					break;
				case 2 :
					secretaire.afficherFileAttente();
					break;
				case 3 :
					secretaire.enPause();
					SaisieString("Tapez sur n'importe quelle touche quand vous ètes de retour de pause puis sur la touche entrée");
					secretaire.finPause();
					break;	
				case 4:
					Long idPatient1;
					idPatient1 = SaisieLong("Rentrez l'ID du patient : ");
					Patient patient1 = JdbcContext.getDaoPatient().findByKey(idPatient1);
					secretaire.afficherVisitePatient(patient1);
					break;
				case 5:
					secretaire = null;
					System.out.println("Déconnexion");
					break;
				case 6:
					secretaire = null;
					continuProgramme=false;
					break;	
			}
		}
		return continuProgramme;
	}

	public static void main(String[] args){
		/*DaoCompte daoCompte = JdbcContext.getDaoCompte();
		daoCompte.create(new Secretaire("secretaire1", "Secretaire1",TypeCompte.Secretaire));
		daoCompte.create(new Medecin("medecin1", "Medecin1",TypeCompte.Medecin));
		daoCompte.create(new Medecin("medecin2", "Medecin2",TypeCompte.Medecin));*/
		menuprincipal(true);
	}
}
