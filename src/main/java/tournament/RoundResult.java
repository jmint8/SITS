package tournament;

public class RoundResult {
	private Action actionP1;
	private Action actionP2;
	private int scoreP1;
	private int scoreP2;
	private int roundNumber;
	
	 public RoundResult(Action actionP1, Action actionP2, int scoreP1, int scoreP2, int roundNumber)
	 {
	        this.actionP1 = actionP1;
	        this.actionP2 = actionP2;
	        this.scoreP1 = scoreP1;
	        this.scoreP2 = scoreP2;
	        this.roundNumber = roundNumber;
	 }

	 /**
 	 * @return the actionP1
 	 */
	 public Action getActionP1() {
		 return actionP1;
	 }

	 /**
 	 * @return the actionP2
 	 */
	 public Action getActionP2() {
		 return actionP2;
	 }

	 /**
 	 * @return the scoreP1
 	 */
	 public int getScoreP1() {
		 return scoreP1;
	 }

	 /**
 	 * @return the scoreP2
 	 */
	 public int getScoreP2() {
		 return scoreP2;
	 }

	 /**
 	 * @return the roundNumber
 	 */
	 public int getRoundNumber() {
		 return roundNumber;
	 }
	 
	 
	 
}
