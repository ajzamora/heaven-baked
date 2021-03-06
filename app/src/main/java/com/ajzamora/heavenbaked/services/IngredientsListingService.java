package com.ajzamora.heavenbaked.services;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import com.ajzamora.heavenbaked.providers.HeavenBakedWidgetProvider;
import com.ajzamora.heavenbaked.data.entity.Recipe;
import com.ajzamora.heavenbaked.ui.DetailActivity;

public class IngredientsListingService extends IntentService {
    public static final String ACTION_LIST_INGREDIENTS = "com.ajzamora.heavenbaked.action.list_ingredients";
    public static final String ACTION_INITIALIZE_WIDGET = "com.ajzamora.heavenbaked.action.initialize_widget";

    public IngredientsListingService() {
        super("IngredientsListingService");
    }

    public static void startListingIngredients(Context context, Recipe recipe) {
        Intent intent = new Intent(context, IngredientsListingService.class);
        intent.setAction(ACTION_LIST_INGREDIENTS);
        intent.putExtra(DetailActivity.EXTRA_RECIPE, (Parcelable) recipe);
        context.startService(intent);
    }

    public static void initializeWidget(Context context) {
        Intent intent = new Intent(context, IngredientsListingService.class);
        intent.setAction(ACTION_INITIALIZE_WIDGET);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_LIST_INGREDIENTS.equals(action)) {
                Recipe recipe = intent.getParcelableExtra(DetailActivity.EXTRA_RECIPE);
                handleActionListIngredients(recipe);
            } else if (ACTION_INITIALIZE_WIDGET.equals(action)) {
                handleWidgetInitialization();
            }
        }
    }

    private void handleWidgetInitialization() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);


    }

    private void handleActionListIngredients(Recipe recipe) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, HeavenBakedWidgetProvider.class));
        HeavenBakedWidgetProvider.updateRecipeWidgets(this, appWidgetManager, recipe, appWidgetIds);
    }
}

