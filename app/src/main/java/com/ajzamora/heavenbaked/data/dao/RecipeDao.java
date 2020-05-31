package com.ajzamora.heavenbaked.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.ajzamora.heavenbaked.data.entity.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {
    @Query("SELECT * FROM recipe")
    LiveData<List<Recipe>> getRecipeList();

    @Query("SELECT * FROM recipe WHERE id = :id")
    LiveData<Recipe> getRecipeById(int id);

    @Insert
    void insertRecipe(Recipe recipe);

    @Transaction
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    void insertAllRecipe(List<Recipe> recipe);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updateRecipe(Recipe recipe);

    @Delete
    void deleteRecipe(Recipe recipe);
}
