package SITS.MVC.Views;

import SITS.MVC.Models.ViewTransitionModelInterface;
import SITS.MVC.Models.viewerModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class connectionController 
{
	ViewTransitionModelInterface transModel; 
	viewerModel model;
	
	@FXML
	private TextField portText;
	
	@FXML
	private TextField serverIPtext;
	
	public void setModel(ViewTransitionModelInterface tModel,viewerModel newModel)
	{
		this.transModel = tModel;
		this.model = newModel;
	}
	
	@FXML
	void onConnectClick(ActionEvent event) 
	{
		String serverIP = serverIPtext.textProperty().get();
		String portNum = portText.textProperty().get();
		
		model.setConnectionParts(serverIP, portNum);
		
		//clear the text fields
		serverIPtext.textProperty().set("");
		portText.textProperty().set("");
		
		transModel.showTournamentDash();	
	}
}
