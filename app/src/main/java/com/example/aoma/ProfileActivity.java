package com.example.aoma;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ProfileActivity extends AppCompatActivity {

    // Declare variables
    TextView username;
    Button changePictureBtn, faqBtn, contactBtn;
    ImageView profileImageView;
    private static final int PICK_IMAGE = 1;
    private static final String PROFILE_IMAGE_FILE_NAME = "profile_image.jpg"; // File name for the profile image

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize views
        username = findViewById(R.id.textViewUsername);
        changePictureBtn = findViewById(R.id.btnChangePicture);
        profileImageView = findViewById(R.id.profileImageView);
        faqBtn = findViewById(R.id.btnFAQ);
        contactBtn = findViewById(R.id.btnContactUs);

        // Retrieve logged-in user's information from SharedPreferences
        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String loggedInUsername = sharedpreferences.getString("username", "");

        // Set username and email to TextViews
        username.setText(loggedInUsername);

        // Load and set the profile image if it exists
        loadProfileImage();

        // Handle changing profile picture button click
        changePictureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open gallery to select an image
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, PICK_IMAGE);
            }
        });

        // Handle changing profile picture button click
        faqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, FaqActivity.class));
            }
        });

        // Handle changing profile picture button click
        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, ContactActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            try {
                // Decode the selected image URI into Bitmap
                InputStream imageStream = getContentResolver().openInputStream(imageUri);
                Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                // Set the selected image to the ImageView
                profileImageView.setImageBitmap(selectedImage);

                // Save the selected image to internal storage
                saveProfileImage(selectedImage);

                // Show a toast indicating successful profile picture change
                Toast.makeText(this, "Profile picture updated successfully!", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Method to save the profile image to internal storage
    private void saveProfileImage(Bitmap imageBitmap) {
        try {
            FileOutputStream fos = openFileOutput(PROFILE_IMAGE_FILE_NAME, Context.MODE_PRIVATE);
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load the profile image from internal storage
    private void loadProfileImage() {
        try {
            FileInputStream fis = openFileInput(PROFILE_IMAGE_FILE_NAME);
            Bitmap profileImage = BitmapFactory.decodeStream(fis);
            fis.close();
            profileImageView.setImageBitmap(profileImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
