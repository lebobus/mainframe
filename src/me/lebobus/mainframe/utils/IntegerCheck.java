package me.lebobus.mainframe.utils;

public class IntegerCheck {

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);

        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
