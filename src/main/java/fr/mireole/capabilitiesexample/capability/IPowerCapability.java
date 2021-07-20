package fr.mireole.capabilitiesexample.capability;

public interface IPowerCapability {

    /**
     * Returns a number between 0(minimal power) and 1000(maximal power)
     *
     * @return the power stored
     **/
    int getPower();

    /**
     * Allow to define the power stored
     * @param value the new amount of power
     */
    void setPower(int value);

    /**
     * Reduce the power stored from a given amount
     * @param value the power subtracted from the actual power
     */
    default void reducePower(int value){
        this.setPower(this.getPower() - value);
    }

    /**
     * Increase the power stored from a given amount
     * @param value the power added from the actual power
     */
    default void increasePower(int value){
        this.setPower(this.getPower() + value);
    }

}
