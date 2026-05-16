package SITS.MVC;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import com.sun.tools.javac.Main;

import SITS.MVC.Models.ViewTransitionModelInterface;
import SITS.MVC.Models.viewerModel;
import SITS.MVC.Views.viewTournamentController;
import SITS.MVC.main.viewerServer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


@SpringBootTest( webEnvironment = WebEnvironment.RANDOM_PORT,
		classes = viewerServer.class)
@ExtendWith(ApplicationExtension.class)
class viewerServerTest implements ViewTransitionModelInterface {

	viewerModel model;
	
	@Autowired
	private viewerServer server;
	
	@LocalServerPort
	private int port;
	
	@Start
	private void start(Stage stage)
	{
		model = new viewerModel();
		server.setModel(model);
		
		//FXML loader and stuff 
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/SITS/MVC/Views/viewTournament.fxml"));
		Pane view;
		
		try
		{
			view = loader.load();
			viewTournamentController cont = loader.getController();
			cont.setModel(this, model);
			
			Scene s = new Scene(view);
			stage.setScene(s);
			stage.show();
			
		
		}catch(Exception e){e.printStackTrace();} 
		
		
	}
	

	@Override
	public void showConnection() {} 

	@Override
	public void showTournamentDash() {}

	@Override
	public void showViewTournament() {}

	
	@SuppressWarnings("unchecked")
	private ListView<String> getMoveListView(FxRobot r)
	{
		return (ListView<String>) r.lookup("#moveListView").queryAll().iterator().next();
	}//taken from the viewTournament test for the controller, taken from Dr B's repo before that
	
	public void checkIfListViewHasElements(FxRobot robot, String[] elements)
	{
		ListView<String> lv = getMoveListView(robot);
		Assertions.assertThat(lv).hasExactlyNumItems(elements.length);
		for(String i : elements)
		{
			Assertions.assertThat(lv).hasListCell(i);
		}
	}//also pasted from that viewTournament test
	
	
	@Test
	public void updatingMovesEnpoint(FxRobot r)
	{
		String url = "http://:"+port+"/updateMoveList";
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
