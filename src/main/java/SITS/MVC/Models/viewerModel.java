package SITS.MVC.Models;

import java.util.List;
import java.util.Map;

import SITS.Remote.Client.TournamentServerClient;
import SITS.Remote.Network.dto.RoundResultDTO;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class viewerModel 
{
	StringProperty serverIp = new SimpleStringProperty();
	StringProperty port = new SimpleStringProperty();
	
	ObservableList<String> tournamentList = FXCollections.observableArrayList();
	TournamentServerClient client;
	
	private static viewerModel instance;
	ObservableList<String> moves = FXCollections.observableArrayList();
	
	public viewerModel() {instance = this;}
	
	public StringProperty getServerIp() {return serverIp;};
	
	public StringProperty getPort() {return port;}
	
	public void setConnectionParts(String ip, String p)
	{
		serverIp.set(ip);
		port.set(p);
		client = new TournamentServerClient("http://"+ip+":"+p); 
	}

	public ObservableList<String> getTournamentList() 
	{
		return tournamentList; 
	}
	
	public TournamentServerClient getClient() {return client;}
	
	public ObservableList<String> getMoveList() {return moves;}
	
	public static viewerModel getInstance() {return instance;}
	
	public void addMove(String movetxt)
	{
		Platform.runLater(() -> {moves.add(movetxt);});
	}
	
	//new method 
	public void retreiveTournaments()
	{
		try
		{
			tournamentList.clear();
			
			List<?> rawdataList = client.listTournaments();
			if (rawdataList != null) {
				for(Object o : rawdataList)
				{
					if(o instanceof Map)
					{
						// RestTemplate parses json objects into Maps when using the List.class
						Map<?, ?> map = (Map<?,?>) o;
					
						String id = (String) map.get("id");
						String name = (String) map.get("name");
					
						String status = (String) map.get("status");
					
						if ("REGISTERING".equals(status)||"RUNNING".equals(status)) 
						{
							
						tournamentList.add(name+"("+id+")");
						
						}
					}
				}
			}
			
		}catch (Exception e) {System.out.println("Failed to fetch tournaments:");
			tournamentList.add("Error");
			e.printStackTrace();}
	}
	
	private boolean isWatching;
	
	public void watchTournament(String id)
	{
		isWatching = true;
		moves.clear();
		new Thread(()->{
			int lastMoveIndex =0;
			while(isWatching) 
			{
				try
				{
					RoundResultDTO[] current = client.getRestTemplate()
							.getForObject(client.getServer_url()+"/watch/"+id, RoundResultDTO[].class);
					if(current !=null && current.length > lastMoveIndex) 
					{
						for(int i = lastMoveIndex; i<current.length;i++)
						{
							RoundResultDTO m = current[i];
							String moveString = "P1:"+m.actionP1+" P2:"+m.actionP2+
									" | P1 score: "+m.payoffP1+" P2 score: "+m.payoffP2;
							javafx.application.Platform.runLater(() -> moves.add(moveString));
						}
						lastMoveIndex = current.length;
						
					}
					Thread.sleep(500);
				}catch (Exception e) {}
		
			}
		}).start();
	}
	
	public void leaveTournament()
	{
		isWatching = false;
	}
	
	
	
	
	
	
	
	
	
	
}
