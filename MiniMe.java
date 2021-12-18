/**
 * This class creates a MiniMi player who has a 30% chance of turning left and 70% chance of turning right
 * 
 * @author Riberiko Niyomwungere
 */

import java.awt.Color;
import java.util.Random;

public class MiniMe extends MashUpPlayer{

	private Random rand;
	
	public MiniMe() {
		super("M", Color.GREEN);
		rand = new Random();
		
	}

	@Override
	public MashUpPlayer.Action getMove(MashUpPlayerInfo info) {
		// TODO Auto-generated method stub
		
		MashUpPlayer.Neighbor front = info.getFront();

		if(front == MashUpPlayer.Neighbor.OTHER) {
			return MashUpPlayer.Action.FIGHT;
		}
		
		//from 0 to 100
		int chance = rand.nextInt(101);
		if(chance <= 30) {
			return MashUpPlayer.Action.LEFT;
		}
		return MashUpPlayer.Action.RIGHT;
	}

}
