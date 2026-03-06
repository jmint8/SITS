package tournament;

public interface GameObserver 
{
	void onMoveMade(MoveEvent event);
	void onTournamentEnd(TournamentResult result);
	void onGameEnd(GameResult result);
}
