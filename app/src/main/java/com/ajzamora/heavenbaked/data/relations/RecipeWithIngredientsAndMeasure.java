package com.ajzamora.heavenbaked.data.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.ajzamora.heavenbaked.data.entity.Measure;
import com.ajzamora.heavenbaked.data.entity.RecipeIngredientCrossRef;

import java.util.List;

public class RecipeWithIngredientsAndMeasure {
    @Embedded
    public Measure measure;
    @Relation(
            parentColumn = "measure_id",
            entityColumn = "recipe_ingredient_measure_id"
    )
    public List<RecipeIngredientCrossRef> recipesWithIngredients;
}
