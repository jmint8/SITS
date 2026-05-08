package SITS.MVC.Views;

import SITS.MVC.Models.ViewTransitionModelInterface;
import SITS.MVC.Models.viewerModel;
import javafx.fxml.FXML;

public class viewTournamentController 
{
	ViewTransitionModelInterface transModel;
	viewerModel model;
	
	public void setModel(ViewTransitionModelInterface tModel,viewerModel newModel )
	{
		this.transModel = tModel;
		this.model = newModel;
	}
	
	@FXML
	void onBackClick()
	{
		transModel.showTournamentDash();
		System.out.println("going back to Dashboard");
		
	}

}
