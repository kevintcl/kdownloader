package com.tcl.collect;

import java.util.HashMap;

public class LongSparseLongArray {

	// private SparseLongArray mArray;
	//
	// public LongSparseLongArray() {
	// mArray = new SparseLongArray();
	// }
	//
	// public void put(int key, long value) {
	// mArray.put(key, value);
	// }
	//
	// public void delete(int key) {
	// mArray.delete(key);
	// }

	private HashMap<Long, Long> mArray;

	public LongSparseLongArray() {
		mArray = new HashMap<Long, Long>();
	}

	public void put(long key, long value) {
		mArray.put(key, value);
	}

	public long get(long key) {
		return mArray.get(key);
	}
	public void delete(long key) {
		mArray.remove(key);
	}

	
}
