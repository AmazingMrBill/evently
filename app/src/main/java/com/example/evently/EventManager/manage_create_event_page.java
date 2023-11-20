package com.example.evently.EventManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.evently.R;

public class manage_create_event_page extends Fragment {

    public manage_create_event_page() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manage_create_event_page, container, false);

        Button btnCreateEvent = view.findViewById(R.id.btnCreateEvent);
        Button btnManageEvent = view.findViewById(R.id.btnManageEvents);
        Button btnEventAnalytics = view.findViewById(R.id.btnEventAnalytics);

        btnCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.create_Event);
            }
        });

        btnManageEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.navigation);
            }
        });

        btnEventAnalytics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.event_Analytics);
            }
        });

        return view;
    }
}
