/**
 * This class create a Orc Player Object that moves in steps and turn when it comes into contact
 * with the wall or the same other objects. It also turns black if it is ever surrounded by 2 or more
 * other objects
 * 
 * @author Riberiko Niyomwungere
 */

import java.awt.Color;

public class Orc extends MashUpPlayer{
	
	//This does not need to ever change. Its an array of the moves for orc in order
	private static final MashUpPlayer.Action[] movement = new MashUpPlayer.Action[] {MashUpPlayer.Action.MOVE,
			MashUpPlayer.Action.LEFT, MashUpPlayer.Action.MOVE, MashUpPlayer.Action.RIGHT};
	
	//counter so that the monster can keep track of what happened last
	private short mover;
	//counter so that the monster can keep track of what happened last
	private short turnCounter;
	
	/**
	 * Create an orc player
	 */
	public Orc() {
		super("//", Color.RED);
		//because the first thing that will happen to this number is plus 1
		mover = -1;
		//used for getting the character to turn around
		turnCounter = 0;
	}

	@Override
	public MashUpPlayer.Action getMove(MashUpPlayerInfo info) {
		
		MashUpPlayer.Neighbor front = info.getFront();
		
		if(front == MashUpPlayer.Neighbor.OTHER) {
			return MashUpPlayer.Action.FIGHT;
		}
		
		return move(info);
	}
	
	/**
	 * 
	 * @param info of the character
	 * @return the number of character it has
	 */
	private int getNeighbors(MashUpPlayerInfo info) {
		int count = 0;
		
		MashUpPlayer.Neighbor current = info.getFront();
		if(current == MashUpPlayer.Neighbor.OTHER) count++;
		current = info.getLeft();
		if(current == MashUpPlayer.Neighbor.OTHER) count++;
		current = info.getBack();
		if(current == MashUpPlayer.Neighbor.OTHER) count++;
		current = info.getRight();
		if(current == MashUpPlayer.Neighbor.OTHER) count++;
		
		return count;
	}
	
	/**
	 * Checks how many neighbors it has then it changed the color accordingly
	 * 
	 * @param info of the character
	 */
	private void currentColor(MashUpPlayerInfo info) {
		if(getNeighbors(info) >= 2) {
			super.setColor(Color.BLACK);
		}else {
			super.setColor(Color.RED);
		}
	}
	
	/**
	 * 
	 * @param info of the character
	 * @return the move
	 */
	private MashUpPlayer.Action move(MashUpPlayerInfo info) {
		MashUpPlayer.Neighbor current = info.getFront();
		currentColor(info);
		if(current == MashUpPlayer.Neighbor.WALL || current == MashUpPlayer.Neighbor.SAME) {
			//starts the turning count
			turnCounter = 2;
			//Because first thing that happens to this is it adds 1 and index -1 does not exist
			mover = -1;
		}
		else if(current == MashUpPlayer.Neighbor.OTHER) {
			return MashUpPlayer.Action.FIGHT;
		}
		if(turnCounter != 0) {
			//counts down so it knows when its done
			turnCounter--;
			return MashUpPlayer.Action.LEFT;
		}else {
			mover++;
			if(mover == 4) mover=0;
			return movement[mover];
		}
	}

}
