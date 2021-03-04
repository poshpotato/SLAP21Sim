import java.util.Scanner;
/**
 *
 * Jeb Dudfield
 * 4/03/2021
 */
public class Slap21Sim
{
    /**
     * This is the overarching main class that manages settings and starting simulations.
     * This is responsible for:
     * Rendering the main menu
     * Rendering the settings menu
     * Changing settings
     * Storing✓ and retrieving settings
     * Navigation between menus
     * Buildup and teardown of program
     */

    //Slap21SimMain must store:
    //The settings as an int array.
    static int[] settings = new int[]{100,100,30,1,100,10,10,200};
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
    //A scanner for reading/writing to settings.txt
    static Scanner readWrite;
    //A scanner for keyboard input
    static Scanner kb = new Scanner(System.in);
    //A empty Simulation object to fill with the currently running simulation
    static Simulation currentSim;
    
    static void main(){
        System.out.println("Welcome to the SLAP-21 simulation program.");
        System.out.println("Written by Jebadiah Dudfield in 2021.");
        displayMainMenu();
    }
    
    static void displayMainMenu(){
        System.out.println("SLAP-21 Simulation v5/03/2021");
        while(true){
            System.out.println("1: Run Simulation");
            System.out.println("2: View/Change Simulation Settings");
            System.out.println("3: Exit");
            switch(getInput("Please type the number of the option you want to select.")){
                case 1:
                    System.out.println("TODO: Run simulation with current settings here.");
                    break;
                case 2:
                    System.out.println("TODO: Render settings menu");
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Option does not exist.");
            }
        }
    }
    
    static int getInput(String request){
        System.out.println(request);
        try{
            int hours = kb.nextInt();
            return hours;
        } catch(Exception e){
            System.out.println("Error: Non-integer-input.");
            kb.nextLine();
            return getInput(request);
        }
    }
}
