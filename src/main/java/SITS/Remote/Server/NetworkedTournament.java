package SITS.Remote.Server;

import java.util.List;

import SITS.Actions.Participant;
import SITS.Game.Game;
import SITS.Game.TournamentFormat;
import SITS.Game.TournamentResult;
import SITS.Remote.Network.dto.RegistrationRequest;

public class NetworkedTournament {
	private String id;
	private String name;
	private TournamentFormat format;
	private Game game;
	private List<Participant> paticipants;
	private TournamentStatus status;
	
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
		//stuff here
	}
	
	public TournamentResult start() 
	{
		return null;
		//stuff here
	}
	
	
	
}
