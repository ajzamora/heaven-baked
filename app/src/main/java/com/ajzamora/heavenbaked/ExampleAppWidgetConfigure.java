package com.ajzamora.heavenbaked;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RemoteViews;

import com.ajzamora.heavenbaked.providers.HeavenBakedWidgetProvider;
import com.ajzamora.heavenbaked.ui.MainActivity;

public class ExampleAppWidgetConfigure extends Activity {
    static final String TAG = "ExampleAppWidgetConfigure";
    private static final String PREFS_NAME
            = "com.example.android.apis.appwidget.ExampleAppWidgetProvider";
    private static final String PREF_PREFIX_KEY = "prefix_";
    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    public ExampleAppWidgetConfigure() {
        super();
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if they press the back button.
        setResult(RESULT_CANCELED);
        // Find the widget id from the intent.

        // Bind the action for the save button.
        findViewById(R.id.widget_layout).setOnClickListener(mOnClickListener);

//
//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        if (extras != null) {
//            mAppWidgetId = extras.getInt(
//                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
//        }
//        // If they gave us an intent without the widget id, just bail.
//        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
//            finish();
//        }
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            final Context context = ExampleAppWidgetConfigure.this;

            AppWidgetManager mgr = AppWidgetManager.getInstance(context);
            RemoteViews defaultViews = new RemoteViews(context.getPackageName(), R.layout.heaven_baked_widget);
            Intent idefault = new Intent(context, MainActivity.class);
            idefault.putExtra("widget", "1");
            PendingIntent defaultpendingIntent = PendingIntent.getActivity(context, 0, idefault, 0);
            defaultViews.setOnClickPendingIntent(R.id.widget_layout, defaultpendingIntent);
            ComponentName comp = new ComponentName(context.getPackageName(), HeavenBakedWidgetProvider.class.getName());
            mgr.updateAppWidget(comp, defaultViews);
        }
    };
}
