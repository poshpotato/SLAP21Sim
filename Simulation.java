import java.util.HashMap;
import java.util.ArrayList;
/**
 * 
 * Jeb Dudfield
 * 15/03/2021
 */
public class Simulation
{
    /**
     * This is the class for Simulation objects, which represent the virtual environmentof the simulation.
     * This is responsible for:
     * Reading current simulation settings
     * Simulating an are
     * Storing infected spaces
     * Storing human objects
     * Controlling the simulation
     * Directing each human
     * Storing statistics
     * Printing statistics
     */
    
    //Simulations must store:
    //Settings, passed to it by Slap21SimMain and with the same content. will be initialized in constructor.
    int[] settings;
    //For reference:
    /*The settings stored,their default values, and their bounds are:
     *0: Simulation x size: 100 : Greater than 0
     *1: Simulation y size: 100 : Greater than 0
     *2: Number of people: 30 : Greater than 0
     *3: Number of initial infected: 1 : Greater than 0
     *4: Chance of transmission: 100 : Greater or equal than 0 and less than or equal to 100
     *5: Infectious time: 10 : Greater than 0
     *6: Recovery time: 10 : Greater than or equal to 0
     *7: Simulation duration: Twice the x axis, if size is default then 200. : Greater than 0
     */
    
    //An Array of Humans. This will be initialised with whatever is set for human count.
    Human[] humanArray;
    
    //A count of the total infected humans
    int totalInfected;
    
    //A two dimensional int array to store infected spaces. This will be a temporary variable inside the round method(s).
    
    //A per-round count of infections, recoveries, current infected, current immune, current uninfected. This will be a temporary variable inside the round method(s).
    public Simulation(int[] settings){
        //First! Make sure settings are correct.
        this.settings = settings;
        //Secondly! Make sure the static reference for humans to this class is correct.
        Human.parentSim = this;
        //Thirdly! Initialize humanArray
        humanArray = initHumans();
    }
    
    //This method takes nothing and processes a round using the Simulations current humanArray
    public void runRound()
    {
        /* Order of operations for a round:
         * 1: Movement
         * 1.1: Timer incrementing (This is technically step 2 but it happens in the same loop as step 1, so it is included.)
         * 1.2: Infection marking (See above.)
         * 2: Infection processing
         * 3: Print statistics
         * This is done with several passes through the humanArray.
         */
        
        //it must also keep temporary variables
        ArrayList<int[]> infectedSpaces = new ArrayList<int[]>();
        //TODO: implement this properly.
        
        //Movement+Timers
        for(int i=0;i<humanArray.length;i++){
            //movement
            humanArray[i].move();
            //timer incrementing.
            humanArray[i].updateTimers();
            //Infection Marking
            if(humanArray[i].infectionCount > 0){infectedSpaces.add(humanArray[i].infectSpace());}
            
            //Was used to debug.
            System.out.println(i);
        }
        //for debug purposes.
        //System.out.println(infectedSpaces.get(0)[0] + "" + infectedSpaces.get(0)[1]);
        
        //Infection processing- this happens after infection marking to avoid errors from processing order.
        for(int i=0; i<humanArray.length;i++){
            if(humanArray[i].infectionCount == 0 && humanArray[i].recoveryCount == 0){
                humanArray[i].checkInfection(infectedSpaces);
            }
        }
    }
    
    //This method will eventually take several integers representing the statistics for a round, and use those statistics to output a round summary.
    public void printStats(){
    
    }
    
    //This method returns a humanArray based upon the simulations settings.
    public Human[] initHumans(){
        //First, make sure the static reference for humans to this class is correct.
        Human.parentSim = this;
        //use a temporary array to store humans before returning.
        Human[] tempHumanArray = new Human[settings[2]];
        //creates all uninfected humans
        for(int i=0;i<settings[2]-settings[3];i++){
            tempHumanArray[i] = new Human();
        }
        //create infected humans
        for(int i=0;i<settings[3]&&i<settings[2];i++){
            tempHumanArray[(settings[2]-1)-i] = new Human(settings[5]);
        }
        //for debug purposes.
        //if(tempHumanArray.length == settings[2])System.out.println("Test passed");
        //this.humanArray = tempHumanArray;
        return tempHumanArray;
    }
}
