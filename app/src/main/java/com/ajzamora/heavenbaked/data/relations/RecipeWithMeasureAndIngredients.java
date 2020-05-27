package com.ajzamora.heavenbaked.data.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.ajzamora.heavenbaked.data.entity.Measure;
import com.ajzamora.heavenbaked.data.entity.Recipe;

import java.util.List;

public class RecipeWithMeasureAndIngredients {
    @Embedded
    public Measure measure;
    @Relation(
            entity = Recipe.class,
            parentColumn = "measure_id",
            entityColumn = "recipe_measure_id"
    )
    public List<RecipeWithIngredients> recipes;
}
