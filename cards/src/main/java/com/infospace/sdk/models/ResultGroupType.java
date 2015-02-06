package com.infospace.sdk.models;

import java.util.Locale;

/**
 * The type of result group.
 */
public enum ResultGroupType {
	MAIN("MAIN"),
	RELATED("RELATED"),
	TOP("TOP"),
	LEFT("LEFT"),
	RIGHT("RIGHT"),
	BOTTOM("BOTTOM"),
	SITE("SITE"),
	HOROSCOPE("HOROSCOPE"),
	SPELLING("SPELLING"),
	STOCK("STOCK"),
	WEATHER("WEATHER"),

	WEBGROUP("WEBGROUP"),
	IMAGEGROUP("IMAGEGROUP"),
	VIDEOGROUP("VIDEOGROUP"),
	NEWSGROUP("NEWSGROUP"),
	SHOPPINGGROUP("SHOPPINGGROUP"),
	DEALGROUP("DEALGROUP"),
	OFFERGROUP("OFFERGROUP"),

	RELATEDGROUP("RELATEDGROUP"),
	REVIEWS("REVIEWS");

	private final String value;

	ResultGroupType(String value) {
		this.value = value.toUpperCase(Locale.US);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public static ResultGroupType fromString(String value) {
		for (ResultGroupType category : values()) {
			if (category.value.equalsIgnoreCase(value)) {
				return category;
			}
		}
		return getDefault();
	}

	public static ResultGroupType getDefault() {
		return MAIN;
	}
}
