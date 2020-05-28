package com.ajzamora.heavenbaked.utils;

import com.ajzamora.heavenbaked.data.Ingredient;
import com.ajzamora.heavenbaked.data.Step;
import com.ajzamora.heavenbaked.data.entity.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class RecipeUtils {
    public static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net";
    public static final String TOPHER_2017_SAMPLE_BAKING_PATH = "topher/2017/May/59121517_baking/baking.json";
    public static final String RECIPE_ID = "id";
    public static final String RECIPE_NAME = "name";
    public static final String RECIPE_INGREDIENTS = "ingredients";
    public static final String RECIPE_STEPS = "steps";
    public static final String RECIPE_SERVINGS = "servings";
    public static final String RECIPE_IMAGE = "image";

    public static final String INGREDIENT_NAME = "ingredient";
    public static final String INGREDIENT_QUANTITY = "quantity";
    public static final String INGREDIENT_MEASURE = "measure";

    public static final String STEP_NUM = "id";
    public static final String STEP_SHORT_DESCRIPTION = "shortDescription";
    public static final String STEP_DESCRIPTION = "description";
    public static final String STEP_VIDEO_URL = "videoURL";
    public static final String STEP_THUMBNAIL_URL = "thumbnailURL";

    public static List<Recipe> parseRecipe(JSONArray recipeJSONArr) throws JSONException {
        List<Recipe> results = new ArrayList<>();
        for (int i = 0, size = recipeJSONArr.length(); i < size; i++) {
            results.add(parseRecipe(recipeJSONArr.getJSONObject(i)));
        }
        return results;
    }

    public static List<Ingredient> parseIngredient(JSONArray ingredientsJSONArr) throws JSONException {
        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0, size = ingredientsJSONArr.length(); i < size; i++) {
            JSONObject ingredientJSONObj = ingredientsJSONArr.getJSONObject(i);
            ingredients.add(parseIngredient(ingredientJSONObj));
        }
        return ingredients;
    }

    public static List<Step> parseStep(JSONArray stepJSONArr) throws JSONException {
        List<Step> steps = new ArrayList<>();
        for (int i = 0, size = stepJSONArr.length(); i < size; i++) {
            JSONObject stepJSONObj = stepJSONArr.getJSONObject(i);
            steps.add(parseStep(stepJSONObj));
        }

        return steps;
    }

    // code below can be parsed with gson
    public static Recipe parseRecipe(JSONObject recipeJSONObj) throws JSONException {
        long id = recipeJSONObj.getLong(RECIPE_ID);
        String name = recipeJSONObj.getString(RECIPE_NAME);
        JSONArray ingredientsJSONArr = recipeJSONObj.getJSONArray(RECIPE_INGREDIENTS);
        JSONArray stepsJSONArr = recipeJSONObj.getJSONArray(RECIPE_STEPS);
        int servings = recipeJSONObj.getInt(RECIPE_SERVINGS);
        String image = recipeJSONObj.getString(RECIPE_IMAGE);

        List<Ingredient> ingredients = parseIngredient(ingredientsJSONArr);
        List<Step> steps = parseStep(stepsJSONArr);

        return new Recipe(id, name, ingredients, steps, servings, image);
    }



    public static Ingredient parseIngredient(JSONObject ingredientJSONObj) throws JSONException {
        String name = ingredientJSONObj.getString(INGREDIENT_NAME);
        int quantity = ingredientJSONObj.getInt(INGREDIENT_QUANTITY);
        String measure = ingredientJSONObj.getString(INGREDIENT_MEASURE);

        return new Ingredient(quantity, measure, name);
    }

    public static Step parseStep(JSONObject stepJSONObj) throws JSONException {
        int stepNum = stepJSONObj.getInt(STEP_NUM);
        String shortDescription = stepJSONObj.getString(STEP_SHORT_DESCRIPTION);
        String description = stepJSONObj.getString(STEP_DESCRIPTION);
        String videoURL = stepJSONObj.getString(STEP_VIDEO_URL);
        String thumbnailURL = stepJSONObj.getString(STEP_THUMBNAIL_URL);

        return new Step(stepNum, shortDescription, description, videoURL, thumbnailURL);
    }
}
