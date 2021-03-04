
/**
 *
 * Jeb Dudfield
 *  4/03/2021
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
    
    //The direction they are facing. Starting with 0=up and proceeding clockwise. 0=up,1=right,2=down,3=left. This is a byte to save memory.
    byte direction = 0;
    public Human(){
        
    }
   
    public int sampleMethod(int y)
    {
        // put your code here
        return y;
    }
}
