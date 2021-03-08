import java.util.HashMap;
/**
 * 
 * Jeb Dudfield
 * 4/03/2021
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
    Human[] HumanArray;
    //A count of the total infected humans
    int totalInfected;
    //A per-round count of infections, recoveries, current infected, current immune, current uninfected. This will be a temporary variable inside the round method(s).
    public Simulation(int[] settings){
        this.settings = settings;
    }
  
    public int sampleMethod(int y)
    {
        // put your code here
        return y;
    }
}
