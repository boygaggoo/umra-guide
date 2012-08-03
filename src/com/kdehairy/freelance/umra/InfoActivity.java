package com.kdehairy.freelance.umra;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;

public class InfoActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// create the actionbar custom view
		/*RelativeLayout layout = new RelativeLayout(this);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		layout.setLayoutParams(lp);
		layout.setGravity(Gravity.CENTER);
		DecoratedTextView textView = new DecoratedTextView(this);
		textView.setText(R.string.menu_info);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
		textView.setTextColor(0xffCEE5F5);
		Drawable dl = getResources().getDrawable(R.drawable.ornament_l);
		dl.setBounds(0, 0, 14, 33);
		Drawable dr = getResources().getDrawable(R.drawable.ornament_r);
		dr.setBounds(0, 0, 14, 33);
		textView.setCompoundDrawables(dl, null, dr, null);
		textView.setCompoundDrawablePadding(5);
		textView.setShadowLayer(1.5f, 1, 1, 0xFF000000);
		layout.addView(textView);

		// attach the custom view to the actionbar
		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getSupportActionBar().setCustomView(layout);*/
		
		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		View customView = getSupportActionBar().getCustomView();
		TextView title = (TextView) customView.findViewById(R.id.custom_view_title);
		title.setText(R.string.menu_info);
		
		setContentView(R.layout.info);

		findViewById(R.id.review_app).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri
						.parse("market://details?id=com.kdehairy.freelance.umra"));
				startActivity(intent);
			}

		});

		findViewById(R.id.visit_site).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://umra.vacau.com/"));
				startActivity(intent);
			}

		});

		findViewById(R.id.share_app).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(android.content.Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(android.content.Intent.EXTRA_TITLE,
						getString(R.string.app_name));
				intent.putExtra(android.content.Intent.EXTRA_SUBJECT,
						getString(R.string.app_name));
				intent.putExtra(android.content.Intent.EXTRA_TEXT,
						"http://play.google.com/store/apps/details?id=com.kdehairy.freelance.umra");
				startActivity(Intent.createChooser(intent, "Share via"));
			}

		});
	}

}
