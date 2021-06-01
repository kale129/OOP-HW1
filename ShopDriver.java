/** This is the class for the shop driver.
 * @author Caleb Page
 * @version 1.0
 */
public class ShopDriver {
    /**main method for Shop Driver.
     *
     * @param args main method argument
     */
    public static void main(String[] args) {
        Car c1 = new Car("BMW", 15000, 100, new double[] {0.9, 1.0, 0.5, 0.5});
        Mechanic m1 = new Mechanic("Raul", 50, .98, .85);
        m1.service(c1);

        System.out.println(m1);
        System.out.println(c1);
        // Testing if if illegal parameter goes to default value. Specifically mileage
        Car c2 = new Car("BMW", -1, 100, new double[] {0.9, 1.0, 0.5, 0.5});
        System.out.println(c2);
    }
}
