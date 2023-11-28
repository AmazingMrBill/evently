package com.example.evently;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class user_profile_page extends Fragment {

    FirebaseAuth auth;
    Button button;
    FirebaseUser user;
    TextView textView;

    public user_profile_page() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile_page, container, false);

        Button btnManage_Create_event = view.findViewById(R.id.manageEventButton);

        auth = FirebaseAuth.getInstance();
        button = view.findViewById(R.id.signOutButton);
        user = auth.getCurrentUser();
        textView = view.findViewById(R.id.emailAddress);

        if (user == null) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        } else {
            // Displaying user's email
            textView.setText(user.getEmail());

            // Fetch and display user's name
            String userName = user.getDisplayName();
            if (userName != null && !userName.isEmpty()) {
                TextView usernameTextView = view.findViewById(R.id.username);
                usernameTextView.setText(userName);
            }
        }

        btnManage_Create_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.manage_create_event_page);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}