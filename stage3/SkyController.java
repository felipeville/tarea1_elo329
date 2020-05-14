public class SkyController implements Actionable {
    public SkyController (Drone drone) {
        this.drone = drone;
        this.button = State.TAKE_OFF;
        lStick = new Joystick();
        rStick = new Joystick();
    }
    public Joystick getLeftStick(){
        return lStick;
    }
    public Joystick getRightStick(){
        return rStick;
   }
    public void pushTakeOff_Land () {
        if(button == State.TAKE_OFF){
            drone.takeOff();
            button = State.LANDING;
        }
        else if(button == State.LANDING){
            drone.land();
            button = State.TAKE_OFF;
        }
    }

   public void takeAction(float time) {
        float verPer, forwPer, sidePer, rotPer;
        verPer=forwPer=sidePer=rotPer=0;
              
        if(this.input==Input.JOYSTICKS){
            verPer = lStick.getVerPos();
            forwPer = rStick.getVerPos();
            sidePer = rStick.getHorPos();
            rotPer = lStick.getHorPos();
        }
        else if(this.input==Input.KEYBOARD){
            verPer = keyboard.getVerticalPos();
            forwPer = keyboard.getForwardPos();
            sidePer = keyboard.getSidewaysPos();
            rotPer = keyboard.getRotationPos();
        }
        drone.setFlySpeed(verPer, forwPer, sidePer);
        drone.setRotationSpeed(rotPer);
   }
   
    public void setInputDevice(InputDevice input){
        if(input instanceof Joysticks){
            this.input = Input.JOYSTICKS;
            lStick = ((Joysticks) input).getLeftStick();
            rStick = ((Joysticks) input).getRightStick();
        }
        else if(input instanceof Keyboard){
            keyboard = (Keyboard) input;
            this.input = Input.KEYBOARD;
            lStick = new Joystick();
            rStick = new Joystick();
        }
    }
   
    public enum Input {
        JOYSTICKS,
        KEYBOARD,
    }
   
    protected Drone drone;
    private Joystick lStick, rStick;
    private State button;
    private Input input;
    private Keyboard keyboard;
}


