package com.infospace.sdk.util;

import android.text.Html;

public final class HtmlHelper {
	private static final String HTML_TAG_PATTERN = "<[^>]*>";

	private HtmlHelper() {
	}

	public static String stripHTML(String html) {
		if (html != null) {
			html = html.replaceAll(HTML_TAG_PATTERN, "");
			html = Html.fromHtml(html).toString();
		}
		return html;
	}
}
