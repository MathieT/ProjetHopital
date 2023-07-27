package dao;

import java.util.List;

import model.Patient;
import model.Visite;

public interface DaoVisite extends DaoGeneric<Visite, Long>{
	public List<Visite> findByPatient(Patient patient);
}
