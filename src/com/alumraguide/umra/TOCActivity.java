package com.alumraguide.umra;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.alumraguide.umra.model.Repository;
import com.alumraguide.umra.model.Toc;

public class TOCActivity extends SherlockActivity implements OnClickListener {

	private ArrayList<RelativeLayout> mTocViews;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		// create the actionbar custom view
		/*RelativeLayout layout = new RelativeLayout(this);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		layout.setLayoutParams(lp);
		layout.setGravity(Gravity.CENTER);
		RobotoTextView textView = new RobotoTextView(this);
		textView.setText(R.string.app_name);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
		textView.setTextColor(0xffCEE5F5);
		Drawable dl = getResources().getDrawable(R.drawable.ornament_l);
		dl.setBounds(0, 0, 14, 33);
		Drawable dr = getResources().getDrawable(R.drawable.ornament_r);
		dr.setBounds(0, 0, 14, 33);
		textView.setCompoundDrawables(dl, null, dr, null);
		textView.setCompoundDrawablePadding(5);
		textView.setShadowLayer(1.5f, 1, 1, 0xFF000000);
		layout.addView(textView);*/

		// attach the custom view to the actionbar
		//getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		View customView = getSupportActionBar().getCustomView();
		TextView title = (TextView) customView.findViewById(R.id.custom_view_title);
		title.setText(R.string.app_name);
		//getSupportActionBar().setCustomView(layout);
		setContentView(R.layout.toc);

		mTocViews = new ArrayList<RelativeLayout>(6);
		mTocViews.add((RelativeLayout) findViewById(R.id.umra_1));
		mTocViews.add((RelativeLayout) findViewById(R.id.umra_2));
		mTocViews.add((RelativeLayout) findViewById(R.id.umra_3));
		mTocViews.add((RelativeLayout) findViewById(R.id.umra_4));
		mTocViews.add((RelativeLayout) findViewById(R.id.umra_5));
		mTocViews.add((RelativeLayout) findViewById(R.id.umra_6));

		// wire the click listeners
		int i = 1;
		for (RelativeLayout l : mTocViews) {
			l.setTag(i++);
			l.setOnClickListener(this);
		}
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.toc, menu);
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
		case R.id.menu_info:
			intent = new Intent(this, InfoActivity.class);
			startActivity(intent);
			break;
		default:
			is_processed = super.onOptionsItemSelected(item);
		}
		return is_processed;
	}

	@Override
	public void onClick(View v) {
		int id = (Integer) v.getTag();
		Repository repo = Repository.getInstance(this.getApplicationContext());
		Toc toc = repo.findTocById(id);
		Intent intent = new Intent(this, SectionActivity.class);
		intent.putExtra(SectionActivity.EXTRA_TOC, toc);
		startActivity(intent);
	}
}