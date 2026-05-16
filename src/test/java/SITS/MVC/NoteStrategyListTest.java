package SITS.MVC;

import org.testfx.assertions.api.Assertions;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;

import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

import SITS.MVC.Models.ViewTransitionModelInterface;
import SITS.MVC.Models.viewerModel;
import SITS.MVC.Views.viewTournamentController;
import SITS.MVC.main.Main;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
class NoteStrategyListTest implements ViewTransitionModelInterface{

	int dashCalled;
	viewerModel model;
	
	@Start
	public void start(Stage stage)
	{
		model = new viewerModel();
		dashCalled = 0;
		
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
	public void showConnection() {}

	@Override
	public void showTournamentDash() {dashCalled++;}

	@Override
	public void showViewTournament() {}
	
	
	
	private void clearTextField(FxRobot robot,String selector)
	  {
		  TextField tf = robot.lookup(selector).queryAs(TextField.class);
		  Platform.runLater(()->{tf.clear();});
		  WaitForAsyncUtils.waitForFxEvents(); 
	  }
	
	
	private void enterText(FxRobot robot, String text, String target) { 
		clearTextField(robot, target); 

		robot.clickOn(target);
		robot.write(text); 
		//lil helper method here
	}
	
	@SuppressWarnings("unchecked")
	public void checkIfListViewHasElements(FxRobot robot, String target,String elements[])
	{
		ListView<String> lv = (ListView<String>) robot.lookup(target).queryAll().iterator().next();
		Assertions.assertThat(lv).hasExactlyNumItems(elements.length);
		for(String i:elements){Assertions.assertThat(lv).hasListCell(i); }		
	}
	
	
	@SuppressWarnings({ "unchecked", "unused" })
	private ListView<String> getNotesListView(FxRobot r)
	{
		return (ListView<String>)r.lookup("#notesListView").queryAll().iterator().next();
	}
	
	
	@Test
	public void testNotextPost(FxRobot r)
	{
		enterText(r,"okay","#noteInput");
		r.clickOn("#postButton");
		
		String[] exp = {"okay"};
		checkIfListViewHasElements(r,"#notesListView", exp); 
		
	}
	
	@Test
	public void testSmileyPost(FxRobot r)
	{
		r.clickOn("#smileyButton");
		String[] exp = {"😄"};
		checkIfListViewHasElements(r,"#notesListView", exp);
		r.clickOn("#smileyButton");
		String[] exp2 = {"😄","😄"};
		checkIfListViewHasElements(r,"#notesListView", exp2);
	}
	
	@Test
	public void testNerveyPost(FxRobot r)
	{
		r.clickOn("#nervyButton");
		String[] exp = {"😅"};
		checkIfListViewHasElements(r,"#notesListView", exp);
		r.clickOn("#nervyButton");
		
		String[] exp2 = {"😅","😅"};
		checkIfListViewHasElements(r,"#notesListView", exp2);
	}
	
}
