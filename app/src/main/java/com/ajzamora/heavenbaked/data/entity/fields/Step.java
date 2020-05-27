package com.ajzamora.heavenbaked.data.entity.fields;

import androidx.room.ColumnInfo;

public class Step {
    @ColumnInfo(name = "steps_json_array")
    private String stepsJsonArray;

    public String getStepsJsonArray() {
        return "getStepsJsonArray";
    }

    public void setStepsJsonArray(String stepsJsonArray) {
        this.stepsJsonArray = stepsJsonArray;
    }
}