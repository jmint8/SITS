package SITS.MVC.main;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import SITS.Game.RoundResult;
import SITS.MVC.Models.viewerModel;

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
	public String receiveMove(@RequestBody RoundResult move)
	{
		return "ok";
	}
	
	
	
}
