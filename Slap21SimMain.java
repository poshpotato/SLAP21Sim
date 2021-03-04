import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * Jeb Dudfield
 * 4/03/2021
 */
public class Slap21SimMain
{
    /**
     * This is the overarching main class that manages settings and starting simulations.
     * This is responsible for:
     * Rendering the main menu
     * Rendering the settings menu
     * Changing settings
     * Storing and retrieving settings
     * Navigation between menus
     * Buildup and teardown of program
     */

    //Slap21SimMain must store:
    //The settings as a String, int hashmap.
    static HashMap<String, Integer> settings = new HashMap<String, Integer>();
    /*The settings stored,their default values, and their bounds are:
     *Simulation x size: 100 : Greater than 0
     *Simulation y size: 100 : Greater than 0
     *Number of people: 30 : Greater than 0
     *Number of initial infected: 1 : Greater than 0
     *Chance of transmission: 100 : Greater or equal than 0 and less than or equal to 100
     *Infectious time: 10 : Greater than 0
     *Recovery time: 10 : Greater than or equal to 0
     *Simulation duration: Twice the x axis, if size is default then 200. : Greater than 0
     */
    //A scanner for reading/writing to settings.txt
    Scanner readWrite;
    //A empty Simulation object to fill with the currently running simulation
    Simulation currentSim;

    public Slap21SimMain()
    {

    }

    public int sampleMethod(int y)
    {
        // put your code here
        return y;
    }
}
