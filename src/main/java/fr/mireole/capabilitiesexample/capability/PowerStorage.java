package fr.mireole.capabilitiesexample.capability;

public class PowerStorage implements IPowerCapability {

    private int power = 0;

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public void setPower(int value) {
        this.power = clamp(value);
    }

    private int clamp(int value) {
        if(value > 1000) return 1000;
        if(value < 0) return 0;
        return value;
    }
}
