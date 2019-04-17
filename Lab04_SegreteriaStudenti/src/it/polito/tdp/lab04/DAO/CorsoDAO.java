package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> lista = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				//System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				
				Corso c = new Corso(codins,numeroCrediti,nome,periodoDidattico);
				lista.add(c);
			}

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		

		return lista;
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	
	public Corso getCorsoNome(String nome) {
		// TODO
		final String sql = "SELECT * FROM corso WHERE nome = ?";

		Corso c = new Corso();
		int flag = 0;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, nome);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nomeCorso = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				//System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				c.setCodIns(codins);
				c.setCrediti(numeroCrediti);
				c.setNome(nomeCorso);
				c.setPeriodoDidattico(periodoDidattico);
				flag++;	
			}
		
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
		if ( flag != 0 )
			return c;
		else
			return null;
	}
	
	public Corso getCorso(String cod) {
	// TODO
	final String sql = "SELECT * FROM corso WHERE codins = ?";

	Corso c = new Corso();
	int flag = 0;

	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setString(1, cod);

		ResultSet rs = st.executeQuery();

		while (rs.next()) {

			String codins = rs.getString("codins");
			int numeroCrediti = rs.getInt("crediti");
			String nome = rs.getString("nome");
			int periodoDidattico = rs.getInt("pd");

			//System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

			c.setCodIns(codins);
			c.setCrediti(numeroCrediti);
			c.setNome(nome);
			c.setPeriodoDidattico(periodoDidattico);
			flag++;	
		}
		
	} catch (SQLException e) {
		// e.printStackTrace();
		throw new RuntimeException("Errore Db");
	}
	
	if ( flag != 0 )
		return c;
	else
		return null;
}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		
		StudenteDAO s = new StudenteDAO();
		
		final String sql = "SELECT matricola FROM iscrizione WHERE codins = ?";

		List<Studente> lista = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1,corso.getCodIns());

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				int matricola = rs.getInt("matricola");

				lista.add(s.getStudente(matricola));
			}

		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException(e);
		}
		return lista;
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}

}
