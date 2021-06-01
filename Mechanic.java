/** This is the class for the Mechanic.
 * @author caleb
 * @version 1.0
 */
public class Mechanic {
    private final String name;
    private double revenue;
    private double oilChangePrice;
    private double newTirePrice;

    /**Getter for Mechanic Name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /** Getter for Revenue.
     *
     * @return revenue
     */
    public double getRevenue() {
        return revenue;
    }

    /** Setter for Revenue.
     *
     * @param revenue The revenue of the mechanic.
     */
    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    /** Getter for the Change in Oil Price.
     *
     * @return oilChangePrice
     */
    public double getOilChangePrice() {
        return oilChangePrice;
    }

    /** Setter for the Change in Oil Price.
     *
     * @param oilChangePrice The change in oil price.
     */
    public void setOilChangePrice(double oilChangePrice) {
        this.oilChangePrice = oilChangePrice;
    }

    /** Getter for new tire price.
     *
     * @return newTirePrice
     */
    public double getNewTirePrice() {
        return newTirePrice;
    }

    /** Setter for new tire price.
     *
     * @param newTirePrice Price of the new tire.
     */
    public void setNewTirePrice(double newTirePrice) {
        this.newTirePrice = newTirePrice;
    }

    /** Constructor with all Parameters for Mechanic Class.
     *
     * @param name name of Mechanic.
     * @param revenue revenue of Mechanic.
     * @param oilChangePrice The Change in Oil Pricing.
     * @param newTirePrice The new tire price.
     */
    public Mechanic(String name, double revenue, double oilChangePrice, double newTirePrice) {
        this.name = name;
        if (revenue >= 0.0) {
            this.revenue = revenue;
        } else {
            this.revenue = 0.0;
        }
        if (oilChangePrice > .99) {
            this.oilChangePrice = oilChangePrice;
        } else {
            this.oilChangePrice = 44.99;
        }
        if (newTirePrice > .99) {
            this.newTirePrice = oilChangePrice;
        } else {
            this.newTirePrice = 59.99;
        }
    }

    /** Constructor with 3 parameter for Mechanic Class.
     *
     * @param name name of Mechanic.
     * @param oilChangePrice The Change in Oil Pricing.
     * @param newTirePrice The new tire price.
     */
    public Mechanic(String name, double oilChangePrice, double newTirePrice) {
        this(name, 0.0, oilChangePrice, newTirePrice);
    }

    /** Constructor with 1 for Mechanic Class.
     *
     * @param name name of Mechanic.
     */
    public Mechanic(String name) {
        this(name, 0.0, 44.99, 59.99);
    }
    @Override
    public String toString() {
        return String.format("The is the mechanic named %s. I charge $%.2f for an oil change, and I charge"
                + " %.2f for a new tire. I've made $%.2f in revenue", name, oilChangePrice, newTirePrice, revenue);
    }

    /** This method determines if a Car needs an oil change or new tires.
     *
     * @param c Car object used in service method.
     */
    public void service(Car c) {
        if (c.getMileage() >= c.getNextOilChange()) {
            c.setNextOilChange(c.getMileage() + 3000);
            revenue += oilChangePrice;
        }
        for (int i = 0; i < c.getTireLife().length; i++) {
            double[] currentTire = c.getTireLife();
            if (currentTire[i] <= 0.50) {
                revenue += newTirePrice;
                currentTire[i] = 1.0;
                c.setTireLife(currentTire);
            }
        }
    }
}

