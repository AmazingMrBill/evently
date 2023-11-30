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
        eventModelList.add(new EventModel("21", "Dec", "Fashion Expo", "Kite Mall", "576", R.drawable.party));
        eventModelList.add(new EventModel("03", "Feb", "Youth Ted Talk", "GMC hall", "204", R.drawable.fireworks));
        eventModelList.add(new EventModel("23", "Nov", "Environmental Program", "Umma University", "400", R.drawable.partyj));
        eventModelList.add(new EventModel("16", "Nov", "Fenty Beauty Launch", "Enchola", "304", R.drawable.party));
        eventModelList.add(new EventModel("21", "Dec", "Fashion Expo", "Kite Mall", "576", R.drawable.party));
        eventModelList.add(new EventModel("03", "Feb", "Youth Ted Talk", "GMC hall", "204", R.drawable.fireworks));
        eventModelList.add(new EventModel("23", "Nov", "Environmental Program", "Umma University", "400", R.drawable.partyj));
        eventModelList.add(new EventModel("16", "Nov", "Fenty Beauty Launch", "Enchola", "304", R.drawable.party));

        // Initialize SearchModel list (you can populate this with relevant data)
        searchModelList.add(new SearchModel("Mon,", " 2:00 PM", "","22 Dec • ", "Umma University", "Codding Night", R.drawable.party));
        searchModelList.add(new SearchModel("Mon,", " 2:00 PM", "","22 Dec •", "Codding Night", "Umma University", R.drawable.partyj));
        searchModelList.add(new SearchModel("Tue,", " 3:30 PM","", "23 Dec •", "Tech Meetup", "Tech Hub", R.drawable.party));
        searchModelList.add(new SearchModel("Wed,", " 5:00 PM","", "24 Dec •", "Business Networking", "Downtown Business Center", R.drawable.party));
        searchModelList.add(new SearchModel("Thu,", " 6:30 PM", "","25 Dec •", "Food Festival", "City Park", R.drawable.party));
        searchModelList.add(new SearchModel("Mon,", " 2:00 PM", "","22 Dec • ", "Umma University", "Codding Night", R.drawable.party));
        searchModelList.add(new SearchModel("Mon,", " 2:00 PM", "","22 Dec •", "Codding Night", "Umma University", R.drawable.partyj));
        searchModelList.add(new SearchModel("Tue,", " 3:30 PM","", "23 Dec •", "Tech Meetup", "Tech Hub", R.drawable.party));
        searchModelList.add(new SearchModel("Wed,", " 5:00 PM","", "24 Dec •", "Business Networking", "Downtown Business Center", R.drawable.party));
        searchModelList.add(new SearchModel("Thu,", " 6:30 PM", "","25 Dec •", "Food Festival", "City Park", R.drawable.party));



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
