package com.jys.bluetoothproj.actiandfrag;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jys.bluetoothproj.R;

/**
 * fragment left_menu for drawer
 * 
 * @author Administrator
 *
 */
public class FragMenuLeft extends Fragment {

	ListView lv_menu_left; // frag_menu_left中的ListView
	ArrayAdapter<CharSequence> adapter; // adapter for listview

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.layout_fragment_menu_left, container, false);
		adapter = ArrayAdapter.createFromResource(getActivity(), R.array.menu_left, R.layout.layout_fragment_menu_left_item);
		lv_menu_left = (ListView) view.findViewById(R.id.fragment_meun_left_lv);
		lv_menu_left.setAdapter(adapter);

		return view;
	}
}
