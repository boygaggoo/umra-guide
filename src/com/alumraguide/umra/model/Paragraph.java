package com.alumraguide.umra.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Paragraph extends ORMObject implements Parcelable {
	
	public static final String TABLE = "paragraph";
	public static final String ID = "_id";
	public static final String CONTENT = "content";
	public static final String ORDER = "ordinal";
	public static final String TOC_ID = "toc_id";
	
	private final String mContent;
	private final int mOrder;
	
	/*package*/ Paragraph(int id, String content, int order) {
		mId = id;
		mContent = content;
		mOrder = order;
	}
	
	protected Paragraph(Parcel source) {
		mId = source.readInt();
		mContent = source.readString();
		mOrder = source.readInt();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(mId);
		dest.writeString(mContent);
		dest.writeInt(mOrder);
	}
	
	public static final Parcelable.Creator<Paragraph> CREATOR = new Parcelable.Creator<Paragraph>() {

		@Override
		public Paragraph createFromParcel(Parcel source) {
			return new Paragraph(source);
		}

		@Override
		public Paragraph[] newArray(int size) {
			return new Paragraph[size];
		}
		
	};

	public String getContent() {
		return mContent;
	}

	public int getOrder() {
		return mOrder;
	}

}
