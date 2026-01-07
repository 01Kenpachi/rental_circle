package fileHandle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    public static void saveToFile(String filename, List<String> data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (String line : data) {
                writer.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    public static List<String> loadFromFile(String filename) {
        List<String> data = new ArrayList<>();
        File file = new File(filename);
        
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    data.add(line);
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }
        return data;
    }
    
    public static void appendToFile(String filename, String data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(data);
        } catch (IOException e) {
            System.out.println("Error appending to file: " + e.getMessage());
        }
    }
}
