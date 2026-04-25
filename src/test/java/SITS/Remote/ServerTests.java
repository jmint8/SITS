package SITS.Remote;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SITS.Remote.Server.NetworkedTournament;
import SITS.Remote.Server.TournamentRegistry;
import SITS.Remote.Server.TournamentStatus;

class ServerTests {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void TournamentResgistryInitandGetTest() {
		TournamentRegistry reg = new TournamentRegistry();
		
		NetworkedTournament tourna = reg.get("ipd-1");
		assertNotNull(tourna);
		assertEquals("Iterated Prisoner's Dilemma",tourna.getName());
	}
	
	
	
	@Test
	void addAndListTests() {
		TournamentRegistry reg = new TournamentRegistry();
		
		//adding a mock tournament that is in the registering state 
		NetworkedTournament tourna1 = mock(NetworkedTournament.class);
		when(tourna1.getId()).thenReturn("reg_Test");
		when(tourna1.getStatus()).thenReturn(TournamentStatus.REGISTERING);
		
		NetworkedTournament tourna2 = mock(NetworkedTournament.class);
		when(tourna2.getId()).thenReturn("comp_Test");
		when(tourna2.getStatus()).thenReturn(TournamentStatus.COMPLETED);
		
		reg.add(tourna1);
		reg.add(tourna2);
		
		//list registering shouldnt list out completed tournaments 
		List<NetworkedTournament> regList = reg.listRegistering();
		
		assertFalse(regList.contains(tourna2)); 
		assertTrue(regList.contains(tourna1));
	}
	
	@Test
	void controllerTesting() {
		
		//nothing yet
		
		
		
	}
	
	
	
	
	
	

}
