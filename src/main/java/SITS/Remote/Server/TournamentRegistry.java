package SITS.Remote.Server;

import java.util.List;
import java.util.Map;

public class TournamentRegistry 
{
	private Map<String, NetworkedTournament> tournaments;
	
	public void add(NetworkedTournament tournament)
	{
		tournaments.put(tournament.getId(), tournament);
	
	}
	
	public NetworkedTournament get(String id)
	{
		return tournaments.get(id);
	}
	
	public List<NetworkedTournament> listRegistering()
	{
		return null;
		//TODO
	}
	
	
	
}
