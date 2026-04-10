package SITS.Remote.Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import SITS.Actions.PrisonerAction;
import SITS.Game.ItteratedPrisonersDilemma;
import SITS.Game.RoundRobin;

@Service
public class TournamentRegistry 
{
	private Map<String, NetworkedTournament> tournaments = new HashMap<>();
	
	public TournamentRegistry() {
		NetworkedTournament ipd = new NetworkedTournament(
				"ipd-1",
				"Iterated Prisoner's Dilemma",
				new RoundRobin(),
				new ItteratedPrisonersDilemma(10),
				PrisonerAction::valueOf
		);
		this.add(ipd);
	}
	
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
		List<NetworkedTournament> regi = new ArrayList<>();
		for (NetworkedTournament tourn : tournaments.values()) {
			if(tourn.getStatus() == TournamentStatus.REGISTERING)
			{
				regi.add(tourn);
			}
		}
		return regi;
		
	}
	
	
	
}
