package com.example.owner.grocerylist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.owner.grocerylist.Entities.GroceryEntity;
import com.example.owner.grocerylist.ViewModels.GroceryViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MAIN_ACTIVITY";
    public static final int NEW_GROCERY_ACTIVITY_REQUEST_CODE = 1;
    public static final int STORAGE_PERMISSION = 2;

    private GroceryViewModel groceryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groceryViewModel = ViewModelProviders.of(this).get(GroceryViewModel.class);

        final GroceryListAdapter adapter = new GroceryListAdapter(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        groceryViewModel.getMyAllGroceries().observe(this, new Observer<List<GroceryEntity>>() {
            @Override
            public void onChanged(@Nullable List<GroceryEntity> groceryEntities) {
                adapter.setGroceries(groceryEntities);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddGroceryActivity.class);
                startActivityForResult(intent, NEW_GROCERY_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_GROCERY_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String name = data.getStringExtra(AddGroceryActivity.EXTRA_NAME);
            String notes = data.getStringExtra(AddGroceryActivity.EXTRA_NOTES);
            String quantity = data.getStringExtra(AddGroceryActivity.EXTRA_QUANTITY);
            String filepath = data.getStringExtra(AddGroceryActivity.EXTRA_FILEPATH);

            GroceryEntity grocery = new GroceryEntity(name, quantity, notes, filepath);
            groceryViewModel.insert(grocery);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
