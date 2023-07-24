package application;

import java.util.Scanner;

import dao.DaoCompte;
import dao.DaoPatient;
import dao.JdbcContext;
import model.Compte;
import model.ExceptionLoginFail;
import model.Medecin;
import model.Patient;
import model.Salle;
import model.Secretaire;
import model.TypeCompte;

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
	
	static void identification() throws ExceptionLoginFail {
		System.out.println("Veuillez vous identifier");
		DaoCompte daoCompte = JdbcContext.getDaoCompte();
		String login = SaisieString("Login :");
		String password = SaisieString("Password :");
		if(daoCompte.findByIdentifiant(login,password)==null) {
			throw new ExceptionLoginFail("Erreur : ce login et/ou password ne correspondent pas à un compte existant");
		}
		Compte compte = daoCompte.findByIdentifiant(login,password);
		if(compte!= null) {
			if(compte.getTypeCompte()==TypeCompte.Medecin) {
				String nomsalle = SaisieString("Choisissez une salle (Tapez Salle1 ou Salle2) :");
				Medecin medecin = new Medecin(compte.getId(),compte.getLogin(),compte.getPassword(),Salle.valueOf(nomsalle));
				menuPrincipalMedecin(medecin);
			}
			else {
				Secretaire secretaire = new Secretaire(compte.getId(),compte.getLogin(),compte.getPassword());
				menuPrincipalSecretaire(secretaire);
			}
		}
	}
	
	static void menuprincipal() throws ExceptionLoginFail{
		identification();
	}
	
	static void menuPrincipalMedecin(Medecin medecin) throws ExceptionLoginFail {
		System.out.println("Pour rendre votre salle disponible, vous pouvez cliquer sur 1 ");
		System.out.println("Pour voir la file d'attente, vous pouvez cliquer sur 2 ");
		System.out.println("Pour voir le prochain patient, vous pouvez cliquer sur 3 ");
		System.out.println("Pour sauvegarder la liste des visites, vous pouvez cliquer sur 4 ");
		System.out.println("Pour retourner au menu précédent (déconnexion), vous pouvez cliquer sur 5 ");
		int reponse = SaisieInt("Tapez votre réponse :");
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
				break;
		}
		identification();
	}
	
	static void menuPrincipalSecretaire(Secretaire secretaire) {
		System.out.println("Pour ajouter un patient en liste d'attente, vous pouvez cliquer sur 1 ");
		System.out.println("Pour voir la file d'attente, vous pouvez cliquer sur 2 ");
		System.out.println("Pour partir en pause, vous pouvez cliquer sur 3 ");
		System.out.println("Pour retourner au menu précédent (déconnexion), vous pouvez cliquer sur 4 ");
		int reponse = SaisieInt("Tapez votre réponse :");
		switch(reponse) {
			case 1:
				String patientConnu;
				patientConnu = SaisieString("Le patient est-il dans la base de données (Y/N)?");
				if(patientConnu.equals("N")) {
					String nomPatient = SaisieString("Rentrez le nom du patient : ");
					String prenomPatient = SaisieString("Rentrez le prénom du patient : ");
					DaoPatient daoPatient = JdbcContext.getDaoPatient();
					daoPatient.create(new Patient(nomPatient,prenomPatient));
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
				String retourPause = SaisieString("Tapez sur n'importe quelle touche quand ovus ètes de retour de pause");
				secretaire.finPause();
				break;
			case 4:
				secretaire = null;
				break;
		}
	}

	public static void main(String[] args) throws ExceptionLoginFail{
		/*DaoCompte daoCompte = JdbcContext.getDaoCompte();
		daoCompte.create(new Secretaire("secretaire1", "Secretaire1",TypeCompte.Secretaire));
		daoCompte.create(new Medecin("medecin1", "Medecin1",TypeCompte.Medecin));
		daoCompte.create(new Medecin("medecin2", "Medecin2",TypeCompte.Medecin));*/
		menuprincipal();
		
		
	}

}
