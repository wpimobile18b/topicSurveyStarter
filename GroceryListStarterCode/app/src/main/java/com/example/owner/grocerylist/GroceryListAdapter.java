package com.example.owner.grocerylist;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.owner.grocerylist.Entities.GroceryEntity;

import java.util.List;

public class GroceryListAdapter extends RecyclerView.Adapter<GroceryListAdapter.GroceryViewHolder>{

    public static final String TAG = "GROCERY_LIST_ADAPTER";

    class GroceryViewHolder extends RecyclerView.ViewHolder {
        private final TextView groceryNameView;
        private final TextView groceryQuantityView;
        private final TextView groceryNotesView;
        private final ImageView groceryImageView;

        private GroceryViewHolder(View itemView) {
            super(itemView);
            groceryNameView = itemView.findViewById(R.id.recyclerview_name);
            groceryQuantityView = itemView.findViewById(R.id.recyclerview_quantity);
            groceryNotesView = itemView.findViewById(R.id.recyclerview_notes);
            groceryImageView = itemView.findViewById(R.id.recyclerview_image);
        }
    }

    private final LayoutInflater mInflater;
    private List<GroceryEntity> mGrocery; // Cached copy of Groceries

    GroceryListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public GroceryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new GroceryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GroceryViewHolder holder, int position) {
        if (mGrocery != null) {
            GroceryEntity current = mGrocery.get(position);
            holder.groceryNameView.setText(current.getName());
            holder.groceryNotesView.setText(current.getNotes());
            holder.groceryQuantityView.setText(current.getQuantity());
            holder.groceryImageView.setImageBitmap(BitmapFactory.decodeFile(current.getFilepath()));
        } else {
            // Covers the case of data not being ready yet.
            holder.groceryNameView.setText("No Grocery");
        }
    }

    void setGroceries(List<GroceryEntity> grocery){
        mGrocery = grocery;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mCats has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mGrocery != null)
            return mGrocery.size();
        else return 0;
    }
}
