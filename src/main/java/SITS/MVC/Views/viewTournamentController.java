package SITS.MVC.Views;


import SITS.MVC.Models.ViewTransitionModelInterface;
import SITS.MVC.Models.viewerModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import SITS.MVC.Models.EmojiNoteStrategy;
import SITS.MVC.Models.NoteStrategy;

public class viewTournamentController 
{
	ViewTransitionModelInterface transModel;
	viewerModel model;
	
	@FXML
	private Button backButton;
	
	@FXML
	private Button smileyButton;
	@FXML
	private Button nervyButton;
	@FXML
	private Button postButton;
	
	
	@FXML
	private ListView<String> moveListView;
	
	public void setModel(ViewTransitionModelInterface tModel,viewerModel newModel )
	{
		this.transModel = tModel;
		this.model = newModel;
		
		moveListView.setItems(model.getMoveList());
		notesListView.setItems(model.getNotesList());
	}
	
	@FXML
	void onBackClick()
	{
		//de register somehow
		transModel.showTournamentDash();
		System.out.println("going back to Dashboard");
		
	}
	
	
	//sprint 4 content again 
	
	@FXML
	private ListView<String> notesListView;
	
	@FXML
	private TextField noteInput;
	
	@FXML
	void onPostClick()
	{
		String text = noteInput.getText();
		if (text != null)
		{
			model.postNote(new NoteStrategy(), text);
			noteInput.clear();
		}
	}
	
	@FXML
	void onSmileyClick()
	{
		model.postNote(new EmojiNoteStrategy(), "smiley");
	}
	
	@FXML
	void onNervyClick()
	{
		model.postNote(new EmojiNoteStrategy(), "nervy");
	}
	

}
