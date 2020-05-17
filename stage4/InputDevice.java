public abstract class InputDevice {
    public InputDevice (SkyController controller) {
       button=State.TAKE_OFF;
       this.controller=controller;
    }
    public abstract float getForwardPos();
    public abstract float getSidewaysPos();
    public abstract float getVerticalPos();
    public abstract float getRotationPos();
   
    /* TakeOff/Land Button */
    public void pushTakeOff_Land() {
        switch (button) {
            case TAKE_OFF:
                controller.drone.takeOff();
                break;
            case LANDING:
                controller.drone.land();
                break;
        }
        button = button==State.TAKE_OFF ? State.LANDING : State.TAKE_OFF;
    }
    public boolean isWaitingToTakeOff () {
        return button == State.TAKE_OFF;
    }
    private State button;
    private SkyController controller;
}