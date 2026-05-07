package SITS.MVC.Views;

import SITS.MVC.Models.ViewTransitionModelInterface;
import SITS.MVC.Models.viewerModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class TournamentDashController 
{
	ViewTransitionModelInterface transModel; 
	viewerModel model;
	//more to add here
	
	@FXML
	private ListView<String> tournamentListView;
	
	@FXML
	private Button watchButton;
	@FXML
	private Button refreshButton;
	
	public void setModel(ViewTransitionModelInterface tModel, viewerModel newModel) {
		this.transModel = tModel;
		this.model = newModel;
		
		//maybe something else for the getTournaments() needed for showing the list
		tournamentListView.setItems(model.getTournamentList()); // needs a fuction to retrieve the list of tournaments
	}
	
	
	@FXML
	void onWatchClick()
	{
		String subject = tournamentListView.getSelectionModel().getSelectedItem();
		
		if (subject != null)
		{
			transModel.showViewTournament();
			System.out.println("switch to view tournament"+subject);
			
		}
		else {System.out.println("select a tournament");}
	}
	
	
	@FXML
	void onRefreshClick() 
	{
		//for the refresh button, reprint the listview by wiping it and then printing it out again
		System.out.println("Refreshing");
	}
	
	
}
