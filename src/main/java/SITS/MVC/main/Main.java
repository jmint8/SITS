package SITS.MVC.main;

import SITS.MVC.Models.ViewTransitionModel;
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
		BorderPane root = new BorderPane();
		
		ViewTransitionModel transModel = new ViewTransitionModel(root,model);
		transModel.showConnection();
		
		Scene s = new Scene(root, 600,400);
		stage.setScene(s);
		stage.setTitle("TournamentViewer");
		stage.show();
	}
	
	public static void main(String [] args) 
	{
		launch(args);
	}
}
