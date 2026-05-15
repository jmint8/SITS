package SITS.MVC;

import org.testfx.assertions.api.Assertions;

import java.io.IOException;
import java.util.Collections;

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
	int viewCalled = 0;
	viewerModel model;
	ViewTransitionModel tm;
	
	String[] mockTournaments = {"ipd-1"};
	
	@Start
	private void start(Stage stage)
	{
		int viewCalled = 0;
		model = new viewerModel();
		
		Collections.addAll(model.getTournamentList(), mockTournaments);
		
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
	public void showConnection() {}

	@Override
	public void showTournamentDash() {}

	@Override
	public void showViewTournament() {
		viewCalled++;
	}
	
	@SuppressWarnings("unchecked")
	private ListView<String> getTournamentList(FxRobot robot)
	{
		return (ListView<String>) robot.lookup("#tournamentListView").queryAll().iterator().next();
	}
	
	//this was also in your example repo
	public void checkIfListViewHasElements(FxRobot robot, String[] elements)
	{
		ListView<String> lv = getTournamentList(robot);
		
		Assertions.assertThat(lv).hasExactlyNumItems(elements.length);
		
		for(String i : elements)
		{
			Assertions.assertThat(lv).hasListCell(i);
		}
	}
	
	private void selectItem()
	{
		
	}
	
	
	@Test
	public void watchTest(FxRobot r)
	{
		//r.clickOn("#refreshButton"); if refresh is called it may do a failed server call
		r.clickOn("ipd-1");
		r.clickOn("#watchButton");
		
		
	}
}
