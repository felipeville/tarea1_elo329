import java.util.Scanner;

public class Operator implements Actionable {
    public Operator (Scanner in, Joystick l_Joy, Joystick r_Joy){
        inFile = in;
        l_Joystick = l_Joy;
        r_Joystick = r_Joy;
        inFile.nextLine(); // skip description line
        t = inFile.nextFloat();
    }
    
    public Operator (Scanner in, Joysticks sticks){
        inFile = in;
        this.sticks = sticks;
        l_Joystick = sticks.getLeftStick();
        r_Joystick = sticks.getRightStick();
        inFile.nextLine();
        t = inFile.nextFloat();
    }

    public void takeAction(float time){
        
        if (time > t) {
            /* Check if there's any element left in the file */
            if(inFile.hasNext()){
                l_Joystick.setHorPos(inFile.nextFloat());
                l_Joystick.setVerPos(inFile.nextFloat());
                r_Joystick.setHorPos(inFile.nextFloat());
                r_Joystick.setVerPos(inFile.nextFloat());
                
                if(inFile.hasNext())
                    t = inFile.nextFloat();
            }
            else{
                /* Aterriza el drone una vez terminado el archivo de entrada */
                do{
                    sticks.pushTakeOff_Land();
                } while(false);
            }
            

        }
    }
    private float t;
    private Scanner inFile;
    private Joystick l_Joystick, r_Joystick;
    private Joysticks sticks;
}
