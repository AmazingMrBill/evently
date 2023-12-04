package com.example.evently.Models;

public class MainModel {

    String eventName, eventLocation, eventTime, eventDate, EventPrice, imageUrl;

    MainModel()
    {

    }
    public MainModel(String eventName, String eventLocation, String eventTime, String eventDate, String eventPrice, String imageUrl, String userId) {
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.eventTime = eventTime;
        this.eventDate = eventDate;
        EventPrice = eventPrice;
        this.imageUrl = imageUrl;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventPrice() {
        return EventPrice;
    }

    public void setEventPrice(String eventPrice) {
        EventPrice = eventPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
