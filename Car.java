/** This is the Class Car.
 * @author caleb
 * @version 1.0
 */
public class Car {

    private String type;
    private int mileage;
    private int nextOilChange;
    private double[] tireLife = new double[4];
    private final int numCars = 0;

    /** Getter for type of Vehicle.
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**Setter for type of Vehicle.
     *
     * @param type This is the type of the vehicle.
     */
    public void setType(String type) {
        this.type = type;
    }

    /** Getter for the mileage of the vehicle.
     *
     * @return mileage
     */
    public int getMileage() {
        return mileage;
    }

    /**Setter for the mileage of the vehicle.
     *
     * @param mileage This is the mileage of the vehicle.
     */

    public void setMileage(int mileage) {
        if (mileage < 0) {
            this.mileage = 0;
        } else {
            this.mileage = mileage;
        }
    }
    /** Getter for the number of miles until the next Oil Change.
     *
     * @return nextOilChange
     */
    public int getNextOilChange() {
        return nextOilChange;
    }

    /** Setter for the number of miles until the next Oil Change.
     *
     * @param nextOilChange This parameter holds the number of miles until the next oil change.
     */
    public void setNextOilChange(int nextOilChange) {
        if (nextOilChange < 0) {
            this.nextOilChange = mileage + 3000;
        } else {
            this.nextOilChange = nextOilChange;
        }
    }

    /**Getter for the percentage of the Tire's Life remaining.
     *
     * @return tireLife
     */
    public double[] getTireLife() {
        return tireLife;
    }

    /** Setter for the percentage of the Tire's Life remaining.
     *
     * @param tireLife this parameter represents the tire life.
     */
    public void setTireLife(double[] tireLife) {
        for (int i = 0; i < 4; i++) {
            if (tireLife[i] > 1.0 || tireLife[i] < 0.0) {
                tireLife[i] = 1.0;
            }
        }
        this.tireLife = tireLife;
    }

    /** Constructor that takes in all of the parameters of the class Car.
     *
     * @param type This is the type of the vehicle.
     * @param mileage This is the mileage of the vehicle.
     * @param nextOilChange This parameter holds the number of miles until the next oil change.
     * @param tireLife This parameter represents the tire life.
     */
    public Car(String type, int mileage, int nextOilChange, double[] tireLife) {
        this.type = type;
        if (mileage < 0) {
            this.mileage = 0;
        } else {
            this.mileage = mileage;
        }
        if (nextOilChange < 0) {
            this.nextOilChange = mileage + 3000;
        } else {
            this.nextOilChange = nextOilChange;
        }
        for (int i = 0; i < 4; i++) {
            if (tireLife[i] > 1.0 || tireLife[i] < 0.0) {
                tireLife[i] = 1.0;
            }
        }
        this.tireLife = tireLife;
    }

    /** Constructor that takes in three parameters of the class Car.
     *
     * @param type This is the type of the vehicle.
     * @param mileage This is the mileage of the vehicle.
     * @param tireLife This parameter represents the tire life.
     */
    public Car(String type, int mileage, double[] tireLife) {
        this(type, mileage, mileage + 3000, tireLife);
    }

    /** Constructor that takes in two parameters of the class Car.
     *
     * @param type This is the type of the vehicle.
     * @param tireLife This parameter represents the tire life.
     */
    public Car(String type, double[] tireLife) {
        this(type, 0, 3000, tireLife);
    }

    /** Constructor that takes in the an object of class Car.
     *
     * @param car The object of class Car.
     */
    public Car(Car car) {
        this.type = new String(car.type);
        this.mileage = car.mileage;
        this.nextOilChange = car.nextOilChange;
        this.tireLife = car.tireLife;
    }

    /** This Method calculates the average tire life of a vehicle
     *
     * @param input this parameter represents the tire life.
     * @return avg
     */
    private int averageTireLife(double[] input) {
        double total = 0.0;
        for (int i = 0; i < input.length; i++) {
            total += input[i];
        }
        int avg = (int) ((total / 4) * 100);
        return avg;
    }
    @Override
    public String toString() {
        return String.format("This is car of type %s with a mileage of %d miles."
        + " I'm due for a checkup in %d miles, and my average tire "
                +  "life is %d%%", type, mileage, nextOilChange - mileage, averageTireLife(tireLife));
    }

}






