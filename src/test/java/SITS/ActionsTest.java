package SITS;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SITS.Actions.AlwaysCooperate;
import SITS.Actions.AlwaysDefect;
import SITS.Actions.PrisonerAction;
import SITS.Actions.TitForTat;
import SITS.Game.GameHistory;
import SITS.Game.RoundResult;

public class ActionsTest {
	
	AlwaysCooperate alwaysCoop;
	AlwaysDefect alwaysDef;
	TitForTat tft;
	
	@BeforeEach
	void setUp() {
		alwaysCoop = new AlwaysCooperate();
        alwaysDef = new AlwaysDefect();
        tft = new TitForTat();
	}
	
	//always cooperate 
	@Test
	void alwaysCoopTest() {
		assertEquals("Always Cooperate", alwaysCoop.getName());
		
		GameHistory history = new GameHistory("p1AC", "p2");
        assertEquals(PrisonerAction.COOPERATE, alwaysCoop.chooseAction(history));
	}
	
	//always defect
	@Test 
	void alwaysDefTest(){
		assertEquals("Always Defect", alwaysDef.getName());
		GameHistory history = new GameHistory("p1AD", "p2");
        assertEquals(PrisonerAction.DEFECT, alwaysDef.chooseAction(history));
	}
	
	//titFortat
	@Test
	void tftTest() {
		assertEquals("Tit For Tat", tft.getName());
		
		GameHistory history = new GameHistory("p1Tft", "p2");
		assertEquals(PrisonerAction.COOPERATE, tft.chooseAction(history));
	}
	
	@Test
	void p1TftTest() {
		GameHistory history = new GameHistory("p1Tft", "p2");
		//round no history
        assertEquals(PrisonerAction.COOPERATE, tft.chooseAction(history));
		// round 1
        history.getRounds().add(new RoundResult(PrisonerAction.DEFECT, PrisonerAction.COOPERATE, 10, 0, 1));
        assertEquals(PrisonerAction.COOPERATE, tft.chooseAction(history));
	}
	
	
	@Test
	void p2TftTest() {
		GameHistory history = new GameHistory("p1", "p2Tft");
		history.getRounds().add(new RoundResult(PrisonerAction.DEFECT, PrisonerAction.COOPERATE, 10, 0, 1));
        assertEquals(PrisonerAction.DEFECT, tft.chooseAction(history));
	}

	
	
}
