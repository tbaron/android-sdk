package com.infospace.sdk.models.actions;

import com.infospace.sdk.models.*;

import java.util.regex.*;

class ResultPhoneNumberExtractor {

	private static final Pattern phonePattern = Pattern.compile("\\b(?:\\+?([1-9]\\d?)[-.\\s]?)?\\(*([1-9]\\d{2})[-.)\\s]*(\\d{3})[-.\\s]*(\\d{4})\\b");


	String getPhoneNumber(Result result) {
		if (result instanceof LocalAdResult) {
			return getPhoneNumber((LocalAdResult) result);
		} else if (result instanceof WebResult) {
			return getPhoneNumber((WebResult) result);
		}
		return null;
	}

	private String getPhoneNumber(LocalAdResult result) {
		return result.getTelephoneNumber();
	}

	private String getPhoneNumber(WebResult result) {
		String description = result.getPlainTextDescription();

		if (description == null) {
			return null;
		}

		Matcher matcher = phonePattern.matcher(description);

		if (matcher.find()) {
			String countryCode = matcher.group(1);
			String areaCode = matcher.group(2);
			String phoneNumber = matcher.group(3) + matcher.group(4);

			StringBuilder builder = new StringBuilder();

			if (countryCode != null) {
				builder.append(countryCode);
			}
			if (areaCode != null) {
				builder.append(areaCode);
			}

			return builder.append(phoneNumber).toString();
		}
		return null;
	}
}
