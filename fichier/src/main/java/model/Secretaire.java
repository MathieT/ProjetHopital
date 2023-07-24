package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Queue;



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

	public boolean ajoutFileAttente(Patient patient) {
		return fileAttente.add(patient);
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
