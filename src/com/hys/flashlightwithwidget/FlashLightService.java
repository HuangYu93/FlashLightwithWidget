package com.hys.flashlightwithwidget;

//import com.pxc.widget.packet.UpdateService;
//import com.pxc.widget.packet.mywidget;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
//import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.IBinder;
import android.util.Log;
//import android.webkit.WebView.FindListener;
import android.widget.RemoteViews;

public class FlashLightService extends Service {

	private boolean isTurnOn = false;
	private Camera camera;
	private Parameters mParameters;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
	
		Log.i("HYS' s TAG", ">>>>>>>>>>Service onBind>>>>>>>>>>");
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Log.i("HYS' s TAG", ">>>>>>>>>>Service onCreate>>>>>>>>>>");
		super.onCreate();
	}
	
	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		Log.i("HYS' s TAG", ">>>>>>>>>>Service onStart>>>>>>>>>>");
		
		
		AppWidgetManager mAppWidgetManager = AppWidgetManager.getInstance(FlashLightService.this);
		if(!isTurnOn)
		{
			camera = Camera.open();
			mParameters = camera.getParameters();
			mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
			camera.setParameters(mParameters);
			
			RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.widget_main);
			remoteViews.setImageViewResource(R.id.btnFlashLight, R.drawable.flashlight_on_icon);
			mAppWidgetManager = AppWidgetManager.getInstance(FlashLightService.this);
			ComponentName thisName = new ComponentName(this, MainWidget.class);
			mAppWidgetManager.updateAppWidget(thisName, remoteViews);
			isTurnOn = true;
		}
		else
		{
			mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
			camera.setParameters(mParameters);
			camera.release();
			
			RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.widget_main);
			remoteViews.setImageViewResource(R.id.btnFlashLight, R.drawable.flashlight_off_icon);
			mAppWidgetManager = AppWidgetManager.getInstance(FlashLightService.this);
			ComponentName thisName = new ComponentName(this, MainWidget.class);
			mAppWidgetManager.updateAppWidget(thisName, remoteViews);
			isTurnOn = false;
			
			stopSelf();
		}
	}
	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i("HYS' s TAG", ">>>>>>>>>>Service onStartCommand>>>>>>>>>>");
		return super.onStartCommand(intent, flags, startId);
	}
	
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("HYS' s TAG", ">>>>>>>>>>Service onDestroy>>>>>>>>>>");
		
		if(isTurnOn)
		{
			mParameters = camera.getParameters();
			mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
			camera.setParameters(mParameters);
			camera.release();
			isTurnOn = false;
		}
	}
	
	
}
