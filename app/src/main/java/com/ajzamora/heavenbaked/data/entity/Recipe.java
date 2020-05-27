package com.ajzamora.heavenbaked.data.entity;


import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.ajzamora.heavenbaked.data.entity.fields.Step;

@Entity
public class Recipe {
    @PrimaryKey
    @ColumnInfo (name = "recipe_id")
    private long id;
    @ColumnInfo (name = "recipe_name")
    private String name;
    @ColumnInfo (name = "recipe_servings")
    private int servings;
    @ColumnInfo (name = "recipe_image")
    private String image;
    @Embedded
    public Step step;

    public Recipe(long id, String name, int servings, String image, Step step) {
        this.id = id;
        this.name = name;
        this.servings = servings;
        this.image = image;
        this.step = step;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }
}