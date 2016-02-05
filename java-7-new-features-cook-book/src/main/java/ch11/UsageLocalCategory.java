package ch11;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UsageLocalCategory {
    public static void main(String[] args) {
        Locale locale = Locale.getDefault();
        Calendar calendar = Calendar.getInstance();
        calendar.setWeekDate(2012, 16, 3);

        System.out.println(DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(calendar.getTime()));
        System.out.println("" + locale.getDisplayLanguage());

        Locale.setDefault(Locale.Category.FORMAT, Locale.GERMAN);
        Locale.setDefault(Locale.Category.DISPLAY, Locale.JAPANESE);

        System.out.println(DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(calendar.getTime()));
        System.out.println("" + locale.getDisplayLanguage());
    }
}
