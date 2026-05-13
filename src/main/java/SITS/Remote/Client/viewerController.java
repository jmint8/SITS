package SITS.Remote.Client;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import SITS.MVC.Models.viewerModel;
import SITS.Remote.Network.dto.RoundResultDTO;

@RestController
public class viewerController 
{
	@PostMapping()
	public void getMove(@RequestBody RoundResultDTO move)
	{
		viewerModel model = viewerModel.getInstance();
		if(model!=null)
		{
			String moveString = "P1:"+move.actionP1+" P2:"+move.actionP2
					+" | P1 score:"+move.payoffP1+" P2 score:"+move.payoffP2;
			
			model.addMove(moveString);
		}
	}
	
	
}
