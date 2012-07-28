package com.kdehairy.freelance.umra;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.kdehairy.freelance.umra.adapters.ParagraphAdapter;
import com.kdehairy.freelance.umra.model.Toc;
import com.kdehairy.widgets.DecoratedTextView;

public class SectionActivity extends SherlockListActivity {

	public static final String EXTRA_TOC = "com.kdehairy.extra.toc";
	
	private Toc mTopic;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.section, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean is_processed = false;
		Intent intent;
		switch (item.getItemId()) {
		case android.R.id.home:
			intent = new Intent(this, TOCActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			is_processed = true;
			break;
		case R.id.menu_prayer:
			intent = new Intent(this, PrayerActivity.class);
			intent.putExtra(PrayerActivity.EXTRA_TOPIC, mTopic);
			startActivity(intent);
			is_processed = true;
			break;
		default:
			is_processed = super.onOptionsItemSelected(item);
		}
		return is_processed;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Toc toc = (Toc) getIntent().getParcelableExtra(EXTRA_TOC);
		if (toc == null) {
			throw new NullPointerException("Toc cannot be null");
		}
		mTopic = toc;

		// create the actionbar custom view
		RelativeLayout layout = new RelativeLayout(this);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		layout.setLayoutParams(lp);
		layout.setGravity(Gravity.CENTER);
		DecoratedTextView textView = new DecoratedTextView(this);
		textView.setText(mTopic.getTitle());
		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
		textView.setTextColor(0xffCEE5F5);
		textView.setSingleLine(true);
		textView.setEllipsize(TextUtils.TruncateAt.END);
		Drawable dl = getResources().getDrawable(R.drawable.ornament_l);
		dl.setBounds(0, 0, 14, 33);
		Drawable dr = getResources().getDrawable(R.drawable.ornament_r);
		dr.setBounds(0, 0, 14, 33);
		textView.setCompoundDrawables(dl, null, dr, null);
		textView.setCompoundDrawablePadding(5);
		textView.setShadowLayer(1.5f, 1, 1, 0xFF000000);
		layout.addView(textView);

		// attach the custom view to the actionbar
		getSupportActionBar().setDisplayOptions(
				ActionBar.DISPLAY_SHOW_CUSTOM);
		getSupportActionBar().setCustomView(layout);
		// enable the home button on the actionbar
		getSupportActionBar().setHomeButtonEnabled(true);

		ListView ls = getListView();
		ls.setDivider(null);
		ls.setDividerHeight(0);
		ls.setCacheColorHint(getResources().getColor(R.color.window_bkgrnd));

		getSupportActionBar().setTitle(toc.getTitle());
		ParagraphAdapter adapter = new ParagraphAdapter(this, toc);
		ls.setAdapter(adapter);
		// ls.setSelector(android.R.color.transparent);
		// ls.setFocusableInTouchMode(false);
	}

}
