package SITS.Remote;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.web.client.RestTemplate;

import SITS.Game.TournamentResult;
import SITS.Remote.Network.dto.RegistrationRequest;
import SITS.Remote.Server.NetworkedTournament;
import SITS.Remote.Server.TournamentRegistry;
import SITS.Remote.Server.TournamentServerApp;
import SITS.Remote.Server.TournamentStatus;


@SpringBootTest(classes ={TournamentServerApp.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
		properties = {"server.port = 6000"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD) // reset after so we dont get stuck in COMPLETED state
class ServerTests {

	@LocalServerPort
	private int port = 6000;
	
	private RestTemplate restTemp = new RestTemplate();
	
	@Autowired
	private TournamentRegistry reg;
	
	@Test
	void TournamentResgistryInitandGetTest() {
		
		NetworkedTournament tourna = reg.get("ipd-1");
		assertNotNull(tourna);
		assertEquals("Iterated Prisoner's Dilemma",tourna.getName());
	}
	
	@Test
	void addAndListTests() 
	{
		List<NetworkedTournament> regList = reg.listRegistering();
		assertNotNull(regList);
		assertFalse(regList.isEmpty());
	}
	
	@Test
	void testGetTournaments()
	{
		String url = "http://localhost:"+port+"/tournaments";
		ResponseEntity<List> response = restTemp.getForEntity(url, List.class);
		assertTrue(response.getStatusCode().is2xxSuccessful());
		assertNotNull(response.getBody());
		assertFalse(response.getBody().isEmpty());
	}
	
	@Test
	void testRegisterClose()
	{
		reg.get("ipd-1").start();
		String url = "http://localhost:"+port+"/register/ipd-1"; 
		RegistrationRequest req = new RegistrationRequest("TooLate", "127.0.0.1", 9021);
		//TODO
		assertThrows(org.springframework.web.client.HttpClientErrorException.BadRequest.class, () -> {
			restTemp.postForEntity(url, req, String.class);});
		
		
		
	}
	
	@Test
	void testStart()
	{
		String url = "http://localhost:"+port+"/start/ipd-1";
		ResponseEntity<TournamentResult> response = restTemp.getForEntity(url, TournamentResult.class);
		assertTrue(response.getStatusCode().is2xxSuccessful());
		assertEquals(TournamentStatus.COMPLETED, reg.get("ipd-1").getStatus());
	}
	
	
	
	

}
