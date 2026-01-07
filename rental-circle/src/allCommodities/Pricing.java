package allCommodities;

public interface Pricing {
    double calculateTotalRent(int days);
    double calculateTotalRent(int days, String discountCode);
    double calculateTotalRent(int days, boolean hasDamage);
}
