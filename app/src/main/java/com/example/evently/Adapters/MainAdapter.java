package com.example.evently.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.evently.Models.MainModel;
import com.example.evently.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel,MainAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull MainModel model) {
        holder.eventName.setText(model.getEventName());
        holder.eventDate.setText(model.getEventDate());
        holder.eventTime.setText(model.getEventTime());
        holder.eventLocation.setText(model.getEventLocation());
        holder.eventPrice.setText(model.getEventPrice());

        Glide.with(holder.imageView.getContext())
                .load(model.getImageUrl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.imageView);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.createitem_event,parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView eventName, eventLocation, eventTime, eventDate, eventPrice;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.eventImageM);
            eventName = (TextView) itemView.findViewById(R.id.eventNameM);
            eventLocation = (TextView) itemView.findViewById(R.id.event_locationM);
            eventTime = (TextView) itemView.findViewById(R.id.eventTimeM);
            eventDate = (TextView) itemView.findViewById(R.id.eventDateM);
            eventPrice = (TextView) itemView.findViewById(R.id.eventPriceM);

        }
    }
}
