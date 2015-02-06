package com.infospace.sdk.models.parsers;

import com.infospace.sdk.models.*;

import org.json.JSONObject;

import java.text.*;
import java.util.*;

public class ShoppingReviewResultParser extends StandardResultParser {
	@Override
	protected ShoppingReviewResult onParse(JSONObject obj) {
		ShoppingReviewResult result = (ShoppingReviewResult) super.onParse(obj);

		result.setAuthor(getAuthor(obj));
		result.setBottomLine(getBottomLine(obj));
		result.setCons(getCons(obj));
		result.setPros(getPros(obj));
		result.setRating(getRating(obj));
		result.setDate(getDate(obj));
		return result;
	}

	private String getAuthor(JSONObject obj) {
		return obj.optString("author");
	}

	private String getCons(JSONObject obj) {
		return obj.optString("cons");
	}

	private String getPros(JSONObject obj) {
		return obj.optString("pros");
	}

	private String getBottomLine(JSONObject obj) {
		return obj.optString("bottom_line");
	}

	private double getRating(JSONObject obj) {
		double rating = obj.optDouble("rating", ShoppingReviewResult.INVALID_RATING);
		if (rating < 0d) {
			rating = ShoppingReviewResult.INVALID_RATING;
		}
		return rating;
	}

	private long getDate(JSONObject obj) {
		long dateAsTime = ShoppingReviewResult.INVALID_DATE;
		// assumes ECN format of: 01/29/2014 00:00:00
		try {
			Date date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US).parse(obj.optString("posted_date"));
			dateAsTime = date.getTime();
		} catch (ParseException ex) {

		}
		return dateAsTime;
	}

	@Override
	protected Result createResult() {
		return new ShoppingReviewResult();
	}

}
