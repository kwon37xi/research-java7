package ch11;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UsageLocaleBuilder {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.setWeekDate(2012, 16, 3);

        // 이탈리아 라틴문자 사용 Eastern Armenia
        Locale.Builder builder = new Locale.Builder();
        builder.setLanguage("hy");
        builder.setScript("Latn");
        builder.setRegion("IT");
        builder.setVariant("arevela");

        Locale locale = builder.build();
        Locale.setDefault(locale);

        System.out.println(DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(calendar.getTime()));
        System.out.println("" + locale.getDisplayLanguage());

        // 중국, 간자체
        builder.setLanguage("zh");
        builder.setScript("Hans");
        builder.setRegion("CN");

        locale = builder.build();
        Locale.setDefault(locale);

        System.out.println(DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(calendar.getTime()));
        System.out.println("" + locale.getDisplayLanguage());

    }
}
