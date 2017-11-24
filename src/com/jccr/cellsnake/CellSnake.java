package com.jccr.cellsnake;

import android.app.Activity;
//import android.content.Context;
//import android.os.Bundle;
//import android.os.Vibrator;
//import android.provider.Settings.Secure;
//import android.telephony.TelephonyManager;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.Toast;

public class CellSnake extends Activity {

//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		super.onCreate(savedInstanceState);
//		Vibrator v = (Vibrator) getSystemService(getApplicationContext().VIBRATOR_SERVICE);
//		//v.vibrate(1000);
//		setContentView(R.layout.main);
//		
//			Obtenemos IMEI y el UniqueId del terminal
//		Toast.makeText(this, getIMEI(this), Toast.LENGTH_SHORT).show();
//		Toast.makeText(this, getDeviceUniqueID(), Toast.LENGTH_SHORT).show();
//	}
//
//	public String getIMEI(Activity activity) {
//	    TelephonyManager telephonyManager = (TelephonyManager) activity
//	            .getSystemService(Context.TELEPHONY_SERVICE);
//	    return telephonyManager.getDeviceId();
//	}
//	
//	public String getDeviceUniqueID(){
//	    String device_unique_id = Secure.getString(this.getContentResolver(),
//	            Secure.ANDROID_ID);
//	    return device_unique_id;
//	}
}
