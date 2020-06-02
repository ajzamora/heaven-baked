package com.ajzamora.heavenbaked;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.text.method.ScrollingMovementMethod;
import android.widget.RemoteViews;

import com.ajzamora.heavenbaked.data.Ingredient;
import com.ajzamora.heavenbaked.data.entity.Recipe;
import com.ajzamora.heavenbaked.ui.DetailActivity;
import com.ajzamora.heavenbaked.utils.FormatUtils;

import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class HeavenBakedWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, Recipe recipe,
                                int appWidgetId) {

        // Create an Intent to launch DetailActivity when clicked
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_RECIPE, (Parcelable) recipe);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.heaven_baked_widget);
        views.setTextViewText(R.id.tv_recipe_label_widget, recipe.getName());
        views.setTextViewText(R.id.tv_ingredients_widget, formatIngredientList(recipe.getIngredients()));
        // Widgets allow click handlers to only launch pending intents
        views.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private static String formatIngredientList(List<Ingredient> ingredients) {
        final char SEPARATOR = ' ';
        final String HYPHEN_IN_SPACES = " - ";
        StringBuilder formattedIngredientListBuilder = new StringBuilder();
        for (Ingredient ingredient : ingredients) {

            formattedIngredientListBuilder.append(FormatUtils.toFormattedStringDecimal(ingredient.getQuantity(), 2));

            formattedIngredientListBuilder.append(SEPARATOR)
                    .append(ingredient.getMeasure());

            formattedIngredientListBuilder.append(HYPHEN_IN_SPACES).append(ingredient.getIngredient());
            formattedIngredientListBuilder.append("\n");
        }

        return formattedIngredientListBuilder.toString();
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
//        IngredientsListingService.startListingIngredients(context);
    }

    public static void updateRecipeWidgets(Context context, AppWidgetManager appWidgetManager,
                                           Recipe recipe, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, recipe, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

