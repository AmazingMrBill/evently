package com.example.evently.EventManager;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.evently.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Create_Event extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri;

    public Create_Event() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create__event, container, false);

        Button btnChooseImage = view.findViewById(R.id.btnChooseImage);
        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch the gallery picker
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
            }
        });

        Button btnSaveEvent = view.findViewById(R.id.btnSaveEvent);
        btnSaveEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if an image is selected
                if (selectedImageUri != null) {
                    // Get event details from UI components
                    String eventName = ((EditText) view.findViewById(R.id.editTextEventName)).getText().toString();
                    String eventDate = ((EditText) view.findViewById(R.id.editTextEventDate)).getText().toString();
                    String eventTime = ((EditText) view.findViewById(R.id.editTextEventTime)).getText().toString();
                    String eventLocation = ((EditText) view.findViewById(R.id.editTextEventLocation)).getText().toString();
                    String eventDescription = ((EditText) view.findViewById(R.id.editTextEventDescription)).getText().toString();
                    String eventMonth = ((EditText) view.findViewById(R.id.editTextEventMonth)).getText().toString();
                    String eventCount = ((EditText) view.findViewById(R.id.editTextEventCount)).getText().toString();
                    String eventDay = ((EditText) view.findViewById(R.id.editTextEventDay)).getText().toString();
                    double eventPrice = Double.parseDouble(((EditText) view.findViewById(R.id.editTextEventPrice)).getText().toString());

                    // Validate other fields as needed

                    // Save event data to Firestore and Storage
                    saveEventDataToFirestore(eventName, eventDate, eventTime,eventCount, eventLocation, eventDescription, eventMonth, eventPrice, eventDay,selectedImageUri);
                } else {
                    // Inform the user that an image must be selected
                    Toast.makeText(getActivity(), "Please choose an image first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();

            // Update an ImageView with the selected image if needed
            ImageView eventImageView = getView().findViewById(R.id.eventImageView);
            eventImageView.setImageURI(selectedImageUri);
        }
    }

    private void saveEventDataToFirestore(String eventName,String eventDate,String eventCount, String eventTime, String eventLocation, String eventDescription,String eventDay, double eventPrice,String eventMonth, Uri imageUri) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String userId = user.getUid();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Reference to the Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference eventsRef = database.getReference("events");

        String eventId = eventsRef.push().getKey();

        Map<String, Object> eventData = new HashMap<>();
        eventData.put("eventName", eventName);
        eventData.put("eventDate", eventDate);
        eventData.put("eventMonth", eventMonth);
        eventData.put("eventCount", eventCount);
        eventData.put("eventTime", eventTime);
        eventData.put("eventLocation", eventLocation);
        eventData.put("eventDescription", eventDescription);
        eventData.put("eventDay", eventDay);
        eventData.put("eventPrice", String.valueOf(eventPrice));
        eventData.put("userId", userId); // Link event to the user who created it

        // Add the event data to the Realtime Database
        eventsRef.child(eventId)
                .setValue(eventData)
                .addOnSuccessListener(aVoid -> {
                    // Show a Toast message
                    Toast.makeText(getActivity(), "Event created", Toast.LENGTH_SHORT).show();

                    // Upload image to Firebase Storage
                    uploadImageToStorage(imageUri, new OnImageUploadListener() {
                        @Override
                        public void onImageUploadSuccess(String imageUrl) {
                            // Image uploaded successfully, now save event data to Realtime Database with the image URL
                            eventData.put("imageUrl", imageUrl);
                            updateEventDataInRealtimeDatabase(eventId, eventData);

                            // Navigate back to manage_create_event_page
                            NavHostFragment.findNavController(Create_Event.this)
                                    .navigate(R.id.action_create_Event_to_manage_create_event_page);
                        }

                        @Override
                        public void onImageUploadFailure(Exception e) {
                            // Handle image upload failure
                            Log.w(TAG, "Error uploading image to Firebase Storage", e);
                        }
                    });
                })
                .addOnFailureListener(e -> {
                    Log.w(TAG, "Error adding event to Realtime Database", e);
                });
    }

    private void uploadImageToStorage(Uri imageUri, OnImageUploadListener listener) {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("event_images/" + UUID.randomUUID().toString());
        storageRef.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        String imageUrl = uri.toString();
                        listener.onImageUploadSuccess(imageUrl);
                    });
                })
                .addOnFailureListener(e -> listener.onImageUploadFailure(e));
    }
    private void updateEventDataInRealtimeDatabase(String eventId, Map<String, Object> eventData) {
        // Reference to the Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference eventsRef = database.getReference("events");

        // Update the event data in the Realtime Database
        eventsRef.child(eventId)
                .updateChildren(eventData)
                .addOnSuccessListener(aVoid -> {
                    // Navigate back to manage_create_event_page
                    NavHostFragment.findNavController(Create_Event.this)
                            .navigate(R.id.action_create_Event_to_manage_create_event_page);
                })
                .addOnFailureListener(e -> {
                    Log.w(TAG, "Error updating event data in Realtime Database", e);
                });
    }


    // Interface to listen for image upload events
    private interface OnImageUploadListener {
        void onImageUploadSuccess(String imageUrl);

        void onImageUploadFailure(Exception e);
    }
}
