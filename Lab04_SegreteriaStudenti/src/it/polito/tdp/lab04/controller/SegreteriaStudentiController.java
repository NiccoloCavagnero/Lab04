package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cbxCorso;

    @FXML
    private Button btnCercaIscrittiCorso;

    @FXML
    private Button btnVerifica;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {

    	txtResult.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	
        int matricola = 0;
    	
    	try {
    		matricola = Integer.parseInt(txtMatricola.getText());
    	} catch (NumberFormatException nfe) {
    		txtResult.appendText("Errore formato matricola!\n");
    	}
    	
    	if ( model.getStudente(matricola) != null ) {
    		txtNome.setText(model.getStudente(matricola).getNome());
    		txtCognome.setText(model.getStudente(matricola).getCognome());
    		txtResult.appendText(model.getCorsiStudente(matricola));
    		}
    	else
    		txtResult.appendText("Errore: la matricola inserita non corrisponde a nessuno studente nel database!\n");
    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	
    	txtResult.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	
    	String s = cbxCorso.getValue();

    	if ( s == null )
    		txtResult.appendText("Errore: nessun corso selezionato!\n");
    	else
    		txtResult.appendText(model.getIscrittiCorso(s));
    }

    @FXML
    void doCercaMatricola(ActionEvent event) {
    	
    	txtResult.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	
    	int matricola = 0;
    	
    	try {
    		matricola = Integer.parseInt(txtMatricola.getText());
    	} catch (NumberFormatException nfe) {
    		txtResult.appendText("Errore formato matricola!\n");
    	}
    	
    	if ( matricola != 0 && model.getStudente(matricola) != null ) {
    		txtNome.setText(model.getStudente(matricola).getNome());
    		txtCognome.setText(model.getStudente(matricola).getCognome());
    	}
    	else 
    		txtResult.appendText("Errore: la matricola inserita non corrisponde a nessuno studente nel database!\n");
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	
        txtResult.clear();
        txtNome.clear();
    	txtCognome.clear();
    	
    	String s = cbxCorso.getValue();
        int matricola = 0;
    	
    	try {
    		matricola = Integer.parseInt(txtMatricola.getText());
    	} catch (NumberFormatException nfe) {
    		txtResult.setText("Errore formato matricola!\n");
    	}

    	if ( s == null )
    		txtResult.setText("Errore: nessun corso selezionato!\n");
    	
    	if ( matricola != 0 && model.getStudente(matricola) != null && s != null ) {
    		
    		txtNome.setText(model.getStudente(matricola).getNome());
    		txtCognome.setText(model.getStudente(matricola).getCognome());
    		
    		if ( model.verifica(model.getCorsoNome(s).getCodIns(), matricola) )
    			txtResult.appendText("Studente gia' iscritto al corso "+s+"\n");
    		else {
    			model.iscrivi(model.getCorsoNome(s).getCodIns(), matricola);
    			txtResult.appendText("Studente iscritto al corso"+s+"\n");
    		}
    	}

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtResult.clear();
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    }

    @FXML
    void doVerifica(ActionEvent event) {
    	
    	txtResult.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	
    	String s = cbxCorso.getValue();
        int matricola = 0;
        
        if ( s == null )
    		txtResult.appendText("Errore: nessun corso selezionato!\n");
    	
    	try {
    		matricola = Integer.parseInt(txtMatricola.getText());
    	} catch (NumberFormatException nfe) {
    		txtResult.appendText("Errore formato matricola!\n");
    	}
    	System.out.println(matricola);
    	
    	if ( matricola != 0 && model.getStudente(matricola) != null && s != null ) {
    		
    		txtNome.setText(model.getStudente(matricola).getNome());
    		txtCognome.setText(model.getStudente(matricola).getCognome());
    		
    		if ( model.verifica(model.getCorsoNome(s).getCodIns(), matricola) )
    			txtResult.appendText("Studente iscritto al corso "+s+"\n");
    		else
    			txtResult.appendText("Studente non iscritto al corso "+s+"\n");
    	}
    	else 
    		txtResult.appendText("Errore: la matricola inserita non corrisponde a nessuno studente nel database!\n");
    }

    @FXML
    void initialize() {
        assert cbxCorso != null : "fx:id=\"cbxCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnVerifica != null : "fx:id=\"btnVerifica\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnMatricola != null : "fx:id=\"btnMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		cbxCorso.getItems().addAll(model.getNomiCorsi());
	}
}
