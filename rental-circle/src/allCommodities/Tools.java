package allCommodities;

public class Tools extends Commodity {
    private final String toolType;

    public Tools(String id, String name, double actualPrice, double rentPerDay, String toolType) {
        super(id, name, actualPrice, rentPerDay, CommodityType.TOOLS);
        this.toolType = toolType;
    }

    public double getCategoryCost() {
        return 100; // Battery cost
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
        return super.getDetails() + ", Tool Type: " + toolType;
    }
}