package com.monstersaku;

import com.monstersaku.util.Config;
import com.monstersaku.view.*;

public class Main {
    public static void main(String[] args) {
        //Menu menu = new Menu();
        Config.clearConsole();
        Menu.showWelcome();
        Menu.showStartupMenu();
        Menu.selectMenu();
    }
}
