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
		return null;
		//TODO
	}
	
	
	@PostMapping("/start/{id}")
	public TournamentResult start(@PathVariable String id) 
	{
		return null;
		//TODO
	}
	

}
