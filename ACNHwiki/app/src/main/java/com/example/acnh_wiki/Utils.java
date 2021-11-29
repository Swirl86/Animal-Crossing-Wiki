package com.example.acnh_wiki;

import android.annotation.SuppressLint;

import java.util.Locale;

public class Utils {
    public static String capitalizeString(String str) {
        String retStr = str;
        try {
            retStr = str.substring(0, 1).toUpperCase() + str.substring(1);
        } catch (Exception ignored){}
        return retStr;
    }

    @SuppressLint("DefaultLocale")
    public static String getTimeString(long millis) {
        long allSeconds = millis / 1000;
        int allMinutes;
        byte seconds, minutes, hours;
        if (allSeconds >= 60) {
            allMinutes = (int) (allSeconds / 60);
            seconds = (byte) (allSeconds % 60);
            if (allMinutes >= 60) {
                hours = (byte) (allMinutes / 60);
                minutes = (byte) (allMinutes % 60);
                return String.format(Locale.ROOT, "%d:%d:" + formatSeconds(seconds), hours, minutes, seconds);
            } else
                return String.format(Locale.ROOT, "%d:" + formatSeconds(seconds), allMinutes, seconds);
        } else
            return String.format(Locale.ROOT, "0:" + formatSeconds((byte) allSeconds), allSeconds);
    }

    public static String formatSeconds(byte seconds) {
        String secondsFormatted;
        if (seconds < 10) secondsFormatted = "0%d";
        else secondsFormatted = "%d";
        return secondsFormatted;
    }
}
