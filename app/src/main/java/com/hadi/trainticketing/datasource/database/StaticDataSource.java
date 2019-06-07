package com.hadi.trainticketing.datasource.database;

import com.hadi.trainticketing.R;
import com.hadi.trainticketing.passenger.model.PassengerCards;

import java.util.ArrayList;
import java.util.List;

/**
 * this class will hold static data for the application
 * to share it with {@link com.hadi.trainticketing.welcome.WelcomeActivity} for the welcome view pager
 * and {@link com.hadi.trainticketing.passenger.view.activities.PassengerMainActivity} for the passenger
 * cards
 *
 * @author Hady Ahmed
 * @version 1.0
 * @since 09th, April 2019
 */
public abstract class StaticDataSource {
    /**
     * list that holds the list of {@link PassengerCards} welcome list of features
     */
    private static final List<PassengerCards> passengerCardsList;
    // number of the list item in the list
    public static final int ENQUIRE_ITEM = 0;
    public static final int RESERVE_ITEM = 1;
    public static final int BALANCE_ITEM = 2;
    public static final int TICKET_ITEM = 3;
    public static final int HISTORY_ITEM = 4;
    // is a list that holding the text description for the page in welcome screens
    private static final List<String> contentDescription;

    static {
        passengerCardsList = new ArrayList<>();
        addWelcomeItemList(new PassengerCards(ENQUIRE_ITEM,
                "Enquire",
                "You Can Enquire For Any Trip You like so easy any time without wasting time on phone call, or going your self to the train station and ask for trips and tickets price", R.drawable.ic_enquire_item));

        addWelcomeItemList(new PassengerCards(RESERVE_ITEM,
                "Reserve",
                "We present powerful way to reserve train tickets so easy, and you can hold your ticket in your phone without any additional papers, just search, choose and confirm", R.drawable.ic_reserve));
        addWelcomeItemList(new PassengerCards(BALANCE_ITEM,
                "Balance",
                "Your wallet for our services is secure with us, use your balance to buy tickets, reserve for others and enjoy with our train services", R.drawable.ic_wallet));
        addWelcomeItemList(new PassengerCards(TICKET_ITEM,
                "Tickets",
                "Use your tickets to validate it, when you reach your trip, the validator will ask to scan it, so easy right ?", R.drawable.ic_ticket));
        addWelcomeItemList(new PassengerCards(HISTORY_ITEM,
                "History",
                "A History for all you need, track your trips, tickets and your receipts all the time", R.drawable.ic_history));
    }

    public static List<String> getContentDescription() {
        return contentDescription;
    }

    public static List<Integer> getContentResources() {
        return contentResources;
    }

    // is a list that holding the image resources for the page in welcome screens
    private static final List<Integer> contentResources;

    // initialize static list variables
    static {
        contentDescription = new ArrayList<>();
        contentResources = new ArrayList<>();

        addItems("Welcome to train ticket reservation application, where you can reserve electronic ticket, enquire trips, services and more", R.drawable.app_logo);
        addItems("Enquire for any trip with your phone, no need to go the station or go on phone call and waste your time", R.drawable.ic_enquire);
        addItems("Keep your ticket safe, the application will keep your tickets safe on your phone", R.drawable.ic_ticket);
        addItems("Don't hold cash anymore, no need to hold cash and get robbed or lose your money, charge your wallet on the app and enjoy our services, and track all your receipts", R.drawable.ic_wallet);
        addItems("Now validation your ticket is so easy, just let the validator scan your ticket and that's it", R.drawable.ic_validation);
    }

    private StaticDataSource() {
        // to prevent from instantiating instance from this class
    }

    /**
     * this method will add items in the list of contentDescription and contentResources
     *
     * @param desc       is the text item to add in the WelcomeContent
     * @param resourceId is the image resource item to add in the WelcomeContent
     */
    private static void addItems(String desc, int resourceId) {
        contentDescription.add(desc);
        contentResources.add(resourceId);
    }

    public static List<PassengerCards> getPassengerCardsList() {
        return passengerCardsList;
    }

    /**
     * will add items of {@link PassengerCards} into the static list
     * to show up in the adapter of the {@link com.hadi.trainticketing.passenger.view.activities.PassengerMainActivity}
     */
    private static void addWelcomeItemList(PassengerCards passengerCards) {
        passengerCardsList.add(passengerCards);
    }

}
