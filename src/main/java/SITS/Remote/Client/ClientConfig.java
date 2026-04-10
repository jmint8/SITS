package SITS.Remote.Client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import SITS.Actions.Participant;
import SITS.Actions.TitForTat;

@Configuration
public class ClientConfig {

	@Value("${tournament.server.url:http://localhost:8080}")
	private String server_url;

	@Bean
	public Participant participant() {
		return new TitForTat();
	}
	
	@Bean
	public TournamentServerClient tournamentServerClient() {
		return new TournamentServerClient(server_url);
	}
}