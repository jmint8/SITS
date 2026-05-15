package SITS.MVC;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

import SITS.MVC.Models.ViewTransitionModelInterface;
import SITS.MVC.Models.viewerModel;
import SITS.MVC.Views.connectionController;
import SITS.MVC.main.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.application.Platform;

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
		//nah it shows up
	}



	@Override
	public void showTournamentDash() {
		dashCalled++; // this shows that the switch was attempted 
		
	}
	@Override
	public void showViewTournament() {
		//nahh this is for the next page
	}

	//Im using these from your example repo just cause
	private void clearTextField(FxRobot robot,String selector)
	  {
		  TextField tf = robot.lookup(selector)
		  .queryAs(TextField.class);
		  
		  Platform.runLater(()->{tf.clear();});
		  WaitForAsyncUtils.waitForFxEvents();
	  }
	
	private void enterText(FxRobot robot, String text, String target)
	{
		clearTextField(robot, target);
		robot.clickOn(target);
		robot.write(text);
	}
	
	@Test
	public void testSuccessfulConnection(FxRobot robot)
	{
		enterText(robot, "127.0.0.1", "#serverIPtext");
		enterText(robot, "8080", "#portText");
		robot.clickOn("#connectButton");
		Assertions.assertThat(dashCalled).isEqualTo(1);
		
		//assert that the model received the connection parts 
		Assertions.assertThat(model.getServerIp().get()).isEqualTo("127.0.0.1");
		Assertions.assertThat(model.getPort().get()).isEqualTo("8080");
		
	}
	
	
}
