package SITS.Remote.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.web.client.RestTemplate;

import SITS.Actions.Action;
import SITS.Actions.Participant;
import SITS.Game.Game;
import SITS.Game.GameResult;
import SITS.Game.MoveEvent;
import SITS.Game.TournamentFormat;
import SITS.Game.TournamentResult;
import SITS.Observers.GameObserver;
import SITS.Remote.Network.RemoteParticipant;
import SITS.Remote.Network.dto.RegistrationRequest;
import SITS.Remote.Network.dto.RoundResultDTO;

public class NetworkedTournament {
	private String id;
	private String name;
	private TournamentFormat format;
	private Game game;
	private List<Participant> participants;
	private TournamentStatus status;
	
	private Function<String, Action> actionFactory;
	
	private List<String> viewers;
	private RestTemplate restTemp;
	
	public NetworkedTournament(String id, String name, TournamentFormat format,Game game, Function<String,Action> actionFactory) {
		this.id = id;
		this.name = name;
		this.format = format;
		this.game = game;
		this.actionFactory = actionFactory;

		this.participants = new ArrayList<>();
		this.status = TournamentStatus.REGISTERING;
		this.viewers = new ArrayList<>();
		this.restTemp = new RestTemplate();
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
	
	
	public void addParticipant(Participant p){participants.add(p);} //this is a testing method
	
	public void addViewer(String ip, int port) {
		String url ="http://"+ip+":"+port;
		if (!viewers.contains(url)) {
			viewers.add(url);
		}
	}
	
	public void removeViewer(String ip, int port) {
		viewers.remove("http://"+ip+":"+port);
	}
	
	private void pushMoves(RoundResultDTO dto)
	{
		for (String viewerURL:viewers)
		{
			try {
				restTemp.put(viewerURL + "/updateMoveList", dto);
				
			}catch (Exception e) {System.out.println("Failed move push" + viewerURL);}
		}	
	}
	
	
	public TournamentResult start() 
	{
		
		this.status = TournamentStatus.RUNNING; 
		
		GameObserver netObserver = new GameObserver() 
		{
			@Override
			public void onMoveMade(MoveEvent event) {
				RoundResultDTO dto = new RoundResultDTO(
						event.getRound().getActionP1().getLabel(),
						event.getRound().getActionP2().getLabel(),
						event.getRound().getScoreP1(),
						event.getRound().getScoreP2());
				
				pushMoves(dto);
			}

			@Override
			public void onTournamentEnd(TournamentResult result) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void onGameEnd(GameResult result) {
				// TODO Auto-generated method stub	
			}
			
		};
		
		game.addObserver(netObserver); //this is a specific overwritten class to send round results to the listview
		TournamentResult result = format.run(participants,game); 
		this.status = TournamentStatus.COMPLETED; 
		return result; 
		
	}
	
}
