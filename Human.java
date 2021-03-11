import java.util.Random;
/**
 *
 * Jeb Dudfield
 * 11/03/2021
 */
public class Human
{
    /**
     * This is the class for the human object, which represents each human and their state in the simulation.
     * Their responsibilities are to:
     * Store own statistics
     * Upon simulation call:
     *     Move
     *     Increment status timers
     *     Mark infected spaces
     *     Process state.
     *     Report infections/recoveries.
     */

    //Human must store:
    //The time left for them to infect or recover. 
    int infectionCount= 0;
    int recoveryCount = 0;

    //Their x and y positions relative to top-left.
    int x = 0;
    int y = 0;

    //The direction they are facing. Starting with 0=up and proceeding clockwise. 0=up,1=right,2=down,3=left. 
    //This used to be a byte to save memory, but problems with random generation forced a change to int.
    int direction = 0;
    
    //It must have a reference to its parent simulation. Initializes with default settings for testing pruposes.
    Simulation parentSim = new Simulation(new int[]{100,100,30,1,100,10,10,200});
    
    //A human, when created, needs x, y, direction, and infection count set at minimum.
    
    //If all parameters specified. For use in testing.
    public Human(int[] testSettings, int x, int y, int direction, int infectionCount){
        this.x = x;
        this.y = y;
        this.direction = direction % 4;
        this.infectionCount = infectionCount;
        //printDebugStats();
    }
    
    //Random uninfected generation with parameters.
    
    public Human(Simulation parentSim){
        Random rand = new Random();
        this.x = rand.nextInt(parentSim.settings[0]);
        this.y = rand.nextInt(parentSim.settings[1]);
        this.direction = rand.nextInt(4);
        //printDebugStats();
    }
    
    //Random infected generation with parameters.
    public Human(Simulation parentSim,int infectionCount){
        Random rand = new Random();
        this.x = rand.nextInt(parentSim.settings[0]);
        this.y = rand.nextInt(parentSim.settings[1]);
        this.direction = rand.nextInt(4);
        this.infectionCount = infectionCount;
        //printDebugStats();
    }
    
    //This method adjusts the humans position.
    public void move(int maxX, int maxY){
        //This will run every round.
        //Check if by wall and change direction, check direction and adjust x and y values.
        if(x == parentSim.settings[0]-1 || y == parentSim.settings[1]-1 || x == 0 || y == 0)direction = (direction+2)%4;
        switch(direction){
            case 0:
                //north.
                y -= 1;
                break;
            case 1:
                //east.
                x += 1;
                break;
            case 2:
                //south.
                y += 1;
                break;
            case 3:
                //west.
                x -= 1;
                break;
            default:
                //This is in case of an invalid direction. The person will not move.
                System.out.println("Invalid direction: " + direction + ".");
                break;
        }
    }
    
    //This method updates the human's timers.
    public void updateTimers(){
        if(infectionCount > 0){
            //increment timer
            infectionCount--;
            if(infectionCount == 0){
                recoveryCount = parentSim.settings[6];
            }
        }else if(recoveryCount > 0){
            recoveryCount--;
        }
    }
    
    //this method takes nothing and returns an boolean[] representing the human's status.
    /*
     * 0: infected: bool, true/false
     * 1: recovering: bool, true/false
     * note: both of these being false indicates the Human is normal, so no "normal" element is needed.
     * 2: becameInfected: whether the Human became infected this turn.
     * 3: becameRecovered: whether the Human recovered this turn.
     */
    public boolean[] reportStatus(){
        boolean[] status = new boolean[]{(infectionCount > 0),(recoveryCount > 0),(infectionCount == parentSim.settings[5]),(recoveryCount == parentSim.settings[6])};
        return status;
    }
    
    
    
    //A function to print statistics for a human. for testing purposes.
    /*public void printDebugStats(){
        System.out.println("x: " + x);
        System.out.println("y: " + y);
        System.out.println("direction: " + direction);
        System.out.println("recoveryCount: " + recoveryCount);
        System.out.println("infectionCount: " + infectionCount);
    }*/
}
