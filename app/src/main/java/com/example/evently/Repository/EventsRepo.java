package com.example.evently.Repository;

import com.example.evently.Models.EventModel;

import java.util.ArrayList;
import java.util.List;

public class EventsRepo {
    private static EventsRepo eventsRepo;
   private ArrayList<EventModel> eventModelList = new ArrayList<>();
    public EventsRepo(){
eventModelList.add(new EventModel("21","Dec","Fashion Expo", "Kite Mall","576","https://cloudfront-us-east-1.images.arcpublishing.com/archive.tronc/HG6SGIBCP5CINMI6AYSNQ5ZD7I.jpg"));
eventModelList.add(new EventModel("03","Feb","Youth Ted Talk","GMC hall","204","https://images.pexels.com/photos/534271/pexels-photo-534271.jpeg?auto=compress&cs=tinysrgb&w=600"));
eventModelList.add(new EventModel("23","Nov","Environmental Program","Umma University","400","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQvTGBKKDM4bVQcU_R7GDc0pnUZLwHIc6lPrw&usqp=CAU"));
eventModelList.add(new EventModel("16","Nov","Fenty Beauty Launch","Enchola","304","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR-on-OXnoJmfH0DBD1hzq8qKcf0DjfUyiS9Q&usqp=CAU"));
    }
    public static EventsRepo getEventsRepo(){

        if(eventsRepo == null)
        {
        eventsRepo=new EventsRepo();
        }

        return eventsRepo;
    }

    public ArrayList<EventModel> getEventModelList() {
        return eventModelList;
    }
}
