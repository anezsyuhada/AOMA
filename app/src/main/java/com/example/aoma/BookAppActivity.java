package com.example.aoma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BookAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_app);

        CardView exit = findViewById(R.id.cardFDBack);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppActivity.this,HomeActivity.class));
            }
        });

        CardView scalling = findViewById(R.id.cardFDScallingAndPolishing);
        scalling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(BookAppActivity.this,ScallingActivity.class);
                it.putExtra("title", "Scalling and Polishing");
                startActivity(it);
            }
        });

        CardView dentures = findViewById(R.id.cardFDDentures);
        dentures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(BookAppActivity.this,ScallingActivity.class);
                it.putExtra("title", "Dentures");
                startActivity(it);
            }
        });

        CardView root = findViewById(R.id.cardFDRootCanal);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(BookAppActivity.this,ScallingActivity.class);
                it.putExtra("title", "Root Canal Treatment");
                startActivity(it);
            }
        });

        CardView braces = findViewById(R.id.cardFDBraces);
        braces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(BookAppActivity.this,ScallingActivity.class);
                it.putExtra("title", "Braces");
                startActivity(it);
            }
        });

        CardView wisdom = findViewById(R.id.cardFDWisdomTooth);
        wisdom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(BookAppActivity.this,ScallingActivity.class);
                it.putExtra("title", "Wisdom Tooth");
                startActivity(it);
            }
        });
    }
}