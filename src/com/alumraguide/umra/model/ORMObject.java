package com.alumraguide.umra.model;

public abstract class ORMObject {

	protected int mId = -1;

	public int getId() {
		return mId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ORMObject)) {
			return false;
		}
		ORMObject other = (ORMObject) obj;
		if (mId != other.mId) {
			return false;
		}
		return true;
	}

	
}