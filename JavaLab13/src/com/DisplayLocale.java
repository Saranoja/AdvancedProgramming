package com;

/**
 * @author: Calin Irina, I2E2
 */

import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayLocale implements LocaleCommand{
    @Override
    public String execute() {
        Locale locale = Locale.getDefault();
        ResourceBundle messages = ResourceBundle.getBundle("res/Messages", locale);
        String locales = messages.getString("locales");
        StringBuilder returnValue = new StringBuilder("");
        returnValue.append(locales).append("\n");
        for(Locale locale1: Locale.getAvailableLocales()){
            returnValue.append(locale1.getDisplayLanguage(locale)).append("   ").append(locale1.getDisplayCountry()).append("\n");
        }
        return returnValue.toString();
    }
}
