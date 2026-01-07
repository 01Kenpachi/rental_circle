package allCommodities;

public class Electronics extends Commodity {
    private final String brand;

    public Electronics(String id, String name, double actualPrice, double rentPerDay, String brand) {
        super(id, name, actualPrice, rentPerDay, CommodityType.ELECTRONICS);
        this.brand = brand;
    }

    public double getCategoryCost() {
        return 0; // No transportation cost
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
        return super.getDetails() + ", Brand: " + brand;
    }
}
