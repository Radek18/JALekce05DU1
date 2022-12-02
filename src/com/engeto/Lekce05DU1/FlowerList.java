package com.engeto.Lekce05DU1;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlowerList {

    private List<Plant> plants = new ArrayList<>();

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public Plant getPlant (int index) {
        return plants.get(index);
    }

    public void removePlant(int index) {
        plants.remove(index);
    }

    public void readPlantsFromFile (String input) throws FileNotFoundException, PlantException {

        int lineNumber = 0;
        String nextLine;
        String[] items = new String[0];
        Plant newPlant;

        String name;
        String notes;
        int frequencyOfWatering;
        LocalDate planted;
        LocalDate watering;

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(input)))) {
            while (scanner.hasNextLine()) {
                lineNumber++;
                nextLine = scanner.nextLine();
                items = nextLine.split(Settings.delimiter());
                name = items[0];
                notes = items[1];
                frequencyOfWatering = Integer.parseInt(items[2]);
                planted = LocalDate.parse(items[4]);
                watering = LocalDate.parse(items[3]);
                newPlant = new Plant(name, notes, planted, watering, frequencyOfWatering);
                plants.add(newPlant);
            }
        } catch (FileNotFoundException e) {
            throw new PlantException("Nepodařilo se najít soubor (" + Settings.input() + ").");
        } catch (NumberFormatException e) {
            throw new PlantException("Nesprávný formát frekvence zálivky (" + items[2] + ") na řádku: " + lineNumber);
        } catch (DateTimeParseException e) {
            throw new PlantException("Nesprávný formát data zasazení rostliny nebo data poslední zálivky (" + items[3] + " " + items[4] + ") na řádku: " + lineNumber);
        }

    }

    public void writePlantsToFile (String output) throws PlantException {

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(output)))) {
            for (Plant plant : plants) {
                String outputLine = plant.getName() + Settings.delimiter() + plant.getNotes() + Settings.delimiter() + plant.getFrequencyOfWatering() + Settings.delimiter() + plant.getWatering() + Settings.delimiter() + plant.getPlanted();
                writer.println(outputLine);
            }
        } catch (IOException e) {
            throw new PlantException("Nastala chyba při zápisu do souboru.");
        }

    }

}