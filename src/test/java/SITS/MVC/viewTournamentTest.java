package SITS.MVC;

import java.io.IOException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import SITS.MVC.Models.ViewTransitionModelInterface;
import SITS.MVC.Models.viewerModel;
import SITS.MVC.Views.viewTournamentController;
import SITS.MVC.main.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class viewTournamentTest implements ViewTransitionModelInterface {

	int dashCalled;
	viewerModel model;
	
	@Start
	public void start(Stage stage)
	{
		dashCalled = 0;
		model = new viewerModel();
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/SITS/MVC/Views/viewTournament.fxml"));
		Pane view;
		try {
			
			view = loader.load();
			viewTournamentController cont = loader.getController();
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
		dashCalled++;
		
	}

	@Override
	public void showViewTournament() {
		// TODO Auto-generated method stub
		
	}
	
	@Test
	public void testBackButtonToDash(FxRobot r)
	{
		r.clickOn("#backButton");
		Assertions.assertThat(dashCalled).isEqualTo(1);
	}

}
