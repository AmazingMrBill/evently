package com.example.evently.Models;

public class EventModel {
    private String day;
    private String month;
    private String title;
    private String place;
    private String count;
    private int image;
    public EventModel(String day, String month, String title,String place,String count, int image)
    {
        this.day=day;
        this.month=month;
        this.title=title;
        this.place=place;
        this.count=count;
        this.image=image;

    }

    public String getDay()
    {
        return day;
    }
    public String getMonth()
    {
        return month;
    }
    public String getTitle()
    {
        return title;
    }
    public String getPlace()
    {
        return place;
    }
    public String getCount()
    {
        return count;
    }
    public int getImage() { return image;}
}
