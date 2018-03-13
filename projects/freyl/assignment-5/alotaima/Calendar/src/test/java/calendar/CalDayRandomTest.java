package calendar;

import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Ignore;

import java.util.Calendar;
import java.util.Random;
import java.util.*;

/**
 * Random Test Generator for CalDay class.
 */

public class CalDayRandomTest {
    private static final long TestTimeout = 20 * 500 * 1; /* Timeout at 30 seconds */
    private static final int NUM_TESTS = 100;

    /**
     * Generate Random Tests that tests CalDay Class.
     */

    @Test
    public void testRandom() throws Throwable {

        long startTime = Calendar.getInstance().getTimeInMillis();
        long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

        System.out.println("Start CalDay Random testing...");

        long randomseed = System.currentTimeMillis(); // 10
        Random random = new Random(randomseed);

        String title = "Birthday Party";
        String description = "This is my birthday party.";

        try {
            for (int iteration = 0; elapsed < TestTimeout; iteration++) {
                int startMonth = ValuesGenerator.getRandomIntBetween(random, 1, 11);
                int startYear = ValuesGenerator.RandInt(random);
                int NumDaysInMonth = CalendarUtil.NumDaysInMonth(startYear, startMonth - 1);
                int startDay = ValuesGenerator.getRandomIntBetween(random, 1, NumDaysInMonth);
                GregorianCalendar cal = new GregorianCalendar(startYear, startMonth, startDay);
                CalDay calday = new CalDay(cal);
                for (int i = 0; i < NUM_TESTS; i++) {
                    int startHour = ValuesGenerator.RandInt(random);
                    int startMinute = ValuesGenerator.RandInt(random);
                    Appt appt = new Appt(startHour, startMinute, startDay, startMonth, startYear,
                            title, description);
                    calday.addAppt(appt);
                }

                elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
                if ((iteration % 10000) == 0 && iteration != 0)
                    System.out.println("elapsed time: " + elapsed + " of " + TestTimeout);

            }
        } catch (NullPointerException e) {
            System.out.println("Caught exception: " + e);
        }

        System.out.println("Done testing...");
    }
}
