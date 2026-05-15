package SITS.MVC;

import org.testfx.assertions.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

import SITS.MVC.Models.ViewTransitionModel;
import SITS.MVC.Models.viewerModel;
import SITS.Remote.Server.TournamentServerApp;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
		classes = TournamentServerApp.class)
@ExtendWith(ApplicationExtension.class)
class TestViewNetworkedTournament {

	viewerModel model;
	ViewTransitionModel tm;
	

	
	@LocalServerPort
	private int port;
	
	@Start
	private void start(Stage stage)
	{
		
		model = new viewerModel();
		
		BorderPane root = new BorderPane();
		tm = new ViewTransitionModel(root, model);
		tm.showConnection();
		
		Scene s = new Scene(root,600,400);
		stage.setScene(s);
		stage.show();
	}
	
	@SuppressWarnings("unchecked")
	public void checkIfListViewHasElements(FxRobot robot, String target,String elements[])
	{
		ListView<String> lv = (ListView<String>) robot.lookup(target).queryAll().iterator().next();
		Assertions.assertThat(lv).hasExactlyNumItems(elements.length);
		for(String i:elements){Assertions.assertThat(lv).hasListCell(i); }		
	}
	

	private void clearTextField(FxRobot robot,String selector)
	  {
		  TextField tf = robot.lookup(selector).queryAs(TextField.class);
		  Platform.runLater(()->{tf.clear();});
		  WaitForAsyncUtils.waitForFxEvents(); 
	  }
	
	
	
	@SuppressWarnings("unchecked")
	private ListView<String> getTournamentList(FxRobot robot)
	{
		return (ListView<String>) robot.lookup("#tournamentListView").queryAll().iterator().next();
	}//using this like I used in the Dashtests
	

	private void selectItem(FxRobot robot, int index)
	{
		Platform.runLater(()->{
			  ListView<String> grocs = getTournamentList(robot);
			  grocs.scrollTo(index);
			  grocs.getSelectionModel().clearAndSelect(index);
		  });
		  WaitForAsyncUtils.waitForFxEvents();
	}//same thing I did in dash tests
	
	
	private void enterText(FxRobot robot, String text, String target) { 
		clearTextField(robot, target); 

		robot.clickOn(target);
		robot.write(text); 
		//lil helper method here
	}
	
	@Test
	void testNavFX(FxRobot r) {
		
		enterText(r, "127.0.0.1","#serverIPtext");
		enterText(r,String.valueOf(port),"#portText");
		r.clickOn("#connectButton");
		Assertions.assertThat(model.getServerIp().get()).isEqualTo("127.0.0.1");
		Assertions.assertThat(model.getPort().get()).isEqualTo(String.valueOf(port));
		
		r.clickOn("#refreshButton");
		
		ListView<String> tournatestList = getTournamentList(r);
		Assertions.assertThat(tournatestList.getItems().isEmpty()).isFalse();
		
		selectItem(r,0);
		r.clickOn("#watchButton");
		
		r.clickOn("#backButton");
		r.clickOn("#refreshButton");

	}

}
