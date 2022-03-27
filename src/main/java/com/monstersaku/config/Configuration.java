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
    private static final String monsterFile = "../configs/monsterpool.csv";
    private static final String moveFile = "../configs/movepool.csv";
    private static final String elementEffFile = "../configs/element-type-effectivity-chart.csv";

    private static List<Monster> listOfMonsters = new ArrayList<Monster>();
    private static List<Move> listOfMoves = new ArrayList<Move>();
    private static List<ElementEff> listOfElementEff = new ArrayList<ElementEff>();

    public void setupElementEff() {
        try {
            System.out.printf("Filename: %s\n", elementEffFile);
            CSVReader reader = new CSVReader(new File(Configuration.class.getResource(elementEffFile).toURI()), ";");
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
            System.out.printf("Filename: %s\n", moveFile);
            CSVReader reader = new CSVReader(new File(Configuration.class.getResource(moveFile).toURI()), ";");
            reader.setSkipHeader(true);
            List<String[]> lines = reader.read();
            System.out.println("=========== CONTENT START ===========");
            int i = 0;
            for (String[] line : lines) {
                if (line[1].equals("NORMAL")) {
                    NormalMove readNormalMove = new NormalMove(line[0], line[2], line[3], line[4], line[5], line[6], line[7], line[8]);
                    listOfMoves.add(readNormalMove);
                    System.out.println(listOfMoves.get(i));
                    i++;
                    System.out.println(i + " item has been read");
                } else if (line[1].equals("SPECIAL")) {
                    // create special move
                } else if (line[1].equals("STATUS")) {
                    // create status move
                }
            }
            System.out.println("=========== CONTENT END ===========");
            System.out.println();
        } catch (Exception e) {
            // do nothing
        }
    }

}