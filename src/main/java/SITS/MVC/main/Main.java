package SITS.MVC.main;

import SITS.MVC.Models.viewerModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application
{

	@Override
	public void start(Stage stage) throws Exception 
	{
		//probably need a model for the client viewer here like how Dr. B does it in his videos and Repo
		viewerModel model = new viewerModel();
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("SITS/MVC/Views/connection.fxml"));
		BorderPane view = loader.load(); // this might need to change to tiled pane or something else. 
		
		//TODO for the controller
		
		
		Scene s = new Scene(view);
		stage.setScene(s);
		stage.show();
	}
	
	public static void main(String [] args) 
	{
		launch(args);
	}

	
}
