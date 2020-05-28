package com.ajzamora.heavenbaked.data.converters;

import androidx.room.TypeConverter;

import com.ajzamora.heavenbaked.data.Ingredient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class IngredientListConverter {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static List<Ingredient> stringToIngredientList(String ingredientStr) {
        if (ingredientStr == null) { return Collections.emptyList(); }

        Type listType = new TypeToken<List<Ingredient>>() {}.getType();
        return gson.fromJson(ingredientStr, listType);
    }

    @TypeConverter
    public static String ingredientListToString(List<Ingredient> ingredientList) {
        return gson.toJson(ingredientList);
    }

}
