package SITS.Remote;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import SITS.Actions.Action;
import SITS.Game.GameHistory;
import SITS.Remote.Network.RemoteParticipant;
import SITS.Remote.Network.StringAction;
import SITS.Remote.Network.dto.GameHistoryDTO;

class NetworkTests {
	
	

	@BeforeEach
	void setUp() throws Exception {
	}

	//I know human participant is in this package but I'm going to do it in a separate test file
	//amendment to the comment, testing for human participant does not need to happen
	//professor made a class wide decision. 
	
	//String action test, i still have it in there. 
	@Test
	void StringActionTest() {
		StringAction action = new StringAction("DEFECT");
		assertEquals("DEFECT",action.getLabel());
	}
	
	
	@Test
	void RMParticipantMethodsTest()
	{
		// this is the test setup for testing RestTemplate, the other things are 
		// for the remoteParticipant constructor
		RestTemplate mockRestTemp = mock(RestTemplate.class);
		Function<String, Action> actionFactory = StringAction::new;
		String client_url = "htp://localhost:8080";
		//spring has a tool called MockRestServiceServer that tests http traffic rather than mocking the object but 
		
		RemoteParticipant realconstructor = new RemoteParticipant("NetRunner", client_url, actionFactory);
		assertEquals("NetRunner", realconstructor.getName());
		
		//mockito: when tells the mock template how to behave so when the app makes a post request
		// this makes sure to return "COOPERATe when the /action endpoint is hit"
		when(mockRestTemp.postForObject(eq(client_url+"/action"), any(GameHistoryDTO.class), eq(String.class)))
				.thenReturn("COOPERATE");
		
		//test the getName(). NetRunner is a Cyberpunk reference
		RemoteParticipant rm = new RemoteParticipant("NetRunner", client_url, actionFactory, mockRestTemp);
		assertEquals("NetRunner", rm.getName());
		
		GameHistory history = new GameHistory("Adam","NetRunner");
		
		Action action = rm.chooseAction(history);
		assertEquals("COOPERATE", action.getLabel());
		
		// make sure the template was actually used to make exactly 1 Post to the client url
		verify(mockRestTemp, times(1)).postForObject( eq(client_url+ "/action"),any(GameHistoryDTO.class), eq(String.class)); 
		
		
		//lets see if reset works 
		rm.reset();
		// make sure that the /reset endpoint got hit with a post req and null body 
		verify(mockRestTemp, times(1)).postForEntity(eq(client_url + "/reset"),isNull(),eq(Void.class));
		
	}
}
