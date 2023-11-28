package com.example.evently.EventManager;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.evently.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Create_Event extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView eventImageView;
    private Bitmap selectedImageBitmap;
    public Create_Event() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create__event, container, false);

        Button btnChooseImage = view.findViewById(R.id.btnChooseImage);
        Button btnSaveEvent = view.findViewById(R.id.btnSaveEvent);
        ImageView eventImageView = view.findViewById(R.id.eventImageView);

        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open gallery to choose an image
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        btnSaveEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get event details from UI components
                String eventName = ((EditText) view.findViewById(R.id.editTextEventName)).getText().toString();
                String eventDate = ((EditText) view.findViewById(R.id.editTextEventDate)).getText().toString();
                String eventTime = ((EditText) view.findViewById(R.id.editTextEventTime)).getText().toString();
                String eventLocation = ((EditText) view.findViewById(R.id.editTextEventLocation)).getText().toString();
                String eventDescription = ((EditText) view.findViewById(R.id.editTextEventDescription)).getText().toString();
                double eventPrice = Double.parseDouble(((EditText) view.findViewById(R.id.editTextEventPrice)).getText().toString());

                // Validate other fields as needed

                // Save event data to Firestore
                saveEventDataToFirestore(eventName, eventDate, eventTime, eventLocation, eventDescription, eventPrice);
            }
        });

        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            // Set the chosen image to the ImageView
            try {
                selectedImageBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                eventImageView.setImageBitmap(selectedImageBitmap);

                // Upload the image to Firebase Storage
                uploadImageToStorage(data.getData());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    private void saveEventDataToFirestore(String eventName, String eventDate, String eventTime, String eventLocation, String eventDescription, double eventPrice) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String userId = user.getUid();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> eventData = new HashMap<>();
        eventData.put("eventName", eventName);
        eventData.put("eventDate", eventDate);
        eventData.put("eventTime", eventTime);
        eventData.put("eventLocation", eventLocation);
        eventData.put("eventDescription", eventDescription);
        eventData.put("eventPrice", eventPrice);
        eventData.put("userId", userId); // Link event to the user who created it


        // Convert the image to a byte array
        if (selectedImageBitmap != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            selectedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageData = baos.toByteArray();

            // Add the image data to the eventData
            eventData.put("image", Base64.encodeToString(imageData, Base64.DEFAULT));
        }

        db.collection("events")
                .add(eventData)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "Event added to Firestore with ID: " + documentReference.getId());
                        getActivity().onBackPressed();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding event to Firestore", e);
                    }
                });
    }
}