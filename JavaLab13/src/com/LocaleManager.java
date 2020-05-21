package com;

/**
 * @author: Calin Irina, I2E2
 */

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleManager {
    static ResourceBundle messages;

    public static void updateLocale(){
        Locale locale = Locale.getDefault();
        messages = ResourceBundle.getBundle("res/Messages", locale);
    }

    public static String getPrompt() {
        return messages.getString("prompt");
    }

    public static String getQuit() {
        return messages.getString("quit");
    }

    public static String getShow() {
        return messages.getString("show");
    }

    public static String getSet() {
        return messages.getString("set");
    }

    public static String getInfo() {
        return messages.getString("info");
    }
    public static String Info() {
        return messages.getString("getInfo");
    }
    public static String getTag() {
        return messages.getString("tag");
    }
    public static String getCountry(){
        return messages.getString("country");
    }
    public static String getLanguage(){
        return messages.getString("language");
    }

    public static String getWeekdays(){
        return messages.getString("weekdays");
    }
    public static String getCurrency(){
        return messages.getString("currency");
    }
    public static String getMonths(){
        return messages.getString("months");
    }
    public static String getToday(){
        return messages.getString("today");
    }
    public static String getLocalSet(){
        return messages.getString("locale.set");
    }
}
