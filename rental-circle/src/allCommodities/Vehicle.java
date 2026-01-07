package allCommodities;

public class Vehicle extends Commodity {
    private final String fuelType;

    public Vehicle(String id, String name, double actualPrice, double rentPerDay, String fuelType) {
        super(id, name, actualPrice, rentPerDay, CommodityType.VEHICLE);
        this.fuelType = fuelType;
    }

    public double getCategoryCost() {
        return 1000; // Transportation cost
    }
    
    public double calculateTotalRent(int days) {
        return super.calculateTotalRent(days) + getCategoryCost();
    }
    
    public double calculateTotalRent(int days, String discountCode) {
        return super.calculateTotalRent(days, discountCode) + getCategoryCost();
    }
    
    public double calculateTotalRent(int days, boolean hasDamage) {
        return super.calculateTotalRent(days, hasDamage) + getCategoryCost();
    }

    public String getDetails() {
        return super.getDetails() + ", Fuel Type: " + fuelType;
    }
}
