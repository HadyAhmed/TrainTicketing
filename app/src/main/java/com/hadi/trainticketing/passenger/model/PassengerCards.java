package com.hadi.trainticketing.passenger.model;

public class PassengerCards {
    private String header;
    private String body;
    private int itemPosition, imageResource;

    public PassengerCards(int itemPosition, String header, String body, int imageResource) {
        this.header = header;
        this.body = body;
        this.itemPosition = itemPosition;
        this.imageResource = imageResource;
    }

    public int getImageResource() {
        return imageResource;
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
