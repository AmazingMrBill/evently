package com.example.evently.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.evently.R;


public class purchase_ticket extends Fragment {

    public purchase_ticket() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_purchase_ticket, container, false);

        Button btnProceedToPayment = view.findViewById(R.id.proceedToPayment);
        ImageView backToEvent = view.findViewById(R.id.backToTickets);


        btnProceedToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Event Purchased!", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).navigate(R.id.main_pageList);
            }

        });

        backToEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the main_page list fragment
                Navigation.findNavController(view).navigate(R.id.view_ticket);
            }
        });
        return view;
}
}