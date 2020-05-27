package com.ajzamora.heavenbaked.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Measure {
    @PrimaryKey
    @ColumnInfo (name = "measure_id")
    private int id;
    @ColumnInfo (name = "measure_name")
    private String name;

    public Measure(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
