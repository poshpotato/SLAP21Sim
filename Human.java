import java.util.Random;
import java.util.ArrayList;
/**
 *
 * Jeb Dudfield
 * 25/03/2021
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
    
    //It must have a reference to its parent simulation. 
    //Used to initialize with default settings for testing pruposes until this caused recursion. 
    //Used to be non-static until it was changed to save memory. 
    static Simulation parentSim;
    
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
    
    public Human(){
        Random rand = new Random();
        this.x = rand.nextInt(Human.parentSim.settings[0]);
        this.y = rand.nextInt(Human.parentSim.settings[1]);
        this.direction = rand.nextInt(4);
        //printDebugStats();
    }
    
    //Random infected generation with parameters.
    public Human(int infectionCount){
        Random rand = new Random();
        this.x = rand.nextInt(Human.parentSim.settings[0]);
        this.y = rand.nextInt(Human.parentSim.settings[1]);
        this.direction = rand.nextInt(4);
        this.infectionCount = infectionCount;
        //printDebugStats();
    }
    
    //This method adjusts the humans position.
    public void move(){
        //This will run every round.
        //Check if by wall and change direction, check direction and adjust x and y values.
        
        //For debug purposes.
        //System.out.println("Next human");
        
        //direction is changed by a switch station which takes the direction and checks whether they're currently next to a wall If they turn, they check again whether they are facing a wall. if so, they do not move.
        switch(direction){
            case 0:
                //north.
                if(y==0){
                    direction = (direction+2)%4;
                    if(y == Human.parentSim.settings[1]-1)return;
                }
                break;
            case 1:
                //east.
                if(x == Human.parentSim.settings[0]-1){
                    direction = (direction+2)%4;
                    if(x==0)return;
                }
                break;
            case 2:
                //south.
                if(y == Human.parentSim.settings[1]-1){
                    direction = (direction+2)%4;
                    if(y==0)return;
                }
                break;
            case 3:
                //west.
                if(x==0){
                    direction = (direction+2)%4;
                    if(x == Human.parentSim.settings[0]-1)return;
                }
                break;
            default:
                //This is in case of an invalid direction. The person will not change direction no matter what.
                System.out.println("Invalid Direction");
                break;
        }
        
        
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
        
        //For debug purposes. TODO: Disable in final product
        /*if(x < 0 || y < 0 || x >= Human.parentSim.settings[0]  || y >= Human.parentSim.settings[1]){
            System.out.println("Invalid position:");
            printDebugStats();
        }*/
    }
    
    //This method updates the human's timers.
    public void updateTimers(){
        if(infectionCount > 0){
            //increment timer
            infectionCount--;
            if(infectionCount == 0){
                recoveryCount = Human.parentSim.settings[6];
            }
        }else if(recoveryCount > 0){
            recoveryCount--;
        }
    }
    
    //This method will mark the space the human that calls this is on as infected by returning their coords as an int[]
    public int[] infectSpace(){
        return new int[]{x,y};
    }
    
    
    //this method takes an ArrayList<int[]> of infected spaces and returns nothing.
    //It used to take an int[][] but as the amount of infected spaces was an unknown this was changed.
    //It is responsible for infecting any human who calls this and is on an infected space.
    public void checkInfection(ArrayList<int[]> infectedSpaces){
        for(int i=0; i<infectedSpaces.size(); i++){
            
            //Debug purposes
            //System.out.println("Checking infected space " + i + " on co-ordinates " + infectedSpaces.get(i)[0] +"," + infectedSpaces.get(i)[1]);
            if(infectedSpaces.get(i)[0] == x && infectedSpaces.get(i)[1] == y){
                //if The space the Human is on is infected, check infection rate.
                Random rand = new Random();
                if(rand.nextInt(100) < Human.parentSim.settings[4]){
                    this.infectionCount = Human.parentSim.settings[5];
                }
                return;
            }
        }
    }
    
    //this method takes nothing and returns an boolean[] of length 4 representing the human's status.
    /*
     * 0: infected: bool, true/false
     * 1: recovering: bool, true/false
     * note: both of these being false indicates the Human is normal, so no "normal" element is needed.
     * 2: becameInfected: whether the Human became infected this turn.
     * 3: becameRecovered: whether the Human recovered this turn.
     */
    public boolean[] reportStatus(){
        boolean[] status = new boolean[]{(infectionCount > 0),(recoveryCount > 0),(infectionCount == Human.parentSim.settings[5]),(recoveryCount == Human.parentSim.settings[6])};
        return status;
    }
    
    
    
    //A function to print statistics for a human. for testing purposes. TODO: Disable in final product
    /*public void printDebugStats(){
        System.out.println("x: " + x);
        System.out.println("y: " + y);
        System.out.println("direction: " + direction);
        System.out.println("recoveryCount: " + recoveryCount);
        System.out.println("infectionCount: " + infectionCount);
    }*/
}
