package com.kdehairy.freelance.umra.model;


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

}
