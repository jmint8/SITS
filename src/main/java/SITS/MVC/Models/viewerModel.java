package SITS.MVC.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class viewerModel 
{
	StringProperty serverIp = new SimpleStringProperty();
	StringProperty port = new SimpleStringProperty();
	
	public viewerModel() {}
	
	public StringProperty getServerIp() {return serverIp;};
	
	public StringProperty getPort() {return port;}
	
	public void setConnectionParts(String ip, String p)
	{
		serverIp.set(ip);
		port.set(p);
	}
	
	
}
