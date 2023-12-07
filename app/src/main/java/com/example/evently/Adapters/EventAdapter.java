package com.example.evently.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.evently.Models.EventModel;
import com.example.evently.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class EventAdapter extends FirebaseRecyclerAdapter<EventModel, EventAdapter.EventViewHolder> {

    private OnItemClickListener onItemClickListener;

    public EventAdapter(@NonNull FirebaseRecyclerOptions<EventModel> options) {
        super(options);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_card, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull EventViewHolder holder, int position, @NonNull EventModel model) {
        holder.evenDate.setText(model.getEventDate());
        holder.eventMonth.setText(model.getEventMonth());
        holder.eventName.setText(model.getEventName());
        holder.eventLocation.setText(model.getEventLocation());
        holder.eventCount.setText(model.getEventCount());

        // Set image using Glide or any other image loading library
        Glide.with(holder.imageUrl.getContext())
                .load(model.getImageUrl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.imageUrl);
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {

        ImageView imageUrl;
        TextView evenDate, eventMonth, eventName, eventLocation, eventCount;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            imageUrl = itemView.findViewById(R.id.card_image);
            evenDate = itemView.findViewById(R.id.day);
            eventMonth = itemView.findViewById(R.id.month);
            eventName = itemView.findViewById(R.id.eventTitle);
            eventLocation = itemView.findViewById(R.id.location);
            eventCount = itemView.findViewById(R.id.count);
        }
    }
}