package com.jys.bluetoothproj.actiandfrag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.jys.bluetoothproj.R;
import com.jys.bluetoothproj.adapter.AdapterForAim;
import com.jys.bluetoothproj.bean.Frag_tab_target_Aim;
import com.jys.bluetoothproj.util.UtilStream;

/**
 * fragment home for tab target
 * 
 * @author Administrator
 *
 */
public class FragTabTarget extends Fragment {
	
	AdapterForAim adapter ;

	private static final String TAG = "-----FragTabTarget";
	private static final String SP_FILE_NAME = "frag_tab_target_aims";
	private static final String SP_FILE_NAME_KEY = "aims";
	
	/**
	 *  存入sp之前，把aims转换成一个字符串（wj的方法，便于保密），读出来之后在把字符串转成arraylist<aim>
	 *  将aims转成aimsStr之后保存到sp中
	 */
	ArrayList<Frag_tab_target_Aim> aims = new ArrayList<Frag_tab_target_Aim>(); // 包含所有日期的aim
	String aimsStr = ""; 
	
	private String date; // 今天日期
	private int hour;
	private int minute;
	private int goal;
	private int minHour = 0; // number picker Hour 最小值
	private int minMinute = 0;
	private int minGoal = 0;

	ArrayList<Frag_tab_target_Aim> aimsForAdapter = new ArrayList<Frag_tab_target_Aim>(); // 当前日期的aims，给adapter填充数据
	
	SharedPreferences sp;
	SharedPreferences.Editor editor;

	ListView lv__addAims;
	ImageButton ib_toAddAim;// 添加目标按钮

	// ======================================================
	/**
	 * onCreateView
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View layout_fragment_target = inflater.inflate(R.layout.layout_fragment_tab_target, container, false);
		
		//日期初始化
		TextView tv_date = (TextView) layout_fragment_target.findViewById(R.id.frag_tab_target_tv_date);//界面上显示的日期
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
		date = df.format(new Date());
		tv_date.setText(date);
		
		// SharedPreferences初始化
		sp = getActivity().getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE); // 只能被本应用程序读写;
		editor = sp.edit();

		lv__addAims = (ListView) layout_fragment_target.findViewById(R.id.frag_tab_target_lv_addAims);
		ib_toAddAim = (ImageButton) layout_fragment_target.findViewById(R.id.frag_tab_target_ib_toAddAim);
		ib_toAddAim.setOnClickListener(new MyOnClickListener());

		// 将数据显示到页面上
		try {
			String spContent = sp.getString(SP_FILE_NAME_KEY, "");
			Log.i(TAG, "onCreateView-----sp中存储的内容是：" + spContent);
			
			if( !spContent.isEmpty() ) { //内容不空
				ArrayList<Frag_tab_target_Aim> allAims = UtilStream.String2SurveyList(spContent);
				
				// 从allAims中解析出 当前日期 的aims
				for (int i = 0; i < allAims.size(); i++) {
					Frag_tab_target_Aim aimtemp = allAims.get(i);
					if (date.equals(aimtemp.getDayOfWeek())) {
						aimsForAdapter.add(aimtemp);// 把所有当天的aim放入ArrayList中
					}
				}
			}
			adapter = new AdapterForAim(getActivity(), aimsForAdapter);
			lv__addAims.setAdapter(adapter);
			//setAdapterForListView(); // 给listview设置adapter---------------------------------------------------------------------

		} catch (Exception e) {
			//Toast.makeText(getActivity(), "onCreateView----------There is an Exception!", Toast.LENGTH_SHORT).show();
			Toast.makeText(getActivity(), "请设定目标!", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}

		return layout_fragment_target;

	}
	
	// ======================================================
	/**
	 * 监听 addImageButton的onclick事件；
	 * 
	 * @author Administrator
	 *
	 */
	private class MyOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {

			switch (v.getId()) {

			// ==添加aim=====
			case R.id.frag_tab_target_ib_toAddAim:
				
				// ==设定目标的弹出框中的时间和卡路里量的设置==========

				// 测试 弹出框让用户选择，弹出框是自定义的
				//LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				LinearLayout dialog_aim = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.layout_dialog_aim, null);

				// picTime and picGoal
				NumberPicker picTimeHour = (NumberPicker) dialog_aim.findViewById(R.id.dialog_aim_np_time_hour);
				NumberPicker picTimeMinute = (NumberPicker) dialog_aim.findViewById(R.id.dialog_aim_np_time_minute);
				NumberPicker picGoal = (NumberPicker) dialog_aim.findViewById(R.id.dialog_aim_np_goal);

				// 小时数
				picTimeHour.setMinValue(0);
				picTimeHour.setMaxValue(24);
				picTimeHour.setValue(minHour); // 默认显示数值
				picTimeHour.setOnValueChangedListener(new OnValueChangeListener() {

					@Override
					public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
						hour = newVal;
					}
				});

				// 分钟数
				picTimeMinute.setMinValue(0);
				picTimeMinute.setMaxValue(59);
				picTimeMinute.setValue(minMinute); // 默认显示数值
				picTimeMinute.setOnValueChangedListener(new OnValueChangeListener() {

					@Override
					public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
						minute = newVal;
					}
				});

				// 卡路里数
				picGoal.setMinValue(0);
				picGoal.setMaxValue(1000);
				picGoal.setValue(minGoal); // 默认显示数值
				picGoal.setOnValueChangedListener(new OnValueChangeListener() {

					@Override
					public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
						goal = newVal;
					}
				});

				// ==设定目标的弹出框==========---------------------------------------------------------------------------------------------

				// AlertDialog
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setTitle("设定目标").setView(dialog_aim) // 自定义弹出框样式
						.setPositiveButton("确定", new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {

								int duration = 60 * hour + minute;//计算目标的时间，转换为分钟

								if (duration != 0 || goal != 0) {

									Frag_tab_target_Aim aimTemp = new Frag_tab_target_Aim();
									aimTemp.setDayOfWeek(date); // 当前日期
									aimTemp.setTime_hour(hour+"");
									aimTemp.setTime_minute(minute+"");
									aimTemp.setGoal(goal + "");

									aims.add(aimTemp);// aims中包含所有日期的目标，将新添加的目标 保存到aims中
									aimsForAdapter.add(aimTemp); // 将新添加的目标 也保存到listview的adapter中
									adapter.notifyDataSetChanged();
									
									//String str = "";
									try {
										aimsStr = UtilStream.SurveyList2String(aims);
									} catch (IOException e) {
										e.printStackTrace();
									}
									editor.putString(SP_FILE_NAME_KEY, aimsStr);
									editor.commit(); // 把新建的aim添加到包含所有日期的aims中去
									
									//====================================================
									
									//将新添加的目标显示出来
									
									
									
									
									//----------------------------------------------------------------------
									
							/*		
									// 将数据显示到页面上
									String spContent = sp.getString(SP_FILE_NAME_KEY, "");
									try {

										ArrayList<Frag_tab_target_Aim> allAims = UtilStream.String2SurveyList(spContent);

										// 从allAims中解析出 当前 的aims
										for (int i = 0; i < allAims.size(); i++) {
											Frag_tab_target_Aim aimtemp = allAims.get(i);
											if (date.equals(aimtemp.getDayOfWeek())) {
												aimsForAdapter.add(aimtemp);// 把所有当天的aim放入ArrayList中
											}
										}
										
										adapter.notifyDataSetChanged();
										//lv__addAims.setAdapter(adapter);
										//setAdapterForListView();// setAdapter
										

									} catch (Exception e) {
										Toast.makeText(getActivity(), "There is an Exception!", Toast.LENGTH_SHORT).show();
										e.printStackTrace();
									}*/

								} else {
									// 时间或卡路里量中有空值
									Toast.makeText(getActivity(), "目标设定有误，请重新设定！", Toast.LENGTH_SHORT).show();
								}

							}
						}).setNegativeButton("取消", new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {

							}
						}).create().show(); // 创建并展示
				break;
			}
		}
	}

	// ======================================================
	/**
	 * 给listview设置adapter
	 */
	public void setAdapterForListView() {
		
		//AdapterForAim adapter = new AdapterForAim(getActivity(), aimsForAdapter);
		//lv__addAims.setAdapter(adapter);
		
		}
		
	}
	// ======================================================