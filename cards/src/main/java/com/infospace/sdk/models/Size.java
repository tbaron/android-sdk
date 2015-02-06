package com.infospace.sdk.models;

public class Size {
	private final int height;
	private final int width;

	public Size(int height, int width) {
		this.height = height;
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}
}
