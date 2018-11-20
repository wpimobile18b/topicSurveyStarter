package com.example.owner.grocerylist.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.owner.grocerylist.Dao.GroceryDao;
import com.example.owner.grocerylist.Entities.GroceryEntity;

import java.util.List;

public class GroceryRepository {

    public static final String TAG = "GROCERY_REPOSITORY";

    private GroceryDao myGroceryDao;
    private LiveData<List<GroceryEntity>> myAllGrocery;

    public GroceryRepository(Application _app){
        GroceryDatabase db = GroceryDatabase.getDatabase(_app);
        myGroceryDao = db.groceryDao();
        myAllGrocery = myGroceryDao.getAllGroceries();
    }

    public LiveData<List<GroceryEntity>> getMyAllGrocery(){ return this.myAllGrocery; };

    public void insert (GroceryEntity _grocery) {
        new insertAsyncTask(myGroceryDao).execute(_grocery);
    }

    private static class insertAsyncTask extends AsyncTask<GroceryEntity, Void, Void> {

        private GroceryDao myAsyncTaskDao;

        insertAsyncTask(GroceryDao dao) { myAsyncTaskDao = dao; }

        @Override
        protected Void doInBackground(final GroceryEntity... params) {
            myAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
