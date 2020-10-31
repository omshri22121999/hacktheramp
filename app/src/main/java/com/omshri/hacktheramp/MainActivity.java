package com.omshri.hacktheramp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView dress_rv;
    ArrayList<Dresses> dress_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dress_rv = findViewById(R.id.dress_rv);
        dress_list = new ArrayList<>();
        String gdrive_prefix = "https://docs.google.com/uc?id=";
        dress_list.add(new Dresses(gdrive_prefix+"1-_W48f8ikKxAAWjngH4bklExagCdt8ho","White Tshirt",1999));
        dress_list.add(new Dresses(gdrive_prefix+"12NHbFaFiRzbUNr7JLpi72mN5TCb5FZ0u","Red Jacket",2399));
        dress_list.add(new Dresses(gdrive_prefix+"1ocySxaA89mmM6iYLXgWTDc9O3VXf_1AE","Yellow Hoodie",2099));
        dress_list.add(new Dresses(gdrive_prefix+"1G4O5xlfjjo8Wt1q5p0UUYLtwA-b7DAJX","Black Jumper",2499));

        dress_rv = findViewById(R.id.dress_rv);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        dress_rv.setLayoutManager(layoutManager);

        DressAdapter dressAdapter = new DressAdapter(dress_list,this);

        dress_rv.setAdapter(dressAdapter);

        dressAdapter.notifyDataSetChanged();
    }
}