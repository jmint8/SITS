package SITS.MVC.Views;

import SITS.MVC.Models.viewerModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class connectionController 
{
	//peobably need the view transition model here later 
	viewerModel model;
	
	@FXML
	private TextField portText;
	
	@FXML
	private TextField serverIPtext;
	
	public void setModel(viewerModel newModel)
	{
		this.model = newModel;
	}
	
	@FXML
	void onConnectClick(ActionEvent event) 
	{
		String serverIP = serverIPtext.textProperty().get();
		String portNum = portText.textProperty().get();
		
	
		
		
		
		
	}
	
	
}
