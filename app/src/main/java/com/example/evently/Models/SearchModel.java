package com.example.evently.Models;

public class SearchModel {
    private String day;
    private String time;
    private String title;
    private String date;
    private String location;

    private String name;
    private int image;
    public SearchModel(String day, String time, String title,String date,String location, String name, int image)
    {
        this.day=day;
        this.time=time;
        this.title=title;
        this.date=date;
        this.location=location;
        this.image=image;
        this.name =name;

    }

    public String getDay()
    {
        return day;
    }
    public String getTime()
    {
        return time;
    }
    public String getDate()
    {
        return date;
    }
    public String getLocation()
    {
        return location;
    }
    public int getImage() { return image;}

    public String getName() { return name;}
}
