package com.jys.bluetoothproj.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jys.bluetoothproj.R;
import com.jys.bluetoothproj.bean.Frag_tab_target_Aim;

/**
 * frag_tab_target中的listview的adapter
 * 
 * @author Administrator
 *
 */
public class AdapterForAim extends BaseAdapter {

	private Context context;
	private ArrayList<Frag_tab_target_Aim> aims;

	// =================================================
	@Override
	public int getCount() {
		return aims.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	
		LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.layout_fragment_tab_target_item, null);
		TextView tv_time = (TextView) view.findViewById(R.id.frag_tab_target_item_time);
		TextView tv_goal = (TextView) view.findViewById(R.id.frag_tab_target_item_goal);

		Frag_tab_target_Aim aim = aims.get(position);
		tv_time.setText(aim.getTime_hour()+" 小时 " + aim.getTime_minute()+" 分钟");
		tv_goal.setText(aim.getGoal()+"  大卡");

		return view;
	}

	// =================================================

	/**
	 * 构造函数
	 */
	public AdapterForAim(Context context, ArrayList<Frag_tab_target_Aim> aims) {
		this.context = context;
		this.aims = aims;
	}

	// =================================================

}
