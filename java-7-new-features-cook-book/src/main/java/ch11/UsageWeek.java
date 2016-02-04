package ch11;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.SimpleTimeZone;

public class UsageWeek {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();

        if (calendar.isWeekDateSupported()) {
            System.out.println("Number of weeks in this year: " + calendar.getWeeksInWeekYear());
            System.out.println("Current week number: " + calendar.get(Calendar.WEEK_OF_YEAR));

            calendar.setWeekDate(2012, 16, 3); // 2012년 16번째주의 세번째 날. 주의 시작은 Locale마다 다른데 보통은 일요일, 프랑스는 월요일.
            System.out.println(DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(calendar.getTime()));

            // 한해의 첫번째주
            calendar.setWeekDate(2022, 1, 1); // 2021년 12월 26일(일요일)
            System.out.println(DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(calendar.getTime()));
        }

        SimpleTimeZone simpleTimeZone = new SimpleTimeZone(-21600000, "CST",
                Calendar.MARCH, 1, -Calendar.SUNDAY,
                7200000,
                Calendar.NOVEMBER, -1, Calendar.SUNDAY,
                7200000,
                3600000);
        System.out.println(simpleTimeZone.getDisplayName() + " - " + simpleTimeZone.observesDaylightTime()); //중부 표준시 - true
    }
}
