package com.infospace.sdk.cards;

import android.content.res.Resources;
import android.net.Uri;
import android.util.DisplayMetrics;

public class ResourceUriBuilder {
	private final String densityString;
	private String theme;
	private String category;
	private String name;
	private String extension;

	public ResourceUriBuilder(Resources resources) {
		densityString = getDensityString(resources);
	}

	private static String getDensityString(Resources resources) {
		int density = resources.getDisplayMetrics().densityDpi;

		if (density <= DisplayMetrics.DENSITY_MEDIUM) {
			return "mdpi";
		}
		if (density <= DisplayMetrics.DENSITY_HIGH) {
			return "hdpi";
		}
		if (density <= DisplayMetrics.DENSITY_XHIGH) {
			return "xhdpi";
		}
		if (density <= DisplayMetrics.DENSITY_XXHIGH) {
			return "xxhdpi";
		}

		return "xxxhdpi";
	}

	public ResourceUriBuilder setTheme(String theme) {
		this.theme = theme;
		return this;
	}

	public ResourceUriBuilder setCategory(String category) {
		this.category = category;
		return this;
	}

	public ResourceUriBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public ResourceUriBuilder setExtension(String extension) {
		this.extension = extension;
		return this;
	}

	public Uri build() {
		if (category == null) {
			throw new IllegalStateException("Category must be specified.");
		}
		if (name == null) {
			throw new IllegalStateException("Name must be specified.");
		}
		if (theme == null) {
			theme = "default";
		}
		if (extension == null) {
			extension = "png";
		}

		return new Uri.Builder()
			.scheme("https")
			.authority("appassets.infospace.com")
			.path("appassets")
			.appendPath(theme)
			.appendPath(category)
			.appendPath(densityString + "-" + name + "." + extension)
			.build();
	}
}
