package SITS.MVC.main;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import SITS.MVC.Models.ViewTransitionModel;
import SITS.MVC.Models.viewerModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application
{
	private ConfigurableApplicationContext springCont;
	
	@Override
	public void start(Stage stage) throws Exception 
	{
		//probably need a model for the client viewer here like how Dr. B does it in his videos and Repo
		viewerModel model = new viewerModel();
		BorderPane root = new BorderPane();
		
		//spring boot on random port
		springCont = new SpringApplicationBuilder(viewerServer.class)
				.properties("server.port=0")
				.run();
		
		viewerServer serv = springCont.getBean(viewerServer.class);
		serv.setModel(model);
		
		String ip = java.net.InetAddress.getLocalHost().getHostAddress();
		int port = springCont.getEnvironment().getProperty("local.server.port", Integer.class);
		 
		
		model.setConnectionParts(ip, Integer.toString(port));
		
		ViewTransitionModel transModel = new ViewTransitionModel(root,model);
		transModel.showConnection();
		
		Scene s = new Scene(root, 600,400);
		stage.setScene(s);
		stage.setTitle("TournamentViewer");
		stage.show();
	}
	
	//maybe a leave or stop override here? 
	
	public static void main(String [] args) 
	{
		launch(args);
	}
}
