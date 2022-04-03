package com.monstersaku.util;

import java.io.File;
import java.util.List;

import com.monstersaku.monsters.*;
import com.monstersaku.monsters.move.*;

public class Config {
    private static final String fileOfMonster = "../configs/monsterpool.csv";
    private static final String fileOfMove = "../configs/movepool.csv";
    private static final String fileOfElementEff = "../configs/element-type-effectivity-chart.csv";

    private static Map<Integer, Monster> mapOfMonster = new Map<Integer, Monster>();
    private static Map<Integer, Move> mapOfMove = new Map<Integer, Move>();
    private static Map<ElementEffKey, Double> mapOfElementEff = new Map<ElementEffKey, Double>();

    // GETTER
    public static Map<Integer, Monster> getMapOfMonster() {
        return mapOfMonster;
    }
    public static Map<Integer, Move> getMapOfMove() {
        return mapOfMove;
    }
    public static Map<ElementEffKey, Double> getMapOfElementEff() {
        return mapOfElementEff;
    }

    // METHODS
    public static void setAllElementEff() {
        try {
            CSVReader reader = new CSVReader(new File(Config.class.getResource(fileOfElementEff).toURI()), ";");
            reader.setSkipHeader(true);
            List<String[]> lines = reader.read();
            for (String[] line : lines) {
                ElementEffKey key = new ElementEffKey(line[0], line[1]);
                Double effectivity = Double.valueOf(line[2]);
                mapOfElementEff.put(key, effectivity);
            }
        } catch (Exception e) {
            System.out.println("Can't set element effectivity : " + e.getMessage());
        }
    }

    public static void setAllMonster() {
        try {
            CSVReader reader = new CSVReader(new File(Config.class.getResource(fileOfMonster).toURI()), ";");
            reader.setSkipHeader(true);
            List<String[]> lines = reader.read();
            for (String[] line : lines) {
                int key = Integer.valueOf(line[0]);
                Monster readMonster = new Monster(line[1], line[2], line[3], line[4]);
                mapOfMonster.put(key, readMonster);
            }
        } catch (Exception e) {
            System.out.println("Can't set monsters : " + e.getMessage());
        }
    }
    
    public static void addNewMove(int id) {
        try {
            CSVReader reader = new CSVReader(new File(Config.class.getResource(fileOfMove).toURI()), ";");
            reader.setSkipHeader(true);
            List<String[]> lines = reader.read();

            for (String[] line : lines) {
                if (Integer.valueOf(line[0]) == id) {
                    int key = Integer.valueOf(line[0]);
                    switch (line[1]) {
                        case "NORMAL":
                            NormalMove readNormalMove = new NormalMove(line[2], line[3], line[4], line[5], line[6], line[7], line[8]);
                            mapOfMove.put(key, readNormalMove);
                            break;
                        case "SPECIAL":
                            SpecialMove readSpecialMove = new SpecialMove(line[2], line[3], line[4], line[5], line[6], line[7], line[8]);
                            mapOfMove.put(key, readSpecialMove);
                            break;
                        case "STATUS":
                            StatusMove readStatusMove = new StatusMove(line[2], line[3], line[4], line[5], line[6], line[7], line[8], line[9]);
                            mapOfMove.put(key, readStatusMove);
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Can't add new move : " + e.getMessage());
        }
    }

}
