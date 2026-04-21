package SITS.Remote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SITS.Remote.Server.NetworkedTournament;
import SITS.Remote.Server.TournamentRegistry;

class ServerTests {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void TournamentResgistryTest() {
		TournamentRegistry reg = new TournamentRegistry();
		
		NetworkedTournament tourna = reg.get("ipd-1");
		assertNotNull(tourna);
		assertEquals("Iterated Prisoner's Dilemma",tourna.getName());
		
		
		
	}

}
