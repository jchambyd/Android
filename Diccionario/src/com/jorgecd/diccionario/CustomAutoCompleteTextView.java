package com.jorgecd.diccionario;

import java.util.HashMap;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

public class CustomAutoCompleteTextView extends AutoCompleteTextView {
	
	public CustomAutoCompleteTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected CharSequence convertSelectionToString(Object selectedItem) {
		
		HashMap<String, String> hm = (HashMap<String, String>) selectedItem;
		
		return hm.get("txt");
	}
}
