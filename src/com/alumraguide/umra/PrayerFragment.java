package com.alumraguide.umra;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.alumraguide.umra.model.Prayer;

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
		View returnedOne = getActivity().getLayoutInflater().inflate(R.layout.prayer_item, null);
		TextView title = (TextView) returnedOne.findViewById(R.id.title);
		title.setText(mPrayer.getTitle());
		TextView content = (TextView) returnedOne.findViewById(R.id.content);
		content.setText(unescape(mPrayer.getContent()));

        return returnedOne;
	}

	@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_PRAYER, mPrayer);
    }
	
	private String unescape(String content) {
	    return content.replaceAll("\\\\n", "\\\n");
	}
}
