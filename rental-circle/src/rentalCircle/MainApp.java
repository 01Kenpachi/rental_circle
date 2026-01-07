package rentalCircle;

import allCommodities.*;
import customexception.*;
import system.*;
import java.util.*;

public class MainApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final RentalSystem rentalSystem = new RentalSystem();
    private static final String ADMIN_USER = "admin";
    private static final String ADMIN_PASS = "admin123";

    public static void main(String[] args) {
        if (login()) {
            mainMenu();
        }
    }

    private static boolean login() {
        System.out.println("===== Commodity Rental System =====");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        if (ADMIN_USER.equals(username) && ADMIN_PASS.equals(password)) {
            return true;
        } else {
            System.out.println("Invalid username or password. Please try again.\n");
            return login(); 
        }
    }

    private static void mainMenu() {
        while (true) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Add New Commodity");
            System.out.println("2. Borrow a Commodity");
            System.out.println("3. Return a Commodity");
            System.out.println("4. View Total Profit");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1": addCommodityMenu(); break;
                case "2": borrowMenu(); break;
                case "3": returnMenu(); break;
                case "4": 
                    System.out.printf("\nTotal Profit: %.2f Taka\n", 
                        rentalSystem.getTotalProfit());
                    break;
                case "5": System.exit(0);
                default: System.out.println("Invalid option!");
            }
        }
    }

    private static void addCommodityMenu() {
        try {
            System.out.println("\n===== ADD NEW COMMODITY =====");
            System.out.print("Commodity Name: ");
            String name = scanner.nextLine();
            
            System.out.print("Actual Price (Taka): ");
            double actualPrice = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Rent Per Day (Taka): ");
            double rentPerDay = Double.parseDouble(scanner.nextLine());
            
            CommodityType type = selectCategory();
            String detail = "";
            
            switch (type) {
                case ELECTRONICS:
                    System.out.print("Enter Brand: ");
                    detail = scanner.nextLine();
                    break;
                case FURNITURE:
                    System.out.print("Enter Material: ");
                    detail = scanner.nextLine();
                    break;
                case VEHICLE:
                    System.out.print("Enter Fuel Type: ");
                    detail = scanner.nextLine();
                    break;
                case CLOTHING:
                    System.out.print("Enter Color: ");
                    detail = scanner.nextLine();
                    break;
                case TOOLS:
                    System.out.print("Enter Tool Type: ");
                    detail = scanner.nextLine();
                    break;
                case OTHERS:
                    System.out.print("Enter Description: ");
                    detail = scanner.nextLine();
                    break;
            }
            
            String id = "CMD-" + System.currentTimeMillis();
            Commodity commodity = createCommodity(id, name, actualPrice, rentPerDay, type, detail);
            
            rentalSystem.addCommodity(commodity);
            System.out.println("\nCommodity added successfully!");
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void borrowMenu() {
        try {
            System.out.println("\n===== BORROW COMMODITY =====");
            System.out.print("Your Name: ");
            String name = scanner.nextLine();
            
            System.out.print("Your NID (9 digits): ");
            String nid = scanner.nextLine();
            rentalSystem.validateNID(nid);
            
            CommodityType type = selectCategory();
            List<Commodity> items = rentalSystem.getAvailableCommodities();
            
            List<Commodity> categoryItems = new ArrayList<>();
            for (Commodity item : items) {
                if (item.getType() == type) {
                    categoryItems.add(item);
                }
            }
            
            if (categoryItems.isEmpty()) {
                System.out.println("\nNo items available in this category.");
                return;
            }
            
            System.out.println("\nAvailable " + type + ":");
            for (int i = 0; i < categoryItems.size(); i++) {
                System.out.println((i + 1) + ". " + categoryItems.get(i).getDetails());
            }
            
            System.out.print("\nSelect item (1-" + categoryItems.size() + "): ");
            int choice = Integer.parseInt(scanner.nextLine());
            
            if (choice < 1 || choice > categoryItems.size()) {
                System.out.println("Invalid selection!");
                return;
            }
            
            Commodity selected = categoryItems.get(choice - 1);
            
            System.out.print("\nEnter rental days: ");
            int days = Integer.parseInt(scanner.nextLine());
            
            if (days < 1) {
                System.out.println("Minimum rental period is 1 day.");
                return;
            }
            
            System.out.print("Discount Code (or press Enter): ");
            String discountCode = scanner.nextLine();
            
            double totalAmount;
            if (!discountCode.isEmpty()) {
                totalAmount = selected.calculateTotalRent(days, discountCode);
            } else {
                totalAmount = selected.calculateTotalRent(days);
            }
            
            rentalSystem.borrowCommodity(selected.getId(), name, nid, days);
            
            System.out.println("\n--- PAYMENT INFO ---");
            System.out.println("Total Amount: " + totalAmount + " Taka");
            System.out.println("Payment Method: Cash on Delivery");
            System.out.println("Commodity borrowed successfully!");
            
        } catch (InvalidNIDException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void returnMenu() {
        try {
            System.out.println("\n===== RETURN COMMODITY =====");
            List<Commodity> borrowed = rentalSystem.getBorrowedCommodities();
            
            if (borrowed.isEmpty()) {
                System.out.println("No borrowed items to return.");
                return;
            }
            
            System.out.println("\nBorrowed Items:");
            for (int i = 0; i < borrowed.size(); i++) {
                System.out.println((i + 1) + ". " + borrowed.get(i).getDetails());
            }
            
            System.out.print("\nSelect item to return (1-" + borrowed.size() + "): ");
            int choice = Integer.parseInt(scanner.nextLine());
            
            if (choice < 1 || choice > borrowed.size()) {
                System.out.println("Invalid selection!");
                return;
            }
            
            Commodity selected = borrowed.get(choice - 1);
            
            System.out.print("Enter actual days kept: ");
            int actualDays = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Was the item damaged? (yes/no): ");
            String damageInput = scanner.nextLine();
            boolean hasDamage = damageInput.equalsIgnoreCase("yes");
            
            rentalSystem.returnCommodity(selected.getId(), actualDays, hasDamage);
            
            System.out.println("\nCommodity returned successfully!");
            if (hasDamage) {
                System.out.println("Damage fee applied: " + selected.getActualPrice() + " Taka");
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static CommodityType selectCategory() {
        System.out.println("\nSelect Category:");
        System.out.println("1. Electronics");
        System.out.println("2. Furniture");
        System.out.println("3. Vehicle");
        System.out.println("4. Clothing");
        System.out.println("5. Tools");
        System.out.println("6. Others");
        System.out.print("Enter category number: ");
        
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1: return CommodityType.ELECTRONICS;
            case 2: return CommodityType.FURNITURE;
            case 3: return CommodityType.VEHICLE;
            case 4: return CommodityType.CLOTHING;
            case 5: return CommodityType.TOOLS;
            default: return CommodityType.OTHERS;
        }
    }
    
    private static Commodity createCommodity(String id, String name, double actualPrice, 
                                            double rentPerDay, CommodityType type, String detail) {
        switch (type) {
            case ELECTRONICS: return new Electronics(id, name, actualPrice, rentPerDay, detail);
            case FURNITURE: return new Furniture(id, name, actualPrice, rentPerDay, detail);
            case VEHICLE: return new Vehicle(id, name, actualPrice, rentPerDay, detail);
            case CLOTHING: return new Clothing(id, name, actualPrice, rentPerDay, detail);
            case TOOLS: return new Tools(id, name, actualPrice, rentPerDay, detail);
            case OTHERS: return new OtherCommodity(id, name, actualPrice, rentPerDay, detail);
            default: return null;
        }
    }
}