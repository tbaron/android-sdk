package com.infospace.sdk.models;

public class BookmarkResult extends StandardResult {
	public BookmarkResult(String title, String url) {
		setTitle(title);
		setURL(url);
		setDisplayURL(url);
	}
}
