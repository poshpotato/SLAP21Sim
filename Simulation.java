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
    //Settings, passed to it by Slap21SimMain and with the same content.
    HashMap<String, Integer> settings = new HashMap<String, Integer>();
    //An Array of Humans. This will be initialised with whatever is set for human count.
    int[] HumanArray;
    //A count of the total infected humans
    int totalInfected;
    //A per-round count of infections, recoveries, current infected, current immune, current uninfected. This will be a temporary variable inside the round method(s).
    public Simulation()
    {
        
    }
  
    public int sampleMethod(int y)
    {
        // put your code here
        return y;
    }
}
