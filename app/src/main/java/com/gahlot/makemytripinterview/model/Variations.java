package com.gahlot.makemytripinterview.model;

import androidx.annotation.NonNull;

public class Variations {

    private String name;

    private int price;

    private int baseDefault;

    private String id;

    private int inStock;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public int getPrice(){
        return this.price;
    }
    public void setDefault(int baseDefault){
        this.baseDefault = baseDefault;
    }
    public int getDefault(){
        return this.baseDefault;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setInStock(int inStock){
        this.inStock = inStock;
    }
    public int getInStock(){
        return this.inStock;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
