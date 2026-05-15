package SITS.MVC.Models;

public class EmojiNoteStrategy implements NoteStrategyInterface {

	@Override
	public String createNote(String input) {
		if(input.equalsIgnoreCase("smiley"))
		{
			return "😄";
		}
		else if(input.equalsIgnoreCase("nervy"))
		{
			return "😅";
		}
		else {
			return null;
		}
	}
}
