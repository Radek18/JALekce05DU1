package com.engeto.Lekce05DU1;

public class Settings {

    private static final String INPUT = "kvetiny.txt";

    private static final String OUTPUT = "kvetiny-vystup.txt";

    private static final String DELIMITER = "\t";

    public static String input() {
        return INPUT;
    }

    public static String output() {
        return OUTPUT;
    }

    public static String delimiter() {
        return DELIMITER;
    }

}