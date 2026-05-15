package SITS.MVC;

import static org.junit.jupiter.api.Assertions.*;

import org.testfx.assertions.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import java.util.Collections;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.util.WaitForAsyncUtils;

import SITS.MVC.Models.ViewTransitionModel;
import SITS.MVC.Models.viewerModel;
import SITS.Remote.Server.TournamentServerApp;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
		//Dr. b just loaded the model straight from the url but I set mine.
		//
		model = new viewerModel();
		model.setViewerIp("127.0.0.1");
		model.setViewerPort(9090);
		
		BorderPane root = new BorderPane();
		tm = new ViewTransitionModel(root, model);
		
		Scene s = new Scene(root,800,600);
		stage.setScene(s);
		tm.showConnection();
		stage.show();
	}
	
	
	

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
