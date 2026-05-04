package SITS.Observers;

import java.io.FileWriter;
import java.io.IOException;

import SITS.Game.GameResult;
import SITS.Game.MoveEvent;
import SITS.Game.RoundResult;
import SITS.Game.TournamentResult;

public class MoveLogger implements GameObserver {
	private String filePath;
	
	public MoveLogger(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public void onMoveMade(MoveEvent event) {
		try (FileWriter fileW = new FileWriter(filePath, true)) {
            RoundResult r = event.getRound();
            fileW.write("Round " + r.getRoundNumber() + ": P1: " + r.getActionP1().getLabel() + 
                     " (Score: " + r.getScoreP1() + "), P2: " + r.getActionP2().getLabel() + 
                     " (Score: " + r.getScoreP2() + ")\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}

	@Override
	public void onTournamentEnd(TournamentResult result) {
		

	}

	@Override
	public void onGameEnd(GameResult result) {

	}

}
