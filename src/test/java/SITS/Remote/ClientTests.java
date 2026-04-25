package SITS.Remote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import SITS.Remote.Client.ClientApp;
import SITS.Remote.Client.TournamentServerClient;

import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = {ClientApp.class, ClientTests.TestConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientTests {

	/*
	 requirements for this test file include hitting the GET/name, POST/action, 
	 POST/reset endpoints.
	 
	 note to self: create an actual tournament instance. not with mockito like how you started ServerTests.
	 - Test the endpoint communications. 
	 - All methods and classes in this package should be tested. 
	*/
	
	@LocalServerPort
	private int port;
	
	private RestTemplate restTemp = new RestTemplate();
	
	@TestConfiguration
	static class TestConfig{
		@Bean
		@Primary
		public TournamentServerClient testStartupClient() {
			return new TournamentServerClient("http://localhost:8080") 
			{
				@Override
				public void register(String tournamentId, String name, String ip, int port) 
				{ //nothing	
				}
			};
		}
	}
	
	
	@Test 
	void nametest(){
		String url = "http://localhost:"+port +"/name";
		ResponseEntity<String> response = restTemp.getForEntity(url, String.class);
		assertEquals("Tit For Tat", response.getBody());
	}








}