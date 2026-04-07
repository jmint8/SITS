package SITS.Remote.Network;

import SITS.Actions.Action;
import SITS.Actions.Participant;
import SITS.Game.GameHistory;
import SITS.Remote.Network.dto.GameHistoryDTO;

import java.util.function.Function;
import org.springframework.web.client.RestTemplate;

public class RemoteParticipant implements Participant {
	
	private final String name;
	private final String url;
	private final Function<String, Action> actionFactory;
	private final RestTemplate restTemp;
	
	/**
	 * @param name
	 * @param url
	 * @param actionFactory
	 * @param restTemp
	 * 
	 * this one is for testing
	 */
	RemoteParticipant(String name, String url, Function<String, Action> actionFactory, RestTemplate restTemp) {
		this.name = name;
		this.url = url;
		this.actionFactory = actionFactory;
		this.restTemp = restTemp;
	}
	

	/**
	 * @param name
	 * @param url
	 * @param actionFactory
	 */
	public RemoteParticipant(String name, String url, Function<String, Action> actionFactory) {
		this.name = name;
		this.url = url;
		this.actionFactory = actionFactory;
		this.restTemp = new RestTemplate();
	}



	@Override
	public String getName() {
		return name;
	}

	@Override
	public Action chooseAction(GameHistory history) 
	{
		
		String label =	restTemp.postForObject(url+"/action", GameHistoryDTO.fromGameHistory(history), String.class); 
		return actionFactory.apply(label); 
		
		
	}

	@Override
	public void reset() {
		restTemp.postForEntity(url+"/reset",null, Void.class); 

	}

}
