package com.jys.bluetoothproj.actiandfrag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.jys.bluetoothproj.R;
/**
 * ActiWelcome 欢迎界面
 * @author Administrator
 *
 */
public class ActiWelcome extends Activity {

	private Handler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_acti_welcome);
		
		mHandler = new Handler();
		mHandler.postDelayed(gotoActiLogin, 2000);

	}
	
	Runnable gotoActiLogin = new  Runnable() {
		public void run() {
			Intent intent = new Intent(ActiWelcome.this, ActiLogin.class);
			startActivity(intent);
		}
	};

	

}
