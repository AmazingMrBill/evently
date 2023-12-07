package com.example.evently.Models;

public class EventModel {
    String eventDate;
    String eventMonth;
    String eventName;
    String eventLocation;
    String eventCount;
    String imageUrl;

    // Default constructor
    public EventModel() {
        // Default constructor required for Firebase
    }


    public EventModel(String eventDate, String eventMonth, String eventName, String eventLocation, String count, String imageUrl)
    {
        this.eventDate =eventDate;
        this.eventMonth =eventMonth;
        this.eventName = eventName;
        this.eventLocation =eventLocation;
        this.eventCount =count;
        this.imageUrl = imageUrl;
    }



    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventMonth() {
        return eventMonth;
    }

    public void setEventMonth(String eventMonth) {
        this.eventMonth = eventMonth;
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

    public String getEventCount() {
        return eventCount;
    }

    public void setEventCount(String eventCount) {
        this.eventCount = eventCount;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
