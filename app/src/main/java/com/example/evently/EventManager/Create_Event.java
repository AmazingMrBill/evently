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
                    double eventPrice = Double.parseDouble(((EditText) view.findViewById(R.id.editTextEventPrice)).getText().toString());

                    // Validate other fields as needed

                    // Save event data to Firestore and Storage
                    saveEventDataToFirestore(eventName, eventDate, eventTime, eventLocation, eventDescription, eventPrice, selectedImageUri);
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

    private void saveEventDataToFirestore(String eventName, String eventDate, String eventTime, String eventLocation, String eventDescription, double eventPrice, Uri imageUri) {
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

        db.collection("events")
                .add(eventData)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // Show a Toast message
                        Toast.makeText(getActivity(), "Event created", Toast.LENGTH_SHORT).show();

                        // Upload image to Firebase Storage
                        uploadImageToStorage(imageUri, new OnImageUploadListener() {
                            @Override
                            public void onImageUploadSuccess(String imageUrl) {
                                // Image uploaded successfully, now save event data to Firestore with the image URL
                                eventData.put("imageUrl", imageUrl);
                                updateEventDataInFirestore(documentReference.getId(), eventData);

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
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding event to Firestore", e);
                    }
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

    private void updateEventDataInFirestore(String eventId, Map<String, Object> eventData) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("events")
                .document(eventId)
                .set(eventData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Navigate back to manage_create_event_page
                        NavHostFragment.findNavController(Create_Event.this)
                                .navigate(R.id.action_create_Event_to_manage_create_event_page);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating event data in Firestore", e);
                    }
                });
    }

    // Interface to listen for image upload events
    private interface OnImageUploadListener {
        void onImageUploadSuccess(String imageUrl);

        void onImageUploadFailure(Exception e);
    }
}
