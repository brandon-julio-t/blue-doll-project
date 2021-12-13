package edu.binus.bluedollproject.facades;

import java.util.HashMap;
import java.util.Map;

import edu.binus.bluedollproject.R;

public class ImageResolver {
    private static final Map<String, Integer> mappings = new HashMap<>();

    static {
        mappings.put("reimu", R.mipmap.ic_reimu);
        mappings.put("marisa", R.mipmap.ic_marisa);
        mappings.put("kanna", R.mipmap.ic_kanna);
    }

    public static int get(String name) {
        return mappings.get(name);
    }
}
