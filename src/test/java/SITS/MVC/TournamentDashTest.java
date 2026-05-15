package SITS.MVC;

import org.testfx.assertions.api.Assertions;

import java.io.IOException;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import SITS.MVC.Models.ViewTransitionModel;
import SITS.MVC.Models.ViewTransitionModelInterface;
import SITS.MVC.Models.viewerModel;
import SITS.MVC.Views.TournamentDashController;

import SITS.MVC.main.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


@ExtendWith(ApplicationExtension.class)
class TournamentDashTest implements ViewTransitionModelInterface
{
	//int viewCalled = 0;
	viewerModel model;
	ViewTransitionModel tm;
	
	String[] mockTournaments = {"ipd-1"};
	
	@Start
	private void start(Stage stage)
	{
		//int viewCalled = 0;
		model = new viewerModel();
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/SITS/MVC/Views/TournamentDashboard.fxml"));
		Pane view;
		try {
			
			view = loader.load();
			TournamentDashController cont = loader.getController();
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
		//viewCalled++;
		
	}
	
	//I literally just copied this from your repo Dr B.
	@SuppressWarnings("unchecked")
	public void checkIfListViewHasElements(FxRobot robot, String target,String elements[])
	{	
		ListView<String> lv = (ListView<String>) robot.lookup(target).queryAll().iterator().next();
		Assertions.assertThat(lv).hasExactlyNumItems(elements.length);
		for(String i:elements)
	    {
	     Assertions.assertThat(lv).hasListCell(i);  
	    }
	}
	
	@Test
	public void watchTest(FxRobot r)
	{
		this.checkIfListViewHasElements(r, "#tournamentListView", mockTournaments);
		r.clickOn("#refreshButton");
		r.clickOn("ipd-1");
		r.clickOn("#watchButton");
		
	}
}
