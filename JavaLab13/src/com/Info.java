package com;

/**
 * @author: Calin Irina, I2E2
 */


import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

public class Info implements LocaleCommand {
    @Override
    public String execute() {
        Locale locale = Locale.getDefault();
        String currentTag = new MessageFormat(LocaleManager.getInfo()).format(new Object[]{locale});
        String returnValue = currentTag + "\n" + LocaleManager.getCountry() + locale.getDisplayCountry() + '\n' +
                LocaleManager.getLanguage() + locale.getDisplayLanguage() + '\n' +
                LocaleManager.getCurrency() + Currency.getInstance(locale).getCurrencyCode() + '(' + Currency.getInstance(locale).getSymbol() + ')' + '\n' +
                getWeekdays(locale) + '\n' +
                getMonths(locale) + '\n' +
                LocaleManager.getToday() + getTodaysData(locale) + '\n';
        return returnValue;
    }

    public String getWeekdays(Locale locale) {
        WeekFields wf = WeekFields.of(locale);
        DayOfWeek day = wf.getFirstDayOfWeek();
        StringBuilder returnValue = new StringBuilder(LocaleManager.getWeekdays());
        for (int i = 0; i < DayOfWeek.values().length - 1; i++) {
            returnValue.append(day.getDisplayName(TextStyle.FULL, locale)).append(", ");
            day = day.plus(1);
        }
        returnValue.append(day.getDisplayName(TextStyle.FULL, locale));
        return returnValue.toString();
    }

    public String getMonths(Locale locale) {
        DateFormatSymbols dfs = new DateFormatSymbols(locale);
        String[] months = dfs.getMonths();
        StringBuilder returnValue = new StringBuilder(LocaleManager.getMonths());
        int i = 0;
        for (; i < months.length - 2; i++) {
            returnValue.append(months[i]).append(", ");
        }
        returnValue.append(months[i]);

        return returnValue.toString();
    }

    public String getTodaysData(Locale locale) {
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, locale);
        Date today = new Date();
        return df.format(today);
    }
}
