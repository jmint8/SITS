package tournament;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {
	private final List<GameObserver> observers = new ArrayList<>();
	
	protected abstract boolean isOver(GameHistory history);
	
	protected abstract RoundResult doRound(Participant p1, Participant p2, GameHistory history);
	
	protected abstract GameResult computeFinalResult(GameHistory history);
	
	public void addObserver(GameObserver observer)
	{
        observers.add(observer);
    }
	
    public void removeObserver(GameObserver observer)
    {
        observers.remove(observer);
    }
    
    protected void notifyMoveMade(MoveEvent event) 
    {
        for (GameObserver observer : observers) {
            observer.onMoveMade(event);
        }
    }
    
    protected void notifyGameOver(GameResult result)
    {
        for (GameObserver observer : observers) {
            observer.onGameEnd(result);
        }
    }
    
    //TODO
	
}
