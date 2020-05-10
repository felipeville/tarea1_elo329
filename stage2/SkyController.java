public class SkyController {
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
       float verPer = lStick.getVerPos();
       float forwPer = rStick.getVerPos();
       float sidePer = rStick.getHorPos();
       float rotPer = lStick.getHorPos();
       drone.setFlySpeed(verPer, forwPer, sidePer);
       drone.setRotationSpeed(rotPer);
   }
   private Drone drone;
   private Joystick lStick, rStick;
   private State button;
}


