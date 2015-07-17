package com.utilproject.util;

/**
 *
 * @author Zygimantas
 */
public class Time {

    public static String getDurationString(int seconds) {

	int hours = seconds / 3600;
	int minutes = (seconds % 3600) / 60;
	seconds = seconds % 60;

	return twoDigitString(hours) + " : " + twoDigitString(minutes) + " : " + twoDigitString(seconds);
    }

    private static String twoDigitString(int number) {

	if (number == 0) {
	    return "00";
	}

	if (number / 10 == 0) {
	    return "0" + number;
	}

	return String.valueOf(number);
    }

    public static String secondsToHMS(int totalSecs) {

	int hours = totalSecs / 3600;
	int minutes = (totalSecs % 3600) / 60;
	int seconds = totalSecs % 60;

	return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

}
