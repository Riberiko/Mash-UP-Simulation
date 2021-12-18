
/**
 * MashUpMain provides the main method for a simple simulation program.  Alter
 * the number of each entity added to the simulation if you want to experiment
 * with different scenarios.  You can also alter the width and height passed to
 * the MashUpFrame constructor.

 * by Stuart Reges and Marty Stepp
 */

public class MashUpMain {
    public static int COUNT = 30;
    public static void main(String[] args) {
    	
        MashUpFrame frame = new MashUpFrame(60, 40);

        frame.add(COUNT, MiniMe.class);
        frame.add(COUNT, Orc.class);
        frame.add(COUNT, Personal.class);

        frame.start();
    }
}
