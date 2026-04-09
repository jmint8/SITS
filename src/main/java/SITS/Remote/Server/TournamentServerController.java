package SITS.Remote.Server;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import SITS.Game.TournamentResult;
import SITS.Remote.Network.dto.RegistrationRequest;

@RestController
public class TournamentServerController 
{
	private TournamentRegistry registry;

	/**
	 * @param registry
	 */
	public TournamentServerController(TournamentRegistry registry) {
		this.registry = registry;
	}
	
	@GetMapping("/tournaments") 
	public List<NetworkedTournament> getTournaments()
	{ 
		return registry.listRegistering(); 
		
	}
	
	@PostMapping("/register/{id}")
	public ResponseEntity<String> register(@PathVariable String id, @RequestBody RegistrationRequest body) 
	{
		NetworkedTournament tournament=registry.get(id); 
		
		if (tournament == null)
		{
			return ResponseEntity.badRequest().body("can't acquire tournament status:"+id);
		}
		else if (tournament.getStatus() !=TournamentStatus.REGISTERING)  
		{
			return ResponseEntity.badRequest().body("registrations are closed");
		}
		
		tournament.addRemoteParticipant(body);
		return ResponseEntity.ok("registered "+body.name+" to tournament id"+id);
	}
	
	@PostMapping("/start/{id}")
	public TournamentResult start(@PathVariable String id) 
	{
		NetworkedTournament tournament = registry.get(id);
		return tournament.start();
	}
	
}
