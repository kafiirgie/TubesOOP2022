package com.monstersaku;

import com.monstersaku.view.*;

public class Main {
    public static void main(String[] args) {
        System.out.flush();
        Menu.showWelcome();
        Menu.showStartupMenu();
        Menu.selectMenu();
        
        // for (String fileName : CSV_FILE_PATHS) {
        //     try {
        //         System.out.printf("Filename: %s\n", fileName);
        //         CSVReader reader = new CSVReader(new File(Main.class.getResource(fileName).toURI()), ";");
        //         reader.setSkipHeader(true);
        //         List<String[]> lines = reader.read();
        //         System.out.println("=========== CONTENT START ===========");
        //         for (String[] line : lines) {
        //             for (String word : line) {
        //                 System.out.printf("%s ", word);
        //             }
        //             System.out.println();
        //         }
        //         System.out.println("=========== CONTENT END ===========");
        //         System.out.println();
        //     } catch (Exception e) {
        //         // do nothing
        //     }
        // }
    }
}
