/**
 * This create a Personal player object which constantly performs random actions
 * There are essentially 3 different types of Personal objects that are controlled with booleans
 * 
 * Master Moves in random movements and is capable of fighting others
 * 
 * Servant Moves in a circle and is not capable of fighting other monsters
 * 
 * Free(both master and servant false) Moves forward until running into a neighbor that isn't empty
 * Then it turns right. Is capable of fighting others.
 * 
 * @author Riberiko Niyomwungere
 */

import java.awt.Color;
import java.util.Random;

public class Personal extends MashUpPlayer{
	
	private static final Random rand = new Random();
	//capable of fighting others
	private boolean master;
	//incapable of fighting other
	private boolean servant;
	private short mover;
	private int chance;
	//This does not need to ever change.
	private final MashUpPlayer.Action[] movement = new MashUpPlayer.Action[] {MashUpPlayer.Action.MOVE,
			MashUpPlayer.Action.LEFT, MashUpPlayer.Action.MOVE, MashUpPlayer.Action.RIGHT};
	private final MashUpPlayer.Action[] sMovement = new MashUpPlayer.Action[] {MashUpPlayer.Action.MOVE,
			MashUpPlayer.Action.MOVE, MashUpPlayer.Action.LEFT};
	
	public Personal() {
		super("X", Color.BLUE);
		chance = rand.nextInt(101);
		master = false;
		mover = -1;
		if(chance <= 35) {
			master = true;
		}
		servant = false;
	}

	@Override
	public MashUpPlayer.Action getMove(MashUpPlayerInfo info) {

		//used to get an equal random chance of every possible action
		int choice = rand.nextInt(4);
		if(master) {
			super.setColor(Color.YELLOW);
			super.setDisplay("K");
			if(info.getFront() == MashUpPlayer.Neighbor.OTHER) {
				return MashUpPlayer.Action.FIGHT;
			}
		}
		//incapable of fighting others, stuck in circle
		else if(servant) {
			super.setColor(Color.PINK);
			super.setDisplay("S");
			if(info.getFront() != MashUpPlayer.Neighbor.EMPTY) {
				return MashUpPlayer.Action.LEFT;
			}if(mover == 2) {
				mover = -1;
			}
			mover++;
			return sMovement[mover];
		}
		else if(!master && !servant) {
			super.setColor(Color.BLUE);
			super.setDisplay("X");
			
			if(info.getFront() == MashUpPlayer.Neighbor.OTHER) {
				return MashUpPlayer.Action.FIGHT;
			}
			if(getNeighbors(info) >=2) {
				servant = true;
			}
			if(info.getFront() != MashUpPlayer.Neighbor.EMPTY) {
				return MashUpPlayer.Action.RIGHT;
			}
			return MashUpPlayer.Action.MOVE;
		}
		
		return movement[choice];
	}
	
	/**
	 * 
	 * @param info of the character
	 * @return the number of character it has
	 */
	private int getNeighbors(MashUpPlayerInfo info) {
		int count = 0;
		
		MashUpPlayer.Neighbor current = info.getFront();
		if(current == MashUpPlayer.Neighbor.SAME) count++;
		current = info.getLeft();
		if(current == MashUpPlayer.Neighbor.SAME) count++;
		current = info.getBack();
		if(current == MashUpPlayer.Neighbor.SAME) count++;
		current = info.getRight();
		if(current == MashUpPlayer.Neighbor.SAME) count++;
		
		return count;
	}

}
