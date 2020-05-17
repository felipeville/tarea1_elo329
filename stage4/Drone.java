import java.text.DecimalFormat;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class Drone implements Actionable {
    public Drone () {
        fSpeed = 0;
        vSpeed = 0;
        sSpeed = 0;
        rSpeed = 0;
        x = 0;
        y = 0;
        h = 0;
        direction = (float)Math.PI/2;     // Facing (0,y_positive)
        state = State.LANDED;
        to_write = false;
    }
    public Drone (float x_, float y_, float z_, int id) {
        fSpeed = 0;
        vSpeed = 0;
        sSpeed = 0;
        rSpeed = 0;
        x = x_;
        y = y_;
        h = z_;
        direction = (float)Math.PI/2;     // Facing (0,y_positive)
        state = State.LANDED;
        to_write = false;
        Id = id;
    }

    static {
        MAX_F_SPEED = MAX_S_SPEED = 5; // [m/s]
        MAX_V_SPEED = 2;    // [m/s]
        MAX_R_SPEED = (float)(Math.PI/2); // [rad/s]
        TAKEOFF_LANDING_SPEED = 1; // [m/s]
    }

    public void takeAction(float t){
        float delta_t = t-time;
        switch (state) {
        case TAKING_OFF:  //drone moves only upwards in this stage
            if( h < TAKEOFF_HEIGHT ){
                h += delta_t*TAKEOFF_LANDING_SPEED;
            }
            else{
               this.state = State.FLYING;
            }
            break;
        case FLYING:
            h += delta_t*vSpeed;
            direction -= delta_t*rSpeed;
            // Negative is left and positive right
            this.setPosition(direction, fSpeed, sSpeed, delta_t);
            if( h < 0 ){
                h = 0;
                state = State.LANDED;
            }
            break;

        case LANDING: //drone moves only downwards in this stage
            h -= delta_t*TAKEOFF_LANDING_SPEED;
            if( h < 0 ){
                h = 0;
                state = State.LANDED;
            }
            break;

        }
        time = t;
        if(to_write){
            try {
                DecimalFormat df = new DecimalFormat("##0.00000");
                write_file.write(df.format(x)+','+df.format(y)+'\n');
            }catch (IOException e){}
        }
        
    }
    public void setRotationSpeed(float percentage) {
        rSpeed = percentage*MAX_R_SPEED;
    }
    public void setFlySpeed(float verPer, float forwPer, float sidePer) {
       vSpeed = verPer*MAX_V_SPEED;
       fSpeed = forwPer*MAX_F_SPEED;
       sSpeed = sidePer*MAX_S_SPEED;
    }
    private void setPosition(float theta, float fSpeed, float sSpeed, float dt){
        float x_, y_, phi;
        x_ = sSpeed*dt;
        y_ = fSpeed*dt;
        /* phi es el angulo de rotacion de los ejes */
        phi = theta-(float)Math.PI/2;  // theta = PI/2 corresponde a una rotacion de 0
        x += x_*(float)Math.cos(phi) - y_*(float)Math.sin(phi);
        y += x_*(float)Math.sin(phi) + y_*(float)Math.cos(phi);
    }
    public float getHeight() {
       return h;
    }
   
    public State getState(){
        return state;
    }
    public String toString() {
        /* Print up to 5 decimal places */
        DecimalFormat df = new DecimalFormat("##0.00000");
        return df.format(x) + ", " + df.format(y) + ", " + df.format(h);
    }
    public void takeOff() {
        if (state==State.LANDED)
            state = State.TAKING_OFF;
    }
    public void land() {
        if (state==State.FLYING)
            state = State.LANDING;
    }
    
    public void NewFile() throws IOException {
        new_file = new File("drone"+Integer.toString(Id)+".csv");
        new_file.createNewFile();
        write_file = new FileWriter("drone"+Integer.toString(Id)+".csv");
        to_write = true;  
    }
    public void CloseFile() throws IOException {
        write_file.close();
    }
    
    private boolean to_write;
    private State state;
    private float time;
    private float fSpeed, vSpeed, sSpeed, rSpeed;
    private float direction; // angle
    private float x,y,h;
    private FileWriter write_file;
    private File new_file;
    private static float MAX_F_SPEED;
    private static float MAX_V_SPEED;
    private static float MAX_S_SPEED;
    private static float MAX_R_SPEED;
    private static float TAKEOFF_LANDING_SPEED;
    private final static float TAKEOFF_HEIGHT = 1;
    public int Id=0;

}