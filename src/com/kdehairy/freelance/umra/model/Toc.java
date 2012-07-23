package com.kdehairy.freelance.umra.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcel;
import android.os.Parcelable;

public class Toc extends ORMObject implements Parcelable {

	public static final String TABLE = "toc";
	public static final String ID = "_id";
	public static final String TITLE = "title";

	private final String mTitle;

	/* package */Toc(int id, String title) {
		mId = id;
		mTitle = title;
	}

	protected Toc(Parcel source) {
		mId = source.readInt();
		mTitle = source.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(mId);
		dest.writeString(mTitle);
	}

	public static final Parcelable.Creator<Toc> CREATOR = new Parcelable.Creator<Toc>() {

		@Override
		public Toc createFromParcel(Parcel source) {
			return new Toc(source);
		}

		@Override
		public Toc[] newArray(int size) {
			return new Toc[size];
		}
	};

	public List<Paragraph> getParagraphs(Context context) {
		Cursor cursor = null;
		SQLiteDatabase db = null;
		ArrayList<Paragraph> paragraphs = null;
		try {
			Repository repo = Repository.getInstance(context);
			db = repo.getReadableDatabase();
			String where = Paragraph.TOC_ID + " = ?";
			String[] args = new String[] { Integer.toString(this.getId()) };
			String orderBy = Paragraph.ORDER + " ASC";
			cursor = db.query(Paragraph.TABLE, null, where, args, null, null,
					orderBy);
			int count = 0;
			if (cursor != null && (count = cursor.getCount()) > 0 ){
				paragraphs = new ArrayList<Paragraph>(count);
				while (cursor.moveToNext()) {
					int id = cursor.getInt(cursor.getColumnIndex(Paragraph.ID));
					String content = cursor.getString(cursor.getColumnIndex(Paragraph.CONTENT));
					int order = cursor.getInt(cursor.getColumnIndex(Paragraph.ORDER));
					paragraphs.add(new Paragraph(id, content, order));
				}
			}
		} catch (SQLiteException e) {
			e.printStackTrace();
		} finally {
			if (db != null && db.isOpen()) {
				db.close();
			}
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}
		
		List<Paragraph> immutableList = null;
		if (paragraphs != null) {
			immutableList = Collections.unmodifiableList(paragraphs);
		}
		return immutableList;
	}

}
