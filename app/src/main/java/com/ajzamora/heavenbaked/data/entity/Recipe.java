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
    @ColumnInfo (name = "recipe_measure_id")
    private int recipeMeasureId;
    @Embedded
    public Step step;

    public Recipe(long id, String name, int recipeMeasureId, Step step) {
        this.id = id;
        this.name = name;
        this.recipeMeasureId = recipeMeasureId;
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

    public int getRecipeMeasureId() {
        return recipeMeasureId;
    }

    public void setRecipeMeasureId(int recipeMeasureId) {
        this.recipeMeasureId = recipeMeasureId;
    }
}