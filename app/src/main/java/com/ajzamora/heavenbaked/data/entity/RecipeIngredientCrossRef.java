package com.ajzamora.heavenbaked.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"recipe_id", "ingredient_id"})
public class RecipeIngredientCrossRef {
    @ColumnInfo(name = "recipe_id")
    public long recipeId;
    @ColumnInfo(name = "ingredient_id")
    public long ingredientId;
    public int quantity;
}
