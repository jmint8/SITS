package SITS.Remote.Network;

import java.util.Scanner;

import SITS.Actions.Action;
import SITS.Actions.Participant;
import SITS.Game.GameHistory;
import SITS.Game.RoundResult;

public class HumanParticipant implements Participant {
	
	private String name;
	private Scanner scanner;
	
	public HumanParticipant(String name) {
		this.name = name;
		this.scanner = new Scanner(System.in);
	}
	

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Action chooseAction(GameHistory history) {
		RoundResult lastRound = history.getLastRound();
		if(lastRound != null) {
			System.out.println(" in the last round you:"+ 
					lastRound.getActionP1().getLabel()+
					"and the opponent played:"+
					lastRound.getActionP2().getLabel());
		}
		
		String input = "";
		while (!input.equals("COOPERATE") && !input.equals("DEFECT")) {
			System.out.print("Enter your action (COOPERATE or DEFECT): ");
			//this is for input validation, make sure that the input is actually accepted. 
			input = scanner.nextLine().trim().toUpperCase();
		}
		
		return new StringAction(input);

	}

	@Override
	public void reset() {
		//nothing to do here really
	}

}