package de.limited_dev.limited_utils.utils.jokes;

import de.limited_dev.limited_utils.utils.Get;

public class RndmDayJoke {
    public static String getRndmJoke(){
        return getJoke(Get.RndmNum(1 , 2));
    }
    public static String getJoke(int rndmNum){
        return switch (rndmNum) {
            case 1 -> "Rise and shine Mr.Freeman, rise and smell the ashes...";
            case 2 -> "It's a beautiful day outside. The birds are singing, the flowers are blooming...";
            default -> "Null";
        };
    }
}
