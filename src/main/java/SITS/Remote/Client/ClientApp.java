package SITS.Remote.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

import SITS.Actions.Participant;
import SITS.Actions.TitForTat;

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
	
	//stuff here 
	
	
	
	
}
