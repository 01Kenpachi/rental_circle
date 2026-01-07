package system;

import fileHandle.*;
import allCommodities.*;
import java.util.*;
import customexception.*;

public class RentalSystem {
    private static final String AVAILABLE_FILE = "available.txt";
    private static final String BORROWED_FILE = "borrowed.txt";
    private static final String PAYMENTS_FILE = "payments.txt";
    
    public void validateNID(String nid) throws InvalidNIDException {
        if (nid == null || !nid.matches("\\d{9}")) {
            throw new InvalidNIDException("NID must be 9 digits");
        }
    }
    
    public void addCommodity(Commodity commodity) {
        String data = commodityToCsv(commodity);
        FileService.appendToFile(AVAILABLE_FILE, data);
    }
    
    public List<Commodity> getAvailableCommodities() {
        return loadCommodities(AVAILABLE_FILE);
    }
    
    public List<Commodity> getBorrowedCommodities() {
        return loadCommodities(BORROWED_FILE);
    }
    
    public void borrowCommodity(String commodityId, String name, String nid, int days) throws Exception {
        List<Commodity> available = getAvailableCommodities();
        List<String> availableData = FileService.loadFromFile(AVAILABLE_FILE);
        Commodity toBorrow = null;
        String itemData = null;
        
        for (Commodity c : available) {
            if (c.getId().equals(commodityId)) {
                toBorrow = c;
                break;
            }
        }
        
        if (toBorrow == null) throw new Exception("Commodity not available");
        
        for (String line : availableData) {
            if (line.startsWith(commodityId + ",")) {
                itemData = line;
                break;
            }
        }
        
        if (itemData == null) throw new Exception("Commodity data not found");
        
        availableData.remove(itemData);
        FileService.saveToFile(AVAILABLE_FILE, availableData);
        
        String borrowedData = itemData + "," + name + "," + nid + "," + days;
        FileService.appendToFile(BORROWED_FILE, borrowedData);
    }
    
    public void returnCommodity(String commodityId, int actualDays, boolean hasDamage) throws Exception {
        List<Commodity> borrowed = getBorrowedCommodities();
        List<String> borrowedData = FileService.loadFromFile(BORROWED_FILE);
        Commodity toReturn = null;
        String itemData = null;
        int agreedDays = 0;
        
        for (Commodity c : borrowed) {
            if (c.getId().equals(commodityId)) {
                toReturn = c;
                break;
            }
        }
        
        if (toReturn == null) throw new Exception("Commodity not borrowed");
        
        for (String line : borrowedData) {
            if (line.startsWith(commodityId + ",")) {
                String[] parts = line.split(",");
                agreedDays = Integer.parseInt(parts[parts.length - 1]);
                itemData = String.join(",", Arrays.copyOf(parts, parts.length - 3));
                break;
            }
        }
        
        if (itemData == null) throw new Exception("Commodity data not found");
        
        borrowedData.removeIf(line -> line.startsWith(commodityId + ","));
        FileService.saveToFile(BORROWED_FILE, borrowedData);
        
        FileService.appendToFile(AVAILABLE_FILE, itemData);
        
        double payment = toReturn.calculateTotalRent(agreedDays);
        if (actualDays > agreedDays) {
            int extraDays = actualDays - agreedDays;
            payment += toReturn.calculateTotalRent(extraDays);
        }
        
        if (hasDamage) {
            payment = toReturn.calculateTotalRent(0, hasDamage); // 0 days, just damage charge
        }
        
        FileService.appendToFile(PAYMENTS_FILE, commodityId + "," + payment);
    }
    
    public double getTotalProfit() {
        List<String> payments = FileService.loadFromFile(PAYMENTS_FILE);
        double total = 0;
        for (String payment : payments) {
            String[] parts = payment.split(",");
            if (parts.length >= 2) {
                total += Double.parseDouble(parts[1]);
            }
        }
        return total;
    }
    
    private List<Commodity> loadCommodities(String filename) {
        List<Commodity> commodities = new ArrayList<>();
        for (String line : FileService.loadFromFile(filename)) {
            Commodity commodity = createCommodityFromCsv(line);
            if (commodity != null) {
                commodities.add(commodity);
            }
        }
        return commodities;
    }
    
    private Commodity createCommodityFromCsv(String csv) {
        String[] parts = csv.split(",");
        if (parts.length < 6) return null;
        
        String id = parts[0];
        String name = parts[1];
        double actualPrice = Double.parseDouble(parts[2]);
        double rentPerDay = Double.parseDouble(parts[3]);
        CommodityType type = CommodityType.valueOf(parts[4]);
        String detail = parts[5];
        
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
    
    private String commodityToCsv(Commodity commodity) {
        String detail = "";
        if (commodity instanceof Electronics) {
            detail = ((Electronics) commodity).getDetails().split("Brand: ")[1];
        } else if (commodity instanceof Furniture) {
            detail = ((Furniture) commodity).getDetails().split("Material: ")[1];
        } else if (commodity instanceof Vehicle) {
            detail = ((Vehicle) commodity).getDetails().split("Fuel Type: ")[1];
        } else if (commodity instanceof Clothing) {
            detail = ((Clothing) commodity).getDetails().split("Color: ")[1];
        } else if (commodity instanceof Tools) {
            detail = ((Tools) commodity).getDetails().split("Tool Type: ")[1];
        } else if (commodity instanceof OtherCommodity) {
            detail = ((OtherCommodity) commodity).getDetails().split("Description: ")[1];
        }
        
        return String.join(",",
            commodity.getId(),
            commodity.getName(),
            String.valueOf(commodity.getActualPrice()),
            String.valueOf(commodity.getRentPerDay()),
            commodity.getType().name(),
            detail
        );
    }
}
