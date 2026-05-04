package SITS;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SITS.Actions.AlwaysCooperate;
import SITS.Actions.AlwaysDefect;
import SITS.Actions.Participant;
import SITS.Actions.TitForTat;
import SITS.Game.Game;
import SITS.Game.ItteratedPrisonersDilemma;
import SITS.Game.RoundRobin;
import SITS.Game.TournamentResult;

class RoundRobinTest {
	
	RoundRobin tourna;
	Game game;
	
	Participant alwaysCoop;
    Participant alwaysDefect;
    Participant tft;
    List<Participant> players;

	@BeforeEach
	void setUp() throws Exception {
		tourna = new RoundRobin();
		game = new ItteratedPrisonersDilemma(1);	
		alwaysCoop = new AlwaysCooperate();
        alwaysDefect = new AlwaysDefect();
        tft = new TitForTat();
        players = Arrays.asList(alwaysCoop, alwaysDefect, tft);
	}

	@Test
	void testRoundRobin() {
		TournamentResult result = tourna.run(players, game);
		
		assertEquals(5, result.getScores("Always Cooperate"));
        assertEquals(20, result.getScores("Always Defect"));
        assertEquals(5, result.getScores("Tit For Tat"));
        
	}

}
