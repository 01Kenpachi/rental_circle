package allCommodities;

public class Furniture extends Commodity {
    private final String material;

    public Furniture(String id, String name, double actualPrice, double rentPerDay, String material) {
        super(id, name, actualPrice, rentPerDay, CommodityType.FURNITURE);
        this.material = material;
    }

    public double getCategoryCost() {
        return 200; // Transportation cost
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
        return super.getDetails() + ", Material: " + material;
    }
}