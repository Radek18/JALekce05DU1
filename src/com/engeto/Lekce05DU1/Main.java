package com.engeto.Lekce05DU1;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Main {

    public static void main(String[] args) {

        FlowerList flowerList = new FlowerList();

        try {
            flowerList.readPlantsFromFile(Settings.VSTUP());
        } catch (FileNotFoundException | NumberFormatException | DateTimeParseException | PlantExpection e) {
            System.err.println("Chyba při čtení souboru. " + e.getLocalizedMessage());
        }

        try {
            flowerList.addPlant(new Plant("Hortenzie", "Proschlá", LocalDate.of(2022, 8, 1), LocalDate.now().minusDays(1), 1));
        } catch (PlantExpection e) {
            System.err.println(e.getLocalizedMessage());
        }

        try {
            flowerList.addPlant(new Plant("Orchidej", "Bez květů", LocalDate.of(2022, 10, 12), LocalDate.of(2022, 11, 10), 7));
        } catch (PlantExpection e) {
            System.err.println(e.getLocalizedMessage());
        }

        flowerList.removePlant(1);

        for (Plant plant : flowerList.getPlants()) System.out.println(plant.getWateringInfo());

        try {
            flowerList.writePlantsToFile(Settings.VYSTUP());
        } catch (PlantExpection e) {
            System.err.println(e.getLocalizedMessage());
        }

    }

}