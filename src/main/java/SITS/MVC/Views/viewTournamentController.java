package SITS.MVC.Views;

import SITS.MVC.Models.ViewTransitionModelInterface;
import SITS.MVC.Models.viewerModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class viewTournamentController 
{
	ViewTransitionModelInterface transModel;
	viewerModel model;
	
	@FXML
	private Button backButton;
	
	@FXML
	private ListView<String> moveListView;
	
	public void setModel(ViewTransitionModelInterface tModel,viewerModel newModel )
	{
		this.transModel = tModel;
		this.model = newModel;
		
		//moveListView.setItems(model.getTournamentList().getMoveList());
	}
	
	@FXML
	void onBackClick()
	{
		//model.deRegister();
		transModel.showTournamentDash();
		System.out.println("going back to Dashboard");
		
	}

}
