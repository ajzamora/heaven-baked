package com.ajzamora.heavenbaked.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.OnConflictStrategy;

@Entity(primaryKeys = {"recipe_id", "ingredient_id"})
public class RecipeIngredientCrossRef {
    @ColumnInfo(name = "recipe_id")
    public long recipeId;
    @ColumnInfo(name = "ingredient_id")
    public long ingredientId;
    @ColumnInfo(name = "recipe_ingredient_quantity")
    public int quantity;
    @ColumnInfo(name = "recipe_ingredient_measure_id")
    public int measureId;
}
