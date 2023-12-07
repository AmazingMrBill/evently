package com.example.evently.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.evently.Models.SearchModel;
import com.example.evently.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends FirebaseRecyclerAdapter<SearchModel, SearchAdapter.ViewHolder> implements Filterable {
    private List<SearchModel> searchModelList;
    private List<SearchModel> searchModelListFull;

    public SearchAdapter(@NonNull FirebaseRecyclerOptions<SearchModel> options) {
        super(options);
        this.searchModelList = new ArrayList<>();
        this.searchModelListFull = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull SearchModel model) {
        holder.day.setText(model.getEventDay());
        holder.date.setText(model.getEventDate());
        holder.time.setText(model.getEventTime());
        holder.name.setText(model.getEventName());
        holder.location.setText(model.getEventLocation());

        Glide.with(holder.imageView.getContext())
                .load(model.getImageUrl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return searchModelList.size();
    }

    @Override
    public Filter getFilter() {
        return searchFilter;
    }

    private final Filter searchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<SearchModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(searchModelListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (SearchModel item : searchModelListFull) {
                    if (item.getEventName().toLowerCase().contains(filterPattern) ||
                            item.getEventLocation().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            searchModelList.clear();
            searchModelList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView day, date, time, name, location;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.event_day);
            date = itemView.findViewById(R.id.eventDate);
            time = itemView.findViewById(R.id.eventTime);
            name = itemView.findViewById(R.id.eventName);
            location = itemView.findViewById(R.id.event_location);
            imageView = itemView.findViewById(R.id.eventImage);
        }
    }
}
