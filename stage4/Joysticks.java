public class Joysticks extends InputDevice {
    public Joysticks (SkyController controller) {
        super(controller);
        rStick = new Joystick();
        lStick = new Joystick();
    }
    public float getForwardPos() {
        return rStick.getVerPos();
    }
    public float getSidewaysPos() {
        return rStick.getHorPos();
    }
    public float getVerticalPos() {
        return lStick.getVerPos();
    }
    public float getRotationPos() {
        return lStick.getHorPos();
    }

    public Joystick getLeftStick(){
        return lStick;
    }

    public Joystick getRightStick(){
        return rStick;
    }

    private Joystick lStick, rStick;
}
