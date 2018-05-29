package solutions;

import lib.AbstractSolution;

import java.util.Calendar;
import java.util.concurrent.ConcurrentLinkedDeque;

/*
 * You are given the following information, but you may prefer to do some research for yourself.
 *
 * 1 Jan 1900 was a Monday.
 * Thirty days has September,
 * April, June and November.
 * All the rest have thirty-one,
 * Saving February alone,
 * Which has twenty-eight, rain or shine.
 * And on leap years, twenty-nine.
 * A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
 *
 * How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
 */
public class Solution019 extends AbstractSolution {
    @Override
    public String run() {

        Calendar startDate = Calendar.getInstance();
        startDate.set(1901, 1, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2000, 12, 31);

        int sundays = 0;

        Calendar currentDate = (Calendar) startDate.clone();
        while (currentDate.compareTo(endDate) < 0) {
            if (currentDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                sundays += 1;
            }

            // Go to the next first of the month
            currentDate.add(Calendar.MONTH, 1);
        }

        return String.valueOf(sundays);
    }
}
