package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Sandwich> SandwichList;
    private RecyclerView RecyclerViews;
    private SAdapter sanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.sandwiches_RecyclerView);

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_names);
        String[] sandwichList = getResources().getStringArray(R.array.sandwich_details);

        SandwichList = new ArrayList<>();
        for (int i = 0; i < sandwiches.length; i++) {
            String json = sandwichList[i];
            Sandwich sandwich = null;
            sandwich = JsonUtils.parseSandwichJson(json);
            SandwichList.add(sandwich);
        }

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        SAdapter sanAdapter = new SAdapter(SandwichList);
        recyclerView.setAdapter(sanAdapter);;
    }

}
