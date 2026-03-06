package tournament;

public class AlwaysCooperate implements Participant{
	
	@Override
	public String getName(){
		return "Always Cooperate";
	}
	
	@Override
    public Action chooseAction(GameHistory history) {
        return PrisonerAction.COOPERATE;
    }

    @Override
    public void reset() {
    	//nothing 
    }
}
