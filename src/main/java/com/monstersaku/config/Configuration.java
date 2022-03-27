package com.monstersaku.config;

import com.monstersaku.util.CSVReader;
import com.monstersaku.monsters.*;
import com.monstersaku.monsters.move.*;

import java.io.File;
import java.util.Arrays;
//import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Configuration {
    private static final String fileOfMonster = "../configs/monsterpool.csv";
    private static final String fileOfMove = "../configs/movepool.csv";
    private static final String fileOfElementEff = "../configs/element-type-effectivity-chart.csv";

    public static final List<Monster> listOfMonster = new ArrayList<Monster>();
    public static final List<Move> listOfMove = new ArrayList<Move>();
    public static final List<ElementEff> listOfElementEff = new ArrayList<ElementEff>();

    //public static void

    public void setupElementEff() {
        try {
            System.out.printf("Filename: %s\n", fileOfElementEff);
            CSVReader reader = new CSVReader(new File(Configuration.class.getResource(fileOfElementEff).toURI()), ";");
            reader.setSkipHeader(true);
            List<String[]> lines = reader.read();
            System.out.println("=========== CONTENT START ===========");
            int i = 0;
            for (String[] line : lines) {
                ElementEff readElementEff = new ElementEff(line[0], line[1], line[2]);
                listOfElementEff.add(readElementEff);
                System.out.println(listOfElementEff.get(i).getSource());
                System.out.println(listOfElementEff.get(i).getTarget());
                System.out.println(listOfElementEff.get(i).getEffectivity());
                i++;
                System.out.println(i + " item has been read");
            }
            System.out.println("=========== CONTENT END ===========");
            System.out.println();
        } catch (Exception e) {
            // do nothing
        }
    }

    public void setupMove() {
        try {
            System.out.printf("Filename: %s\n", fileOfMove);
            CSVReader reader = new CSVReader(new File(Configuration.class.getResource(fileOfMove).toURI()), ";");
            reader.setSkipHeader(true);
            List<String[]> lines = reader.read();
            System.out.println("=========== CONTENT START ===========");
            int i = 0;
            for (String[] line : lines) {
                if (line[1].equals("NORMAL")) {
                    NormalMove readNormalMove = new NormalMove(line[0], line[2], line[3], line[4], line[5], line[6], line[7], line[8]);
                    listOfMove.add(readNormalMove);
                    System.out.println(listOfMove.get(i));
                    i++;
                    System.out.println(i + " item has been read");
                } else if (line[1].equals("SPECIAL")) {
                    SpecialMove readSpecialMove = new SpecialMove(line[0], line[2], line[3], line[4], line[5], line[6], line[7], line[8]);
                    listOfMove.add(readSpecialMove);
                    System.out.println(listOfMove.get(i));
                    i++;
                    System.out.println(i + " item has been read");
                } else if (line[1].equals("STATUS")) {
                    StatusMove readStatusMove = new StatusMove(line[0], line[2], line[3], line[4], line[5], line[6], line[7], line[8], line[9]);
                    listOfMove.add(readStatusMove);
                    System.out.println(listOfMove.get(i));
                    i++;
                    System.out.println(i + " item has been read");
                }
            }
            System.out.println("=========== CONTENT END ===========");
            System.out.println();
        } catch (Exception e) {
            // do nothing
        }
    }
    
    public void setupMonster() {
        try {
            System.out.printf("Filename: %s\n", fileOfMonster);
            CSVReader reader = new CSVReader(new File(Configuration.class.getResource(fileOfMonster).toURI()), ";");
            reader.setSkipHeader(true);
            List<String[]> lines = reader.read();
            System.out.println("=========== CONTENT START ===========");
            int i = 0;
            for (String[] line : lines) {
                Monster readMonster = new Monster(line[0], line[1], line[2], line[3], line[4]);
                listOfMonster.add(readMonster);
                System.out.println(listOfMonster.get(i));
              i++;
                System.out.println(i + " item has been read");
            }
            System.out.println("=========== CONTENT END ===========");
            System.out.println();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}