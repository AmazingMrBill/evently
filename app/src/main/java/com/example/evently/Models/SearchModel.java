package com.example.evently.Models;

public class SearchModel {
    String eventDay;
    String eventTime;
    String eventName;
    String eventDate;
    String eventLocation;

    String eventMonth;


    String imageUrl;

    public SearchModel() {
        // Default constructor required for Firebase
    }

    public SearchModel(String day, String time, String title, String date, String location, String eventMonth, String imageUrl)
    {
        this.eventDay =day;
        this.eventTime =time;
        this.eventName =title;
        this.eventDate =date;
        this.eventLocation =location;
        this.eventMonth = eventMonth;
        this.imageUrl = imageUrl;

    }


    public String getEventDay() {
        return eventDay;
    }

    public void setEventDay(String eventDay) {
        this.eventDay = eventDay;
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
    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
