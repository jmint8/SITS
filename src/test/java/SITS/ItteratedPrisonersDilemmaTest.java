package SITS;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SITS.Actions.Action;
import SITS.Actions.AlwaysCooperate;
import SITS.Actions.AlwaysDefect;
import SITS.Actions.Participant;
import SITS.Actions.TitForTat;
import SITS.Game.GameHistory;
import SITS.Game.GameResult;
import SITS.Game.ItteratedPrisonersDilemma;

class ItteratedPrisonersDilemmaTest {
	
	AlwaysCooperate alwaysCoop;
	AlwaysDefect alwaysDef;
	TitForTat tft;
	ItteratedPrisonersDilemma game1Round;
	ItteratedPrisonersDilemma game5Rounds;
	
	Participant Unknown;

	@BeforeEach
	void setUp() throws Exception {
		alwaysCoop = new AlwaysCooperate();
		alwaysDef = new AlwaysDefect();
		tft = new TitForTat();
		
		Unknown = new Participant() {
			public String getName() { 
				return "UnknownPlayer";
			}
			public void reset() {} // still nothing to do here
			
			public Action chooseAction(GameHistory history) { 
				return new Action() {public String getLabel() { return "UNKNOWN"; }};
			}
		};
	
		game1Round = new ItteratedPrisonersDilemma(1);
		game5Rounds = new ItteratedPrisonersDilemma(5);
	}
	
	

	@Test
	void payoffsTest() {
		//ties
		GameResult resultCC = game1Round.play(alwaysCoop, alwaysCoop);
		assertEquals(5, resultCC.getTotalScoreP1());
		assertEquals(5, resultCC.getTotalScoreP2());
		assertEquals("it's a tie", resultCC.getWinner());	
		
		GameResult resultDD = game1Round.play(alwaysDef, alwaysDef);
		assertEquals(1, resultDD.getTotalScoreP1());
		assertEquals(1, resultDD.getTotalScoreP2());
		assertEquals("it's a tie", resultDD.getWinner());
		
		// alwaysDef vs alwaysCoop both players 
		GameResult resultDC = game1Round.play(alwaysDef, alwaysCoop);
		assertEquals(10, resultDC.getTotalScoreP1());
		assertEquals(0, resultDC.getTotalScoreP2());
		assertEquals("Always Cooperate", resultDC.getWinner());
		
		GameResult resultCD = game1Round.play(alwaysCoop, alwaysDef);
		assertEquals(0, resultCD.getTotalScoreP1());
		assertEquals(10, resultCD.getTotalScoreP2());
		assertEquals("Always Cooperate", resultCD.getWinner());
	}
	
	@Test
	void gameTest() {
		
		GameResult resultTFTCoop = game5Rounds.play(tft, alwaysCoop);
		//due to the empty history on the first round this is effectively COOPvsCOOP
		assertEquals(25, resultTFTCoop.getTotalScoreP1());
		assertEquals(25, resultTFTCoop.getTotalScoreP2());
		assertEquals("it's a tie", resultTFTCoop.getWinner());
		
		GameResult resultTFTDefect = game5Rounds.play(tft, alwaysDef);
		// should be TFT = 4, AD = 14
		assertEquals(4, resultTFTDefect.getTotalScoreP1());
		assertEquals(14, resultTFTDefect.getTotalScoreP2());
		assertEquals("Tit For Tat", resultTFTDefect.getWinner());
	}
	
	@Test
	void unknownPlayerTests() {
		GameResult resultADvUnknown = game1Round.play(alwaysDef, Unknown);
		assertEquals(0, resultADvUnknown.getTotalScoreP1());
		assertEquals(0, resultADvUnknown.getTotalScoreP2());
		
		GameResult resultACvUnknown = game1Round.play(alwaysCoop, Unknown);
		assertEquals(0, resultACvUnknown.getTotalScoreP1());
		assertEquals(0, resultACvUnknown.getTotalScoreP2());
		
		GameResult resultUvU = game1Round.play(Unknown, Unknown);
		assertEquals(0, resultUvU.getTotalScoreP1());
		assertEquals(0, resultUvU.getTotalScoreP2());

		
	}
	

}
