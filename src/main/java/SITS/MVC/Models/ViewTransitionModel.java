package SITS.MVC.Models;

import java.io.IOException;

import SITS.MVC.Views.connectionController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class ViewTransitionModel implements ViewTransitionModelInterface
{
	BorderPane mainview;//probably a different pane but this is a placeholder for now. 
	viewerModel model;
	
	public ViewTransitionModel(BorderPane view, viewerModel newModel)
	{
		mainview = view;
		model = newModel;
	}
	
	
	
	@Override
	public void showConnection()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewTransitionModel.class.getResource("/SITS/MVC/Views/connection.fxml"));
		 try 
		 {
			 //basically copy paste from Dr.B's repository
		      Pane view = loader.load();
		      mainview.setCenter(view);
		      connectionController cont = loader.getController();
		      cont.setModel(model);
		      
		 } catch (IOException e) 
		 {
		      e.printStackTrace();
		 }

	}

	@Override
	public void showTournamentDash() 
	{
		//placeholder
		System.out.println(" test: to Tournament Dashboard");
	}

}
