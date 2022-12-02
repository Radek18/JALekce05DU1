package com.engeto.Lekce05DU1;

import java.util.Comparator;

public class PlantWateringComparator implements Comparator<Plant> {

    @Override
    public int compare(Plant firstPlant, Plant secondPlant) {
        return firstPlant.getWatering().compareTo(secondPlant.getWatering());
    }

}