package de.limited_dev.limited_utils.utils;

import de.limited_dev.limited_utils.utils.jokes.RndmDayJoke;
import de.limited_dev.limited_utils.utils.jokes.RndmDeathJoke;
import de.limited_dev.limited_utils.utils.jokes.RndmJoinJoke;
import de.limited_dev.limited_utils.utils.jokes.RndmQuitJoke;

public class JokeHandler {
    public static String getJoke(String Type){
        switch (Type){
            case "Death":
                return RndmDeathJoke.getRndmJoke();
            case "Join":
                return RndmJoinJoke.getRndmJoke();
            case "Quit":
                return RndmQuitJoke.getRndmJoke();
            case "Day":
                return RndmDayJoke.getRndmJoke();
            default:
                return null;
        }
    }
}