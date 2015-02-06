package com.infospace.sdk.util;

import org.json.*;

import java.util.Iterator;

public class JsonObjectCollection implements Iterable<JSONObject> {
	private class JsonObjectIterator implements Iterator<JSONObject> {
		private final JSONArray array;
		private int index;

		public JsonObjectIterator(JSONArray array) {
			this.array = array;
			this.index = 0;
		}

		@Override
		public boolean hasNext() {
			return array != null && index < array.length();
		}

		@Override
		public JSONObject next() {
			if (array == null) {
				return null;
			}

			return this.array.optJSONObject(index++);
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private final JSONArray array;

	public JsonObjectCollection(JSONArray array) {
		this.array = array;
	}

	@Override
	public Iterator<JSONObject> iterator() {
		return new JsonObjectIterator(this.array);
	}

	public int length() {
		if (array == null) {
			return 0;
		}
		return array.length();
	}
}
