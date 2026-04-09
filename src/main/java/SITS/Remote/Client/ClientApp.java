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

@SpringBootApplication(scanBasePackages = "SITS")
public class ClientApp 
{
	private int port;
	
	@Value("${tournament.server.url}")
	private String server_url;
	
	@Value("${tournament.id}")
	private String tournamentId;
	
	@Value("${participant.name}")
	private String participantName;
	
	@Autowired
	private TournamentServerClient client;
	
	public static void main(String[] args) 
	{
		SpringApplication.run(ClientApp.class, args);
	}
	
	@Bean
	public Participant participant()
	{
		return new TitForTat();
	}
	
	@Bean
	public TournamentServerClient tournamentServerClient()
	{
		return new TournamentServerClient(server_url);
	}
	 
	
	@EventListener
	public void onServerInitialized(ServletWebServerInitializedEvent event)
	{
		this.port =event.getWebServer().getPort(); 
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReady() throws UnknownHostException
	{
		String ip = InetAddress.getLocalHost().getHostAddress();
		client.register(tournamentId, participantName, ip, port);
		System.out.println("you are: "+ participantName
				+", your ip is: " +ip+ ", and your port is: " + port);
	}
	
}
