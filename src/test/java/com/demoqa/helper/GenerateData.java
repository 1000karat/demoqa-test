package com.demoqa.helper;

import net.datafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

public class GenerateData {
    static Random random = new Random();
    static Faker faker = new Faker();

    public static String[] generateCalendarDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
        String[] date = sdf.format(faker.date().birthday(16, 100)).split(" ");
        return date;
    }

    public static String generateState() {
        String[] stateArray = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
        int indexStateArray = random.nextInt(stateArray.length);
        return stateArray[indexStateArray];
    }

    public static String generateCity(String state) {
        switch (state) {
            case "NCR": {
                String[] cityArray = {"Delhi", "Gurgaon", "Noida"};
                int indexCityArray = random.nextInt(cityArray.length);
                return cityArray[indexCityArray];

            }
            case "Uttar Pradesh": {
                String[] cityArray = {"Agra", "Lucknow", "Merrut"};
                int indexCityArray = random.nextInt(cityArray.length);
                return cityArray[indexCityArray];
            }
            case "Haryana": {
                String[] cityArray = {"Karnal", "Panipat"};
                int indexCityArray = random.nextInt(cityArray.length);
                return cityArray[indexCityArray];

            }
            case "Rajasthan": {
                String[] cityArray = {"Jaipur", "Jaiselmer"};
                int indexCityArray = random.nextInt(cityArray.length);
                return cityArray[indexCityArray];
            }
        }
        return state;
    }
}
