package SITS.MVC.Models;

import java.util.List;
import java.util.Map;

import SITS.Remote.Client.TournamentServerClient;
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
	
	public viewerModel() {}
	
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
		

}
