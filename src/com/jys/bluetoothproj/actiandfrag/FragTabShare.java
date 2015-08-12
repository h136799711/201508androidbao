package com.jys.bluetoothproj.actiandfrag;

import com.jys.bluetoothproj.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * fragment home for tab share
 * @author Administrator
 *
 */
public class FragTabShare extends Fragment{
	
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,  
	            Bundle savedInstanceState) {  
	        View layout_fragment_share = inflater.inflate(R.layout.layout_fragment_tab_share, container, false);  
	        return layout_fragment_share;  
	    }

}
