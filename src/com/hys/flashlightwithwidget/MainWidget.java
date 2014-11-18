package com.hys.flashlightwithwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class MainWidget extends AppWidgetProvider {
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		Log.i("HYS' s TAG", ">>>>>>>>>>>onUpdate>>>>>>>>>>");
		
		
		Intent intent = new Intent(context, FlashLightService.class);
		PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_main);
		
		remoteViews.setOnClickPendingIntent(R.id.btnFlashLight, pendingIntent);
		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
		
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
		Log.i("HYS' s TAG", ">>>>>>>>>>>onDeleted>>>>>>>>>>");
		Intent intent = new Intent(context, FlashLightService.class);
		context.stopService(intent);
	}

	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
		Log.i("HYS' s TAG", ">>>>>>>>>>>onEnabled>>>>>>>>>>");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
		Log.i("HYS' s TAG", ">>>>>>>>>>>onReceive>>>>>>>>>>");
		
		//Intent intent1 = new Intent(context, FlashLightService.class);
		//context.startService(new Intent(context, FlashLightService.class));

	}

	
	@Override
	public void onDisabled(Context context) {
		// TODO Auto-generated method stub
		super.onDisabled(context);
		Log.i("HYS' s TAG", ">>>>>>>>>>>onDisabled>>>>>>>>>>");
		context.stopService(new Intent(context, FlashLightService.class));
	}
}
