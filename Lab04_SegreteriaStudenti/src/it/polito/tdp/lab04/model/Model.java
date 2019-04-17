package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	StudenteDAO s = new StudenteDAO();
	CorsoDAO c = new CorsoDAO();
	
	public Studente getStudente(int cod) {
		return s.getStudente(cod);
	}
	
	public Corso getCorso(String cod) {
		return c.getCorso(cod);
	}
	
	public Corso getCorsoNome(String cod) {
		return c.getCorsoNome(cod);
	}
	
	public List<String> getNomiCorsi(){
		
		List<String> lista = new LinkedList<String>();
		
		for ( Corso corso : c.getTuttiICorsi() )
			lista.add(corso.getNome());
		
		return lista;
	}
	
	public String getIscrittiCorso(String nome) {
		
		String out = "";
		Corso corso = new Corso();
		corso = c.getCorsoNome(nome);
		
		for ( Studente studente : c.getStudentiIscrittiAlCorso(corso) )
			out += studente.toString();
		
		return out;
	}
	
	public String getCorsiStudente(int matricola) {
		String out = "";
		Studente studente = new Studente();
		studente = s.getStudente(matricola);
		
		for ( Corso corso : s.getCorsiStudente(studente) )
			out += corso.toString();
		
		return out;
	}
	
	public boolean verifica(String codins, int matricola) {
		return s.verifica(codins, matricola);
	}
	
	public boolean iscrivi(String codins, int matricola) {
		return s.iscrivi(codins, matricola);
	}

}
