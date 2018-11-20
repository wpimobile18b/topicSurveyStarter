package com.example.owner.grocerylist.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.owner.grocerylist.Database.GroceryRepository;
import com.example.owner.grocerylist.Entities.GroceryEntity;

import java.util.List;

public class GroceryViewModel extends AndroidViewModel {

    private GroceryRepository myGroceryRepository;
    private LiveData<List<GroceryEntity>> myAllGroceries;

    public GroceryViewModel(@NonNull Application _app) {
        super(_app);
        myGroceryRepository = new GroceryRepository(_app);
        myAllGroceries = myGroceryRepository.getMyAllGrocery();
    }

    public LiveData<List<GroceryEntity>> getMyAllGroceries() { return this.myAllGroceries; }
    public void insert(GroceryEntity _grocery) { myGroceryRepository.insert(_grocery); }
}
