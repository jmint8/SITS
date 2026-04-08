package SITS.Remote.Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import SITS.Actions.PrisonerAction;
import SITS.Game.ItteratedPrisonersDilemma;
import SITS.Game.RoundRobin;


@SpringBootApplication
public class TournamentServerApp 
{
	//I guess this wasn't in the design document but think this is going to be the
	//2 server requirement thing that lets the server start up. 
	
	public static void main(String[] args) 
	{
		SpringApplication.run(TournamentServerApp.class, args); 
		
	}
	
	
	
	
	
	
}
