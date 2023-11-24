package com.example.evently.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.evently.R;

public class view_ticket extends Fragment {

    public view_ticket() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_ticket, container, false);

        Button btnPurchaseTicket = view.findViewById(R.id.btnpurchase);
        ImageView backToEvent = view.findViewById(R.id.backToEvent);

        btnPurchaseTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.purchase_ticket);
            }
        });

        backToEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the main_page list fragment
                Navigation.findNavController(view).navigate(R.id.view_event);
            }
        });

        // Ensure you are using the correct IDs for the include tags
        View earlyBirdTicket = view.findViewById(R.id.earlyBirdTicket);
        View secondReleaseTicket = view.findViewById(R.id.secondReleaseTicket);
        View lateComersTicket = view.findViewById(R.id.lateComersTicket);
        View vipTicket = view.findViewById(R.id.vipTicket);

        // Set data for included layouts
        setTicketData(earlyBirdTicket, "Early Bird", "$20.00");
        setTicketData(secondReleaseTicket, "Second Release", "$25.00");
        setTicketData(lateComersTicket, "Late Comers", "$30.00");
        setTicketData(vipTicket, "VIP Ticket", "$50.00");

        return view;
    }

    private void setTicketData(View ticketView, String title, String price) {
        // Find the TextViews in the included layout
        TextView ticketTitle = ticketView.findViewById(R.id.ticketTitle);
        TextView ticketPrice = ticketView.findViewById(R.id.ticketPrice);

        // Set data
        ticketTitle.setText(title);
        ticketPrice.setText(price);
    }
}
