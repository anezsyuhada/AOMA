package com.example.aoma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ScallingActivity extends AppCompatActivity {

    private String[][] doctor_details1 =
            {
                    {"Doctor Name: Dr. Saffiya", "Clinic Address: No.16, Jalan Kolam Air 1, Nong Chik Heights, Johor Bahru, Malaysia", "Exp: 3 yrs", "Tel No: 012-345 6789", "109"},
                    {"Doctor Name: Dr. Anez", "Clinic Address: No.16, Jalan Kolam Air 1, Nong Chik Heights, Johor Bahru, Malaysia", "Exp: 4 yrs", "Tel No: 012-345 6789", "110"},
                    {"Doctor Name: Dr. Aliesya", "Clinic Address: No.16, Jalan Kolam Air 1, Nong Chik Heights, Johor Bahru, Malaysia", "Exp: 5 yrs", "Tel No: 012-345 6789", "120"},
                    {"Doctor Name: Dr. Caya", "Clinic Address: No.16, Jalan Kolam Air 1, Nong Chik Heights, Johor Bahru, Malaysia", "Exp: 9 yrs", "Tel No: 012-345 6789", "150"},
                    {"Doctor Name: Dr. Akmar", "Clinic Address: No.16, Jalan Kolam Air 1, Nong Chik Heights, Johor Bahru, Malaysia", "Exp: 7 yrs", "Tel No: 012-345 6789", "135"}
            };

    private String[][] doctor_details2 =
            {
                    {"Doctor Name: Dr. Qurratu", "Clinic Address: No.16, Jalan Kolam Air 1, Nong Chik Heights, Johor Bahru, Malaysia", "Exp: 3 yrs", "Tel No: 012-345 6789", "109"},
                    {"Doctor Name: Dr. Syuhada", "Clinic Address: No.16, Jalan Kolam Air 1, Nong Chik Heights, Johor Bahru, Malaysia", "Exp: 4 yrs", "Tel No: 012-345 6789", "110"},
                    {"Doctor Name: Dr. Qaisara", "Clinic Address: No.16, Jalan Kolam Air 1, Nong Chik Heights, Johor Bahru, Malaysia", "Exp: 5 yrs", "Tel No: 012-345 6789", "120"},
                    {"Doctor Name: Dr. Ernie", "Clinic Address: No.16, Jalan Kolam Air 1, Nong Chik Heights, Johor Bahru, Malaysia", "Exp: 9 yrs", "Tel No: 012-345 6789", "150"},
                    {"Doctor Name: Dr. Baharudin", "Clinic Address: No.16, Jalan Kolam Air 1, Nong Chik Heights, Johor Bahru, Malaysia", "Exp: 7 yrs", "Tel No: 012-345 6789", "135"}
            };

    private String[][] doctor_details3 =
            {
                    {"Doctor Name: Dr. Aina", "Clinic Address: No.16, Jalan Kolam Air 1, Nong Chik Heights, Johor Bahru, Malaysia", "Exp: 3 yrs", "Tel No: 012-345 6789", "109"},
                    {"Doctor Name: Dr. Ppam", "Clinic Address: No.16, Jalan Kolam Air 1, Nong Chik Heights, Johor Bahru, Malaysia", "Exp: 4 yrs", "Tel No: 012-345 6789", "110"},
                    {"Doctor Name: Dr. Hasmadi", "Clinic Address: No.16, Jalan Kolam Air 1, Nong Chik Heights, Johor Bahru, Malaysia", "Exp: 5 yrs", "Tel No: 012-345 6789", "120"},
                    {"Doctor Name: Dr. Jueriah", "Clinic Address: No.16, Jalan Kolam Air 1, Nong Chik Heights, Johor Bahru, Malaysia", "Exp: 9 yrs", "Tel No: 012-345 6789", "150"},
                    {"Doctor Name: Dr. Hasmahani", "Clinic Address: No.16, Jalan Kolam Air 1, Nong Chik Heights, Johor Bahru, Malaysia", "Exp: 7 yrs", "Tel No: 012-345 6789", "135"}
            };

    private String[][] doctor_details4 =
            {
                    {"Doctor Name: Dr. Liyana", "Clinic Address: No.16, Jalan Kolam Air 1, Nong Chik Heights, Johor Bahru, Malaysia", "Exp: 3 yrs", "Tel No: 012-345 6789", "109"},
                    {"Doctor Name: Dr. Farah", "Clinic Address: No.16, Jalan Kolam Air 1, Nong Chik Heights, Johor Bahru, Malaysia", "Exp: 4 yrs", "Tel No: 012-345 6789", "110"},
                    {"Doctor Name: Dr. Salman", "Clinic Address: No.16, Jalan Kolam Air 1, Nong Chik Heights, Johor Bahru, Malaysia", "Exp: 5 yrs", "Tel No: 012-345 6789", "120"},
                    {"Doctor Name: Dr. Azizi", "Clinic Address: No.16, Jalan Kolam Air 1, Nong Chik Heights, Johor Bahru, Malaysia", "Exp: 9 yrs", "Tel No: 012-345 6781", "150"},
                    {"Doctor Name: Dr. Ahmad", "Clinic Address: No.16, Jalan Kolam Air 1, Nong Chik Heights, Johor Bahru, Malaysia", "Exp: 7 yrs", "Tel No: 012-345 6789", "135"}
            };

    private String[][] doctor_details5 =
            {
                    {"Doctor Name: Dr. Faris", "Clinic Address: No.16, Jalan Kolam Air 1, Nong Chik Heights, Johor Bahru, Malaysia", "Exp: 3 yrs", "Tel No: 012-345 6789", "109"},
                    {"Doctor Name: Dr. Dania", "Clinic Address: No.16, Jalan Kolam Air 1, Nong Chik Heights, Johor Bahru, Malaysia", "Exp: 4 yrs", "Tel No: 012-345 6789", "110"},
                    {"Doctor Name: Dr. Huraida", "Clinic Address: No.16, Jalan Kolam Air 1, Nong Chik Heights, Johor Bahru, Malaysia", "Exp: 5 yrs", "Tel No: 012-345 6789", "120"},
                    {"Doctor Name: Dr. Diyana", "Clinic Address: No.16, Jalan Kolam Air 1, Nong Chik Heights, Johor Bahru, Malaysia", "Exp: 9 yrs", "Tel No: 012-345 6789", "150"},
                    {"Doctor Name: Dr. Za'im", "Clinic Address: No.16, Jalan Kolam Air 1, Nong Chik Heights, Johor Bahru, Malaysia", "Exp: 7 yrs", "Tel No: 012-345 6789", "135"}
            };


    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scalling);

        tv = findViewById(R.id.textViewSDDTitle);
        btn = findViewById(R.id.buttonMBack);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if (title.compareTo("Scalling and Polishing") == 0)
            doctor_details = doctor_details1;
        else if (title.compareTo("Dentures") == 0)
            doctor_details = doctor_details2;
        else if (title.compareTo("Root Canal Treatment") == 0)
            doctor_details = doctor_details3;
        else if (title.compareTo("Braces") == 0)
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScallingActivity.this, BookAppActivity.class));
            }
        });

        list = new ArrayList();
        for (int i = 0; i<doctor_details.length; i++) {
            item = new HashMap<String,String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "   Cons Fee: RM" + doctor_details[i][4]);
            list.add(item);

        }
        sa = new SimpleAdapter(this, list, R.layout.multi_lines, new String[]{"line1", "line2", "line3", "line4", "line5"}, new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        ListView lst = findViewById(R.id.listViewMed);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(ScallingActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1", title);
                it.putExtra("text2", doctor_details[i][0]);
                it.putExtra("text3", doctor_details[i][1]);
                it.putExtra("text4", doctor_details[i][3]);
                it.putExtra("text5", doctor_details[i][4]);
                startActivity(it);
            }
        });
    }
}