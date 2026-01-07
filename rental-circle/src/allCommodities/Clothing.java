package allCommodities;

public class Clothing extends Commodity {
    private final String color;

    public Clothing(String id, String name, double actualPrice, double rentPerDay, String color) {
        super(id, name, actualPrice, rentPerDay, CommodityType.CLOTHING);
        this.color = color;
    }

    public double getCategoryCost() {
        return 100; // Washing cost
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
        return super.getDetails() + ", Color: " + color;
    }
}