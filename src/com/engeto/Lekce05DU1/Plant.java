package com.engeto.Lekce05DU1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Plant {

    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;

    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int frequencyOfWatering) throws PlantExpection {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        setWatering(watering);
        setFrequencyOfWatering(frequencyOfWatering);
    }

    public Plant(String name, LocalDate planted, int frequencyOfWatering) throws PlantExpection {
        this(name, null, planted, LocalDate.now(), frequencyOfWatering);
    }

    public Plant(String name) throws PlantExpection {
        this(name, null, LocalDate.now(), LocalDate.now(), 7);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) throws PlantExpection {
        if (watering.isBefore(planted)) {
            throw new PlantExpection("Datum poslední zálivky musí být po datu zasazení. U rostliny \"" + name + "\" zadáno datum zasazení: " + planted.format(DateTimeFormatter.ofPattern("dd. MM. yyyy")) + ", datum poslední zálivky: " + watering.format(DateTimeFormatter.ofPattern("dd. MM. yyyy")));
        }
        this.watering = watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantExpection {
        if (frequencyOfWatering < 1) {
            throw new PlantExpection("Frekvence zálivky musí být kladné číslo. U rostliny \"" + name + "\" zadáno: " + frequencyOfWatering);
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }


    public String getWateringInfo() {
        return "Název květiny: " + name + ", Datum poslední zálivky: " + watering.format(DateTimeFormatter.ofPattern("dd. MM. yyyy")) + ", Datum doporučené další zálivky: " + watering.plusDays(frequencyOfWatering).format(DateTimeFormatter.ofPattern("dd. MM. yyyy"));
    }

}