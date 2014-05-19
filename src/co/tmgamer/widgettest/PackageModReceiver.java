/**
 * 
 */
package co.tmgamer.widgettest;

import java.util.Set;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * @author hakim
 * http://stackoverflow.com/questions/7470314/receiving-package-install-and-uninstall-events
 */
public class PackageModReceiver extends BroadcastReceiver {
	
	public static final String TAG = "WidgetProvider";

	@Override
	public void onReceive(Context context, Intent intent) {
//		super.onReceive(context, intent);
		Log.d( TAG, "PackageModReceiver.onReceive " + intent.getAction() + " > " + intent.getDataString() );
		Toast.makeText( context, "PackageModeReceiver.onReceive " + intent.getAction(), Toast.LENGTH_LONG ).show();
		
		Bundle pExtra = intent.getExtras();
		debugBundle( pExtra );
	}
	
	public static void debugBundle( Bundle bundle ) {
		if ( bundle == null ) {
			Log.e(TAG, "PackageModReceiver.debugBundle > bundle is null");
			return;
		}
		Set<String> keys = bundle.keySet();
		for ( String key : keys ) {
			Object value = bundle.get(key);
			Log.d(TAG, "PackageModReceiver.debugBundle > " + key + " => " + value.toString() + " [" + value.getClass().getName() + "]");
		}
	}

}
