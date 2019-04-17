package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public Studente getStudente(int cod) {
		
		final String sql = "SELECT * FROM studente WHERE matricola = ?";

		Studente s = new Studente();
		
		int flag = 0;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, cod);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cDS = rs.getString("CDS");

				s.setMatricola(matricola);
				s.setCognome(cognome);
				s.setNome(nome);
				s.setcDS(cDS);
				
				flag++;	
			}

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
		if ( flag != 0 )
			return s;
		else
			return null;
	}
	
   public List<Corso> getCorsiStudente(Studente studente) {
		
		CorsoDAO c = new CorsoDAO();
		
		final String sql = "SELECT codins FROM iscrizione WHERE matricola = ?";

		List<Corso> lista = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1,studente.getMatricola());

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				String cod = rs.getString("codins");

				lista.add(c.getCorso(cod));
			}

		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException(e);
		}
		return lista;
	}
   
   public boolean verifica(String codins, int matricola) {
       
	   final String sql = "SELECT * FROM iscrizione WHERE matricola = ? AND codins = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricola);
			st.setString(2, codins);
			
			ResultSet rs = st.executeQuery();

			if (rs.next()) 
				return true;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
		return false;
	}
   
   public boolean iscrivi(String codIns, int matricola) {
       
	   final String sql = "INSERT INTO iscrizione (matricola, codins) VALUES (?,?)";
		
	   boolean flag = false;
	   
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricola);
			st.setString(2, codIns);
			
			boolean rs = st.execute();
			
			flag = rs;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}
		return flag;
	}
}