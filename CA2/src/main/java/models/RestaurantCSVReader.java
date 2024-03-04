package models;

import objects.Address;
import objects.Restaurant;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RestaurantCSVReader {
//    public static void main(String[] args) {
//        RestaurantCSVReader  csvReader = new RestaurantCSVReader();
//        csvReader.loadRestaurants();
//    }
    public ArrayList<Restaurant> restaurants = new ArrayList<>();
    public String csvFileName;
    public Integer fieldNumber = 9;

    public RestaurantCSVReader(String csvFileName) {
        this.csvFileName = csvFileName;
    }

    public void loadRestaurants() {
        InputStream inputStream = UserCSVReader.class.getClassLoader().getResourceAsStream(csvFileName);
        if (inputStream != null) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length == fieldNumber) {
                        Restaurant obj = new Restaurant();
                        obj.address = new Address();

                        obj.name = values[0];
                        obj.managerUsername = values[1];
                        obj.type = values[2];
                        obj.description = values[3];
                        obj.address.country = values[4];
                        obj.address.city = values[5];
                        obj.address.street = values[6];
                        obj.startTime = values[7];
                        obj.endTime = values[8];

                        restaurants.add(obj);
                    } else {
                        System.err.println("Invalid line: " + line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("CSV file not found: " + csvFileName);
        }

        for (Restaurant obj : restaurants) {
            System.out.println(obj.name + ", " + obj.managerUsername + ", " + obj.address.country + ", " + obj.type);
        }

    }
}
