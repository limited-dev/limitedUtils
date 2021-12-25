package de.limited_dev.limited_utils.utils.jokes;

import de.limited_dev.limited_utils.utils.Get;

public class RndmDeathJoke {
    public static String getRndmJoke(){
        return getJoke(Get.RndmNum(1 , 6));
    }
    public static String getJoke(int rndmNum){
        return switch (rndmNum) {
            case 1 -> "Life is Pain anyways.";
            case 2 -> "Yeet!";
            case 3 -> "His last words: You'll never take me alive!";
            case 4 -> "Death by DEEZ NUTZ!";
            case 5 -> "In this day in age...";
            case 6 -> "Bruh.";
            default -> "Null";
        };
    }
}
