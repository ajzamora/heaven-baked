package com.ajzamora.heavenbaked.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ajzamora.heavenbaked.R;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_RECIPE = "extra_recipe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }
}
