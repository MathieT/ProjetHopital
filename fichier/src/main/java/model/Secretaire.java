package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Queue;

import dao.DaoVisite;
import dao.JdbcContext;


public class Secretaire extends Compte {

	
	public Secretaire(Long id) {
		super(id);
		
	}

	public Secretaire(Long id, String login, String password) {
		super(id, login, password, TypeCompte.Secretaire);
		
	}

	public Secretaire(String login, String password) {
		super(login, password, TypeCompte.Secretaire);
		
	}


	public Secretaire(String login, String password, TypeCompte typeCompte) {
		super(login, password, typeCompte);
		// TODO Auto-generated constructor stub
	}

	public boolean ajoutFileAttente(Patient patient) {
		if(!fileAttente.contains(patient)) {
			return fileAttente.add(patient);
		}
		else {
			System.err.println("Ce patient est déjà dans la file d'attente");
			return false;
		}
	}
	
	public void afficherVisitePatient(Patient patient) {
			DaoVisite daoVisite = JdbcContext.getDaoVisite();
			List<Visite> visites= daoVisite.findByPatient(patient);
			for(Visite visite: visites) {
			System.out.println(visite.toString());	
			}
	}
	
	public void sauvegarderVisites() {
	}
	
	public void creationPatient() {
		
	}
	
	public void enPause() {
		
			try {
				FileOutputStream fos = new FileOutputStream("fileAttente.txt");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(fileAttente);
				oos.close();
				fos.close();
				fileAttente.clear();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	public void finPause() {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("fileAttente.txt"));
				Queue<Patient> list = (Queue<Patient>) ois.readObject();
								ois.close();
				fileAttente=list;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	
}
