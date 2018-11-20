package com.example.owner.grocerylist.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Objects;

@Entity(tableName = "grocery_table")
public class GroceryEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "_id")
    private int _id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    //TODO : add quantity, notes, and filepath fields


    public GroceryEntity(@NonNull String name, @NonNull String quantity, @NonNull String notes, @NonNull String filepath) {
        this.name = name;
        this.quantity = quantity;
        this.notes = notes;
        this.filepath = filepath;
    }

    @NonNull
    public int get_id() {
        return _id;
    }

    public void set_id(@NonNull int _id) {
        this._id = _id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }


    //TODO : add getters and setters for quantity, notes, and filepath fields



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroceryEntity)) return false;
        GroceryEntity that = (GroceryEntity) o;
        return get_id() == that.get_id() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getQuantity(), that.getQuantity()) &&
                Objects.equals(getNotes(), that.getNotes());
    }

    @Override
    public int hashCode() {

        return Objects.hash(get_id(), getName(), getQuantity(), getNotes());
    }

    @Override
    public String toString() {
        return "GroceryEntity{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                ", notes='" + notes + '\'' +
                ", filepath='" + filepath + '\''+
                '}';
    }
}
