import java.util.Scanner;

public class Operator {
    public Operator (Scanner in, Joystick l_Joy, Joystick r_Joy){
        inFile = in;
        l_Joystick = l_Joy;
        r_Joystick = r_Joy;
        inFile.nextLine(); // skip description line
        t = inFile.nextFloat();
    }
    public boolean takeAction(float time){
        float f;
        if (time > t) {
            //f = inFile.nextFloat();
            l_Joystick.setHorPos(inFile.nextFloat());
            l_Joystick.setVerPos(inFile.nextFloat());
            r_Joystick.setHorPos(inFile.nextFloat());
            r_Joystick.setVerPos(inFile.nextFloat());
            if(inFile.hasNext)
        }
        return true;
    }
    private float t;
    private Scanner inFile;
    private Joystick l_Joystick, r_Joystick;
}
