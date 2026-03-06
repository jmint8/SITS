package tournament;

public class MoveEvent {
	private GameHistory history;
    private RoundResult lastRound;
    
    public MoveEvent(GameHistory history, RoundResult rr)
    {
    	this.history = history;
    	this.lastRound = rr;
    }
    
    public GameHistory getHistory() {
    	return history;
    }
    public RoundResult getRound() {
    	return lastRound;
    }
    
}
