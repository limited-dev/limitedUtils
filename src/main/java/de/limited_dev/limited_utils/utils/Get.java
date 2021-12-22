package de.limited_dev.limited_utils.utils;

public class Get {
    public static int RndmNum(int min, int max){
        return (int)(Math.random()*(max - min + 1) + min);
    }
}