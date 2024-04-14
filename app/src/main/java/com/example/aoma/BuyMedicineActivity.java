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

public class BuyMedicineActivity extends AppCompatActivity {

    private String[][] packages =
            {
                    {"Med 1: Fluoride Toothpaste", "", "", "", "10"},
                    {"Med 2: Mouthwash with Chlorhexidine", "", "", "", "16"},
                    {"Med 3: Anesthetic Gel", "", "", "", "12"},
                    {"Med 4: Toothache Drops", "", "", "", "8.50"},
                    {"Med 5: Gum Stimulators", "", "", "", "20"},
                    {"Med 6: Calcium Supplements", "", "", "", "50"},
                    {"Med 7: Vitamin D Supplements", "", "", "", "40"},
                    {"Med 8: Floss Picks", "", "", "", "17"},
                    {"Med 9: Teeth Whitening Strips", "", "", "", "20"},
                    {"Med 10: Orthodontic Wax", "", "", "", "9.70"},
            };

    private String[] package_details =
            {
                    "Aids in remineralizing weakened tooth enamel.\n" + "Helps to reduce sensitivity in teeth by blocking exposed dentin tubules.\n" + "Assists in preventing cavities by inhibiting the growth of harmful bacteria on teeth.\n" + "Promotes overall oral health by maintaining a healthy pH balance in the mouth.",
                    "Reduces plaque buildup on teeth, preventing the formation of tartar.\n" + "Alleviates bad breath (halitosis) by killing odor-causing bacteria in the mouth.\n" + "Facilitates the healing process after dental procedures, such as extractions or gum surgeries.\n" + "Helps to maintain oral hygiene in individuals with orthodontic appliances, such as braces or retainers.",
                    "Eases discomfort during dental procedures, such as scaling or root planing.\n" + "Relieves pain from minor oral injuries, such as cuts, abrasions, or burns.\n" + "Facilitates the application of topical fluoride treatments in patients with heightened tooth sensitivity.\n" + "Soothes the gums and oral tissues during teething in infants and toddlers.",
                    "Provides quick and targeted relief from acute toothache pain.\n" + "Temporarily numbs the affected tooth and surrounding area for several hours.\n" + "Offers convenient and portable pain relief for individuals unable to visit a dentist immediately.",
                    "Enhances blood circulation in the gums, promoting faster healing of minor gum injuries or inflammation.\n" + "Stimulates the production of saliva, which helps to rinse away food particles and bacteria from the mouth.\n" + "Reduces the risk of gum recession by strengthening the connective tissues that support the teeth.\n" + "Improves the effectiveness of gum disease treatments, such as scaling and root planing.",
                    "Strengthens tooth enamel and promotes healthy bone density in the jawbone.\n" + "Supports the development of strong and resilient teeth during childhood and adolescence.\n" + "Helps to prevent tooth decay and reduce the risk of osteoporosis-related bone fractures.\n" + "May contain vitamin D, which aids in the absorption of calcium and further promotes bone health.",
                    "Facilitates the absorption of calcium and phosphorus, essential minerals for maintaining strong teeth and bones.\n" + "Helps to regulate calcium levels in the bloodstream, ensuring optimal mineralization of tooth enamel.\n" + "Contributes to overall oral health by reducing the risk of dental caries and periodontal diseases.\n" + "Supports immune function, potentially reducing the severity of oral infections and inflammation.",
                    "Facilitates easy and convenient flossing between teeth, promoting effective plaque removal and reducing the risk of gum disease.\n" + "Ideal for individuals with limited dexterity or difficulty handling traditional dental floss.\n" + "Helps to prevent food particles from becoming trapped between teeth, which can lead to decay and bad breath.\n" + "Provides on-the-go oral hygiene, allowing users to maintain clean and healthy teeth while away from home.",
                    "Brightens teeth and removes surface stains caused by food, beverages, and tobacco use.\n" + "Enhances the appearance of the smile, boosting confidence and self-esteem.\n" + "Contains enamel-safe bleaching agents, such as hydrogen peroxide or carbamide peroxide, to achieve noticeable results.",
                    "Soothes irritation and discomfort caused by orthodontic appliances, such as braces or wires rubbing against the cheeks or gums.\n" + "Forms a protective barrier over sharp or protruding edges of braces, preventing oral injuries and ulcers.\n" + "Promotes patient comfort during the initial adjustment period after orthodontic appliance placement."
            };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnGoToCart, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        lst = findViewById(R.id.listViewBM);
        btnBack = findViewById(R.id.buttonBMBack);
        btnGoToCart = findViewById(R.id.buttonBMGoToCart);

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i=0;i<packages.length;i++){
            item = new HashMap<String, String>();
            item.put( "line1", packages[i][0]);
            item.put( "line2", packages[i][1]);
            item.put( "line3", packages[i][2]);
            item.put( "line4", packages[i][3]);
            item.put( "line5", "Total Cost: RM"+packages[i][4]);
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
                Intent it = new Intent(BuyMedicineActivity.this, MedicineDetailsActivity.class);
                it.putExtra("text1", packages[position][0]);
                it.putExtra("text2", package_details[position]);
                it.putExtra("text3", packages[position][4]);
                startActivity(it);
            }
        });

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this, CartMedActivity.class));
            }
        });
    }
}