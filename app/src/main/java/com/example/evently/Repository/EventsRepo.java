package com.example.evently.Repository;

import com.example.evently.Models.EventModel;
import com.example.evently.Models.SearchModel;
import com.example.evently.R;

import java.util.ArrayList;
import java.util.List;

public class EventsRepo {
    private static EventsRepo eventsRepo;
    private ArrayList<EventModel> eventModelList = new ArrayList<>();
    private ArrayList<SearchModel> searchModelList = new ArrayList<>(); // Added SearchModel list

    public EventsRepo() {
        // Initialize EventModel list
        eventModelList.add(new EventModel("21", "Dec", "Fashion Expo", "Kite Mall", "576", "s"));


        // Initialize SearchModel list (you can populate this with relevant data)
   searchModelList.add(new SearchModel("A","S","S","S","A","A","2"));



    }

    public static EventsRepo getEventsRepo() {
        if (eventsRepo == null) {
            eventsRepo = new EventsRepo();
        }
        return eventsRepo;
    }

    public ArrayList<EventModel> getEventModelList() {
        return eventModelList;
    }

    public ArrayList<SearchModel> getSearchModelList() {
        return searchModelList;
    }
}
