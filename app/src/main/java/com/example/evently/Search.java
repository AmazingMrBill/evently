package com.example.evently;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;


import com.example.evently.Adapters.EventsRecyclerViewAdapter;
import com.example.evently.Adapters.SearchAdapter;
import com.example.evently.Models.SearchModel;
import com.example.evently.Repository.EventsRepo;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Search extends Fragment {
    RecyclerView recyclerView;
    SearchAdapter searchAdapter;

    SearchView searchView;

    public Search() {

        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);


        recyclerView = view.findViewById(R.id.recyclerViewUpcomingEvents);
        searchView = view.findViewById(R.id.searchView);

        initRecyclerView();

        setupSearchView();

        return view;
    }

    void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        searchAdapter = new SearchAdapter(getSearchAdapterOptions()); // Use SearchAdapter
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(searchAdapter); // Use SearchAdapter
    }

    private void setupSearchView() {
        // Set a query listener for the SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle the query submission if needed
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the RecyclerView based on the query
                searchAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    private FirebaseRecyclerOptions<SearchModel> getSearchAdapterOptions() {
        // Set up FirebaseRecyclerOptions for SearchAdapter
        FirebaseRecyclerOptions<SearchModel> options =
                new FirebaseRecyclerOptions.Builder<SearchModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("events"), SearchModel.class)
                        .build();
        return options;
    }
}