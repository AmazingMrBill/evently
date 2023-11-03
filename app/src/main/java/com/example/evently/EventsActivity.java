package com.example.evently;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.evently.Adapters.EventsRecyclerViewAdapter;
import com.example.evently.Repository.EventsRepo;

public class EventsActivity extends AppCompatActivity {
RecyclerView recyclerView;
EventsRecyclerViewAdapter eventsRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_page);
recyclerView=findViewById(R.id.recyclerView);

    }

    void intRecyclerView(){

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        eventsRecyclerViewAdapter=new EventsRecyclerViewAdapter(EventsRepo.getEventsRepo().getEventModelList());
        recyclerView.setAdapter(eventsRecyclerViewAdapter);
    }
}