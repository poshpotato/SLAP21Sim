import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;
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
    
    //A count of the total infected humans. Global because it must be stored between rounds.
    int totalInfected;
    
    //A two dimensional int array to store infected spaces. This will be a temporary variable inside the round method(s).
    
    //A per-round count of infections, recoveries, current infected, current immune, current uninfected. This will be a temporary variable inside the round method(s).
    public Simulation(int[] settings){
        //First! Make sure settings are correct.
        this.settings = settings;
        //Secondly! Make sure the static reference for humans to this class is correct.
        Human.parentSim = this;
        //Thirdly! Initialize humanArray. 
        humanArray = initHumans();
        int totalInfected = 0;
        printStats();
    }
    
    //This method takes nothing and runs the simulation.
    public void runSimulation(){
        
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
        
        //Movement+Timers
        for(int i=0;i<humanArray.length;i++){
            //movement
            humanArray[i].move();
            //timer incrementing.
            humanArray[i].updateTimers();
            //Infection Marking
            if(humanArray[i].infectionCount > 0){
                if(!infectedSpaces.contains(humanArray[i].infectSpace())){
                infectedSpaces.add(humanArray[i].infectSpace());
            }
            }
            
            //Was used to debug.
            //System.out.println(i);
        }
        //for debug purposes.
        //System.out.println(infectedSpaces.get(0)[0] + "" + infectedSpaces.get(0)[1]);
        
        //Infection processing- this happens after infection marking to avoid errors from processing order.
        for(int i=0; i<humanArray.length;i++){
            if(humanArray[i].infectionCount == 0 && humanArray[i].recoveryCount == 0){
                humanArray[i].checkInfection(infectedSpaces);
            }
        }
        //For infection chance debug purposes
        /* int infected = 0;
         * for(int i=0; i<humanArray.length;i++){
         *     if(humanArray[i].infectionCount > 0){
         *         infected++;
         *      }
         *  }
         *  System.out.println(infected);
         */
        
        this.printStats();
    }
    
    //This method will eventually take several integers representing the statistics for a round, and use those statistics to output a round summary.
    //It also modifies the stats as it reads them, so it should only be run once per round.
    private void printStats(){
        //Must find and print:
        //Infections this round
        int roundInfections = 0;
        //Recoveries this round
        int roundRecoveries = 0;
        //Current uninfected
        //Note: This is later derived from the numbers of infected and recovering, to save time and effort.
        int currentUninfected = 0;
        //Current infected
        int currentInfected = 0;
        //Current recovered
        int currentRecovered = 0;
        
        for(int i = 0; i<humanArray.length;i++){
            //For every human, check their status and change stats accordingly.
            boolean[] humanStatArray = humanArray[i].reportStatus();
            //report status returns a boolean[] of length 4
            /*
             * 0: infected: bool, true/false
             * 1: recovering: bool, true/false
             * note: both of these being false indicates the Human is normal, so no "normal" element is needed.
             * 2: becameInfected: whether the Human became infected this turn.
             * 3: becameRecovered: whether the Human recovered this turn.
             */
            //note: if both infected and recovering are true, the human will only be reported as infected as being recovering will not affect the simulation.
            //This is because infected humans are immune to being infected, and when the infection runs out recovery is reset to its max value anyway.
            if(humanStatArray[0]){
                //if infected
                currentInfected++;
            }else if(humanStatArray[1]){
                //if recovering
                currentRecovered++;
            } else{
                //if neither infected or recovering, you must be uninfected.
                currentUninfected++;
            }
            
            //Similarly, if a human became infected and recovered on the same turn only the infection is reported. Due to timers being processed before infection this should not occur.
            if(humanStatArray[2]){
                //if the human was infected this round
                roundInfections++;
                totalInfected++;
            } else if(humanStatArray[3]){
                //if the human recovered this round
                roundRecoveries++;
            }
        }
        
        //debug purposes
        /*
        if(currentUninfected + currentInfected + currentRecovered != settings[2]){
            System.out.println("Incorrect status totals");
        }*/
        
        
        
        System.out.println("Round Stats");
        System.out.println("Infections: " + roundInfections);
        System.out.println("Recoveries: " + roundRecoveries);
        System.out.println("Uninfected: " + currentUninfected);
        System.out.println("Infected: " + currentInfected);
        System.out.println("Recovered: " + currentRecovered);
        System.out.println("Overall Stats");
        System.out.println("Total Infected: " + totalInfected);
    }
    
    //This method returns a human[] based upon the simulations settings.
    public Human[] initHumans(){
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
