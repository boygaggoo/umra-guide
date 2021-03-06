package com.alumraguide.umra;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.alumraguide.umra.adapters.PrayerFragmentAdapter;
import com.alumraguide.umra.model.Toc;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

public class PrayerActivity extends SherlockFragmentActivity {

	public static final String EXTRA_TOPIC = "topic";
	
	ViewPager mPager;
    PageIndicator mIndicator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.prayer);

		Toc topic = (Toc) getIntent().getParcelableExtra(EXTRA_TOPIC);
		if (topic == null) {
			throw new NullPointerException("topic cannot be null");
		}
		PrayerFragmentAdapter adapter = new PrayerFragmentAdapter(
				getSupportFragmentManager(), this, topic);
		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(adapter);
		mPager.setCurrentItem(adapter.getCount()-1);
		
		mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
		mIndicator.setViewPager(mPager);
		mIndicator.setCurrentItem(adapter.getCount()-1);
		
	}

}
