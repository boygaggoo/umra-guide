package com.kdehairy.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class DroidTextView extends TextView {

	public DroidTextView(Context context) {
		super(context);
		init();
	}

	public DroidTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public DroidTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
				"DroidSansArabic.ttf");
		setTypeface(tf);
	}

}
