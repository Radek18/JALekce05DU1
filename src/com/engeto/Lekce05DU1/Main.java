package com.engeto.Lekce05DU1;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        FlowerList flowerList = new FlowerList();

        try {
            flowerList.readPlantsFromFile(Settings.input());
        } catch (FileNotFoundException | NumberFormatException | DateTimeParseException | PlantException e) {
            System.err.println("Chyba při čtení souboru. " + e.getLocalizedMessage());
        }

        try {
            flowerList.addPlant(new Plant("Hortenzie", "Proschlá", LocalDate.of(2022, 8, 1), LocalDate.now().minusDays(1), 1));
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        }

        try {
            flowerList.addPlant(new Plant("Orchidej", "Bez květů", LocalDate.of(2022, 10, 12), LocalDate.of(2022, 11, 10), 7));
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        }

        flowerList.removePlant(1);

        System.out.println("\n- Výpis informací o zálivce:");
        for (Plant plant : flowerList.getPlants()) System.out.println(plant.getWateringInfo());

        try {
            flowerList.writePlantsToFile(Settings.output());
        } catch (PlantException e) {
            System.err.println(e.getLocalizedMessage());
        }

        System.out.println("\n- Výpis informací o zálivce - řazení podle názvu rostliny:");

        List<Plant> copyOfFlowerList = new ArrayList<>(flowerList.getPlants());

        Collections.sort(copyOfFlowerList);

        for (Plant plant : copyOfFlowerList) System.out.println(plant.getWateringInfo());

        System.out.println("\n- Výpis informací o zálivce - řazení podle data poslední zálivky:");

        copyOfFlowerList.sort(new PlantWateringComparator());

        for (Plant plant : copyOfFlowerList) System.out.println(plant.getWateringInfo());

        System.out.println("\n- Výpis dat zasazení rostlin:");

        Set<LocalDate> planted = new HashSet<>();

        for (Plant plant : copyOfFlowerList) planted.add(plant.getPlanted());

        for (LocalDate date : planted) System.out.println(date.format(DateTimeFormatter.ofPattern("dd. MM. yyyy")));

    }

}