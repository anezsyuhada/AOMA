package com.example.aoma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FaqActivity extends AppCompatActivity {

    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        btnBack = findViewById(R.id.buttonBackFAQ);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FaqActivity.this, ProfileActivity.class));
            }
        });

        // Get references to the FAQ items
        LinearLayout faqItem1 = findViewById(R.id.faqItem1);
        LinearLayout faqItem2 = findViewById(R.id.faqItem2);
        LinearLayout faqItem3 = findViewById(R.id.faqItem3);
        LinearLayout faqItem4 = findViewById(R.id.faqItem4);

        // Set click listeners to toggle visibility of answers
        faqItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleAnswerVisibility(findViewById(R.id.textViewAnswer1));
            }
        });

        faqItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleAnswerVisibility(findViewById(R.id.textViewAnswer2));
            }
        });

        faqItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleAnswerVisibility(findViewById(R.id.textViewAnswer3));
            }
        });

        faqItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleAnswerVisibility(findViewById(R.id.textViewAnswer4));
            }
        });
    }

    // Method to toggle visibility of answer TextView
    private void toggleAnswerVisibility(TextView answerTextView) {
        if (answerTextView.getVisibility() == View.VISIBLE) {
            answerTextView.setVisibility(View.GONE);
        } else {
            answerTextView.setVisibility(View.VISIBLE);
        }
    }
}
