package com.example.coachticket.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefOriginDestination {
    private static final String ORIGIN_NAME = "OriginPrefs";
    private static final String DESTINATION_NAME = "DestinationPrefs";
    private static final String DATE = "DatePrefs";
    private static final String PRICE = "PricePrefs";

    private static final String ORIGIN_KEY = "origin";
    private static final String DESTINATION_KEY = "destination";
    private static final String DATE_KEY = "date";
    private static final String PRICE_KEY = "price";

    public static void saveOrigin(Context context, String origin) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(ORIGIN_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ORIGIN_KEY, origin);
        editor.apply();
    }

    public static void saveDestination(Context context, String destination) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DESTINATION_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DESTINATION_KEY, destination);
        editor.apply();
    }

    public static void saveDate(Context context, String date) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DATE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DATE_KEY, date);
        editor.apply();
    }

    public static void savePrice(Context context, int price) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PRICE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PRICE_KEY, price);
        editor.apply();
    }

    public static String getOrigin(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(ORIGIN_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(ORIGIN_KEY, null);
    }

    public static String getDestination(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DESTINATION_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(DESTINATION_KEY, null);
    }

    public static String getDate(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DATE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(DATE_KEY, null);
    }

    public static int getPrice(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PRICE, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(PRICE_KEY, 0);
    }

    public static void clearOrigin(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(ORIGIN_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(ORIGIN_KEY);
        editor.apply();
    }

    public static void clearDestination(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DESTINATION_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(ORIGIN_KEY);
        editor.apply();
    }

    public static void clearDate(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DATE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(DATE_KEY);
        editor.apply();
    }

    public static void clearPrice(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PRICE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(PRICE_KEY);
        editor.apply();
    }
}
