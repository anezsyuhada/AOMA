package com.example.aoma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ArticleActivity extends AppCompatActivity {

    private String[][] article_details =
            {
                    {"Benefits of Wisdom Tooth Extraction", "", "", "", "Click More Details"},
                    {"How to Get Rid of Bad Odour Teeth?", "", "", "", "Click More Details"},
                    {"Dental Health Tips", "", "", "", "Click More Details"},
                    {"What to do in a Dental Emergency?\nPart 1: Broken Braces", "", "", "", "Click More Details"},
                    {"What to do in a Dental Emergency?\nPart 2: Broken Teeth", "", "", "", "Click More Details"},
            };
    private int[] images = {
            R.drawable.dental1,
            R.drawable.dental2,
            R.drawable.dental3,
            R.drawable.dental4,
            R.drawable.dental5
    };

    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;

    Button btnBack;
    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        lst = findViewById(R.id.listViewHA);
        btnBack = findViewById(R.id.buttonHABack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ArticleActivity.this, HomeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i=0;i<article_details.length;i++){
            item = new HashMap<String, String>();
            item.put("line1", article_details[i][0]);
            item.put("line2", article_details[i][1]);
            item.put("line3", article_details[i][2]);
            item.put("line4", article_details[i][3]);
            item.put("line5", article_details[i][4]);
            list.add( item );
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{ "line1", "line2", "line3", "line4", "line5" },
                new int[] {R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(ArticleActivity.this,DentalHealthArticleDetailsActivity.class);
                it.putExtra("text1", article_details[position][0]);
                it.putExtra("text2", images[position]);
                startActivity(it);
            }
        });
    }
}