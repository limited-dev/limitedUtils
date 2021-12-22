package de.limited_dev.limited_utils.utils.Jokes;

import de.limited_dev.limited_utils.utils.Get;

public class RndmQuitJoke {
    public static String getRndmJoke(){
        return getJoke(Get.RndmNum(1 , 6));
    }
    public static String getJoke(int rndmNum){
        return switch (rndmNum) {
            case 1 -> "I didn't like him anyways.";
            case 2 -> "He's gone now! Grab his diamonds!";
            case 3 -> "If thats not a ragequit, I don't know. ¯\\_(ツ)_/¯";
            case 4 -> "AND NEVER COME BACK!";
            case 5 -> "I just cannot be bothered rn.";
            case 6 -> "Like your dad left your mom.";
            case 7 -> "He finally found out that this isn't Terraria.";
            default -> "Null";
        };
    }
}
