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

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> implements Filterable {
    private List<SearchModel> searchModelList;
    private List<SearchModel> searchModelListFull;

    public SearchAdapter(List<SearchModel> searchModelList) {
        this.searchModelList = searchModelList;
        this.searchModelListFull = new ArrayList<>(searchModelList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchModel searchModel = searchModelList.get(position);
        holder.day.setText(searchModel.getDay());
        holder.date.setText(searchModel.getDate());
        holder.time.setText(searchModel.getTime());
        holder.name.setText(searchModel.getName());
        holder.location.setText(searchModel.getLocation());
        Glide.with(holder.imageView).load(searchModel.getImage()).into(holder.imageView);
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
                    if (item.getName().toLowerCase().contains(filterPattern) ||
                            item.getLocation().toLowerCase().contains(filterPattern)) {
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

    class ViewHolder extends RecyclerView.ViewHolder {
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
