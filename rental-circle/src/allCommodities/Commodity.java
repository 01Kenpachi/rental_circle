package allCommodities;

public abstract class Commodity implements Pricing {
    protected final String id;
    protected final String name;
    protected final double actualPrice;
    protected final double rentPerDay;
    protected final double rentalPricePerDay;
    protected final CommodityType type;
    
    public static final String DIS100 = "DIS100";
    
    public Commodity(String id, String name, double actualPrice, double rentPerDay, CommodityType type) {
        this.id = id;
        this.name = name;
        this.actualPrice = actualPrice;
        this.rentPerDay = rentPerDay;
        this.type = type;
        this.rentalPricePerDay = rentPerDay * 1.20; // 20% profit
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getActualPrice() { return actualPrice; }
    public double getRentPerDay() { return rentPerDay; }
    public double getRentalPricePerDay() { return rentalPricePerDay; }
    public CommodityType getType() { return type; }
    
    public double calculateTotalRent(int days) {
        return days * rentalPricePerDay;
    }
    
    public double calculateTotalRent(int days, String discountCode) {
        double total = days * rentalPricePerDay;
        if (DIS100.equals(discountCode)) {
            return total * 0.90; // 10% discount
        }
        return total;
    }
    
    public double calculateTotalRent(int days, boolean hasDamage) {
        double total = days * rentalPricePerDay;
        if (hasDamage) {
            total += actualPrice; 
        }
        return total;
    }
    
    public String getDetails() {
        return "ID: " + id + ", Name: " + name + ", Type: " + type + 
               ", Actual Price: " + actualPrice + " Taka" + 
               ", Rent/Day: " + rentPerDay + " Taka" +
               ", Rental Price/Day: " + rentalPricePerDay + " Taka";
    }
    
    public abstract double getCategoryCost();
}
