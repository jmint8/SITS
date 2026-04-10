package SITS.Remote.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import SITS.Actions.Action;
import SITS.Actions.Participant;
import SITS.Game.Game;
import SITS.Game.TournamentFormat;
import SITS.Game.TournamentResult;
import SITS.Remote.Network.RemoteParticipant;
import SITS.Remote.Network.dto.RegistrationRequest;

public class NetworkedTournament {
	private String id;
	private String name;
	private TournamentFormat format;
	private Game game;
	private List<Participant> participants;
	private TournamentStatus status;
	
	private Function<String, Action> actionFactory;
	
	public NetworkedTournament(String id, String name, TournamentFormat format,Game game, Function<String,Action> actionFactory) {
		this.id = id;
		this.name = name;
		this.format = format;
		this.game = game;
		this.actionFactory = actionFactory;

		this.participants = new ArrayList<>();
		this.status = TournamentStatus.REGISTERING;
	}
	
	public String getId() 
	{ 
		return id; 
	}

	public String getName() 
	{
		return name; 
	} 
	
	public TournamentStatus getStatus() 
	{
		return status; 
	}
	
	public void addRemoteParticipant(RegistrationRequest request)
	{
		String client_url = "http://" + request.ip + ":" + request.port;
		RemoteParticipant remote = new RemoteParticipant(request.name, client_url, actionFactory);
		participants.add(remote);
	}
	
	public TournamentResult start() 
	{
		
		this.status = TournamentStatus.RUNNING; 
		TournamentResult result = format.run(participants,game); 
		this.status = TournamentStatus.COMPLETED; 
		
		return result; 
		
	}
	
	
	
}
