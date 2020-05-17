import java.io.*;
import java.util.Scanner;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

public class Stage3Test  {
    static {
       Locale.setDefault(Locale.US);
       t0=System.currentTimeMillis();  // time in ms since app 1970.
    }
    public static long t0; 
    public static void main (String[] arg) throws IOException {
        Scanner in = new Scanner(new File(arg[0]));
        Random random = new Random();
        int [] Ids = new int[2];
        Ids[0] = 1+Math.abs(random.nextInt()%1000);
        Ids[1] = 1+Math.abs(random.nextInt()%1000); //Ids = número random del 1 al 1000
        float time, nextPrintTime;
            time=nextPrintTime = getCurrentTime();
        Drone drone1 = new Drone(0,0,0,Ids[0]); //definir sus posiciones iniciales y su Id aleatoria
        Drone drone2 = new Drone(4,0,0,Ids[1]);
        SkyController skyController1 = new SkyController(drone1);
        SkyController skyController2 = new SkyController(drone2);
        Joysticks joysticks = new Joysticks(skyController1);
        Keyboard keyboard = new Keyboard(skyController2);
        skyController1.setInputDevice(joysticks);
        skyController2.setInputDevice(keyboard);
        Operator operator = new Operator(in, joysticks);


        ArrayList<Actionable> actionables = new ArrayList<Actionable>();
        actionables.add(operator);
        actionables.add(keyboard);
        actionables.add(skyController1);
        actionables.add(skyController2);
        actionables.add(drone1);
        actionables.add(drone2);
        drone1.NewFile();
        drone2.NewFile();

        /* Start both Drones, manual is started with SPC key*/
        System.out.printf("Id del Drone 1: %d\n", Ids[0]);
        System.out.printf("Id del Drone 2: %d\n", Ids[1]);
        System.out.printf("\t \t Drone 1 \t\t Drone 2 (manual)");
        joysticks.pushTakeOff_Land();
        do {
            for( Actionable device: actionables)
                device.takeAction(time);
            if (time >= nextPrintTime){
                System.out.printf("\n%.1f,\t"+drone1+"\t\t"+drone2+"; move: ",time);
                System.out.printf(String.valueOf(drone1.getState()!=State.LANDED));
                nextPrintTime+=0.5;
            }
            sleepFor(0.1f);  // let 0.1 [s] pass to run at real time.
            time=getCurrentTime();
        } while (drone1.getState()!=State.LANDED || drone2.getState()!=State.LANDED);

        System.out.printf("\n%.1f,\t"+drone1+"\t"+drone2+"; move: ",time);
        drone1.CloseFile();
        drone2.CloseFile();
    }
    public static float getCurrentTime(){  // time since program started in [s]
        return (float)(System.currentTimeMillis()-t0)/1000.0f;
    }
    public static void sleepFor(float time) { // to let user react
        try {
            Thread.sleep((int)(time*1000));
        } catch (InterruptedException e){
        }
    }
}
