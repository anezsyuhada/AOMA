package com.example.aoma;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class AdminActivity extends AppCompatActivity {

    ListView lst;
    Button btnBackAdmin;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        lst = findViewById(R.id.listViewAdmin);
        btnBackAdmin = findViewById(R.id.buttonAdminLogout);

        btnBackAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, LoginActivity.class));
            }
        });

        db = new Database(this, "aoma", null, 1);

        // Retrieve all order details from the database
        ArrayList<String> allOrderDetails = db.getOrderDataForAllUsers();

        // Create an ArrayList to hold HashMaps of order details
        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        // Parse each order detail and add it to the list
        for (String orderDetail : allOrderDetails) {
            String[] parts = orderDetail.split("RM");
            HashMap<String, String> item = new HashMap<>();
            item.put("username", parts[0]);
            item.put("fullname", parts[1]);
            item.put("time", parts[2]);
            item.put("contact", parts[3]);
            item.put("date", parts[4]);
            list.add(item);
        }

        // Create a SimpleAdapter and set it to the ListView
        SimpleAdapter sa = new SimpleAdapter(
                this,
                list,
                R.layout.multi_lines,
                new String[]{"username", "fullname", "time", "contact", "date"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );
        lst.setAdapter(sa);

        // Set click listener for list items to delete the selected order
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the date from the selected item
                String date = list.get(position).get("date");

                // Show confirmation dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
                builder.setMessage("Are you sure you want to delete this user's booking?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Delete the user from the database
                                db.deleteUser(date);
                                Toast.makeText(AdminActivity.this, "Booking deleted", Toast.LENGTH_SHORT).show();

                                // Refresh the list view
                                refreshListView();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }

    // Method to refresh the list view after deleting a user
    private void refreshListView() {
        // Retrieve all order details from the database again
        ArrayList<String> allOrderDetails = db.getOrderDataForAllUsers();

        // Create an ArrayList to hold HashMaps of order details
        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        // Parse each order detail and add it to the list
        for (String orderDetail : allOrderDetails) {
            String[] parts = orderDetail.split("RM");
            HashMap<String, String> item = new HashMap<>();
            item.put("username", parts[0]);
            item.put("fullname", parts[1]);
            item.put("time", parts[2]);
            item.put("contact", parts[3]);
            item.put("date", parts[4]);
            list.add(item);
        }

        // Create a SimpleAdapter and set it to the ListView
        SimpleAdapter sa = new SimpleAdapter(
                this,
                list,
                R.layout.multi_lines,
                new String[]{"username", "fullname", "time", "contact", "date"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );
        lst.setAdapter(sa);
    }
}
