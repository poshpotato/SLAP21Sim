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
    int infectCount= 0;
    int recoverCount = 0;

    //Their x and y positions relative to top-left.
    int x = 0;
    int y = 0;

    //The direction they are facing. Starting with 0=up and proceeding clockwise. 0=up,1=right,2=down,3=left. 
    //This used to be a byte to save memory, but problems with random generation forced a change to int.
    int direction = 0;

    //A human, when created, needs x, y, direction, and infection count set at minimum.
    
    //If all parameters specified. Generally for use in testing to ensure collisions.
    public Human(int x, int y, byte direction, int infectCount){
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.infectCount = infectCount;
        //printDebugStats();
    }
    
    //Random uninfected generation with parameters.
    
    public Human(int maxX, int maxY){
        Random rand = new Random();
        this.x = rand.nextInt(maxX);
        this.y = rand.nextInt(maxY);
        this.direction = rand.nextInt(4);
        printDebugStats();
    }
    
    //Random infected generation with parameters.
    
    public Human(int maxX, int maxY, int infectCount){
        Random rand = new Random();
        this.x = rand.nextInt(maxX);
        this.y = rand.nextInt(maxY);
        this.direction = rand.nextInt(4);
        this.infectCount = infectCount;
        printDebugStats();
    }

    public int sampleMethod(int y)
    {
        // put your code here
        return y;
    }
    
    //A function to print statistics for a human. for testing purposes.
    public void printDebugStats(){
        System.out.println("x: " + x);
        System.out.println("y: " + y);
        System.out.println("direction: " + direction);
        System.out.println("infectCount: " + infectCount);
    }
}
