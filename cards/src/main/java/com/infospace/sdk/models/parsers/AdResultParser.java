package com.infospace.sdk.models.parsers;

import com.infospace.sdk.models.*;

import org.json.JSONObject;

import java.util.*;

public class AdResultParser extends StandardResultParser {

	@Override
	protected AdResult createResult() {
		return new AdResult();
	}

	@Override
	protected AdResult onParse(JSONObject obj) {
		AdResult result = (AdResult) super.onParse(obj);

		result.setRating(getSellerRating(result));

		return result;
	}

	private double getSellerRating(AdResult result) {
		List<SellerRatingResult> ratings = filterSellerRatings(result.getChildren());

		if (!ratings.isEmpty()) {
			return ratings.get(0).getRating();
		}

		return AdResult.NO_RATING;
	}

	private List<SellerRatingResult> filterSellerRatings(List<ResultNode> items) {
		List<SellerRatingResult> sellerRatings = new ArrayList<SellerRatingResult>();

		if (items != null) {
			for (int i = items.size() - 1; i >= 0; i--) {
				ResultNode item = items.get(i);
				if (item instanceof SellerRatingResult) {
					items.remove(i);
					sellerRatings.add((SellerRatingResult) item);
				}
			}
		}

		return sellerRatings;
	}
}
