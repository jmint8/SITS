package SITS.Game;

import java.util.List;

import SITS.Actions.Action;
import SITS.Actions.Participant;
import SITS.Actions.PrisonerAction;

public class ItteratedPrisonersDilemma extends Game {
	private int maxRounds;
	
	public ItteratedPrisonersDilemma(int maxRounds) {
		this.maxRounds = maxRounds;
	}
	
	@Override
	protected boolean isOver(GameHistory history) {

		return history.getRounds().size() >= maxRounds;
	}

	@Override
	protected RoundResult doRound(Participant p1, Participant p2, GameHistory history) {
		Action a1 = p1.chooseAction(history);
		Action a2 = p2.chooseAction(history);
		int[] payoffs = getPayoff(a1,a2);
		return new RoundResult(a1,a2,payoffs[0],payoffs[1], history.getRounds().size()+1);
	}

	@Override
	protected GameResult computeFinalResult(GameHistory history) {
		
		int p1Total = 0;
		int p2Total = 0;
		
		List<RoundResult> rounds = history.getRounds();
		
		for (int i = 0;i<rounds.size(); i++){
		    RoundResult round = rounds.get(i);
		    p1Total += round.getScoreP1();
		    p2Total += round.getScoreP2();

		}
		String winner;
		//lowest score wins 
		if(p1Total == p2Total) {
			winner = "it's a tie";
		}
		else if (p1Total > p2Total) {
			winner = history.getP2Name();
		}
		else {
			winner = history.getP1Name();
		}
		return new GameResult(history.getP1Name(), history.getP2Name(), p1Total, p2Total, winner);
		
		
		
	}
	
	private int[] getPayoff(Action a1, Action a2) {
		if (a1 == PrisonerAction.DEFECT  && a2 == PrisonerAction.COOPERATE) {
			return new int[]{10, 0};
		}
		else if (a1 == PrisonerAction.COOPERATE && a2== PrisonerAction.DEFECT) {
			return new int[]{0, 10};
		}
		else if (a1 == PrisonerAction.DEFECT && a2 ==PrisonerAction.DEFECT) {
			return new int[]{1, 1};
			//1/4 branches missed
		}
		else if (a1 == PrisonerAction.COOPERATE && a2 == PrisonerAction.COOPERATE) {
			return new int[]{5, 5};
			//2/4 branches missed
		}
		else {
			//should never reach this else.
			return new int[] {0,0};
		}
	}

}
