package SITS.Remote.Network;

import SITS.Actions.Action;
import SITS.Actions.Participant;
import SITS.Game.GameHistory;
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
	 * this one is for testing
	 */
	RemoteParticipant(String name, String url, Function<String, Action> actionFactory, RestTemplate restTemp) {
		super();
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
		super();
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
	public Action chooseAction(GameHistory history) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
