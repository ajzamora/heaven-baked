package com.ajzamora.heavenbaked.data.converters;

import androidx.room.TypeConverter;

import com.ajzamora.heavenbaked.data.Step;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class StepListConverter {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static List<Step> stringToStepList(String stepStr) {
        if (stepStr == null) { return Collections.emptyList(); }

        Type listType = new TypeToken<List<Step>>() {}.getType();
        return gson.fromJson(stepStr, listType);
    }

    @TypeConverter
    public static String stepListToString(List<Step> stepList) {
        return gson.toJson(stepList);
    }
}
