package tournament;

public class AlwaysDefect implements Participant {

	
	@Override
	public String getName(){
		return "Always Defect";
	}
	
	@Override
    public Action chooseAction(GameHistory history) {
        return PrisonerAction.DEFECT;
    }

	@Override
	public void reset() {
		//nothing
		
	}

}
