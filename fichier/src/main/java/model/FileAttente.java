package model;

import java.util.Queue;

public class FileAttente {
	
	
	private Queue<Patient> patients;

	public FileAttente(Queue<Patient> patients) {
		this.patients = patients;
	}

	public Queue<Patient> getPatients() {
		return patients;
	}

	public void setPatients(Queue<Patient> patients) {
		this.patients = patients;
	}
	
	
}
