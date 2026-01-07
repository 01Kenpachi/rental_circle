package allCommodities;

public class OtherCommodity extends Commodity {
    private final String description;

    public OtherCommodity(String id, String name, double actualPrice, double rentPerDay, String description) {
        super(id, name, actualPrice, rentPerDay, CommodityType.OTHERS);
        this.description = description;
    }

    public double getCategoryCost() {
        return 50; // Transportation cost
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
        return super.getDetails() + ", Description: " + description;
    }
}
