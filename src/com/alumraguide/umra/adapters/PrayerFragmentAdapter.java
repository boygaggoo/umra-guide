package com.alumraguide.umra.adapters;

import java.util.List;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.alumraguide.umra.PrayerFragment;
import com.alumraguide.umra.model.Prayer;
import com.alumraguide.umra.model.Toc;

public class PrayerFragmentAdapter extends FragmentPagerAdapter {
	
	private final List<Prayer> mPrayers;

	public PrayerFragmentAdapter(FragmentManager fm, Context context, Toc topic) {
		super(fm);
		mPrayers = topic.getPrayers(context, "DESC");
	}

	@Override
	public Fragment getItem(int position) {
		PrayerFragment fragment = null;
		if (mPrayers != null && mPrayers.size() > 0) {
			fragment = PrayerFragment.createInstance(mPrayers.get(position));
		}
		return fragment;
	}

	@Override
	public int getCount() {
		int size = 0;
		if (mPrayers != null) {
			size = mPrayers.size();
		}
		return size;
	}

}
