package com.gahlot.makemytripinterview.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Variant_groups {

    private String group_id;

    private String name;

    private List<Variations> variations;

    public void setGroup_id(String group_id){
        this.group_id = group_id;
    }
    public String getGroup_id(){
        return this.group_id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setVariations(List<Variations> variations){
        this.variations = variations;
    }
    public List<Variations> getVariations(){
        return this.variations;
    }


}
