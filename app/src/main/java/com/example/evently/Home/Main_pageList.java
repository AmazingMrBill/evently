package com.example.evently.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.evently.Adapters.EventsRecyclerViewAdapter;
import com.example.evently.R;
import com.example.evently.Repository.EventsRepo;

public class Main_pageList extends Fragment {

    RecyclerView recyclerView;
    EventsRecyclerViewAdapter eventsRecyclerViewAdapter;
    public Main_pageList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_page_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        initRecyclerView();

        return view;
    }

    void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext()); // Adjust this based on your needs
        eventsRecyclerViewAdapter = new EventsRecyclerViewAdapter(EventsRepo.getEventsRepo().getEventModelList());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(eventsRecyclerViewAdapter);
    }
}
