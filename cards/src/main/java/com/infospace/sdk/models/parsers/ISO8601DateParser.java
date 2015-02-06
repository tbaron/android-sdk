package com.infospace.sdk.models.parsers;

import java.text.*;
import java.util.*;

public final class ISO8601DateParser {
	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

	public static Date parse(String value) throws ParseException {
		value = convertTimeZonePart(value);

		return new SimpleDateFormat(DATE_FORMAT, Locale.US).parse(value);
	}

	private static String convertTimeZonePart(String value) {
		return value.replace("Z", "+0000");
	}
}
