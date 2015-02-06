package com.infospace.sdk.models;

public class FileSize {
	private final double size;
	private final String unit;

	public FileSize(double size, String unit) {
		this.size = size;
		this.unit = unit;
	}

	public double getSize() {
		return this.size;
	}

	public String getUnit() {
		return this.unit;
	}
}
