/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.bar;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.bar.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void handleSimula(ActionEvent event) {
    	txtResult.clear();
    	this.model.run();
    	int clienti = this.model.getClientiTotali();
    	int clientiSoddisfatti = this.model.getClientiSoddisfatti();
    	int clientiInsoddisfatti = this.model.getClientiInsoddisfatti();
    	
    	txtResult.appendText("Simulazione tasso di soddisfazione bar\n");
    	txtResult.appendText("Numero clienti totali: "+clienti+"\n");
    	txtResult.appendText("Numero clienti soddisfatti: "+clientiSoddisfatti+"\n");
    	txtResult.appendText("Numero clienti insoddisfatti: "+clientiInsoddisfatti+"\n");
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
}
