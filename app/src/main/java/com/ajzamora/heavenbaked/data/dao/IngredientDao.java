package com.ajzamora.heavenbaked.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import com.ajzamora.heavenbaked.data.entity.Ingredient;

@Dao
public interface IngredientDao {
    @Insert
    void insertIngredient(Ingredient ingredient);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateIngredient(Ingredient ingredient);

    @Delete
    void deleteIngredient(Ingredient ingredient);
}
