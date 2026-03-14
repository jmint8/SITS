package SITS;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import SITS.Actions.PrisonerAction;
import SITS.Game.RoundResult;

class RoundResultTest {
	RoundResult result;

	@BeforeEach
	void setUp() throws Exception {
		result = new RoundResult(PrisonerAction.COOPERATE, PrisonerAction.DEFECT, 0, 10, 1);
		
	}

	@Test
	void AttributeTests() {
		assertEquals(PrisonerAction.COOPERATE, result.getActionP1());
		assertEquals(PrisonerAction.DEFECT, result.getActionP2());
		assertEquals(0, result.getScoreP1());
		assertEquals(10, result.getScoreP2());
		assertEquals(1, result.getRoundNumber());
	}
	
	

}
