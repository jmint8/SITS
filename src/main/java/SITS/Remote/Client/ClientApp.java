package SITS.Remote.Client;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import SITS.Actions.Participant;
import SITS.Actions.TitForTat;

@SpringBootApplication(scanBasePackages = {
	    "SITS.Remote.Client",
	    "SITS.Remote.Network",
	    "SITS.Game", 
	    "SITS.Actions", 
	    "SITS.Observers"})
public class ClientApp 
{
	private int port;
	
	@Value("${tournament.server.url:http://localhost:8080}")
	private String server_url;
	
	@Value("${tournament.id:ipd-1}")
	private String tournamentId;
	
	@Value("${participant.name:DefaultPlayer}")
	private String participantName;
	
	@Autowired
	private TournamentServerClient client;
	
	public static void main(String[] args) 
	{
		System.setProperty("server.port", "0");
		SpringApplication.run(ClientApp.class, args);
	}
	
	
	@EventListener
	public void onServerInitialized(ServletWebServerInitializedEvent event)
	{
		this.port =event.getWebServer().getPort(); 
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() throws UnknownHostException
	{
		String ip = "127.0.0.1";
		client.register(tournamentId, participantName, ip, port);
		System.out.println("you are: "+ participantName
				+", your ip is: " +ip+ ", and your port is: " + port);
	}
	
}
