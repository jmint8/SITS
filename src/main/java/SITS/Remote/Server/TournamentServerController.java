package SITS.Remote.Server;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import SITS.Remote.Network.dto.RegistrationRequest;
import SITS.Remote.Network.dto.RoundResultDTO;

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
		return registry.listAll(); 
		
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
		
		body.name = body.name +"-"+ body.ip +":"+ body.port;
		
		tournament.addRemoteParticipant(body);
		return ResponseEntity.ok("registered "+body.name+" to tournament id"+id);
	}
	
	@GetMapping("/start/{id}")
	public ResponseEntity<String> start(@PathVariable String id) 
	{
		NetworkedTournament tournament = registry.get(id);
		new Thread(() ->{
			tournament.start();
		}).start();
		
		return ResponseEntity.ok("tournament started");
	}
	
	@PutMapping("/watch/{id}")
	public ResponseEntity<String> registerViewer(@PathVariable String id, @RequestBody RegistrationRequest body)
	{
		NetworkedTournament tourna = registry.get(id);
		
		if(tourna == null)
		{
			return ResponseEntity.badRequest().body("tournament"+id+"not found");
		}
		tourna.addViewer(body.ip, body.port);
		return ResponseEntity.ok("viewer registered");
	}
	
	@PutMapping("/stopWatching/{id}")
	public ResponseEntity<String> deRegister(@PathVariable String id, @RequestBody RegistrationRequest body)
	{
		NetworkedTournament tourna = registry.get(id);
		if(tourna == null)
		{
			return ResponseEntity.badRequest().body("tournament"+id+"not found");
		}
		tourna.removeViewer(body.ip, body.port);
		return ResponseEntity.ok("viewer was de registered");
	}

	
	
	
}
