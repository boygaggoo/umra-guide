package com.alumraguide.umra.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Prayer extends ORMObject implements Parcelable {
	
	public static final String TABLE = "prayer";
	public static final String ID = "_id";
	public static final String TITLE = "title";
	public static final String CONTENT = "content";
	public static final String TOC_ID = "toc_id";
	
	private final String mTitle;
	private final String mContent;
	
	/*package*/Prayer(int id, String title, String content) {
		mId = id;
		mTitle = title;
		mContent = content;
	}
	
	protected Prayer(Parcel source) {
		mId = source.readInt();
		mTitle = source.readString();
		mContent = source.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(mId);
		dest.writeString(mTitle);
		dest.writeString(mContent);
	}
	
	public static final Parcelable.Creator<Prayer> CREATOR = new Parcelable.Creator<Prayer>() {

		@Override
		public Prayer createFromParcel(Parcel source) {
			return new Prayer(source);
		}

		@Override
		public Prayer[] newArray(int size) {
			return new Prayer[size];
		}
		
	};

	public String getTitle() {
		return mTitle;
	}

	public String getContent() {
		return mContent;
	}

}
