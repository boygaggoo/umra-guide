package com.kdehairy.freelance.umra;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.actionbarsherlock.app.SherlockFragment;
import com.kdehairy.freelance.umra.model.Prayer;

public class PrayerFragment extends SherlockFragment {
	
	public static final String KEY_PRAYER = "prayer";
	private Prayer mPrayer = null;
	
	public static PrayerFragment createInstance(Prayer prayer) {
		PrayerFragment instance = new PrayerFragment();
		instance.setPrayer(prayer);
		return instance;
	}

	private void setPrayer(Prayer prayer) {
		mPrayer = prayer;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null && savedInstanceState.containsKey(KEY_PRAYER)) {
			mPrayer = (Prayer) savedInstanceState.getParcelable(KEY_PRAYER);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		TextView text = new TextView(getActivity());
        text.setGravity(Gravity.CENTER);
        text.setText(mPrayer.getTitle());
        text.setTextSize(20 * getResources().getDisplayMetrics().density);
        text.setPadding(20, 20, 20, 20);
        text.setBackgroundColor(0xFFFFFFFF);

        LinearLayout layout = new LinearLayout(getActivity());
        LayoutParams param = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        param.bottomMargin = 50;
        param.rightMargin = 50;
        param.topMargin = 50;
        param.leftMargin = 50;
        layout.setLayoutParams(param);
        layout.setGravity(Gravity.CENTER);
        
        layout.addView(text);

        return layout;
	}

	@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_PRAYER, mPrayer);
    }
}
