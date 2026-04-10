package SITS.Remote.Network;

import SITS.Actions.Action;

public class StringAction implements Action {
	//im still skeptical on this but it is the design so...
	
	private final String label;

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return label;
	}
	
	public StringAction(String label)
	{
		this.label = label;
	}
	

}
