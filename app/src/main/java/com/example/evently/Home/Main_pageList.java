package com.example.evently.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.evently.Adapters.EventAdapter;
import com.example.evently.Adapters.EventsRecyclerViewAdapter;
import com.example.evently.Models.EventModel;
import com.example.evently.R;
import com.example.evently.Repository.EventsRepo;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Main_pageList extends Fragment {

    RecyclerView recyclerView;
    EventAdapter eventAdapter;

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

        eventAdapter.setOnItemClickListener(new EventAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle item click, e.g., navigate to the view_event fragment
                Navigation.findNavController(requireView()).navigate(R.id.action_main_pageList_to_view_event);
            }
        });

        return view;
    }

    void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext()); // Adjust this based on your needs

        FirebaseRecyclerOptions<EventModel> options =
                new FirebaseRecyclerOptions.Builder<EventModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("events"), EventModel.class)
                        .build();

        eventAdapter = new EventAdapter(options);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(eventAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        eventAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        eventAdapter.stopListening();
    }
}