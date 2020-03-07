package com.gahlot.makemytripinterview.utils;

import android.util.Log;

import com.gahlot.makemytripinterview.model.Exclude_list;
import com.gahlot.makemytripinterview.model.Root;
import com.gahlot.makemytripinterview.model.Variant_groups;
import com.gahlot.makemytripinterview.model.Variations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    private static final String TAG = "Utils";

    private Map<String, List<Variations>> map = new HashMap<>();
    private List<List<Exclude_list>> exclude_list;
    private List<Variant_groups> variant_groups;

    public Map<String, List<Variations>> getMappedGroupIDVariationID(Root root) {
        variant_groups = root.getVariants().getVariant_groups();
        exclude_list = root.getVariants().getExclude_list();
        for (int i=0; i<variant_groups.size(); i++) {
            if (variant_groups.get(i).getName().equals("Size")) {
                map.put(variant_groups.get(i).getGroup_id(), variant_groups.get(i).getVariations());
                } else if (variant_groups.get(i).getName().equals("Sauce")) {
                map.put(variant_groups.get(i).getGroup_id(),variant_groups.get(i).getVariations());
            }
            }
        return map;
    }

    public List<Variations> getSizes(Map<String, List<Variations>> map, List<List<Exclude_list>> exclude_list, String pizze_type, List<Variant_groups> variant_groups) {
        List<Variations> size = new ArrayList<>();
        String group_id = null;
        String variation_id = null;
        String excludedGroup_id = null;
        String exculdedVariation_id = null;

        for (int i = 0; i < variant_groups.size(); i++) {
            for (int j = 0; j < variant_groups.get(i).getVariations().size(); j++) {
                if (variant_groups.get(i).getVariations().get(j).getName().equals(pizze_type)) {
                    group_id = variant_groups.get(i).getGroup_id();
                    variation_id = variant_groups.get(i).getVariations().get(j).getId();
                }
            }
        }
        for (int i = 0; i < exclude_list.size(); i++) {
            for (int j = 0; j < exclude_list.get(i).size(); j++) {
                if (exclude_list.get(i).get(j).getGroup_id().equals(group_id) && exclude_list.get(i).get(j).getVariation_id().equals(variation_id)) {
                    //variation_id = exclude_list.get(i).get(j).getVariation_id();
                    excludedGroup_id = exclude_list.get(i).get(j + 1).getGroup_id();
                    exculdedVariation_id = exclude_list.get(i).get(j + 1).getVariation_id();
                }
            }
        }
        if (map.containsKey(excludedGroup_id) && excludedGroup_id != null && exculdedVariation_id != null) {
                List<Variations> v = map.get(excludedGroup_id);
                for (int i = 0; i < v.size(); i++) {
                    if (!v.get(i).getId().equals(exculdedVariation_id)) {
                        //size.add(v.get(i).getName());
                        size.add(v.get(i));
                    }
                }
            return size;
        } else {
            List<Variations> v = variant_groups.get(1).getVariations();
                for (int i = 0; i < v.size(); i++) {
                    //size.add(v.get(i).getName());
                    size.add(v.get(i));
                }
            return size;
        }
    }

    public List<Variations> getSauces(Map<String, List<Variations>> map, List<List<Exclude_list>> exclude_list, String pizza_size, List<Variant_groups> variant_groups) {
        List<Variations> sauces = new ArrayList<>();
        String group_id = null;
        String variation_id = null;
        String excludedGroup_id = null;
        String exculdedVariation_id = null;

        for (int i = 0; i < variant_groups.size(); i++) {
            for (int j = 0; j < variant_groups.get(i).getVariations().size(); j++) {
                if (variant_groups.get(i).getVariations().get(j).getName().equals(pizza_size)) {
                    group_id = variant_groups.get(i).getGroup_id();
                    variation_id = variant_groups.get(i).getVariations().get(j).getId();
                    Log.d(TAG, "getSizes: group id: " + group_id);
                    Log.d(TAG, "getSizes: Variation Id: " + variation_id);
                }
            }
        }
        for (int i = 0; i < exclude_list.size(); i++) {
            for (int j = 0; j < exclude_list.get(i).size(); j++) {
                if (exclude_list.get(i).get(j).getGroup_id().equals(group_id) && exclude_list.get(i).get(j).getVariation_id().equals(variation_id) && j < exclude_list.get(i).size() - 1) {
                    //variation_id = exclude_list.get(i).get(j).getVariation_id();
                    excludedGroup_id = exclude_list.get(i).get(j+1).getGroup_id();
                    exculdedVariation_id = exclude_list.get(i).get(j+1).getVariation_id();
                    Log.d(TAG, "getSizes: Excluded Group id: " + excludedGroup_id);
                    Log.d(TAG, "getSizes: Excluded Variation id: " + exculdedVariation_id);
                }
            }
        }
        if (map.containsKey(excludedGroup_id) && excludedGroup_id != null && exculdedVariation_id != null) {
            List<Variations> v = map.get(excludedGroup_id);
                for (int i = 0; i < v.size(); i++) {
                    if (!v.get(i).getId().equals(exculdedVariation_id)) {
                        //sauces.add(v.get(i).getName());
                        sauces.add(v.get(i));
                    }
                }
            return sauces;
        } else {
            List<Variations> v = variant_groups.get(2).getVariations();
            for (int i = 0; i < v.size(); i++) {
                //sauces.add(v.get(i).getName());
                sauces.add(v.get(i));
            }
            return sauces;
        }
    }

}
