package com.infospace.sdk.models.parsers;

import com.infospace.sdk.models.*;

import org.json.JSONObject;

public class SellerRatingParser extends BaseResultParser {
	@Override
	protected SellerRatingResult onParse(JSONObject obj) {
		SellerRatingResult result = (SellerRatingResult) super.onParse(obj);

		result.setRating(getRating(obj));

		return result;
	}

	private double getRating(JSONObject obj) {
		return obj.optDouble("rating", obj.optDouble("Rating", 0));
	}

	@Override
	protected Result createResult() {
		return new SellerRatingResult();
	}
}
