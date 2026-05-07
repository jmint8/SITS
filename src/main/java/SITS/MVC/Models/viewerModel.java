package SITS.MVC.Models;

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
	public void retreivetournaments()
	{
		try
		{
			tournamentList.clear();
			
			  
			
			
		}catch (Exception e) {System.out.println("Failed to fetch tournaments:");}
	}
		
		
		
	

}
