import java.util.Scanner;

public class Operator {
    public Operator (Scanner in, Joystick l_Joy, Joystick r_Joy){
        inFile = in;
        l_Joystick = l_Joy;
        r_Joystick = r_Joy;
        inFile.nextLine(); // skip description line
        t = inFile.nextFloat();
    }
    
    public Operator (Scanner in, SkyController ctrl){
        inFile = in;
        l_Joystick = ctrl.getLeftStick();
        r_Joystick = ctrl.getRightStick();
        inFile.nextLine();
        t = inFile.nextFloat();
    }
    
    public boolean takeAction(float time){
        
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
            else
                return false;
        }
        return true;
    }
    private float t;
    private Scanner inFile;
    private Joystick l_Joystick, r_Joystick;
}
