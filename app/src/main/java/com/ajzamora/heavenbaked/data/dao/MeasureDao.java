package com.ajzamora.heavenbaked.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import com.ajzamora.heavenbaked.data.entity.Measure;

@Dao
public interface MeasureDao {
    @Insert
    void insertMeasure(Measure measure);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMeasure(Measure measure);

    @Delete
    void deleteMeasure(Measure measure);
}
