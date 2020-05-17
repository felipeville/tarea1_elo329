import java.util.Scanner;
import java.io.InputStream;
import java.io.IOException;
/*
 * Use WASD for Left Stick movement and IJKL for the Right Stick movement
 */
public class Keyboard extends InputDevice implements Actionable {
   public Keyboard(SkyController controller) {
      super(controller);
      rPos=vPos=fPos=sPos=0;
      in = System.in;
   }
   public float getForwardPos() {
      return fPos;
   }
   public float getSidewaysPos(){
      return sPos;
   }
   public float getVerticalPos(){
      return vPos;
   }
   public float getRotationPos(){
      return rPos;
   }

   public void takeAction(float time) {
        int c;
        try {  // reading from "in" could cause an error.
            // We will see instruction try...catch ahead in this course.
            if (in.available()>0) {// there are bytes to read without being blocked
                c=in.read();
                switch (c) {
                    /* Left Stick */
                    case 'w': 
                        vPos+=sensibility; if (vPos > 1) vPos=1; break;
                    case 's':
                        vPos-=sensibility; if(vPos < -1) vPos=-1; break;
                    case 'a':
                        rPos-=sensibility; if(rPos < -1) rPos=-1; break;
                    case 'd':
                        rPos+=sensibility; if(rPos > 1) rPos=1; break;
                    /* Right Stick */    
                    case 'i':
                        fPos+=sensibility; if(fPos > 1) fPos = 1; break;
                    case 'k':
                        fPos-=sensibility; if(fPos < -1) fPos = -1; break;
                    case 'j':
                        sPos-=sensibility; if(sPos < -1) sPos = -1; break;
                    case 'l':
                        sPos+=sensibility; if(sPos > 1) sPos = 1; break;
                    /* TakeOff/Land Button */    
                    case ' ':
                        super.pushTakeOff_Land(); break;
                    default:
                        rPos=fPos=sPos=vPos=0;     
                }
            }
        } catch ( IOException e ) { 
            System.out.println("Input error");
            return;
        }
   }
   private InputStream in;
   private float rPos, vPos, fPos, sPos;
   private static float sensibility=0.2f;
}
