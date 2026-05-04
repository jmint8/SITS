package SITS;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import SITS.Actions.PrisonerAction;
import SITS.Game.GameHistory;
import SITS.Game.GameResult;
import SITS.Game.MoveEvent;
import SITS.Game.RoundResult;
import SITS.Observers.MoveLogger;
import SITS.Observers.ScoreLogger;

class LoggerTests {
	
	@TempDir
	Path tempDir;
	
	String moveLogPath;
	String scoreLogPath;
	MoveLogger moveLogger;
	ScoreLogger scoreLogger;
	
	@BeforeEach
	void setUp() throws Exception {
		moveLogPath = tempDir.resolve("moves.log").toString();
		scoreLogPath = tempDir.resolve("scoreLog.txt").toString();
		
		moveLogger = new MoveLogger(moveLogPath);
		scoreLogger = new ScoreLogger(scoreLogPath);
		
	}

	@Test
	void moveWriteTests() throws IOException{
		GameHistory history = new GameHistory("p1", "p2");
        RoundResult round = new RoundResult(PrisonerAction.COOPERATE, PrisonerAction.DEFECT, 0, 10, 1);
        history.getRounds().add(round);
        
        moveLogger.onMoveMade(new MoveEvent(history, round));
        
        String lines = Files.readString(Path.of(moveLogPath));
		assertFalse(lines.isEmpty());
		assertTrue(lines.contains("Round 1: P1: COOPERATE (Score: 0), P2: DEFECT (Score: 10)"));
	}
	@Test
	void ScoreWriteTest() throws IOException {
		
		GameResult result = new GameResult("Mitch", "Adam", 50, 120, "Adam");
		scoreLogger.onGameEnd(result);
		String content = Files.readString(Path.of(scoreLogPath));

		assertTrue(content.contains("Mitch vs Adam"));
		assertTrue(content.contains("Winner: Adam"));
		
	}
	
	@Test
	void Append_Tests() throws IOException {
		// Testing that multiple calls append to the same file
		GameResult res1 = new GameResult("p1", "2", 5, 5, "it's a tie");
		GameResult res2 = new GameResult("p3", "p4", 0, 10, "p3");
		
		scoreLogger.onGameEnd(res1);
		scoreLogger.onGameEnd(res2);
		
		String content = Files.readString(Path.of(scoreLogPath));
		
		assertTrue(content.contains("Winner: it's a tie"));
		assertTrue(content.contains("Winner: p3"));
		
	}
	
	
	
	

}
