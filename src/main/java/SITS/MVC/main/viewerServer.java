package SITS.MVC.main;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import SITS.MVC.Models.viewerModel;
import SITS.Remote.Network.dto.RoundResultDTO;

@SpringBootApplication
@RestController
public class viewerServer 
{
	//this is the springboot app that will need to run alongside the JavaFX viewer and recieve moves psushed from the toruanemnt
	private viewerModel model;
	
	public void setModel(viewerModel model)
	{
		this.model =model;
	}
	
	@PutMapping("/updateMoveList")
	public String receiveMove(@RequestBody RoundResultDTO m)
	{
		if(model!=null)
		{
			String moveString = "P1:"+m.actionP1+" P2:"+m.actionP2+
					" | P1 score: "+m.payoffP1+" P2 score: "+m.payoffP2;
			model.addMove(moveString);
		}
		return "ok";
	}
	
	
	
}
