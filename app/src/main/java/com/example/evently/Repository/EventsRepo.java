package com.example.evently.Repository;

import com.example.evently.Models.EventModel;
import com.example.evently.R;

import java.util.ArrayList;
import java.util.List;

public class EventsRepo {
    private static EventsRepo eventsRepo;
   private ArrayList<EventModel> eventModelList = new ArrayList<>();
    public EventsRepo(){
        eventModelList.add(new EventModel("21","Dec","Fashion Expo", "Kite Mall","576", R.drawable.party));
        eventModelList.add(new EventModel("03","Feb","Youth Ted Talk","GMC hall","204",R.drawable.fireworks));
        eventModelList.add(new EventModel("23","Nov","Environmental Program","Umma University","400",R.drawable.party));
        eventModelList.add(new EventModel("16","Nov","Fenty Beauty Launch","Enchola","304",R.drawable.party));
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
