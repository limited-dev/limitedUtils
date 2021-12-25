package de.limited_dev.limited_utils.utils.jokes;

import de.limited_dev.limited_utils.utils.Get;

public class RndmJoinJoke {
    public static String getRndmJoke(){
        return getJoke(Get.RndmNum(1 , 5));
    }
    //TODO: Add more jokes
    public static String getJoke(int rndmNum){
        return switch (rndmNum) {
            case 1 -> "Welcome to Hell.";
            case 2 -> "Why even bother?";
            case 3 -> "I'ma go commit not living anymore.";
            case 4 -> "Nah, I'm good.";
            case 5 -> "Wait. This isn't Terraria.";
            default -> "null";
        };
    }
}
