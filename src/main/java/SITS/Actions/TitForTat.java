package SITS.Actions;

import SITS.Game.GameHistory;
import SITS.Game.RoundResult;


public class TitForTat implements Participant {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Tit For Tat";
	}

	@Override
	public Action chooseAction(GameHistory history) {
		RoundResult lastRound = history.getLastRound();
		if (lastRound == null) {
			return PrisonerAction.COOPERATE;
		}
		
		
		if(history.getP1Name().equals(this.getName())) {
			return lastRound.getActionP2();
			//this is so we make sure the last action is the one that belonged to the opponent.
		}
		else {
			return lastRound.getActionP1();
		}
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
