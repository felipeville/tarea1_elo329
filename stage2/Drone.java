public class Drone {
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
   }
   static {
      MAX_F_SPEED = MAX_S_SPEED = 5; // [m/s]
      MAX_V_SPEED = 2;    // [m/s]
      MAX_R_SPEED = (float)(0.25*Math.PI/2); // [rad/s]
      TAKEOFF_LANDING_SPEED = 1; // [m/s]
   }
   public void takeAction(float t){
      float delta_t = t-time;
      switch (state) {
      case TAKING_OFF:  //drone moves only upwards in this stage
          // Eleva el dron hasta TAKEOFF_HEIGHT = 1 [m]
          if( h < TAKEOFF_HEIGHT ){
            h += delta_t*TAKEOFF_LANDING_SPEED;
          }
          else{
              this.state = State.FLYING;
          }
          break;
            
      case FLYING:
          h += delta_t*vSpeed;
          // Negative is left and positive right
          direction -= delta_t*rSpeed;
          this.setPosition(direction, fSpeed, sSpeed, delta_t);
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
   }
   public void setRotationSpeed(float percentage) {
       rSpeed = percentage*MAX_R_SPEED;
   }
   public void setFlySpeed(float verPer, float forwPer, float sidePer) {
      vSpeed = verPer*MAX_V_SPEED;
      fSpeed = forwPer*MAX_F_SPEED;
      sSpeed = sidePer*MAX_S_SPEED;
   }
   /* Funcion para cambio de coordenadas de acuerdo a la rotacion */
   /* https://advancedsoftware.wordpress.com/2012/05/29/rotacion-en-r2-dos-dimensiones-2d/ */
   private void setPosition(float theta, float fSpeed, float sSpeed, float dt){
       float x_, y_;
       x_ = sSpeed*dt;
       y_ = fSpeed*dt;
       x += x_*(float)Math.cos(theta) - y_*(float)Math.sin(theta);
       y += x_*(float)Math.sin(theta) + y_*(float)Math.cos(theta);
   }
   public float getHeight() {
      return h;
   }
   public String toString() {
      return x + ", " + y + ", " + h;
   }
   public void takeOff() {
      if (state==State.LANDED)
         state = State.TAKING_OFF;
   }
   public void land() {
      if (state==State.FLYING)
         state = State.LANDING;
   }
   private State state;
   private float time;
   private float fSpeed, vSpeed, sSpeed, rSpeed;
   private float direction; // angle
   private float x,y,h;
   private static float MAX_F_SPEED;
   private static float MAX_V_SPEED;
   private static float MAX_S_SPEED;
   private static float MAX_R_SPEED;
   private static float TAKEOFF_LANDING_SPEED;
   private final static float TAKEOFF_HEIGHT = 1;  // Altura por defecto
}
