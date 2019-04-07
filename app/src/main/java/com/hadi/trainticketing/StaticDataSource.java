package com.hadi.trainticketing;

import java.util.ArrayList;
import java.util.List;

public abstract class StaticDataSource {
    private StaticDataSource() {
    }

    private static final List<String> contentDescription;
    private static final List<Integer> contentResources;

    public static List<String> getContentDescription() {
        return contentDescription;
    }

    public static List<Integer> getContentResources() {
        return contentResources;
    }

    static {
        contentDescription = new ArrayList<>();
        contentResources = new ArrayList<>();

        addItems("Welcome to train ticket reservation application, where you can reserve electronic ticket, enquire trips, services and more", R.drawable.app_logo);
        addItems("Enquire for any trip with your phone, no need to go the station or go on phone call and waste your time", R.drawable.ic_enquire);
        addItems("Keep your ticket safe, the application will keep your tickets safe on your phone", R.drawable.ic_ticket);
        addItems("Don't hold cash anymore, no need to hold cash and get robbed or lose your money, charge your wallet on the app and enjoy our services, and track all your receipts", R.drawable.ic_wallet);
        addItems("Now validation your ticket is so easy, just let the validator scan your ticket and that's it", R.drawable.ic_validation);
    }

    private static void addItems(String desc, int resourceId) {
        contentDescription.add(desc);
        contentResources.add(resourceId);
    }
}
