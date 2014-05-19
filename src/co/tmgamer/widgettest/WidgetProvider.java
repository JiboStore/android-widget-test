/**
 * 
 */
package co.tmgamer.widgettest;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * @author hakim
 *
 */
public class WidgetProvider extends AppWidgetProvider {
	
	public static final String TAG = "WidgetProvider";

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		Log.d( TAG, "WidgetProvider.onReceive " + intent.getAction() );
		Toast.makeText( context, "WidgetProvider.onReceive " + intent.getAction(), Toast.LENGTH_LONG );
		
		Bundle pExtra = intent.getExtras();
		if ( pExtra != null && intent.getAction().equals("WIDGET_PROVIDER_CLICKED") ) {
			SharedPreferences pref = context.getApplicationContext().getSharedPreferences("widget_pref", Context.MODE_PRIVATE );
			SharedPreferences.Editor edit = pref.edit();
			int iWidgetId = intent.getExtras().getInt("widgetId");
			int iCount = pref.getInt("widgetCount", 0);
			for ( int i = 0; i < 1; i++ ) {
				iCount = pref.getInt("widgetCount", 0);
				iCount++;
				edit.putInt("widgetCount", iCount);
				boolean bSaved = edit.commit();
//				pref.edit().putInt("widgetCount", iCount);
//				boolean bSaved = pref.edit().commit();
				bSaved = pref.contains("widgetCount");
				Log.w(TAG, "WidgetProvider.onReceive: " + iWidgetId + " > " + iCount + " result: " + bSaved);
			}
			
			RemoteViews remoteViews = new RemoteViews( context.getPackageName(), R.layout.widget_layout );
			remoteViews.setTextViewText( R.id.hellotextview, "ID: " + iWidgetId + " > " + iCount );
			Intent clickIntent = new Intent( context, WidgetProvider.class );
			clickIntent.setAction("WIDGET_PROVIDER_CLICKED");
			clickIntent.putExtra("widgetId", iWidgetId);
			PendingIntent clickPendingIntent = PendingIntent.getBroadcast( context, 0, clickIntent, 0 );
			remoteViews.setOnClickPendingIntent( R.id.hellolayout, clickPendingIntent );
//			AppWidgetManager.getInstance(context).updateAppWidget( iWidgetId, remoteViews );
//			ComponentName name = new ComponentName(context, WidgetProvider.class);
			ComponentName name = new ComponentName(context.getApplicationContext(), WidgetProvider.class);
			AppWidgetManager.getInstance(context).updateAppWidget(name, remoteViews);
		}
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		Log.d( TAG, "WidgetProvider.onUpdate " + appWidgetIds.length );
		Toast.makeText( context, "WidgetProvider.onReceive " + appWidgetManager.toString(), Toast.LENGTH_LONG );
		
		// http://stackoverflow.com/questions/10572813/android-widget-not-responding-to-touches/10573051#10573051
		for ( int i = 0; i < appWidgetIds.length; i++ ) {
			Integer widgetId = appWidgetIds[i];
			Log.w( TAG, "WidgetProvider.onUpdate " + widgetId );
			
			RemoteViews remoteViews = new RemoteViews( context.getPackageName(), R.layout.widget_layout );
			remoteViews.setTextViewText( R.id.hellotextview, "WIDGET_PROVIDER_CLICKED" );
			
			Intent clickIntent = new Intent( context, WidgetProvider.class );
			clickIntent.setAction("WIDGET_PROVIDER_CLICKED");
			clickIntent.putExtra("widgetId", widgetId);
			
			PendingIntent clickPendingIntent = PendingIntent.getBroadcast( context, 0, clickIntent, 0 );
			remoteViews.setOnClickPendingIntent( R.id.hellolayout, clickPendingIntent );
			appWidgetManager.updateAppWidget( widgetId, remoteViews );
		}
	}

	@Override
	public void onAppWidgetOptionsChanged(Context context,
			AppWidgetManager appWidgetManager, int appWidgetId,
			Bundle newOptions) {
		super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId,
				newOptions);
		Log.d( TAG, "WidgetProvider.onAppWidgetOptionsChanged " + appWidgetManager.toString() );
		Toast.makeText( context, "WidgetProvider.onAppWidgetOptionsChanged " + appWidgetManager.toString(), Toast.LENGTH_LONG );
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);
		Log.d( TAG, "WidgetProvider.onDeleted " );
		Toast.makeText( context, "WidgetProvider.onDeleted ", Toast.LENGTH_LONG );
	}

	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		Log.d( TAG, "WidgetProvider.onEnabled " );
		Toast.makeText( context, "WidgetProvider.onEnabled ", Toast.LENGTH_LONG );
	}

	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
		Log.d( TAG, "WidgetProvider.onDisabled " );
		Toast.makeText( context, "WidgetProvider.onDisabled ", Toast.LENGTH_LONG );
	}
	
	

}
