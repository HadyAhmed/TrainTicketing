package com.hadi.trainticketing.passenger.model;

public class PassengerCards {
    private String header;
    private String body;
    private int itemPosition;


    public PassengerCards(int itemPosition, String header, String body) {
        this.header = header;
        this.body = body;
        this.itemPosition = itemPosition;
    }

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }

    public int getItemPosition() {
        return itemPosition;
    }
}
