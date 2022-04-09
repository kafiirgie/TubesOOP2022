package com.monstersaku.view;

import java.lang.Thread;

public class Exit {
    public static void exitCredits() {
        try {
            System.out.println("Thank you for playing 'MONSTER SAKU'\n");
            Thread.sleep(1000);
            System.exit(0);
        } catch (Exception e) {
            System.out.println("[Exception in Exit] : " + e.getMessage());
        }
    }
}
