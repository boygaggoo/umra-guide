package com.alumraguide.umra.model;

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
	
	public int getPrayerCount(Context context) {
		int count = 0;
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			Repository repo = Repository.getInstance(context);
			db = repo.getReadableDatabase();
			StringBuilder bldr = new StringBuilder("SELECT COUNT(")
			.append(Prayer.ID + ") AS prayer_count FROM ")
			.append(Prayer.TABLE)
			.append(" WHERE " + Prayer.TOC_ID + " = " + getId())
			.append(" GROUP BY " + Prayer.TOC_ID);
			cursor = db.rawQuery(bldr.toString(), null);
			if (cursor != null && cursor.getCount() > 0) {
				cursor.moveToNext();
				count = cursor.getInt(cursor.getColumnIndex("prayer_count"));
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
		return count;
	}
	
	public List<Prayer> getPrayers(Context context, String order) {
		Cursor cursor = null;
		SQLiteDatabase db = null;
		ArrayList<Prayer> prayers = null;
		try {
			Repository repo = Repository.getInstance(context);
			db = repo.getReadableDatabase();
			String where = Prayer.TOC_ID + " = ?";
			String args[] = new String[] {Integer.toString(this.getId())};
			order = (order != null) ? order : "ASC";
			String orderBy = Prayer.ID + " " + order;
			cursor = db.query(Prayer.TABLE, null, where, args, null, null, orderBy);
			int count = 0;
			if (cursor != null && (count = cursor.getCount()) > 0) {
				prayers = new ArrayList<Prayer>(count);
				while (cursor.moveToNext()) {
					int id = cursor.getInt(cursor.getColumnIndex(Prayer.ID));
					String title = cursor.getString(cursor.getColumnIndex(Prayer.TITLE));
					String content = cursor.getString(cursor.getColumnIndex(Prayer.CONTENT));
					prayers.add(new Prayer(id, title, content));
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
		
		List<Prayer> immutableList = null;
		if (prayers != null) {
			immutableList = Collections.unmodifiableList(prayers);
		}
		return immutableList;
	}

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

	public String getTitle() {
		return mTitle;
	}

}
