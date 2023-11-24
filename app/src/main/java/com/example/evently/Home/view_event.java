package com.example.evently.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.evently.R;


public class view_event extends Fragment {

    public view_event() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_event, container, false);

        Button btnViewTicket = view.findViewById(R.id.joinBtn);
        ImageView backToHome = view.findViewById(R.id.backToHome);


        btnViewTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.view_ticket);
            }

        });

        // Set OnClickListener for backToHome ImageView
        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the main_page list fragment
                Navigation.findNavController(view).navigate(R.id.main_pageList);
            }
        });

        return view;
    }
}