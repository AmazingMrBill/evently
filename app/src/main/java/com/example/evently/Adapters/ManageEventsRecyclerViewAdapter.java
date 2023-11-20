package com.example.evently.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.evently.Models.EventModel;
import com.example.evently.R;

import java.util.ArrayList;

public class ManageEventsRecyclerViewAdapter extends RecyclerView.Adapter<ManageEventsRecyclerViewAdapter.ViewHolder> {
    private ArrayList<EventModel> ongoingEventsList;
    private ArrayList<EventModel> recentEventsList;

    public ManageEventsRecyclerViewAdapter(ArrayList<EventModel> ongoingEventsList, ArrayList<EventModel> recentEventsList) {
        this.ongoingEventsList = ongoingEventsList;
        this.recentEventsList = recentEventsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Check if it's ongoing or recent event, and bind data accordingly
        if (position < ongoingEventsList.size()) {
            EventModel ongoingEvent = ongoingEventsList.get(position);
            holder.bindEventData(ongoingEvent);
        } else {
            EventModel recentEvent = recentEventsList.get(position - ongoingEventsList.size());
            holder.bindEventData(recentEvent);
        }
    }

    @Override
    public int getItemCount() {
        return ongoingEventsList.size() + recentEventsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView eventNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventNameTextView = itemView.findViewById(R.id.eventNameTextView);
        }

        public void bindEventData(EventModel event) {
            eventNameTextView.setText(event.getTitle());
            // Add other views for displaying event details if needed
        }
    }
}
