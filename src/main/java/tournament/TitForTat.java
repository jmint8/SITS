package tournament;

public class TitForTat implements Participant {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Tit For Tat";
	}

	@Override
	public Action chooseAction(GameHistory history) {
		// TODO Auto-generated method stub
		// if (history.getRounds().isEmpty()){
		return null;
		//need some way to check for last action
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
