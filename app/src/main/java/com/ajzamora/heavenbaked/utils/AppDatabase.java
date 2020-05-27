package com.ajzamora.heavenbaked.utils;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ajzamora.heavenbaked.data.dao.RecipeDao;
import com.ajzamora.heavenbaked.data.entity.Ingredient;
import com.ajzamora.heavenbaked.data.entity.Measure;
import com.ajzamora.heavenbaked.data.entity.Recipe;
import com.ajzamora.heavenbaked.data.entity.RecipeIngredientCrossRef;

@Database(entities = {Recipe.class, Ingredient.class, RecipeIngredientCrossRef.class, Measure.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "heavenbaked";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        // Queries should be done in a separate thread to avoid locking the UI
                        // We will allow this ONLY TEMPORALLY to see that our DB is working
                        .allowMainThreadQueries()
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    public abstract RecipeDao recipeDao();

}