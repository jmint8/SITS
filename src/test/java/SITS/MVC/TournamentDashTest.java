package SITS.MVC;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import SITS.MVC.Models.ViewTransitionModelInterface;
import SITS.MVC.Models.viewerModel;
import SITS.MVC.Views.connectionController;
import SITS.MVC.main.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


@ExtendWith(ApplicationExtension.class)
class TournamentDashTest implements ViewTransitionModelInterface
{
	int viewCalled = 0;
	viewerModel model;
	
	@Start
	private void start(Stage stage)
	{
		int viewCalled = 0;
		model = new viewerModel();
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/SITS/MVC/Views/TournamentDashboard.fxml"));
		Pane view;
		try {
			
			view = loader.load();
			connectionController cont = loader.getController();
			cont.setModel(this, model);
			
			Scene s = new Scene(view);
			stage.setScene(s);
			stage.show();	
		} catch (IOException e) {e.printStackTrace();}
		
	}

	@Override
	public void showConnection() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showTournamentDash() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showViewTournament() {
		viewCalled++;
		
	}
	
//	@Test
//	public void watchTest(FxRobot r)
//	{
//		r.clickOn("");
//	}
	
	

}
