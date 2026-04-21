package SITS.Remote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import SITS.Remote.Client.TournamentServerClient;

import org.springframework.boot.test.context.SpringBootTest;



class ClientTests {

	

	@Test
	void tournamentServerClientTests() {
		TournamentServerClient TSC = new TournamentServerClient("http://testing");
		
		assertEquals("http://testing",TSC.getServer_url());
		assertNotNull(TSC.getRestTemplate());
		
		//ListTournaments test
		//needs more setup from rest template 
	
	}

}
