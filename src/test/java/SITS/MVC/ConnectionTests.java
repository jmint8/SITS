package SITS.MVC;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
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
public class ConnectionTests implements ViewTransitionModelInterface {

	int dashCalled = 0;
	viewerModel model;
	
	@Start
	private void start(Stage stage)
	{
		dashCalled = 0;
		model = new viewerModel();
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/SITS/MVC/Views/connection.fxml"));
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
		//nah
	}



	@Override
	public void showTournamentDash() {
		dashCalled++; // this shows that the switch was attempted 
		
	}
	
	@Test
	public void ConnectionInputTest(FxRobot r) 
	{
		r.clickOn("#serverIPtext");
		r.write("111.111.1.11");
		r.clickOn("#portText");
		r.write("8080");
		
		r.clickOn("#connectButton");
		
		Assertions.assertThat(dashCalled).isEqualTo(1);
		Assertions.assertThat(model.getPort().get()).isEqualTo("8080");
		Assertions.assertThat(model.getServerIp().get()).isEqualTo("111.111.1.11");
		
		r.clickOn("#serverIPtext").write("111.111.1.11");
		r.clickOn("#portText").write("8081");
		r.clickOn("#connectButton");
		Assertions.assertThat(dashCalled).isEqualTo(2);
	}


	
	
	
	
	
	
}
