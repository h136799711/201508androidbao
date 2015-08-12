package com.jys.bluetoothproj.actiandfrag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jys.bluetoothproj.R;
import com.jys.bluetoothproj.bean.Access_token;
import com.jys.bluetoothproj.util.UtilConstantLoginAndRegister;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * 登录activity
 * @author Administrator
 *
 */
public class ActiLogin extends Activity implements View.OnClickListener{

	protected static final String TAG = "-----ActiLogin";
	
	String access_token = ""; //8.5 目前测试

	EditText et_username;
	EditText et_password;
	
	CheckBox cb_rememberPwd; // 记住密码 复选框
	TextView tv_forgetPwd; // 忘记密码 链接
	
	Button btn_login;
	Button btn_toRegister; // 转到注册页面的按钮

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_acti_login);

		et_username = (EditText) findViewById(R.id.acti_login_et_username);
		et_password = (EditText) findViewById(R.id.acti_login_et_password);
		btn_login = (Button) findViewById(R.id.acti_login_btn_login);
		btn_toRegister = (Button) findViewById(R.id.acti_login_btn_toregister);
		//cb_rememberPwd  = (CheckBox) findViewById(R.id.acti_login_cb_rememberpwd);
		//tv_forgetPwd.setOnClickListener(this);
		
	}

	/**
	 * 登录逻辑：先获取access_token，需grant_type、client_id和client_secret三个参数；
	 * 获取access_token成功之后，将其保存到sharedpreferences中，有效期1小时；
	 * 根据access_token、username和password进行登录，登录时access_token已明文的形式出现，
	 * username和password    以post的形式提交
	 * @param v
	 */
	public void login(View v) {
		
		  final String username = et_username.getText().toString().trim();
		  final String password = et_password.getText().toString().trim();
		  
		  HttpUtils httpUtils = new HttpUtils();
		  
		  RequestParams params = new RequestParams();
		  params.addBodyParameter("username","123123");
		  params.addBodyParameter("password","123jing.");
		  String url = "http://lanbao.itboye.com/api.php/User/update?access_token=f6ef66b89a8c1f0e6ab3c39c69f07b89caa1172e";
		  Log.i(TAG, url);
		  
		  httpUtils.send(HttpMethod.POST, url ,params, new RequestCallBack<String>(){
		  @Override 
			  public void onFailure(HttpException arg0, String arg1) {
				  Log.i(TAG, "登录失败：" +  arg0.getLocalizedMessage() + "  " + arg0.getMessage() + "  " + arg1);
			  }
			  
			  @Override public void onSuccess(ResponseInfo<String> arg0) {
				  Log.i(TAG, "登录成功：" + arg0.result); 
			  }
		  });
		  
		  
		  
		  
		  
		
/*		  HttpUtils httpUtils = new HttpUtils();
		  
		  RequestParams params = new RequestParams();
		  params.addBodyParameter(UtilConstantLoginAndRegister.GRANT_TYPE, getApplicationContext().getResources().getString(R.string.config_grant_type));
		  params.addBodyParameter(UtilConstantLoginAndRegister.CLIENT_ID, getApplicationContext().getResources().getString(R.string.config_client_id));
		  params.addBodyParameter(UtilConstantLoginAndRegister.CLIENT_SECRET, getApplicationContext().getResources().getString(R.string.config_client_secret));
		  
		  httpUtils.send(HttpMethod.POST, getApplicationContext().getResources().getString(R.string.config_url_gettoken),params, new RequestCallBack<String>(){
		  @Override 
			  public void onFailure(HttpException arg0, String arg1) {
				  Log.i(TAG, "登录失败：" +  arg0.getLocalizedMessage() + "  " + arg0.getMessage() + "  " + arg1);
			  }
			  
			  @Override public void onSuccess(ResponseInfo<String> arg0) {
				  Log.i(TAG, "登录成功：" + arg0.result); 
				  Access_token token = JSON.parseObject(arg0.result,Access_token.class);
				  Log.i(TAG, "access_token is : " + token.getInfo().getAccess_token());
				  access_token = token.getInfo().getAccess_token();
			  }
		  });*/
		  
		  
		  
		  //--------------------------------------------------------------------------------------------------------
		  
		  
		  
		
		  

		  
		  
	
	

		// 跳转到actimain
		Intent intent = new Intent(ActiLogin.this, ActiMain.class);
		startActivity(intent);


		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
		
		

	/**
	 * 转到 注册 页面
	 */
	public void toRegister(View v) {
		Intent intent = new Intent(ActiLogin.this, ActiRegister.class);
		startActivity(intent);
	}

	/**
	 * 实现View.OnClickListener接口要实现的方法
	 * 点击 忘记密码 链接，转到找回密码页面
	 */
	@Override
	public void onClick(View v) {
		//TODO 此处先不写，8.5
	}
}
	
