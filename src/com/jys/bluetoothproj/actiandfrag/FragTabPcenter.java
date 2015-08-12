package com.jys.bluetoothproj.actiandfrag;

import com.jys.bluetoothproj.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * fragment home for tab pcenter
 * @author Administrator
 *
 */
public class FragTabPcenter extends Fragment{
	
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,  
	            Bundle savedInstanceState) {  
	        View layout_fragment_pcenter = inflater.inflate(R.layout.layout_fragment_tab_pcenter, container, false);  
	        return layout_fragment_pcenter;  
	    }

}
