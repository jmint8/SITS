package SITS.Remote;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import SITS.Remote.Client.ClientApp;
import SITS.Remote.Client.TournamentServerClient;
import SITS.Remote.Network.dto.GameHistoryDTO;
import SITS.Remote.Network.dto.RegistrationRequest;
import SITS.Remote.Server.TournamentServerApp;

import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = {ClientApp.class,TournamentServerApp.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
		properties = {"server.port = 5000","tournament.server.url=http://localhost:5000"})

class ClientTests {

	/*
	 requirements for this test file include hitting the GET/name, POST/action, 
	 POST/reset endpoints.
	 
	 note to self: create an actual tournament instance. not with mockito like how you started ServerTests.
	 - Test the endpoint communications. 
	 - All methods and classes in this package should be tested. 
	*/
	
	@LocalServerPort
	private int port = 5000;
	
	private RestTemplate restTemp = new RestTemplate();
	
	
	//name endpoint
	@Test 
	void nametest(){
		String url = "http://localhost:"+port +"/name";
		ResponseEntity<String> response = restTemp.getForEntity(url, String.class);
		assertEquals("Tit For Tat", response.getBody());
	}

	//action endpoint
	@Test
	void testAction() {
		String url = "http://localhost:" + port + "/action";
		GameHistoryDTO dto = new GameHistoryDTO( "TitForTat", "Opponent",new ArrayList<>()); 
		ResponseEntity<String> response = restTemp.postForEntity(url, dto, String.class);
		assertEquals("COOPERATE", response.getBody());
	}

	//reset endpoint
	@Test
	void resetTest() {
		String url = "http://localhost:"+port+"/reset";
		ResponseEntity<Void> response = restTemp.postForEntity(url, null, Void.class);
		assertTrue(response.getStatusCode().is2xxSuccessful());
	}

	//we need to test the tournamentServerContoller here too. 
	
	//testing getServerUrl()
	@Test
	void testTournamentgeturl() {
		String url = "http://localhost:" + port;
		TournamentServerClient client = new TournamentServerClient(url);
		assertEquals(url,client.getServer_url());
		assertNotNull(client.getRestTemplate());	
	}
	
	
	@Test
	void listTournametsTest() {
		TournamentServerClient client = new TournamentServerClient("http://localhost:" + port);
		
		List<?> tourna = client.listTournaments();
		assertNotNull(tourna);
		assertFalse(tourna.isEmpty());
	}
	
	
	
	@Test
	void registerTest() {
		TournamentServerClient client = new TournamentServerClient("http://localhost:" + port);
		
		client.register("ipd-1","testPlayer","127.0.0.1",9090);
		
		RegistrationRequest req = new RegistrationRequest("Sixer","127.0.0.1",9091);
		ResponseEntity<String> response = restTemp.postForEntity("http://localhost:"+port+"/register/ipd-1", req, String.class);
		
		assertTrue(response.getStatusCode().is2xxSuccessful());
		assertTrue(response.getBody().contains("registered Sixer"));
		
	}

}