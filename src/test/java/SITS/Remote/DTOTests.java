package SITS.Remote;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import SITS.Actions.PrisonerAction;
import SITS.Game.GameHistory;
import SITS.Game.RoundResult;
import SITS.Remote.Network.StringAction;
import SITS.Remote.Network.dto.GameHistoryDTO;
import SITS.Remote.Network.dto.RegistrationRequest;

public class DTOTests 
{

    @Test
    public void mapGameHistoryToDTOAndBack() 
    { 
        GameHistory history = new GameHistory("Mtich", "Adam"); 
        history.getRounds().add(new RoundResult(PrisonerAction.COOPERATE, PrisonerAction.DEFECT,0, 10, 1)); 
        

        // To DTO
        GameHistoryDTO dto = GameHistoryDTO.fromGameHistory(history); 
        assertEquals("Mtich", dto.nameP1);
        assertEquals("Adam", dto.nameP2); 
        
        
        assertEquals(1, dto.rounds.size());
        assertEquals("COOPERATE", dto.rounds.get(0).actionP1);
        
        // From DTO
        GameHistory reconstructed = dto.toGameHistory();
        assertEquals("Mtich", reconstructed.getP1Name());
        assertEquals("COOPERATE", reconstructed.getLastRound().getActionP1().getLabel());
    }

    @Test
    public void stringAction_ReturnsCorrectLabel() 
    {
    	
        StringAction action = new StringAction("DEFECT");
        assertEquals("DEFECT", action.getLabel());
    }
    
    @Test
    public void registrationRequest_ConstructorsAssignValues() 
    {
    	
    	
        RegistrationRequest req = new RegistrationRequest("TestName", "192.168.1.1", 9090);
        assertEquals("TestName", req.name);
        
        assertEquals("192.168.1.1", req.ip);
        assertEquals(9090, req.port); 
     
    }
}
