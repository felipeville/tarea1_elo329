import java.io.*;
import java.util.Scanner;
import java.util.Locale;
import java.util.ArrayList;
import java.lang.Math;

public class Stage4Test  {
    static {
       Locale.setDefault(Locale.US);
       t0=System.currentTimeMillis();  // time in ms since app 1970.
    }
    public static long t0; 
    public static void main (String[] arg) throws IOException {
        Scanner in = new Scanner(new File(arg[0]));
        float time, nextPrintTime;
        time=nextPrintTime = getCurrentTime();
        int Ndrones = 2; //número de drones
        Drone[] drone = new Drone[Ndrones];
        for(int i = 0; i < Ndrones; i++){
            drone[i] = new Drone(4*i,0,0,i+1);} //definir sus posiciones iniciales y su Id aleatoria
        SkyController skyController1 = new SkyController(drone[0]);
        SkyController skyController2 = new SkyController(drone[1]);
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
        actionables.add(drone[0]);
        actionables.add(drone[1]);
        drone[0].NewFile();
        drone[1].NewFile();

        /* Start both Drones, manual is started with SPC key*/
        System.out.printf("\t \t Drone 1 \t\t Drone 2 (manual)");
        joysticks.pushTakeOff_Land();
        do {
            for( Actionable device: actionables)
                device.takeAction(time);
            if (time >= nextPrintTime){
                System.out.printf("\n%.1f,\t"+drone[0]+"\t\t"+drone[1]+"; move: ",time);
                nextPrintTime+=0.5;
            }
            sleepFor(0.1f);  // let 0.1 [s] pass to run at real time.
            time=getCurrentTime();
        } while (drone[0].getState()!=State.LANDED || drone[1].getState()!=State.LANDED);

        System.out.printf("\n%.1f,\t"+drone[0]+"\t"+drone[1]+"; move: ",time);
        drone[0].CloseFile();
        drone[1].CloseFile();
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
