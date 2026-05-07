package SITS.MVC.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class viewerModel 
{
	StringProperty serverIp = new SimpleStringProperty();
	StringProperty port = new SimpleStringProperty();
	
	ObservableList<String> tournamentList = FXCollections.observableArrayList();
	
	public viewerModel() 
	{
		tournamentList.add("testing tournament listview");
		tournamentList.add("testing tournament listview 2");
	}
	
	public StringProperty getServerIp() {return serverIp;};
	
	public StringProperty getPort() {return port;}
	
	public void setConnectionParts(String ip, String p)
	{
		serverIp.set(ip);
		port.set(p);
	}

	public ObservableList<String> getTournamentList() 
	{
		return tournamentList; 
	}

}
