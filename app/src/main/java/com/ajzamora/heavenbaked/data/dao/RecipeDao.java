package com.ajzamora.heavenbaked.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.ajzamora.heavenbaked.data.entity.Recipe;
import com.ajzamora.heavenbaked.data.entity.RecipeIngredientCrossRef;
import com.ajzamora.heavenbaked.data.relations.RecipeWithIngredients;
import com.ajzamora.heavenbaked.data.relations.RecipeWithIngredientsAndMeasure;

import java.util.List;

@Dao
public interface RecipeDao {
    @Transaction
    @Query("SELECT * FROM recipe")
    public List<RecipeWithIngredients> getRecipesWithIngredients();

    @Transaction
    @Query("SELECT * FROM Measure")
    public List<RecipeWithIngredientsAndMeasure> getRecipesWithIngredientsAndMeasure();


    @Query("SELECT * FROM recipe")
    List<Recipe> loadAllTasks();

    @Insert
    void insertRecipe(Recipe recipe);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateRecipe(Recipe recipe);

    @Delete
    void deleteRecipe(Recipe recipe);
}
