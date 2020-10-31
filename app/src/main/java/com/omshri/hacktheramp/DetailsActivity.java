package com.omshri.hacktheramp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailsActivity extends AppCompatActivity {

    Spinner dresswear_sp;
    Spinner dresstype_sp;
    Spinner cloth_sp;
    Spinner size_sp;
    Spinner age_sp;
    Spinner coloring_type_sp;
    Button color_btn;
    TextView dresswear_tv;
    TextView color_value_tv;
    TextView coloring_type_tv;
    TextView dresstype_tv;
    TextView cloth_tv;
    TextView size_tv;
    Button next_btn;
    TextView age_tv;
    TextView color_tv;
    View v;
    Intent mainIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainIntent = getIntent();

        setContentView(R.layout.activity_details);
        dresswear_tv = findViewById(R.id.dresswear_tv);
        dresswear_sp = findViewById(R.id.dresswear_spv);
        dresstype_tv = findViewById(R.id.dresstype_tv);
        dresstype_sp = findViewById(R.id.dresstype_spv);
        cloth_tv = findViewById(R.id.cloth_tv);
        cloth_sp = findViewById(R.id.cloth_spv);
        size_tv = findViewById(R.id.size_tv);
        size_sp = findViewById(R.id.size_spv);
        age_tv = findViewById(R.id.age_tv);
        age_sp = findViewById(R.id.age_spv);
        color_tv = findViewById(R.id.color_tv);
        color_btn = findViewById(R.id.color_btn);
        v = findViewById(R.id.color_value_view);
        next_btn = findViewById(R.id.finish_details_btn);
        color_value_tv = findViewById(R.id.color_value_tv);
        coloring_type_sp = findViewById(R.id.coloring_type_spv);
        coloring_type_tv = findViewById(R.id.coloring_type_tv);

        dresswear_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = dresswear_sp.getSelectedItem().toString();

                switch (s) {
                    case "None":
                        dresstype_sp.setVisibility(View.INVISIBLE);
                        dresstype_sp.setSelection(0);
                        dresstype_tv.setVisibility(View.INVISIBLE);
                        break;
                    case "Top Wear":
                        dresstype_sp.setVisibility(View.VISIBLE);
                        dresstype_tv.setVisibility(View.VISIBLE);

                        ArrayAdapter<String> a = new ArrayAdapter<String>(DetailsActivity.this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.topwear_types));
                        dresstype_sp.setAdapter(a);
                        break;
                    case "Bottom Wear":
                        dresstype_sp.setVisibility(View.VISIBLE);
                        dresstype_tv.setVisibility(View.VISIBLE);

                        ArrayAdapter<String> b = new ArrayAdapter<String>(DetailsActivity.this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.bottomwear_types));
                        dresstype_sp.setAdapter(b);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dresstype_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = dresstype_sp.getSelectedItem().toString();

                if ("None".equals(s)) {
                    cloth_sp.setVisibility(View.INVISIBLE);
                    cloth_sp.setSelection(0);
                    cloth_tv.setVisibility(View.INVISIBLE);
                } else {
                    cloth_sp.setVisibility(View.VISIBLE);
                    cloth_tv.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        cloth_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String s = cloth_sp.getSelectedItem().toString();

                if ("None".equals(s)) {
                    size_sp.setVisibility(View.INVISIBLE);
                    size_sp.setSelection(0);
                    size_tv.setVisibility(View.INVISIBLE);
                } else {
                    size_sp.setVisibility(View.VISIBLE);
                    size_tv.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        size_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = size_sp.getSelectedItem().toString();

                if ("None".equals(s)) {
                    age_sp.setVisibility(View.INVISIBLE);
                    age_sp.setSelection(0);
                    age_tv.setVisibility(View.INVISIBLE);
                } else {
                    age_sp.setVisibility(View.VISIBLE);
                    age_tv.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        age_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = age_sp.getSelectedItem().toString();

                if ("None".equals(s)) {
                    color_tv.setVisibility(View.INVISIBLE);
                    color_btn.setVisibility(View.INVISIBLE);
                    color_value_tv.setVisibility(View.INVISIBLE);
                    v.setVisibility(View.INVISIBLE);
                    color_value_tv.setText("No Color");
                } else {
                    color_btn.setVisibility(View.VISIBLE);
                    color_tv.setVisibility(View.VISIBLE);
                    color_value_tv.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        color_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this,CameraActivity.class);
                startActivityForResult(intent,0);
            }
        });



        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int discount = 100;

                int dresswear = dresswear_sp.getSelectedItemPosition();

                switch (dresswear){
                    case 1:
                        discount+=200;
                        break;
                    case 2:
                        discount+=100;
                        break;
                    default:
                        discount+=0;
                        break;
                }

                int cloth = cloth_sp.getSelectedItemPosition();

                switch (cloth){
                    case 1:
                        discount+=100;
                        break;
                    case 2:
                        discount+=90;
                        break;
                    case 3:
                        discount+=80;
                        break;
                    case 4:
                        discount+=70;
                        break;
                    default:
                        discount+=30;
                        break;
                }

                int size = size_sp.getSelectedItemPosition();

                switch (size){
                    case 1:
                        discount+=20;
                        break;
                    case 2:
                        discount+=30;
                        break;
                    case 3:
                        discount+=40;
                        break;
                    case 4:
                        discount+=50;
                        break;
                    case 5:
                        discount+=60;
                        break;
                    default:
                        break;
                }

                int age = age_sp.getSelectedItemPosition();


                switch (age){
                    case 1:
                        discount+=100;
                        break;
                    case 2:
                        discount+=50;
                        break;
                    case 3:
                        discount+=25;
                        break;
                    case 4:
                        discount+=10;
                        break;
                    default:
                        discount+=0;
                        break;
                }
                Intent intent = new Intent(DetailsActivity.this,PaymentActivity.class);
                intent.putExtra("price",mainIntent.getIntExtra("price",0));
                intent.putExtra("discount",discount);
                startActivity(intent);
                finish();
            }
        });

        color_value_tv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().equals("No Color")){
                    coloring_type_sp.setVisibility(View.INVISIBLE);
                    coloring_type_sp.setSelection(0);
                    coloring_type_tv.setVisibility(View.INVISIBLE);
                }
                else{
                    coloring_type_sp.setVisibility(View.VISIBLE);
                    coloring_type_tv.setVisibility(View.VISIBLE);
                }
            }
        });

        coloring_type_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = coloring_type_sp.getSelectedItem().toString();
                if(s.equals("None")){
                    next_btn.setVisibility(View.INVISIBLE);
                }
                else{
                    next_btn.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            v.setVisibility(View.VISIBLE);

            int m = data.getIntExtra("color",0);
            v.setBackgroundColor(m);

            int R = (m >> 16) & 0xff;
            int G = (m >>  8) & 0xff;
            int B = (m) & 0xff;

            String s = "[ { \"name\": \"Air Force Blue (Raf)\", \"R\": 93, \"G\": 138, \"B\": 168 }, { \"name\": \"Air Force Blue (Usaf)\", \"R\": 0, \"G\": 48, \"B\": 143 }, { \"name\": \"Air Superiority Blue\", \"R\": 114, \"G\": 160, \"B\": 193 }, { \"name\": \"Alabama Crimson\", \"R\": 163, \"G\": 38, \"B\": 56 }, { \"name\": \"Alice Blue\", \"R\": 240, \"G\": 248, \"B\": 255 }, { \"name\": \"Alizarin Crimson\", \"R\": 227, \"G\": 38, \"B\": 54 }, { \"name\": \"Alloy Orange\", \"R\": 196, \"G\": 98, \"B\": 16 }, { \"name\": \"Almond\", \"R\": 239, \"G\": 222, \"B\": 205 }, { \"name\": \"Amaranth\", \"R\": 229, \"G\": 43, \"B\": 80 }, { \"name\": \"Amber\", \"R\": 255, \"G\": 191, \"B\": 0 }, { \"name\": \"Amber (Sae/Ece)\", \"R\": 255, \"G\": 126, \"B\": 0 }, { \"name\": \"American Rose\", \"R\": 255, \"G\": 3, \"B\": 62 }, { \"name\": \"Amethyst\", \"R\": 153, \"G\": 102, \"B\": 204 }, { \"name\": \"Android Green\", \"R\": 164, \"G\": 198, \"B\": 57 }, { \"name\": \"Anti-Flash White\", \"R\": 242, \"G\": 243, \"B\": 244 }, { \"name\": \"Antique Brass\", \"R\": 205, \"G\": 149, \"B\": 117 }, { \"name\": \"Antique Fuchsia\", \"R\": 145, \"G\": 92, \"B\": 131 }, { \"name\": \"Antique Ruby\", \"R\": 132, \"G\": 27, \"B\": 45 }, { \"name\": \"Antique White\", \"R\": 250, \"G\": 235, \"B\": 215 }, { \"name\": \"Ao (English)\", \"R\": 0, \"G\": 128, \"B\": 0 }, { \"name\": \"Apple Green\", \"R\": 141, \"G\": 182, \"B\": 0 }, { \"name\": \"Apricot\", \"R\": 251, \"G\": 206, \"B\": 177 }, { \"name\": \"Aqua\", \"R\": 0, \"G\": 255, \"B\": 255 }, { \"name\": \"Aquamarine\", \"R\": 127, \"G\": 255, \"B\": 212 }, { \"name\": \"Army Green\", \"R\": 75, \"G\": 83, \"B\": 32 }, { \"name\": \"Arsenic\", \"R\": 59, \"G\": 68, \"B\": 75 }, { \"name\": \"Arylide Yellow\", \"R\": 233, \"G\": 214, \"B\": 107 }, { \"name\": \"Ash Grey\", \"R\": 178, \"G\": 190, \"B\": 181 }, { \"name\": \"Asparagus\", \"R\": 135, \"G\": 169, \"B\": 107 }, { \"name\": \"Atomic Tangerine\", \"R\": 255, \"G\": 153, \"B\": 102 }, { \"name\": \"Auburn\", \"R\": 165, \"G\": 42, \"B\": 42 }, { \"name\": \"Aureolin\", \"R\": 253, \"G\": 238, \"B\": 0 }, { \"name\": \"Aurometalsaurus\", \"R\": 110, \"G\": 127, \"B\": 128 }, { \"name\": \"Avocado\", \"R\": 86, \"G\": 130, \"B\": 3 }, { \"name\": \"Azure\", \"R\": 0, \"G\": 127, \"B\": 255 }, { \"name\": \"Azure Mist/Web\", \"R\": 240, \"G\": 255, \"B\": 255 }, { \"name\": \"Baby Blue\", \"R\": 137, \"G\": 207, \"B\": 240 }, { \"name\": \"Baby Blue Eyes\", \"R\": 161, \"G\": 202, \"B\": 241 }, { \"name\": \"Baby Pink\", \"R\": 244, \"G\": 194, \"B\": 194 }, { \"name\": \"Ball Blue\", \"R\": 33, \"G\": 171, \"B\": 205 }, { \"name\": \"Banana Mania\", \"R\": 250, \"G\": 231, \"B\": 181 }, { \"name\": \"Banana Yellow\", \"R\": 255, \"G\": 225, \"B\": 53 }, { \"name\": \"Barn Red\", \"R\": 124, \"G\": 10, \"B\": 2 }, { \"name\": \"Battleship Grey\", \"R\": 132, \"G\": 132, \"B\": 130 }, { \"name\": \"Bazaar\", \"R\": 152, \"G\": 119, \"B\": 123 }, { \"name\": \"Beau Blue\", \"R\": 188, \"G\": 212, \"B\": 230 }, { \"name\": \"Beaver\", \"R\": 159, \"G\": 129, \"B\": 112 }, { \"name\": \"Beige\", \"R\": 245, \"G\": 245, \"B\": 220 }, { \"name\": \"Big Dip O’Ruby\", \"R\": 156, \"G\": 37, \"B\": 66 }, { \"name\": \"Bisque\", \"R\": 255, \"G\": 228, \"B\": 196 }, { \"name\": \"Bistre\", \"R\": 61, \"G\": 43, \"B\": 31 }, { \"name\": \"Bittersweet\", \"R\": 254, \"G\": 111, \"B\": 94 }, { \"name\": \"Bittersweet Shimmer\", \"R\": 191, \"G\": 79, \"B\": 81 }, { \"name\": \"Black\", \"R\": 0, \"G\": 0, \"B\": 0 }, { \"name\": \"Black Bean\", \"R\": 61, \"G\": 12, \"B\": 2 }, { \"name\": \"Black Leather Jacket\", \"R\": 37, \"G\": 53, \"B\": 41 }, { \"name\": \"Black Olive\", \"R\": 59, \"G\": 60, \"B\": 54 }, { \"name\": \"Blanched Almond\", \"R\": 255, \"G\": 235, \"B\": 205 }, { \"name\": \"Blast-Off Bronze\", \"R\": 165, \"G\": 113, \"B\": 100 }, { \"name\": \"Bleu De France\", \"R\": 49, \"G\": 140, \"B\": 231 }, { \"name\": \"Blizzard Blue\", \"R\": 172, \"G\": 229, \"B\": 238 }, { \"name\": \"Blond\", \"R\": 250, \"G\": 240, \"B\": 190 }, { \"name\": \"Blue\", \"R\": 0, \"G\": 0, \"B\": 255 }, { \"name\": \"Blue Bell\", \"R\": 162, \"G\": 162, \"B\": 208 }, { \"name\": \"Blue (Crayola)\", \"R\": 31, \"G\": 117, \"B\": 254 }, { \"name\": \"Blue Gray\", \"R\": 102, \"G\": 153, \"B\": 204 }, { \"name\": \"Blue-Green\", \"R\": 13, \"G\": 152, \"B\": 186 }, { \"name\": \"Blue (Munsell)\", \"R\": 0, \"G\": 147, \"B\": 175 }, { \"name\": \"Blue (Ncs)\", \"R\": 0, \"G\": 135, \"B\": 189 }, { \"name\": \"Blue (Pigment)\", \"R\": 51, \"G\": 51, \"B\": 153 }, { \"name\": \"Blue (Ryb)\", \"R\": 2, \"G\": 71, \"B\": 254 }, { \"name\": \"Blue Sapphire\", \"R\": 18, \"G\": 97, \"B\": 128 }, { \"name\": \"Blue-Violet\", \"R\": 138, \"G\": 43, \"B\": 226 }, { \"name\": \"Blush\", \"R\": 222, \"G\": 93, \"B\": 131 }, { \"name\": \"Bole\", \"R\": 121, \"G\": 68, \"B\": 59 }, { \"name\": \"Bondi Blue\", \"R\": 0, \"G\": 149, \"B\": 182 }, { \"name\": \"Bone\", \"R\": 227, \"G\": 218, \"B\": 201 }, { \"name\": \"Boston University Red\", \"R\": 204, \"G\": 0, \"B\": 0 }, { \"name\": \"Bottle Green\", \"R\": 0, \"G\": 106, \"B\": 78 }, { \"name\": \"Boysenberry\", \"R\": 135, \"G\": 50, \"B\": 96 }, { \"name\": \"Brandeis Blue\", \"R\": 0, \"G\": 112, \"B\": 255 }, { \"name\": \"Brass\", \"R\": 181, \"G\": 166, \"B\": 66 }, { \"name\": \"Brick Red\", \"R\": 203, \"G\": 65, \"B\": 84 }, { \"name\": \"Bright Cerulean\", \"R\": 29, \"G\": 172, \"B\": 214 }, { \"name\": \"Bright Green\", \"R\": 102, \"G\": 255, \"B\": 0 }, { \"name\": \"Bright Lavender\", \"R\": 191, \"G\": 148, \"B\": 228 }, { \"name\": \"Bright Maroon\", \"R\": 195, \"G\": 33, \"B\": 72 }, { \"name\": \"Bright Pink\", \"R\": 255, \"G\": 0, \"B\": 127 }, { \"name\": \"Bright Turquoise\", \"R\": 8, \"G\": 232, \"B\": 222 }, { \"name\": \"Bright Ube\", \"R\": 209, \"G\": 159, \"B\": 232 }, { \"name\": \"Brilliant Lavender\", \"R\": 244, \"G\": 187, \"B\": 255 }, { \"name\": \"Brilliant Rose\", \"R\": 255, \"G\": 85, \"B\": 163 }, { \"name\": \"Brink Pink\", \"R\": 251, \"G\": 96, \"B\": 127 }, { \"name\": \"British Racing Green\", \"R\": 0, \"G\": 66, \"B\": 37 }, { \"name\": \"Bronze\", \"R\": 205, \"G\": 127, \"B\": 50 }, { \"name\": \"Brown (Traditional)\", \"R\": 150, \"G\": 75, \"B\": 0 }, { \"name\": \"Brown (Web)\", \"R\": 165, \"G\": 42, \"B\": 42 }, { \"name\": \"Bubble Gum\", \"R\": 255, \"G\": 193, \"B\": 204 }, { \"name\": \"Bubbles\", \"R\": 231, \"G\": 254, \"B\": 255 }, { \"name\": \"Buff\", \"R\": 240, \"G\": 220, \"B\": 130 }, { \"name\": \"Bulgarian Rose\", \"R\": 72, \"G\": 6, \"B\": 7 }, { \"name\": \"Burgundy\", \"R\": 128, \"G\": 0, \"B\": 32 }, { \"name\": \"Burlywood\", \"R\": 222, \"G\": 184, \"B\": 135 }, { \"name\": \"Burnt Orange\", \"R\": 204, \"G\": 85, \"B\": 0 }, { \"name\": \"Burnt Sienna\", \"R\": 233, \"G\": 116, \"B\": 81 }, { \"name\": \"Burnt Umber\", \"R\": 138, \"G\": 51, \"B\": 36 }, { \"name\": \"Byzantine\", \"R\": 189, \"G\": 51, \"B\": 164 }, { \"name\": \"Byzantium\", \"R\": 112, \"G\": 41, \"B\": 99 }, { \"name\": \"Cadet\", \"R\": 83, \"G\": 104, \"B\": 114 }, { \"name\": \"Cadet Blue\", \"R\": 95, \"G\": 158, \"B\": 160 }, { \"name\": \"Cadet Grey\", \"R\": 145, \"G\": 163, \"B\": 176 }, { \"name\": \"Cadmium Green\", \"R\": 0, \"G\": 107, \"B\": 60 }, { \"name\": \"Cadmium Orange\", \"R\": 237, \"G\": 135, \"B\": 45 }, { \"name\": \"Cadmium Red\", \"R\": 227, \"G\": 0, \"B\": 34 }, { \"name\": \"Cadmium Yellow\", \"R\": 255, \"G\": 246, \"B\": 0 }, { \"name\": \"Café Au Lait\", \"R\": 166, \"G\": 123, \"B\": 91 }, { \"name\": \"Café Noir\", \"R\": 75, \"G\": 54, \"B\": 33 }, { \"name\": \"Cal Poly Green\", \"R\": 30, \"G\": 77, \"B\": 43 }, { \"name\": \"Cambridge Blue\", \"R\": 163, \"G\": 193, \"B\": 173 }, { \"name\": \"Camel\", \"R\": 193, \"G\": 154, \"B\": 107 }, { \"name\": \"Cameo Pink\", \"R\": 239, \"G\": 187, \"B\": 204 }, { \"name\": \"Camouflage Green\", \"R\": 120, \"G\": 134, \"B\": 107 }, { \"name\": \"Canary Yellow\", \"R\": 255, \"G\": 239, \"B\": 0 }, { \"name\": \"Candy Apple Red\", \"R\": 255, \"G\": 8, \"B\": 0 }, { \"name\": \"Candy Pink\", \"R\": 228, \"G\": 113, \"B\": 122 }, { \"name\": \"Capri\", \"R\": 0, \"G\": 191, \"B\": 255 }, { \"name\": \"Caput Mortuum\", \"R\": 89, \"G\": 39, \"B\": 32 }, { \"name\": \"Cardinal\", \"R\": 196, \"G\": 30, \"B\": 58 }, { \"name\": \"Caribbean Green\", \"R\": 0, \"G\": 204, \"B\": 153 }, { \"name\": \"Carmine\", \"R\": 150, \"G\": 0, \"B\": 24 }, { \"name\": \"Carmine (M&P)\", \"R\": 215, \"G\": 0, \"B\": 64 }, { \"name\": \"Carmine Pink\", \"R\": 235, \"G\": 76, \"B\": 66 }, { \"name\": \"Carmine Red\", \"R\": 255, \"G\": 0, \"B\": 56 }, { \"name\": \"Carnation Pink\", \"R\": 255, \"G\": 166, \"B\": 201 }, { \"name\": \"Carnelian\", \"R\": 179, \"G\": 27, \"B\": 27 }, { \"name\": \"Carolina Blue\", \"R\": 153, \"G\": 186, \"B\": 221 }, { \"name\": \"Carrot Orange\", \"R\": 237, \"G\": 145, \"B\": 33 }, { \"name\": \"Catalina Blue\", \"R\": 6, \"G\": 42, \"B\": 120 }, { \"name\": \"Ceil\", \"R\": 146, \"G\": 161, \"B\": 207 }, { \"name\": \"Celadon\", \"R\": 172, \"G\": 225, \"B\": 175 }, { \"name\": \"Celadon Blue\", \"R\": 0, \"G\": 123, \"B\": 167 }, { \"name\": \"Celadon Green\", \"R\": 47, \"G\": 132, \"B\": 124 }, { \"name\": \"Celeste (Colour)\", \"R\": 178, \"G\": 255, \"B\": 255 }, { \"name\": \"Celestial Blue\", \"R\": 73, \"G\": 151, \"B\": 208 }, { \"name\": \"Cerise\", \"R\": 222, \"G\": 49, \"B\": 99 }, { \"name\": \"Cerise Pink\", \"R\": 236, \"G\": 59, \"B\": 131 }, { \"name\": \"Cerulean\", \"R\": 0, \"G\": 123, \"B\": 167 }, { \"name\": \"Cerulean Blue\", \"R\": 42, \"G\": 82, \"B\": 190 }, { \"name\": \"Cerulean Frost\", \"R\": 109, \"G\": 155, \"B\": 195 }, { \"name\": \"Cg Blue\", \"R\": 0, \"G\": 122, \"B\": 165 }, { \"name\": \"Cg Red\", \"R\": 224, \"G\": 60, \"B\": 49 }, { \"name\": \"Chamoisee\", \"R\": 160, \"G\": 120, \"B\": 90 }, { \"name\": \"Champagne\", \"R\": 250, \"G\": 214, \"B\": 165 }, { \"name\": \"Charcoal\", \"R\": 54, \"G\": 69, \"B\": 79 }, { \"name\": \"Charm Pink\", \"R\": 230, \"G\": 143, \"B\": 172 }, { \"name\": \"Chartreuse (Traditional)\", \"R\": 223, \"G\": 255, \"B\": 0 }, { \"name\": \"Chartreuse (Web)\", \"R\": 127, \"G\": 255, \"B\": 0 }, { \"name\": \"Cherry\", \"R\": 222, \"G\": 49, \"B\": 99 }, { \"name\": \"Cherry Blossom Pink\", \"R\": 255, \"G\": 183, \"B\": 197 }, { \"name\": \"Chestnut\", \"R\": 205, \"G\": 92, \"B\": 92 }, { \"name\": \"China Pink\", \"R\": 222, \"G\": 111, \"B\": 161 }, { \"name\": \"China Rose\", \"R\": 168, \"G\": 81, \"B\": 110 }, { \"name\": \"Chinese Red\", \"R\": 170, \"G\": 56, \"B\": 30 }, { \"name\": \"Chocolate (Traditional)\", \"R\": 123, \"G\": 63, \"B\": 0 }, { \"name\": \"Chocolate (Web)\", \"R\": 210, \"G\": 105, \"B\": 30 }, { \"name\": \"Chrome Yellow\", \"R\": 255, \"G\": 167, \"B\": 0 }, { \"name\": \"Cinereous\", \"R\": 152, \"G\": 129, \"B\": 123 }, { \"name\": \"Cinnabar\", \"R\": 227, \"G\": 66, \"B\": 52 }, { \"name\": \"Cinnamon\", \"R\": 210, \"G\": 105, \"B\": 30 }, { \"name\": \"Citrine\", \"R\": 228, \"G\": 208, \"B\": 10 }, { \"name\": \"Classic Rose\", \"R\": 251, \"G\": 204, \"B\": 231 }, { \"name\": \"Cobalt\", \"R\": 0, \"G\": 71, \"B\": 171 }, { \"name\": \"Cocoa Brown\", \"R\": 210, \"G\": 105, \"B\": 30 }, { \"name\": \"Coffee\", \"R\": 111, \"G\": 78, \"B\": 55 }, { \"name\": \"Columbia Blue\", \"R\": 155, \"G\": 221, \"B\": 255 }, { \"name\": \"Congo Pink\", \"R\": 248, \"G\": 131, \"B\": 121 }, { \"name\": \"Cool Black\", \"R\": 0, \"G\": 46, \"B\": 99 }, { \"name\": \"Cool Grey\", \"R\": 140, \"G\": 146, \"B\": 172 }, { \"name\": \"Copper\", \"R\": 184, \"G\": 115, \"B\": 51 }, { \"name\": \"Copper (Crayola)\", \"R\": 218, \"G\": 138, \"B\": 103 }, { \"name\": \"Copper Penny\", \"R\": 173, \"G\": 111, \"B\": 105 }, { \"name\": \"Copper Red\", \"R\": 203, \"G\": 109, \"B\": 81 }, { \"name\": \"Copper Rose\", \"R\": 153, \"G\": 102, \"B\": 102 }, { \"name\": \"Coquelicot\", \"R\": 255, \"G\": 56, \"B\": 0 }, { \"name\": \"Coral\", \"R\": 255, \"G\": 127, \"B\": 80 }, { \"name\": \"Coral Pink\", \"R\": 248, \"G\": 131, \"B\": 121 }, { \"name\": \"Coral Red\", \"R\": 255, \"G\": 64, \"B\": 64 }, { \"name\": \"Cordovan\", \"R\": 137, \"G\": 63, \"B\": 69 }, { \"name\": \"Corn\", \"R\": 251, \"G\": 236, \"B\": 93 }, { \"name\": \"Cornell Red\", \"R\": 179, \"G\": 27, \"B\": 27 }, { \"name\": \"Cornflower Blue\", \"R\": 100, \"G\": 149, \"B\": 237 }, { \"name\": \"Cornsilk\", \"R\": 255, \"G\": 248, \"B\": 220 }, { \"name\": \"Cosmic Latte\", \"R\": 255, \"G\": 248, \"B\": 231 }, { \"name\": \"Cotton Candy\", \"R\": 255, \"G\": 188, \"B\": 217 }, { \"name\": \"Cream\", \"R\": 255, \"G\": 253, \"B\": 208 }, { \"name\": \"Crimson\", \"R\": 220, \"G\": 20, \"B\": 60 }, { \"name\": \"Crimson Glory\", \"R\": 190, \"G\": 0, \"B\": 50 }, { \"name\": \"Cyan\", \"R\": 0, \"G\": 255, \"B\": 255 }, { \"name\": \"Cyan (Process)\", \"R\": 0, \"G\": 183, \"B\": 235 }, { \"name\": \"Daffodil\", \"R\": 255, \"G\": 255, \"B\": 49 }, { \"name\": \"Dandelion\", \"R\": 240, \"G\": 225, \"B\": 48 }, { \"name\": \"Dark Blue\", \"R\": 0, \"G\": 0, \"B\": 139 }, { \"name\": \"Dark Brown\", \"R\": 101, \"G\": 67, \"B\": 33 }, { \"name\": \"Dark Byzantium\", \"R\": 93, \"G\": 57, \"B\": 84 }, { \"name\": \"Dark Candy Apple Red\", \"R\": 164, \"G\": 0, \"B\": 0 }, { \"name\": \"Dark Cerulean\", \"R\": 8, \"G\": 69, \"B\": 126 }, { \"name\": \"Dark Chestnut\", \"R\": 152, \"G\": 105, \"B\": 96 }, { \"name\": \"Dark Coral\", \"R\": 205, \"G\": 91, \"B\": 69 }, { \"name\": \"Dark Cyan\", \"R\": 0, \"G\": 139, \"B\": 139 }, { \"name\": \"Dark Electric Blue\", \"R\": 83, \"G\": 104, \"B\": 120 }, { \"name\": \"Dark Goldenrod\", \"R\": 184, \"G\": 134, \"B\": 11 }, { \"name\": \"Dark Gray\", \"R\": 169, \"G\": 169, \"B\": 169 }, { \"name\": \"Dark Green\", \"R\": 1, \"G\": 50, \"B\": 32 }, { \"name\": \"Dark Imperial Blue\", \"R\": 0, \"G\": 65, \"B\": 106 }, { \"name\": \"Dark Jungle Green\", \"R\": 26, \"G\": 36, \"B\": 33 }, { \"name\": \"Dark Khaki\", \"R\": 189, \"G\": 183, \"B\": 107 }, { \"name\": \"Dark Lava\", \"R\": 72, \"G\": 60, \"B\": 50 }, { \"name\": \"Dark Lavender\", \"R\": 115, \"G\": 79, \"B\": 150 }, { \"name\": \"Dark Magenta\", \"R\": 139, \"G\": 0, \"B\": 139 }, { \"name\": \"Dark Midnight Blue\", \"R\": 0, \"G\": 51, \"B\": 102 }, { \"name\": \"Dark Olive Green\", \"R\": 85, \"G\": 107, \"B\": 47 }, { \"name\": \"Dark Orange\", \"R\": 255, \"G\": 140, \"B\": 0 }, { \"name\": \"Dark Orchid\", \"R\": 153, \"G\": 50, \"B\": 204 }, { \"name\": \"Dark Pastel Blue\", \"R\": 119, \"G\": 158, \"B\": 203 }, { \"name\": \"Dark Pastel Green\", \"R\": 3, \"G\": 192, \"B\": 60 }, { \"name\": \"Dark Pastel Purple\", \"R\": 150, \"G\": 111, \"B\": 214 }, { \"name\": \"Dark Pastel Red\", \"R\": 194, \"G\": 59, \"B\": 34 }, { \"name\": \"Dark Pink\", \"R\": 231, \"G\": 84, \"B\": 128 }, { \"name\": \"Dark Powder Blue\", \"R\": 0, \"G\": 51, \"B\": 153 }, { \"name\": \"Dark Raspberry\", \"R\": 135, \"G\": 38, \"B\": 87 }, { \"name\": \"Dark Red\", \"R\": 139, \"G\": 0, \"B\": 0 }, { \"name\": \"Dark Salmon\", \"R\": 233, \"G\": 150, \"B\": 122 }, { \"name\": \"Dark Scarlet\", \"R\": 86, \"G\": 3, \"B\": 25 }, { \"name\": \"Dark Sea Green\", \"R\": 143, \"G\": 188, \"B\": 143 }, { \"name\": \"Dark Sienna\", \"R\": 60, \"G\": 20, \"B\": 20 }, { \"name\": \"Dark Slate Blue\", \"R\": 72, \"G\": 61, \"B\": 139 }, { \"name\": \"Dark Slate Gray\", \"R\": 47, \"G\": 79, \"B\": 79 }, { \"name\": \"Dark Spring Green\", \"R\": 23, \"G\": 114, \"B\": 69 }, { \"name\": \"Dark Tan\", \"R\": 145, \"G\": 129, \"B\": 81 }, { \"name\": \"Dark Tangerine\", \"R\": 255, \"G\": 168, \"B\": 18 }, { \"name\": \"Dark Taupe\", \"R\": 72, \"G\": 60, \"B\": 50 }, { \"name\": \"Dark Terra Cotta\", \"R\": 204, \"G\": 78, \"B\": 92 }, { \"name\": \"Dark Turquoise\", \"R\": 0, \"G\": 206, \"B\": 209 }, { \"name\": \"Dark Violet\", \"R\": 148, \"G\": 0, \"B\": 211 }, { \"name\": \"Dark Yellow\", \"R\": 155, \"G\": 135, \"B\": 12 }, { \"name\": \"Dartmouth Green\", \"R\": 0, \"G\": 112, \"B\": 60 }, { \"name\": \"Davy'S Grey\", \"R\": 85, \"G\": 85, \"B\": 85 }, { \"name\": \"Debian Red\", \"R\": 215, \"G\": 10, \"B\": 83 }, { \"name\": \"Deep Carmine\", \"R\": 169, \"G\": 32, \"B\": 62 }, { \"name\": \"Deep Carmine Pink\", \"R\": 239, \"G\": 48, \"B\": 56 }, { \"name\": \"Deep Carrot Orange\", \"R\": 233, \"G\": 105, \"B\": 44 }, { \"name\": \"Deep Cerise\", \"R\": 218, \"G\": 50, \"B\": 135 }, { \"name\": \"Deep Champagne\", \"R\": 250, \"G\": 214, \"B\": 165 }, { \"name\": \"Deep Chestnut\", \"R\": 185, \"G\": 78, \"B\": 72 }, { \"name\": \"Deep Coffee\", \"R\": 112, \"G\": 66, \"B\": 65 }, { \"name\": \"Deep Fuchsia\", \"R\": 193, \"G\": 84, \"B\": 193 }, { \"name\": \"Deep Jungle Green\", \"R\": 0, \"G\": 75, \"B\": 73 }, { \"name\": \"Deep Lilac\", \"R\": 153, \"G\": 85, \"B\": 187 }, { \"name\": \"Deep Magenta\", \"R\": 204, \"G\": 0, \"B\": 204 }, { \"name\": \"Deep Peach\", \"R\": 255, \"G\": 203, \"B\": 164 }, { \"name\": \"Deep Pink\", \"R\": 255, \"G\": 20, \"B\": 147 }, { \"name\": \"Deep Ruby\", \"R\": 132, \"G\": 63, \"B\": 91 }, { \"name\": \"Deep Saffron\", \"R\": 255, \"G\": 153, \"B\": 51 }, { \"name\": \"Deep Sky Blue\", \"R\": 0, \"G\": 191, \"B\": 255 }, { \"name\": \"Deep Tuscan Red\", \"R\": 102, \"G\": 66, \"B\": 77 }, { \"name\": \"Denim\", \"R\": 21, \"G\": 96, \"B\": 189 }, { \"name\": \"Desert\", \"R\": 193, \"G\": 154, \"B\": 107 }, { \"name\": \"Desert Sand\", \"R\": 237, \"G\": 201, \"B\": 175 }, { \"name\": \"Dim Gray\", \"R\": 105, \"G\": 105, \"B\": 105 }, { \"name\": \"Dodger Blue\", \"R\": 30, \"G\": 144, \"B\": 255 }, { \"name\": \"Dogwood Rose\", \"R\": 215, \"G\": 24, \"B\": 104 }, { \"name\": \"Dollar Bill\", \"R\": 133, \"G\": 187, \"B\": 101 }, { \"name\": \"Drab\", \"R\": 150, \"G\": 113, \"B\": 23 }, { \"name\": \"Duke Blue\", \"R\": 0, \"G\": 0, \"B\": 156 }, { \"name\": \"Earth Yellow\", \"R\": 225, \"G\": 169, \"B\": 95 }, { \"name\": \"Ebony\", \"R\": 85, \"G\": 93, \"B\": 80 }, { \"name\": \"Ecru\", \"R\": 194, \"G\": 178, \"B\": 128 }, { \"name\": \"Eggplant\", \"R\": 97, \"G\": 64, \"B\": 81 }, { \"name\": \"Eggshell\", \"R\": 240, \"G\": 234, \"B\": 214 }, { \"name\": \"Egyptian Blue\", \"R\": 16, \"G\": 52, \"B\": 166 }, { \"name\": \"Electric Blue\", \"R\": 125, \"G\": 249, \"B\": 255 }, { \"name\": \"Electric Crimson\", \"R\": 255, \"G\": 0, \"B\": 63 }, { \"name\": \"Electric Cyan\", \"R\": 0, \"G\": 255, \"B\": 255 }, { \"name\": \"Electric Green\", \"R\": 0, \"G\": 255, \"B\": 0 }, { \"name\": \"Electric Indigo\", \"R\": 111, \"G\": 0, \"B\": 255 }, { \"name\": \"Electric Lavender\", \"R\": 244, \"G\": 187, \"B\": 255 }, { \"name\": \"Electric Lime\", \"R\": 204, \"G\": 255, \"B\": 0 }, { \"name\": \"Electric Purple\", \"R\": 191, \"G\": 0, \"B\": 255 }, { \"name\": \"Electric Ultramarine\", \"R\": 63, \"G\": 0, \"B\": 255 }, { \"name\": \"Electric Violet\", \"R\": 143, \"G\": 0, \"B\": 255 }, { \"name\": \"Electric Yellow\", \"R\": 255, \"G\": 255, \"B\": 0 }, { \"name\": \"Emerald\", \"R\": 80, \"G\": 200, \"B\": 120 }, { \"name\": \"English Lavender\", \"R\": 180, \"G\": 131, \"B\": 149 }, { \"name\": \"Eton Blue\", \"R\": 150, \"G\": 200, \"B\": 162 }, { \"name\": \"Fallow\", \"R\": 193, \"G\": 154, \"B\": 107 }, { \"name\": \"Falu Red\", \"R\": 128, \"G\": 24, \"B\": 24 }, { \"name\": \"Fandango\", \"R\": 181, \"G\": 51, \"B\": 137 }, { \"name\": \"Fashion Fuchsia\", \"R\": 244, \"G\": 0, \"B\": 161 }, { \"name\": \"Fawn\", \"R\": 229, \"G\": 170, \"B\": 112 }, { \"name\": \"Feldgrau\", \"R\": 77, \"G\": 93, \"B\": 83 }, { \"name\": \"Fern Green\", \"R\": 79, \"G\": 121, \"B\": 66 }, { \"name\": \"Ferrari Red\", \"R\": 255, \"G\": 40, \"B\": 0 }, { \"name\": \"Field Drab\", \"R\": 108, \"G\": 84, \"B\": 30 }, { \"name\": \"Fire Engine Red\", \"R\": 206, \"G\": 32, \"B\": 41 }, { \"name\": \"Firebrick\", \"R\": 178, \"G\": 34, \"B\": 34 }, { \"name\": \"Flame\", \"R\": 226, \"G\": 88, \"B\": 34 }, { \"name\": \"Flamingo Pink\", \"R\": 252, \"G\": 142, \"B\": 172 }, { \"name\": \"Flavescent\", \"R\": 247, \"G\": 233, \"B\": 142 }, { \"name\": \"Flax\", \"R\": 238, \"G\": 220, \"B\": 130 }, { \"name\": \"Floral White\", \"R\": 255, \"G\": 250, \"B\": 240 }, { \"name\": \"Fluorescent Orange\", \"R\": 255, \"G\": 191, \"B\": 0 }, { \"name\": \"Fluorescent Pink\", \"R\": 255, \"G\": 20, \"B\": 147 }, { \"name\": \"Fluorescent Yellow\", \"R\": 204, \"G\": 255, \"B\": 0 }, { \"name\": \"Folly\", \"R\": 255, \"G\": 0, \"B\": 79 }, { \"name\": \"Forest Green (Traditional)\", \"R\": 1, \"G\": 68, \"B\": 33 }, { \"name\": \"Forest Green (Web)\", \"R\": 34, \"G\": 139, \"B\": 34 }, { \"name\": \"French Beige\", \"R\": 166, \"G\": 123, \"B\": 91 }, { \"name\": \"French Blue\", \"R\": 0, \"G\": 114, \"B\": 187 }, { \"name\": \"French Lilac\", \"R\": 134, \"G\": 96, \"B\": 142 }, { \"name\": \"French Lime\", \"R\": 204, \"G\": 255, \"B\": 0 }, { \"name\": \"French Raspberry\", \"R\": 199, \"G\": 44, \"B\": 72 }, { \"name\": \"French Rose\", \"R\": 246, \"G\": 74, \"B\": 138 }, { \"name\": \"Fuchsia\", \"R\": 255, \"G\": 0, \"B\": 255 }, { \"name\": \"Fuchsia (Crayola)\", \"R\": 193, \"G\": 84, \"B\": 193 }, { \"name\": \"Fuchsia Pink\", \"R\": 255, \"G\": 119, \"B\": 255 }, { \"name\": \"Fuchsia Rose\", \"R\": 199, \"G\": 67, \"B\": 117 }, { \"name\": \"Fulvous\", \"R\": 228, \"G\": 132, \"B\": 0 }, { \"name\": \"Fuzzy Wuzzy\", \"R\": 204, \"G\": 102, \"B\": 102 }, { \"name\": \"Gainsboro\", \"R\": 220, \"G\": 220, \"B\": 220 }, { \"name\": \"Gamboge\", \"R\": 228, \"G\": 155, \"B\": 15 }, { \"name\": \"Ghost White\", \"R\": 248, \"G\": 248, \"B\": 255 }, { \"name\": \"Ginger\", \"R\": 176, \"G\": 101, \"B\": 0 }, { \"name\": \"Glaucous\", \"R\": 96, \"G\": 130, \"B\": 182 }, { \"name\": \"Glitter\", \"R\": 230, \"G\": 232, \"B\": 250 }, { \"name\": \"Gold (Metallic)\", \"R\": 212, \"G\": 175, \"B\": 55 }, { \"name\": \"Gold (Web) (Golden)\", \"R\": 255, \"G\": 215, \"B\": 0 }, { \"name\": \"Golden Brown\", \"R\": 153, \"G\": 101, \"B\": 21 }, { \"name\": \"Golden Poppy\", \"R\": 252, \"G\": 194, \"B\": 0 }, { \"name\": \"Golden Yellow\", \"R\": 255, \"G\": 223, \"B\": 0 }, { \"name\": \"Goldenrod\", \"R\": 218, \"G\": 165, \"B\": 32 }, { \"name\": \"Granny Smith Apple\", \"R\": 168, \"G\": 228, \"B\": 160 }, { \"name\": \"Gray\", \"R\": 128, \"G\": 128, \"B\": 128 }, { \"name\": \"Gray-Asparagus\", \"R\": 70, \"G\": 89, \"B\": 69 }, { \"name\": \"Gray (Html/Css Gray)\", \"R\": 128, \"G\": 128, \"B\": 128 }, { \"name\": \"Gray (X11 Gray)\", \"R\": 190, \"G\": 190, \"B\": 190 }, { \"name\": \"Green (Color Wheel) (X11 Green)\", \"R\": 0, \"G\": 255, \"B\": 0 }, { \"name\": \"Green (Crayola)\", \"R\": 28, \"G\": 172, \"B\": 120 }, { \"name\": \"Green (Html/Css Green)\", \"R\": 0, \"G\": 128, \"B\": 0 }, { \"name\": \"Green (Munsell)\", \"R\": 0, \"G\": 168, \"B\": 119 }, { \"name\": \"Green (Ncs)\", \"R\": 0, \"G\": 159, \"B\": 107 }, { \"name\": \"Green (Pigment)\", \"R\": 0, \"G\": 165, \"B\": 80 }, { \"name\": \"Green (Ryb)\", \"R\": 102, \"G\": 176, \"B\": 50 }, { \"name\": \"Green-Yellow\", \"R\": 173, \"G\": 255, \"B\": 47 }, { \"name\": \"Grullo\", \"R\": 169, \"G\": 154, \"B\": 134 }, { \"name\": \"Guppie Green\", \"R\": 0, \"G\": 255, \"B\": 127 }, { \"name\": \"Halayà úBe\", \"R\": 102, \"G\": 56, \"B\": 84 }, { \"name\": \"Han Blue\", \"R\": 68, \"G\": 108, \"B\": 207 }, { \"name\": \"Han Purple\", \"R\": 82, \"G\": 24, \"B\": 250 }, { \"name\": \"Hansa Yellow\", \"R\": 233, \"G\": 214, \"B\": 107 }, { \"name\": \"Harlequin\", \"R\": 63, \"G\": 255, \"B\": 0 }, { \"name\": \"Harvard Crimson\", \"R\": 201, \"G\": 0, \"B\": 22 }, { \"name\": \"Harvest Gold\", \"R\": 218, \"G\": 145, \"B\": 0 }, { \"name\": \"Heart Gold\", \"R\": 128, \"G\": 128, \"B\": 0 }, { \"name\": \"Heliotrope\", \"R\": 223, \"G\": 115, \"B\": 255 }, { \"name\": \"Hollywood Cerise\", \"R\": 244, \"G\": 0, \"B\": 161 }, { \"name\": \"Honeydew\", \"R\": 240, \"G\": 255, \"B\": 240 }, { \"name\": \"Honolulu Blue\", \"R\": 0, \"G\": 127, \"B\": 191 }, { \"name\": \"Hooker'S Green\", \"R\": 73, \"G\": 121, \"B\": 107 }, { \"name\": \"Hot Magenta\", \"R\": 255, \"G\": 29, \"B\": 206 }, { \"name\": \"Hot Pink\", \"R\": 255, \"G\": 105, \"B\": 180 }, { \"name\": \"Hunter Green\", \"R\": 53, \"G\": 94, \"B\": 59 }, { \"name\": \"Iceberg\", \"R\": 113, \"G\": 166, \"B\": 210 }, { \"name\": \"Icterine\", \"R\": 252, \"G\": 247, \"B\": 94 }, { \"name\": \"Imperial Blue\", \"R\": 0, \"G\": 35, \"B\": 149 }, { \"name\": \"Inchworm\", \"R\": 178, \"G\": 236, \"B\": 93 }, { \"name\": \"India Green\", \"R\": 19, \"G\": 136, \"B\": 8 }, { \"name\": \"Indian Red\", \"R\": 205, \"G\": 92, \"B\": 92 }, { \"name\": \"Indian Yellow\", \"R\": 227, \"G\": 168, \"B\": 87 }, { \"name\": \"Indigo\", \"R\": 111, \"G\": 0, \"B\": 255 }, { \"name\": \"Indigo (Dye)\", \"R\": 0, \"G\": 65, \"B\": 106 }, { \"name\": \"Indigo (Web)\", \"R\": 75, \"G\": 0, \"B\": 130 }, { \"name\": \"International Klein Blue\", \"R\": 0, \"G\": 47, \"B\": 167 }, { \"name\": \"International Orange (Aerospace)\", \"R\": 255, \"G\": 79, \"B\": 0 }, { \"name\": \"International Orange (Engineering)\", \"R\": 186, \"G\": 22, \"B\": 12 }, { \"name\": \"International Orange (Golden Gate Bridge)\", \"R\": 192, \"G\": 54, \"B\": 44 }, { \"name\": \"Iris\", \"R\": 90, \"G\": 79, \"B\": 207 }, { \"name\": \"Isabelline\", \"R\": 244, \"G\": 240, \"B\": 236 }, { \"name\": \"Islamic Green\", \"R\": 0, \"G\": 144, \"B\": 0 }, { \"name\": \"Ivory\", \"R\": 255, \"G\": 255, \"B\": 240 }, { \"name\": \"Jade\", \"R\": 0, \"G\": 168, \"B\": 107 }, { \"name\": \"Jasmine\", \"R\": 248, \"G\": 222, \"B\": 126 }, { \"name\": \"Jasper\", \"R\": 215, \"G\": 59, \"B\": 62 }, { \"name\": \"Jazzberry Jam\", \"R\": 165, \"G\": 11, \"B\": 94 }, { \"name\": \"Jet\", \"R\": 52, \"G\": 52, \"B\": 52 }, { \"name\": \"Jonquil\", \"R\": 250, \"G\": 218, \"B\": 94 }, { \"name\": \"June Bud\", \"R\": 189, \"G\": 218, \"B\": 87 }, { \"name\": \"Jungle Green\", \"R\": 41, \"G\": 171, \"B\": 135 }, { \"name\": \"Kelly Green\", \"R\": 76, \"G\": 187, \"B\": 23 }, { \"name\": \"Kenyan Copper\", \"R\": 124, \"G\": 28, \"B\": 5 }, { \"name\": \"Khaki (Html/Css) (Khaki)\", \"R\": 195, \"G\": 176, \"B\": 145 }, { \"name\": \"Khaki (X11) (Light Khaki)\", \"R\": 240, \"G\": 230, \"B\": 140 }, { \"name\": \"Ku Crimson\", \"R\": 232, \"G\": 0, \"B\": 13 }, { \"name\": \"La Salle Green\", \"R\": 8, \"G\": 120, \"B\": 48 }, { \"name\": \"Languid Lavender\", \"R\": 214, \"G\": 202, \"B\": 221 }, { \"name\": \"Lapis Lazuli\", \"R\": 38, \"G\": 97, \"B\": 156 }, { \"name\": \"Laser Lemon\", \"R\": 254, \"G\": 254, \"B\": 34 }, { \"name\": \"Laurel Green\", \"R\": 169, \"G\": 186, \"B\": 157 }, { \"name\": \"Lava\", \"R\": 207, \"G\": 16, \"B\": 32 }, { \"name\": \"Lavender Blue\", \"R\": 204, \"G\": 204, \"B\": 255 }, { \"name\": \"Lavender Blush\", \"R\": 255, \"G\": 240, \"B\": 245 }, { \"name\": \"Lavender (Floral)\", \"R\": 181, \"G\": 126, \"B\": 220 }, { \"name\": \"Lavender Gray\", \"R\": 196, \"G\": 195, \"B\": 208 }, { \"name\": \"Lavender Indigo\", \"R\": 148, \"G\": 87, \"B\": 235 }, { \"name\": \"Lavender Magenta\", \"R\": 238, \"G\": 130, \"B\": 238 }, { \"name\": \"Lavender Mist\", \"R\": 230, \"G\": 230, \"B\": 250 }, { \"name\": \"Lavender Pink\", \"R\": 251, \"G\": 174, \"B\": 210 }, { \"name\": \"Lavender Purple\", \"R\": 150, \"G\": 123, \"B\": 182 }, { \"name\": \"Lavender Rose\", \"R\": 251, \"G\": 160, \"B\": 227 }, { \"name\": \"Lavender (Web)\", \"R\": 230, \"G\": 230, \"B\": 250 }, { \"name\": \"Lawn Green\", \"R\": 124, \"G\": 252, \"B\": 0 }, { \"name\": \"Lemon\", \"R\": 255, \"G\": 247, \"B\": 0 }, { \"name\": \"Lemon Chiffon\", \"R\": 255, \"G\": 250, \"B\": 205 }, { \"name\": \"Lemon Lime\", \"R\": 227, \"G\": 255, \"B\": 0 }, { \"name\": \"Licorice\", \"R\": 26, \"G\": 17, \"B\": 16 }, { \"name\": \"Light Apricot\", \"R\": 253, \"G\": 213, \"B\": 177 }, { \"name\": \"Light Blue\", \"R\": 173, \"G\": 216, \"B\": 230 }, { \"name\": \"Light Brown\", \"R\": 181, \"G\": 101, \"B\": 29 }, { \"name\": \"Light Carmine Pink\", \"R\": 230, \"G\": 103, \"B\": 113 }, { \"name\": \"Light Coral\", \"R\": 240, \"G\": 128, \"B\": 128 }, { \"name\": \"Light Cornflower Blue\", \"R\": 147, \"G\": 204, \"B\": 234 }, { \"name\": \"Light Crimson\", \"R\": 245, \"G\": 105, \"B\": 145 }, { \"name\": \"Light Cyan\", \"R\": 224, \"G\": 255, \"B\": 255 }, { \"name\": \"Light Fuchsia Pink\", \"R\": 249, \"G\": 132, \"B\": 239 }, { \"name\": \"Light Goldenrod Yellow\", \"R\": 250, \"G\": 250, \"B\": 210 }, { \"name\": \"Light Gray\", \"R\": 211, \"G\": 211, \"B\": 211 }, { \"name\": \"Light Green\", \"R\": 144, \"G\": 238, \"B\": 144 }, { \"name\": \"Light Khaki\", \"R\": 240, \"G\": 230, \"B\": 140 }, { \"name\": \"Light Pastel Purple\", \"R\": 177, \"G\": 156, \"B\": 217 }, { \"name\": \"Light Pink\", \"R\": 255, \"G\": 182, \"B\": 193 }, { \"name\": \"Light Red Ochre\", \"R\": 233, \"G\": 116, \"B\": 81 }, { \"name\": \"Light Salmon\", \"R\": 255, \"G\": 160, \"B\": 122 }, { \"name\": \"Light Salmon Pink\", \"R\": 255, \"G\": 153, \"B\": 153 }, { \"name\": \"Light Sea Green\", \"R\": 32, \"G\": 178, \"B\": 170 }, { \"name\": \"Light Sky Blue\", \"R\": 135, \"G\": 206, \"B\": 250 }, { \"name\": \"Light Slate Gray\", \"R\": 119, \"G\": 136, \"B\": 153 }, { \"name\": \"Light Taupe\", \"R\": 179, \"G\": 139, \"B\": 109 }, { \"name\": \"Light Thulian Pink\", \"R\": 230, \"G\": 143, \"B\": 172 }, { \"name\": \"Light Yellow\", \"R\": 255, \"G\": 255, \"B\": 224 }, { \"name\": \"Lilac\", \"R\": 200, \"G\": 162, \"B\": 200 }, { \"name\": \"Lime (Color Wheel)\", \"R\": 191, \"G\": 255, \"B\": 0 }, { \"name\": \"Lime Green\", \"R\": 50, \"G\": 205, \"B\": 50 }, { \"name\": \"Lime (Web) (X11 Green)\", \"R\": 0, \"G\": 255, \"B\": 0 }, { \"name\": \"Limerick\", \"R\": 157, \"G\": 194, \"B\": 9 }, { \"name\": \"Lincoln Green\", \"R\": 25, \"G\": 89, \"B\": 5 }, { \"name\": \"Linen\", \"R\": 250, \"G\": 240, \"B\": 230 }, { \"name\": \"Lion\", \"R\": 193, \"G\": 154, \"B\": 107 }, { \"name\": \"Little Boy Blue\", \"R\": 108, \"G\": 160, \"B\": 220 }, { \"name\": \"Liver\", \"R\": 83, \"G\": 75, \"B\": 79 }, { \"name\": \"Lust\", \"R\": 230, \"G\": 32, \"B\": 32 }, { \"name\": \"Magenta\", \"R\": 255, \"G\": 0, \"B\": 255 }, { \"name\": \"Magenta (Dye)\", \"R\": 202, \"G\": 31, \"B\": 123 }, { \"name\": \"Magenta (Process)\", \"R\": 255, \"G\": 0, \"B\": 144 }, { \"name\": \"Magic Mint\", \"R\": 170, \"G\": 240, \"B\": 209 }, { \"name\": \"Magnolia\", \"R\": 248, \"G\": 244, \"B\": 255 }, { \"name\": \"Mahogany\", \"R\": 192, \"G\": 64, \"B\": 0 }, { \"name\": \"Maize\", \"R\": 251, \"G\": 236, \"B\": 93 }, { \"name\": \"Majorelle Blue\", \"R\": 96, \"G\": 80, \"B\": 220 }, { \"name\": \"Malachite\", \"R\": 11, \"G\": 218, \"B\": 81 }, { \"name\": \"Manatee\", \"R\": 151, \"G\": 154, \"B\": 170 }, { \"name\": \"Mango Tango\", \"R\": 255, \"G\": 130, \"B\": 67 }, { \"name\": \"Mantis\", \"R\": 116, \"G\": 195, \"B\": 101 }, { \"name\": \"Mardi Gras\", \"R\": 136, \"G\": 0, \"B\": 133 }, { \"name\": \"Maroon (Crayola)\", \"R\": 195, \"G\": 33, \"B\": 72 }, { \"name\": \"Maroon (Html/Css)\", \"R\": 128, \"G\": 0, \"B\": 0 }, { \"name\": \"Maroon (X11)\", \"R\": 176, \"G\": 48, \"B\": 96 }, { \"name\": \"Mauve\", \"R\": 224, \"G\": 176, \"B\": 255 }, { \"name\": \"Mauve Taupe\", \"R\": 145, \"G\": 95, \"B\": 109 }, { \"name\": \"Mauvelous\", \"R\": 239, \"G\": 152, \"B\": 170 }, { \"name\": \"Maya Blue\", \"R\": 115, \"G\": 194, \"B\": 251 }, { \"name\": \"Meat Brown\", \"R\": 229, \"G\": 183, \"B\": 59 }, { \"name\": \"Medium Aquamarine\", \"R\": 102, \"G\": 221, \"B\": 170 }, { \"name\": \"Medium Blue\", \"R\": 0, \"G\": 0, \"B\": 205 }, { \"name\": \"Medium Candy Apple Red\", \"R\": 226, \"G\": 6, \"B\": 44 }, { \"name\": \"Medium Carmine\", \"R\": 175, \"G\": 64, \"B\": 53 }, { \"name\": \"Medium Champagne\", \"R\": 243, \"G\": 229, \"B\": 171 }, { \"name\": \"Medium Electric Blue\", \"R\": 3, \"G\": 80, \"B\": 150 }, { \"name\": \"Medium Jungle Green\", \"R\": 28, \"G\": 53, \"B\": 45 }, { \"name\": \"Medium Lavender Magenta\", \"R\": 221, \"G\": 160, \"B\": 221 }, { \"name\": \"Medium Orchid\", \"R\": 186, \"G\": 85, \"B\": 211 }, { \"name\": \"Medium Persian Blue\", \"R\": 0, \"G\": 103, \"B\": 165 }, { \"name\": \"Medium Purple\", \"R\": 147, \"G\": 112, \"B\": 219 }, { \"name\": \"Medium Red-Violet\", \"R\": 187, \"G\": 51, \"B\": 133 }, { \"name\": \"Medium Ruby\", \"R\": 170, \"G\": 64, \"B\": 105 }, { \"name\": \"Medium Sea Green\", \"R\": 60, \"G\": 179, \"B\": 113 }, { \"name\": \"Medium Slate Blue\", \"R\": 123, \"G\": 104, \"B\": 238 }, { \"name\": \"Medium Spring Bud\", \"R\": 201, \"G\": 220, \"B\": 135 }, { \"name\": \"Medium Spring Green\", \"R\": 0, \"G\": 250, \"B\": 154 }, { \"name\": \"Medium Taupe\", \"R\": 103, \"G\": 76, \"B\": 71 }, { \"name\": \"Medium Turquoise\", \"R\": 72, \"G\": 209, \"B\": 204 }, { \"name\": \"Medium Tuscan Red\", \"R\": 121, \"G\": 68, \"B\": 59 }, { \"name\": \"Medium Vermilion\", \"R\": 217, \"G\": 96, \"B\": 59 }, { \"name\": \"Medium Violet-Red\", \"R\": 199, \"G\": 21, \"B\": 133 }, { \"name\": \"Mellow Apricot\", \"R\": 248, \"G\": 184, \"B\": 120 }, { \"name\": \"Mellow Yellow\", \"R\": 248, \"G\": 222, \"B\": 126 }, { \"name\": \"Melon\", \"R\": 253, \"G\": 188, \"B\": 180 }, { \"name\": \"Midnight Blue\", \"R\": 25, \"G\": 25, \"B\": 112 }, { \"name\": \"Midnight Green (Eagle Green)\", \"R\": 0, \"G\": 73, \"B\": 83 }, { \"name\": \"Mikado Yellow\", \"R\": 255, \"G\": 196, \"B\": 12 }, { \"name\": \"Mint\", \"R\": 62, \"G\": 180, \"B\": 137 }, { \"name\": \"Mint Cream\", \"R\": 245, \"G\": 255, \"B\": 250 }, { \"name\": \"Mint Green\", \"R\": 152, \"G\": 255, \"B\": 152 }, { \"name\": \"Misty Rose\", \"R\": 255, \"G\": 228, \"B\": 225 }, { \"name\": \"Moccasin\", \"R\": 250, \"G\": 235, \"B\": 215 }, { \"name\": \"Mode Beige\", \"R\": 150, \"G\": 113, \"B\": 23 }, { \"name\": \"Moonstone Blue\", \"R\": 115, \"G\": 169, \"B\": 194 }, { \"name\": \"Mordant Red 19\", \"R\": 174, \"G\": 12, \"B\": 0 }, { \"name\": \"Moss Green\", \"R\": 173, \"G\": 223, \"B\": 173 }, { \"name\": \"Mountain Meadow\", \"R\": 48, \"G\": 186, \"B\": 143 }, { \"name\": \"Mountbatten Pink\", \"R\": 153, \"G\": 122, \"B\": 141 }, { \"name\": \"Msu Green\", \"R\": 24, \"G\": 69, \"B\": 59 }, { \"name\": \"Mulberry\", \"R\": 197, \"G\": 75, \"B\": 140 }, { \"name\": \"Mustard\", \"R\": 255, \"G\": 219, \"B\": 88 }, { \"name\": \"Myrtle\", \"R\": 33, \"G\": 66, \"B\": 30 }, { \"name\": \"Nadeshiko Pink\", \"R\": 246, \"G\": 173, \"B\": 198 }, { \"name\": \"Napier Green\", \"R\": 42, \"G\": 128, \"B\": 0 }, { \"name\": \"Naples Yellow\", \"R\": 250, \"G\": 218, \"B\": 94 }, { \"name\": \"Navajo White\", \"R\": 255, \"G\": 222, \"B\": 173 }, { \"name\": \"Navy Blue\", \"R\": 0, \"G\": 0, \"B\": 128 }, { \"name\": \"Neon Carrot\", \"R\": 255, \"G\": 163, \"B\": 67 }, { \"name\": \"Neon Fuchsia\", \"R\": 254, \"G\": 65, \"B\": 100 }, { \"name\": \"Neon Green\", \"R\": 57, \"G\": 255, \"B\": 20 }, { \"name\": \"New York Pink\", \"R\": 215, \"G\": 131, \"B\": 127 }, { \"name\": \"Non-Photo Blue\", \"R\": 164, \"G\": 221, \"B\": 237 }, { \"name\": \"North Texas Green\", \"R\": 5, \"G\": 144, \"B\": 51 }, { \"name\": \"Ocean Boat Blue\", \"R\": 0, \"G\": 119, \"B\": 190 }, { \"name\": \"Ochre\", \"R\": 204, \"G\": 119, \"B\": 34 }, { \"name\": \"Office Green\", \"R\": 0, \"G\": 128, \"B\": 0 }, { \"name\": \"Old Gold\", \"R\": 207, \"G\": 181, \"B\": 59 }, { \"name\": \"Old Lace\", \"R\": 253, \"G\": 245, \"B\": 230 }, { \"name\": \"Old Lavender\", \"R\": 121, \"G\": 104, \"B\": 120 }, { \"name\": \"Old Mauve\", \"R\": 103, \"G\": 49, \"B\": 71 }, { \"name\": \"Old Rose\", \"R\": 192, \"G\": 128, \"B\": 129 }, { \"name\": \"Olive\", \"R\": 128, \"G\": 128, \"B\": 0 }, { \"name\": \"Olive Drab #7\", \"R\": 60, \"G\": 52, \"B\": 31 }, { \"name\": \"Olive Drab (Web) (Olive Drab #3)\", \"R\": 107, \"G\": 142, \"B\": 35 }, { \"name\": \"Olivine\", \"R\": 154, \"G\": 185, \"B\": 115 }, { \"name\": \"Onyx\", \"R\": 53, \"G\": 56, \"B\": 57 }, { \"name\": \"Opera Mauve\", \"R\": 183, \"G\": 132, \"B\": 167 }, { \"name\": \"Orange (Color Wheel)\", \"R\": 255, \"G\": 127, \"B\": 0 }, { \"name\": \"Orange Peel\", \"R\": 255, \"G\": 159, \"B\": 0 }, { \"name\": \"Orange-Red\", \"R\": 255, \"G\": 69, \"B\": 0 }, { \"name\": \"Orange (Ryb)\", \"R\": 251, \"G\": 153, \"B\": 2 }, { \"name\": \"Orange (Web Color)\", \"R\": 255, \"G\": 165, \"B\": 0 }, { \"name\": \"Orchid\", \"R\": 218, \"G\": 112, \"B\": 214 }, { \"name\": \"Otter Brown\", \"R\": 101, \"G\": 67, \"B\": 33 }, { \"name\": \"Ou Crimson Red\", \"R\": 153, \"G\": 0, \"B\": 0 }, { \"name\": \"Outer Space\", \"R\": 65, \"G\": 74, \"B\": 76 }, { \"name\": \"Outrageous Orange\", \"R\": 255, \"G\": 110, \"B\": 74 }, { \"name\": \"Oxford Blue\", \"R\": 0, \"G\": 33, \"B\": 71 }, { \"name\": \"Pakistan Green\", \"R\": 0, \"G\": 102, \"B\": 0 }, { \"name\": \"Palatinate Blue\", \"R\": 39, \"G\": 59, \"B\": 226 }, { \"name\": \"Palatinate Purple\", \"R\": 104, \"G\": 40, \"B\": 96 }, { \"name\": \"Pale Aqua\", \"R\": 188, \"G\": 212, \"B\": 230 }, { \"name\": \"Pale Blue\", \"R\": 175, \"G\": 238, \"B\": 238 }, { \"name\": \"Pale Brown\", \"R\": 152, \"G\": 118, \"B\": 84 }, { \"name\": \"Pale Carmine\", \"R\": 175, \"G\": 64, \"B\": 53 }, { \"name\": \"Pale Cerulean\", \"R\": 155, \"G\": 196, \"B\": 226 }, { \"name\": \"Pale Chestnut\", \"R\": 221, \"G\": 173, \"B\": 175 }, { \"name\": \"Pale Copper\", \"R\": 218, \"G\": 138, \"B\": 103 }, { \"name\": \"Pale Cornflower Blue\", \"R\": 171, \"G\": 205, \"B\": 239 }, { \"name\": \"Pale Gold\", \"R\": 230, \"G\": 190, \"B\": 138 }, { \"name\": \"Pale Goldenrod\", \"R\": 238, \"G\": 232, \"B\": 170 }, { \"name\": \"Pale Green\", \"R\": 152, \"G\": 251, \"B\": 152 }, { \"name\": \"Pale Lavender\", \"R\": 220, \"G\": 208, \"B\": 255 }, { \"name\": \"Pale Magenta\", \"R\": 249, \"G\": 132, \"B\": 229 }, { \"name\": \"Pale Pink\", \"R\": 250, \"G\": 218, \"B\": 221 }, { \"name\": \"Pale Plum\", \"R\": 221, \"G\": 160, \"B\": 221 }, { \"name\": \"Pale Red-Violet\", \"R\": 219, \"G\": 112, \"B\": 147 }, { \"name\": \"Pale Robin Egg Blue\", \"R\": 150, \"G\": 222, \"B\": 209 }, { \"name\": \"Pale Silver\", \"R\": 201, \"G\": 192, \"B\": 187 }, { \"name\": \"Pale Spring Bud\", \"R\": 236, \"G\": 235, \"B\": 189 }, { \"name\": \"Pale Taupe\", \"R\": 188, \"G\": 152, \"B\": 126 }, { \"name\": \"Pale Violet-Red\", \"R\": 219, \"G\": 112, \"B\": 147 }, { \"name\": \"Pansy Purple\", \"R\": 120, \"G\": 24, \"B\": 74 }, { \"name\": \"Papaya Whip\", \"R\": 255, \"G\": 239, \"B\": 213 }, { \"name\": \"Paris Green\", \"R\": 80, \"G\": 200, \"B\": 120 }, { \"name\": \"Pastel Blue\", \"R\": 174, \"G\": 198, \"B\": 207 }, { \"name\": \"Pastel Brown\", \"R\": 131, \"G\": 105, \"B\": 83 }, { \"name\": \"Pastel Gray\", \"R\": 207, \"G\": 207, \"B\": 196 }, { \"name\": \"Pastel Green\", \"R\": 119, \"G\": 221, \"B\": 119 }, { \"name\": \"Pastel Magenta\", \"R\": 244, \"G\": 154, \"B\": 194 }, { \"name\": \"Pastel Orange\", \"R\": 255, \"G\": 179, \"B\": 71 }, { \"name\": \"Pastel Pink\", \"R\": 222, \"G\": 165, \"B\": 164 }, { \"name\": \"Pastel Purple\", \"R\": 179, \"G\": 158, \"B\": 181 }, { \"name\": \"Pastel Red\", \"R\": 255, \"G\": 105, \"B\": 97 }, { \"name\": \"Pastel Violet\", \"R\": 203, \"G\": 153, \"B\": 201 }, { \"name\": \"Pastel Yellow\", \"R\": 253, \"G\": 253, \"B\": 150 }, { \"name\": \"Patriarch\", \"R\": 128, \"G\": 0, \"B\": 128 }, { \"name\": \"Payne'S Grey\", \"R\": 83, \"G\": 104, \"B\": 120 }, { \"name\": \"Peach\", \"R\": 255, \"G\": 229, \"B\": 180 }, { \"name\": \"Peach (Crayola)\", \"R\": 255, \"G\": 203, \"B\": 164 }, { \"name\": \"Peach-Orange\", \"R\": 255, \"G\": 204, \"B\": 153 }, { \"name\": \"Peach Puff\", \"R\": 255, \"G\": 218, \"B\": 185 }, { \"name\": \"Peach-Yellow\", \"R\": 250, \"G\": 223, \"B\": 173 }, { \"name\": \"Pear\", \"R\": 209, \"G\": 226, \"B\": 49 }, { \"name\": \"Pearl\", \"R\": 234, \"G\": 224, \"B\": 200 }, { \"name\": \"Pearl Aqua\", \"R\": 136, \"G\": 216, \"B\": 192 }, { \"name\": \"Pearly Purple\", \"R\": 183, \"G\": 104, \"B\": 162 }, { \"name\": \"Peridot\", \"R\": 230, \"G\": 226, \"B\": 0 }, { \"name\": \"Periwinkle\", \"R\": 204, \"G\": 204, \"B\": 255 }, { \"name\": \"Persian Blue\", \"R\": 28, \"G\": 57, \"B\": 187 }, { \"name\": \"Persian Green\", \"R\": 0, \"G\": 166, \"B\": 147 }, { \"name\": \"Persian Indigo\", \"R\": 50, \"G\": 18, \"B\": 122 }, { \"name\": \"Persian Orange\", \"R\": 217, \"G\": 144, \"B\": 88 }, { \"name\": \"Persian Pink\", \"R\": 247, \"G\": 127, \"B\": 190 }, { \"name\": \"Persian Plum\", \"R\": 112, \"G\": 28, \"B\": 28 }, { \"name\": \"Persian Red\", \"R\": 204, \"G\": 51, \"B\": 51 }, { \"name\": \"Persian Rose\", \"R\": 254, \"G\": 40, \"B\": 162 }, { \"name\": \"Persimmon\", \"R\": 236, \"G\": 88, \"B\": 0 }, { \"name\": \"Peru\", \"R\": 205, \"G\": 133, \"B\": 63 }, { \"name\": \"Phlox\", \"R\": 223, \"G\": 0, \"B\": 255 }, { \"name\": \"Phthalo Blue\", \"R\": 0, \"G\": 15, \"B\": 137 }, { \"name\": \"Phthalo Green\", \"R\": 18, \"G\": 53, \"B\": 36 }, { \"name\": \"Piggy Pink\", \"R\": 253, \"G\": 221, \"B\": 230 }, { \"name\": \"Pine Green\", \"R\": 1, \"G\": 121, \"B\": 111 }, { \"name\": \"Pink\", \"R\": 255, \"G\": 192, \"B\": 203 }, { \"name\": \"Pink Lace\", \"R\": 255, \"G\": 221, \"B\": 244 }, { \"name\": \"Pink-Orange\", \"R\": 255, \"G\": 153, \"B\": 102 }, { \"name\": \"Pink Pearl\", \"R\": 231, \"G\": 172, \"B\": 207 }, { \"name\": \"Pink Sherbet\", \"R\": 247, \"G\": 143, \"B\": 167 }, { \"name\": \"Pistachio\", \"R\": 147, \"G\": 197, \"B\": 114 }, { \"name\": \"Platinum\", \"R\": 229, \"G\": 228, \"B\": 226 }, { \"name\": \"Plum (Traditional)\", \"R\": 142, \"G\": 69, \"B\": 133 }, { \"name\": \"Plum (Web)\", \"R\": 221, \"G\": 160, \"B\": 221 }, { \"name\": \"Portland Orange\", \"R\": 255, \"G\": 90, \"B\": 54 }, { \"name\": \"Powder Blue (Web)\", \"R\": 176, \"G\": 224, \"B\": 230 }, { \"name\": \"Princeton Orange\", \"R\": 255, \"G\": 143, \"B\": 0 }, { \"name\": \"Prune\", \"R\": 112, \"G\": 28, \"B\": 28 }, { \"name\": \"Prussian Blue\", \"R\": 0, \"G\": 49, \"B\": 83 }, { \"name\": \"Psychedelic Purple\", \"R\": 223, \"G\": 0, \"B\": 255 }, { \"name\": \"Puce\", \"R\": 204, \"G\": 136, \"B\": 153 }, { \"name\": \"Pumpkin\", \"R\": 255, \"G\": 117, \"B\": 24 }, { \"name\": \"Purple Heart\", \"R\": 105, \"G\": 53, \"B\": 156 }, { \"name\": \"Purple (Html/Css)\", \"R\": 128, \"G\": 0, \"B\": 128 }, { \"name\": \"Purple Mountain Majesty\", \"R\": 150, \"G\": 120, \"B\": 182 }, { \"name\": \"Purple (Munsell)\", \"R\": 159, \"G\": 0, \"B\": 197 }, { \"name\": \"Purple Pizzazz\", \"R\": 254, \"G\": 78, \"B\": 218 }, { \"name\": \"Purple Taupe\", \"R\": 80, \"G\": 64, \"B\": 77 }, { \"name\": \"Purple (X11)\", \"R\": 160, \"G\": 32, \"B\": 240 }, { \"name\": \"Quartz\", \"R\": 81, \"G\": 72, \"B\": 79 }, { \"name\": \"Rackley\", \"R\": 93, \"G\": 138, \"B\": 168 }, { \"name\": \"Radical Red\", \"R\": 255, \"G\": 53, \"B\": 94 }, { \"name\": \"Rajah\", \"R\": 251, \"G\": 171, \"B\": 96 }, { \"name\": \"Raspberry\", \"R\": 227, \"G\": 11, \"B\": 93 }, { \"name\": \"Raspberry Glace\", \"R\": 145, \"G\": 95, \"B\": 109 }, { \"name\": \"Raspberry Pink\", \"R\": 226, \"G\": 80, \"B\": 152 }, { \"name\": \"Raspberry Rose\", \"R\": 179, \"G\": 68, \"B\": 108 }, { \"name\": \"Raw Umber\", \"R\": 130, \"G\": 102, \"B\": 68 }, { \"name\": \"Razzle Dazzle Rose\", \"R\": 255, \"G\": 51, \"B\": 204 }, { \"name\": \"Razzmatazz\", \"R\": 227, \"G\": 37, \"B\": 107 }, { \"name\": \"Red\", \"R\": 255, \"G\": 0, \"B\": 0 }, { \"name\": \"Red-Brown\", \"R\": 165, \"G\": 42, \"B\": 42 }, { \"name\": \"Red Devil\", \"R\": 134, \"G\": 1, \"B\": 17 }, { \"name\": \"Red (Munsell)\", \"R\": 242, \"G\": 0, \"B\": 60 }, { \"name\": \"Red (Ncs)\", \"R\": 196, \"G\": 2, \"B\": 51 }, { \"name\": \"Red-Orange\", \"R\": 255, \"G\": 83, \"B\": 73 }, { \"name\": \"Red (Pigment)\", \"R\": 237, \"G\": 28, \"B\": 36 }, { \"name\": \"Red (Ryb)\", \"R\": 254, \"G\": 39, \"B\": 18 }, { \"name\": \"Red-Violet\", \"R\": 199, \"G\": 21, \"B\": 133 }, { \"name\": \"Redwood\", \"R\": 171, \"G\": 78, \"B\": 82 }, { \"name\": \"Regalia\", \"R\": 82, \"G\": 45, \"B\": 128 }, { \"name\": \"Resolution Blue\", \"R\": 0, \"G\": 35, \"B\": 135 }, { \"name\": \"Rich Black\", \"R\": 0, \"G\": 64, \"B\": 64 }, { \"name\": \"Rich Brilliant Lavender\", \"R\": 241, \"G\": 167, \"B\": 254 }, { \"name\": \"Rich Carmine\", \"R\": 215, \"G\": 0, \"B\": 64 }, { \"name\": \"Rich Electric Blue\", \"R\": 8, \"G\": 146, \"B\": 208 }, { \"name\": \"Rich Lavender\", \"R\": 167, \"G\": 107, \"B\": 207 }, { \"name\": \"Rich Lilac\", \"R\": 182, \"G\": 102, \"B\": 210 }, { \"name\": \"Rich Maroon\", \"R\": 176, \"G\": 48, \"B\": 96 }, { \"name\": \"Rifle Green\", \"R\": 65, \"G\": 72, \"B\": 51 }, { \"name\": \"Robin Egg Blue\", \"R\": 0, \"G\": 204, \"B\": 204 }, { \"name\": \"Rose\", \"R\": 255, \"G\": 0, \"B\": 127 }, { \"name\": \"Rose Bonbon\", \"R\": 249, \"G\": 66, \"B\": 158 }, { \"name\": \"Rose Ebony\", \"R\": 103, \"G\": 72, \"B\": 70 }, { \"name\": \"Rose Gold\", \"R\": 183, \"G\": 110, \"B\": 121 }, { \"name\": \"Rose Madder\", \"R\": 227, \"G\": 38, \"B\": 54 }, { \"name\": \"Rose Pink\", \"R\": 255, \"G\": 102, \"B\": 204 }, { \"name\": \"Rose Quartz\", \"R\": 170, \"G\": 152, \"B\": 169 }, { \"name\": \"Rose Taupe\", \"R\": 144, \"G\": 93, \"B\": 93 }, { \"name\": \"Rose Vale\", \"R\": 171, \"G\": 78, \"B\": 82 }, { \"name\": \"Rosewood\", \"R\": 101, \"G\": 0, \"B\": 11 }, { \"name\": \"Rosso Corsa\", \"R\": 212, \"G\": 0, \"B\": 0 }, { \"name\": \"Rosy Brown\", \"R\": 188, \"G\": 143, \"B\": 143 }, { \"name\": \"Royal Azure\", \"R\": 0, \"G\": 56, \"B\": 168 }, { \"name\": \"Royal Blue (Traditional)\", \"R\": 0, \"G\": 35, \"B\": 102 }, { \"name\": \"Royal Blue (Web)\", \"R\": 65, \"G\": 105, \"B\": 225 }, { \"name\": \"Royal Fuchsia\", \"R\": 202, \"G\": 44, \"B\": 146 }, { \"name\": \"Royal Purple\", \"R\": 120, \"G\": 81, \"B\": 169 }, { \"name\": \"Royal Yellow\", \"R\": 250, \"G\": 218, \"B\": 94 }, { \"name\": \"Rubine Red\", \"R\": 209, \"G\": 0, \"B\": 86 }, { \"name\": \"Ruby\", \"R\": 224, \"G\": 17, \"B\": 95 }, { \"name\": \"Ruby Red\", \"R\": 155, \"G\": 17, \"B\": 30 }, { \"name\": \"Ruddy\", \"R\": 255, \"G\": 0, \"B\": 40 }, { \"name\": \"Ruddy Brown\", \"R\": 187, \"G\": 101, \"B\": 40 }, { \"name\": \"Ruddy Pink\", \"R\": 225, \"G\": 142, \"B\": 150 }, { \"name\": \"Rufous\", \"R\": 168, \"G\": 28, \"B\": 7 }, { \"name\": \"Russet\", \"R\": 128, \"G\": 70, \"B\": 27 }, { \"name\": \"Rust\", \"R\": 183, \"G\": 65, \"B\": 14 }, { \"name\": \"Rusty Red\", \"R\": 218, \"G\": 44, \"B\": 67 }, { \"name\": \"Sacramento State Green\", \"R\": 0, \"G\": 86, \"B\": 63 }, { \"name\": \"Saddle Brown\", \"R\": 139, \"G\": 69, \"B\": 19 }, { \"name\": \"Safety Orange (Blaze Orange)\", \"R\": 255, \"G\": 103, \"B\": 0 }, { \"name\": \"Saffron\", \"R\": 244, \"G\": 196, \"B\": 48 }, { \"name\": \"Salmon\", \"R\": 255, \"G\": 140, \"B\": 105 }, { \"name\": \"Salmon Pink\", \"R\": 255, \"G\": 145, \"B\": 164 }, { \"name\": \"Sand\", \"R\": 194, \"G\": 178, \"B\": 128 }, { \"name\": \"Sand Dune\", \"R\": 150, \"G\": 113, \"B\": 23 }, { \"name\": \"Sandstorm\", \"R\": 236, \"G\": 213, \"B\": 64 }, { \"name\": \"Sandy Brown\", \"R\": 244, \"G\": 164, \"B\": 96 }, { \"name\": \"Sandy Taupe\", \"R\": 150, \"G\": 113, \"B\": 23 }, { \"name\": \"Sangria\", \"R\": 146, \"G\": 0, \"B\": 10 }, { \"name\": \"Sap Green\", \"R\": 80, \"G\": 125, \"B\": 42 }, { \"name\": \"Sapphire\", \"R\": 15, \"G\": 82, \"B\": 186 }, { \"name\": \"Sapphire Blue\", \"R\": 0, \"G\": 103, \"B\": 165 }, { \"name\": \"Satin Sheen Gold\", \"R\": 203, \"G\": 161, \"B\": 53 }, { \"name\": \"Scarlet\", \"R\": 255, \"G\": 36, \"B\": 0 }, { \"name\": \"Scarlet (Crayola)\", \"R\": 253, \"G\": 14, \"B\": 53 }, { \"name\": \"School Bus Yellow\", \"R\": 255, \"G\": 216, \"B\": 0 }, { \"name\": \"Screamin' Green\", \"R\": 118, \"G\": 255, \"B\": 122 }, { \"name\": \"Sea Blue\", \"R\": 0, \"G\": 105, \"B\": 148 }, { \"name\": \"Sea Green\", \"R\": 46, \"G\": 139, \"B\": 87 }, { \"name\": \"Seal Brown\", \"R\": 50, \"G\": 20, \"B\": 20 }, { \"name\": \"Seashell\", \"R\": 255, \"G\": 245, \"B\": 238 }, { \"name\": \"Selective Yellow\", \"R\": 255, \"G\": 186, \"B\": 0 }, { \"name\": \"Sepia\", \"R\": 112, \"G\": 66, \"B\": 20 }, { \"name\": \"Shadow\", \"R\": 138, \"G\": 121, \"B\": 93 }, { \"name\": \"Shamrock Green\", \"R\": 0, \"G\": 158, \"B\": 96 }, { \"name\": \"Shocking Pink\", \"R\": 252, \"G\": 15, \"B\": 192 }, { \"name\": \"Shocking Pink (Crayola)\", \"R\": 255, \"G\": 111, \"B\": 255 }, { \"name\": \"Sienna\", \"R\": 136, \"G\": 45, \"B\": 23 }, { \"name\": \"Silver\", \"R\": 192, \"G\": 192, \"B\": 192 }, { \"name\": \"Sinopia\", \"R\": 203, \"G\": 65, \"B\": 11 }, { \"name\": \"Skobeloff\", \"R\": 0, \"G\": 116, \"B\": 116 }, { \"name\": \"Sky Blue\", \"R\": 135, \"G\": 206, \"B\": 235 }, { \"name\": \"Sky Magenta\", \"R\": 207, \"G\": 113, \"B\": 175 }, { \"name\": \"Slate Blue\", \"R\": 106, \"G\": 90, \"B\": 205 }, { \"name\": \"Slate Gray\", \"R\": 112, \"G\": 128, \"B\": 144 }, { \"name\": \"Smalt (Dark Powder Blue)\", \"R\": 0, \"G\": 51, \"B\": 153 }, { \"name\": \"Smokey Topaz\", \"R\": 147, \"G\": 61, \"B\": 65 }, { \"name\": \"Smoky Black\", \"R\": 16, \"G\": 12, \"B\": 8 }, { \"name\": \"Snow\", \"R\": 255, \"G\": 250, \"B\": 250 }, { \"name\": \"Spiro Disco Ball\", \"R\": 15, \"G\": 192, \"B\": 252 }, { \"name\": \"Spring Bud\", \"R\": 167, \"G\": 252, \"B\": 0 }, { \"name\": \"Spring Green\", \"R\": 0, \"G\": 255, \"B\": 127 }, { \"name\": \"St. Patrick'S Blue\", \"R\": 35, \"G\": 41, \"B\": 122 }, { \"name\": \"Steel Blue\", \"R\": 70, \"G\": 130, \"B\": 180 }, { \"name\": \"Stil De Grain Yellow\", \"R\": 250, \"G\": 218, \"B\": 94 }, { \"name\": \"Stizza\", \"R\": 153, \"G\": 0, \"B\": 0 }, { \"name\": \"Stormcloud\", \"R\": 79, \"G\": 102, \"B\": 106 }, { \"name\": \"Straw\", \"R\": 228, \"G\": 217, \"B\": 111 }, { \"name\": \"Sunglow\", \"R\": 255, \"G\": 204, \"B\": 51 }, { \"name\": \"Sunset\", \"R\": 250, \"G\": 214, \"B\": 165 }, { \"name\": \"Tan\", \"R\": 210, \"G\": 180, \"B\": 140 }, { \"name\": \"Tangelo\", \"R\": 249, \"G\": 77, \"B\": 0 }, { \"name\": \"Tangerine\", \"R\": 242, \"G\": 133, \"B\": 0 }, { \"name\": \"Tangerine Yellow\", \"R\": 255, \"G\": 204, \"B\": 0 }, { \"name\": \"Tango Pink\", \"R\": 228, \"G\": 113, \"B\": 122 }, { \"name\": \"Taupe\", \"R\": 72, \"G\": 60, \"B\": 50 }, { \"name\": \"Taupe Gray\", \"R\": 139, \"G\": 133, \"B\": 137 }, { \"name\": \"Tea Green\", \"R\": 208, \"G\": 240, \"B\": 192 }, { \"name\": \"Tea Rose (Orange)\", \"R\": 248, \"G\": 131, \"B\": 121 }, { \"name\": \"Tea Rose (Rose)\", \"R\": 244, \"G\": 194, \"B\": 194 }, { \"name\": \"Teal\", \"R\": 0, \"G\": 128, \"B\": 128 }, { \"name\": \"Teal Blue\", \"R\": 54, \"G\": 117, \"B\": 136 }, { \"name\": \"Teal Green\", \"R\": 0, \"G\": 130, \"B\": 127 }, { \"name\": \"Telemagenta\", \"R\": 207, \"G\": 52, \"B\": 118 }, { \"name\": \"Tenné (Tawny)\", \"R\": 205, \"G\": 87, \"B\": 0 }, { \"name\": \"Terra Cotta\", \"R\": 226, \"G\": 114, \"B\": 91 }, { \"name\": \"Thistle\", \"R\": 216, \"G\": 191, \"B\": 216 }, { \"name\": \"Thulian Pink\", \"R\": 222, \"G\": 111, \"B\": 161 }, { \"name\": \"Tickle Me Pink\", \"R\": 252, \"G\": 137, \"B\": 172 }, { \"name\": \"Tiffany Blue\", \"R\": 10, \"G\": 186, \"B\": 181 }, { \"name\": \"Tiger'S Eye\", \"R\": 224, \"G\": 141, \"B\": 60 }, { \"name\": \"Timberwolf\", \"R\": 219, \"G\": 215, \"B\": 210 }, { \"name\": \"Titanium Yellow\", \"R\": 238, \"G\": 230, \"B\": 0 }, { \"name\": \"Tomato\", \"R\": 255, \"G\": 99, \"B\": 71 }, { \"name\": \"Toolbox\", \"R\": 116, \"G\": 108, \"B\": 192 }, { \"name\": \"Topaz\", \"R\": 255, \"G\": 200, \"B\": 124 }, { \"name\": \"Tractor Red\", \"R\": 253, \"G\": 14, \"B\": 53 }, { \"name\": \"Trolley Grey\", \"R\": 128, \"G\": 128, \"B\": 128 }, { \"name\": \"Tropical Rain Forest\", \"R\": 0, \"G\": 117, \"B\": 94 }, { \"name\": \"True Blue\", \"R\": 0, \"G\": 115, \"B\": 207 }, { \"name\": \"Tufts Blue\", \"R\": 65, \"G\": 125, \"B\": 193 }, { \"name\": \"Tumbleweed\", \"R\": 222, \"G\": 170, \"B\": 136 }, { \"name\": \"Turkish Rose\", \"R\": 181, \"G\": 114, \"B\": 129 }, { \"name\": \"Turquoise\", \"R\": 48, \"G\": 213, \"B\": 200 }, { \"name\": \"Turquoise Blue\", \"R\": 0, \"G\": 255, \"B\": 239 }, { \"name\": \"Turquoise Green\", \"R\": 160, \"G\": 214, \"B\": 180 }, { \"name\": \"Tuscan Red\", \"R\": 124, \"G\": 72, \"B\": 72 }, { \"name\": \"Twilight Lavender\", \"R\": 138, \"G\": 73, \"B\": 107 }, { \"name\": \"Tyrian Purple\", \"R\": 102, \"G\": 2, \"B\": 60 }, { \"name\": \"Ua Blue\", \"R\": 0, \"G\": 51, \"B\": 170 }, { \"name\": \"Ua Red\", \"R\": 217, \"G\": 0, \"B\": 76 }, { \"name\": \"Ube\", \"R\": 136, \"G\": 120, \"B\": 195 }, { \"name\": \"Ucla Blue\", \"R\": 83, \"G\": 104, \"B\": 149 }, { \"name\": \"Ucla Gold\", \"R\": 255, \"G\": 179, \"B\": 0 }, { \"name\": \"Ufo Green\", \"R\": 60, \"G\": 208, \"B\": 112 }, { \"name\": \"Ultra Pink\", \"R\": 255, \"G\": 111, \"B\": 255 }, { \"name\": \"Ultramarine\", \"R\": 18, \"G\": 10, \"B\": 143 }, { \"name\": \"Ultramarine Blue\", \"R\": 65, \"G\": 102, \"B\": 245 }, { \"name\": \"Umber\", \"R\": 99, \"G\": 81, \"B\": 71 }, { \"name\": \"Unbleached Silk\", \"R\": 255, \"G\": 221, \"B\": 202 }, { \"name\": \"United Nations Blue\", \"R\": 91, \"G\": 146, \"B\": 229 }, { \"name\": \"University Of California Gold\", \"R\": 183, \"G\": 135, \"B\": 39 }, { \"name\": \"Unmellow Yellow\", \"R\": 255, \"G\": 255, \"B\": 102 }, { \"name\": \"Up Forest Green\", \"R\": 1, \"G\": 68, \"B\": 33 }, { \"name\": \"Up Maroon\", \"R\": 123, \"G\": 17, \"B\": 19 }, { \"name\": \"Upsdell Red\", \"R\": 174, \"G\": 32, \"B\": 41 }, { \"name\": \"Urobilin\", \"R\": 225, \"G\": 173, \"B\": 33 }, { \"name\": \"Usafa Blue\", \"R\": 0, \"G\": 79, \"B\": 152 }, { \"name\": \"Usc Cardinal\", \"R\": 153, \"G\": 0, \"B\": 0 }, { \"name\": \"Usc Gold\", \"R\": 255, \"G\": 204, \"B\": 0 }, { \"name\": \"Utah Crimson\", \"R\": 211, \"G\": 0, \"B\": 63 }, { \"name\": \"Vanilla\", \"R\": 243, \"G\": 229, \"B\": 171 }, { \"name\": \"Vegas Gold\", \"R\": 197, \"G\": 179, \"B\": 88 }, { \"name\": \"Venetian Red\", \"R\": 200, \"G\": 8, \"B\": 21 }, { \"name\": \"Verdigris\", \"R\": 67, \"G\": 179, \"B\": 174 }, { \"name\": \"Vermilion (Cinnabar)\", \"R\": 227, \"G\": 66, \"B\": 52 }, { \"name\": \"Vermilion (Plochere)\", \"R\": 217, \"G\": 96, \"B\": 59 }, { \"name\": \"Veronica\", \"R\": 160, \"G\": 32, \"B\": 240 }, { \"name\": \"Violet\", \"R\": 143, \"G\": 0, \"B\": 255 }, { \"name\": \"Violet-Blue\", \"R\": 50, \"G\": 74, \"B\": 178 }, { \"name\": \"Violet (Color Wheel)\", \"R\": 127, \"G\": 0, \"B\": 255 }, { \"name\": \"Violet (Ryb)\", \"R\": 134, \"G\": 1, \"B\": 175 }, { \"name\": \"Violet (Web)\", \"R\": 238, \"G\": 130, \"B\": 238 }, { \"name\": \"Viridian\", \"R\": 64, \"G\": 130, \"B\": 109 }, { \"name\": \"Vivid Auburn\", \"R\": 146, \"G\": 39, \"B\": 36 }, { \"name\": \"Vivid Burgundy\", \"R\": 159, \"G\": 29, \"B\": 53 }, { \"name\": \"Vivid Cerise\", \"R\": 218, \"G\": 29, \"B\": 129 }, { \"name\": \"Vivid Tangerine\", \"R\": 255, \"G\": 160, \"B\": 137 }, { \"name\": \"Vivid Violet\", \"R\": 159, \"G\": 0, \"B\": 255 }, { \"name\": \"Warm Black\", \"R\": 0, \"G\": 66, \"B\": 66 }, { \"name\": \"Waterspout\", \"R\": 164, \"G\": 244, \"B\": 249 }, { \"name\": \"Wenge\", \"R\": 100, \"G\": 84, \"B\": 82 }, { \"name\": \"Wheat\", \"R\": 245, \"G\": 222, \"B\": 179 }, { \"name\": \"White\", \"R\": 255, \"G\": 255, \"B\": 255 }, { \"name\": \"White Smoke\", \"R\": 245, \"G\": 245, \"B\": 245 }, { \"name\": \"Wild Blue Yonder\", \"R\": 162, \"G\": 173, \"B\": 208 }, { \"name\": \"Wild Strawberry\", \"R\": 255, \"G\": 67, \"B\": 164 }, { \"name\": \"Wild Watermelon\", \"R\": 252, \"G\": 108, \"B\": 133 }, { \"name\": \"Wine\", \"R\": 114, \"G\": 47, \"B\": 55 }, { \"name\": \"Wine Dregs\", \"R\": 103, \"G\": 49, \"B\": 71 }, { \"name\": \"Wisteria\", \"R\": 201, \"G\": 160, \"B\": 220 }, { \"name\": \"Wood Brown\", \"R\": 193, \"G\": 154, \"B\": 107 }, { \"name\": \"Xanadu\", \"R\": 115, \"G\": 134, \"B\": 120 }, { \"name\": \"Yale Blue\", \"R\": 15, \"G\": 77, \"B\": 146 }, { \"name\": \"Yellow\", \"R\": 255, \"G\": 255, \"B\": 0 }, { \"name\": \"Yellow-Green\", \"R\": 154, \"G\": 205, \"B\": 50 }, { \"name\": \"Yellow (Munsell)\", \"R\": 239, \"G\": 204, \"B\": 0 }, { \"name\": \"Yellow (Ncs)\", \"R\": 255, \"G\": 211, \"B\": 0 }, { \"name\": \"Yellow Orange\", \"R\": 255, \"G\": 174, \"B\": 66 }, { \"name\": \"Yellow (Process)\", \"R\": 255, \"G\": 239, \"B\": 0 }, { \"name\": \"Yellow (Ryb)\", \"R\": 254, \"G\": 254, \"B\": 51 }, { \"name\": \"Zaffre\", \"R\": 0, \"G\": 20, \"B\": 168 }, { \"name\": \"Zinnwaldite Brown\", \"R\": 44, \"G\": 22, \"B\": 8 } ]";
            String min_name = "";
            try {
                JSONArray j_array = new JSONArray(s);
                int min_d = Integer.MAX_VALUE;
                for(int i=0;i<j_array.length();i++){

                    JSONObject col = j_array.getJSONObject(i);
                    String col_name = col.getString("name");
                    int R_c = col.getInt("R");
                    int G_c = col.getInt("G");
                    int B_c = col.getInt("B");

                    int d = (int) (Math.pow(R-R_c,2)+Math.pow(B-B_c,2)+Math.pow(G-G_c,2));

                    if(d<min_d){
                        min_name = col_name;
                        min_d=d;
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            color_value_tv.setText(min_name);
            color_value_tv.setVisibility(View.VISIBLE);
        }
    }
}