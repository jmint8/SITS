package tournament;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TournamentResult {
	private Map<String, Integer> scores;
	
	public TournamentResult(Map<String, Integer> scores) {
		this.scores = new HashMap<>(scores);
	}

	/**
	 * @return the scores
	 */
	public int getScores(String name) {
		return scores.getOrDefault(name, 0);
	}
	
	public List<String> getRankings(){
		//TODO
		return null;
	}
	
	
}
