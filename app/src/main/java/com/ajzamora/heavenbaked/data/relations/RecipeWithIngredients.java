package com.ajzamora.heavenbaked.data.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.ajzamora.heavenbaked.data.entity.Ingredient;
import com.ajzamora.heavenbaked.data.entity.Recipe;
import com.ajzamora.heavenbaked.data.entity.RecipeIngredientCrossRef;

import java.util.List;

public class RecipeWithIngredients {
    @Embedded
    public Recipe recipe;
    @Relation(
            parentColumn = "recipe_id",
            entityColumn = "ingredient_id",
            associateBy = @Junction(RecipeIngredientCrossRef.class)
    )
    public List<Ingredient> ingredients;
}

