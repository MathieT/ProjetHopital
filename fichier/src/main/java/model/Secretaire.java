package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;



public class Secretaire extends Compte {

	
	
	public Secretaire(Long id) {
		super(id);
		
	}

	public Secretaire(Long id, String login, String password, TypeCompte typeCompte) {
		super(id, login, password, typeCompte);
		
	}

	public Secretaire(String login, String password, TypeCompte typeCompte) {
		super(login, password, typeCompte);
		
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
