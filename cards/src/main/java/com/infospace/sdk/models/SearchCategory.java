package com.infospace.sdk.models;

import java.util.Locale;

/**
 * An enumeration of supported SearchCategrory verticals.
 */
public enum SearchCategory {
	IMAGES("IMAGES"),
	NEWS("NEWS"),
	VIDEO("VIDEO"),
	WEB("WEB"),
	SHOPPING("SHOPPING"),
	DIGEST("DIGEST");

	private final String value;

	SearchCategory(String value) {
		this.value = value.toUpperCase(Locale.US);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public static SearchCategory fromString(String value) {
		for (SearchCategory category : values()) {
			if (category.value.equalsIgnoreCase(value)) {
				return category;
			}
		}
		return getDefault();
	}

	public static SearchCategory getDefault() {
		return WEB;
	}
}
