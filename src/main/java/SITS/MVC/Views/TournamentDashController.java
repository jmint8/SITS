package SITS.MVC.Views;

import SITS.MVC.Models.ViewTransitionModelInterface;
import SITS.MVC.Models.viewerModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TournamentDashController 
{
	ViewTransitionModelInterface transModel; 
	viewerModel model;
	//more to add here
	
	
	@FXML
	private Button watchButton;
	@FXML
	private Button refreshButton;
	
	public void setModel(ViewTransitionModelInterface tModel, viewerModel newModel) {
		this.transModel = tModel;
		this.model = newModel;
		
		//maybe something else for the getTournaments() needed for showing the list
	}
	
	
	@FXML
	void onWatchClick()
	{
		//sends you to the next page where you watch the tournament unfold. 
	}
	
	
	@FXML
	void onRefreshClick() 
	{
		//for the refresh button, reprint the listview by wiping it and then printing it out again
	}
	
	
}
