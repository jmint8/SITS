package SITS.MVC;

import java.io.IOException;

import org.testfx.assertions.api.Assertions;

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
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class viewTournamentTest implements ViewTransitionModelInterface {

	int dashCalled;
	viewerModel model;
	
	String[] mockMoves = {"P1: COOPERATE, P2: DEFECT | 0-5", "P1: DEFECT, P2: DEFECT | 1-1"};
	
	@Start
	public void start(Stage stage)
	{
		dashCalled = 0;
		model = new viewerModel();
		model.getMoveList().addAll(mockMoves);
		
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
	
	//were going to do pretty much the same listview testing 
	@SuppressWarnings("unchecked")
	private ListView<String> getMoveListView(FxRobot r)
	{
		return (ListView<String>) r.lookup("#moveListView").queryAll().iterator().next();
	}
	
	//again pretty much copy paste 
	public void checkIfListViewHasElements(FxRobot robot, String[] elements)
	{
		ListView<String> lv = getMoveListView(robot);
		
		Assertions.assertThat(lv).hasExactlyNumItems(elements.length);
		
		for(String i : elements)
		{
			Assertions.assertThat(lv).hasListCell(i);
		}
	}
	
	@Test
	public void testBackButtonToDash(FxRobot r)
	{
		this.checkIfListViewHasElements(r, mockMoves); 
		
		r.clickOn("#backButton");
		Assertions.assertThat(dashCalled).isEqualTo(1);
	}

}
