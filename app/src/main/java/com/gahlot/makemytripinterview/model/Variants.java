package com.gahlot.makemytripinterview.model;

import java.util.ArrayList;
import java.util.List;

public class Variants {

    private List<Variant_groups> variant_groups;

    private List<List<Exclude_list>> exclude_list;

    public Variants(List<Variant_groups> variant_groups, List<List<Exclude_list>> exclude_list) {
        this.variant_groups = variant_groups;
        this.exclude_list = exclude_list;
    }

    public void setVariant_groups(List<Variant_groups> variant_groups){
        this.variant_groups = variant_groups;
    }

    public List<Variant_groups> getVariant_groups(){
        return this.variant_groups;
    }
    public void setExclude_list(List<List<Exclude_list>> exclude_list){
        this.exclude_list = exclude_list;
    }

    public List<List<Exclude_list>> getExclude_list(){
        return this.exclude_list;
    }



}
