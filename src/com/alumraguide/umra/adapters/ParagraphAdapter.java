package com.alumraguide.umra.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alumraguide.umra.R;
import com.alumraguide.umra.model.Paragraph;
import com.alumraguide.umra.model.Toc;

public class ParagraphAdapter extends BaseAdapter {
	private final Context mContext;
	private final int mLayoutId = R.layout.paragraph;
	private List<Paragraph> mParagraphs;
	private Toc mToc;

	public ParagraphAdapter(Context context, Toc toc) {
		super();
		mContext = context;
		mToc = toc;
		populateData();
	}

	@Override
	public int getCount() {
		int size = 0;
		if (mParagraphs != null) {
			size = mParagraphs.size();
		}
		return size;
	}

	@Override
	public Object getItem(int position) {
		return mParagraphs.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// get the returned view
		View returnedView = null;
		if (convertView == null) {
			final LayoutInflater inflater = LayoutInflater.from(mContext);
			returnedView = inflater.inflate(mLayoutId, null);
		} else {
			returnedView = convertView;
		}
		// prepare the view holder
		final Object tag = returnedView.getTag();
		ViewHolder viewHolder = null;
		if (tag == null || !(tag instanceof ViewHolder)) {
			viewHolder = new ViewHolder();
			viewHolder.content = (TextView) returnedView
					.findViewById(R.id.paragraph_content);
			returnedView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) tag;
		}
		Paragraph paragraph = (Paragraph) getItem(position);
		viewHolder.content.setText(paragraph.getContent());

		return returnedView;
	}

	private void populateData() {
		mParagraphs = mToc.getParagraphs(mContext);
	}

	private static class ViewHolder {
		public TextView content;
	}

	@Override
	public boolean areAllItemsEnabled() {
		return false;
	}

	@Override
	public boolean isEnabled(int position) {
		return false;
	}

}
