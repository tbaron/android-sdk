package com.infospace.sdk.models.parsers;

import com.infospace.sdk.models.*;
import com.infospace.sdk.util.ShoppingHelper;

import org.json.JSONObject;

public class ShoppingProductResultParser extends StandardResultParser {

	private ShoppingHelper shoppingHelper = new ShoppingHelper();

	@Override
	protected ShoppingProductResult onParse(JSONObject obj) {
		ShoppingProductResult result = (ShoppingProductResult) super.onParse(obj);

		result.setPrice(getPrice(obj));
		result.setOnSale(getOnSale(obj));
		result.setRating(getRating(obj));
		result.setRatingImageUrl(getRatingImageUrl(obj));
		result.setReviewCount(getReviewCount(obj));
		result.setMarkdownPercent(getMarkdownPercent(obj));

		shoppingHelper.configureThumbnailUrls(result);

		return result;
	}

	private double getMarkdownPercent(JSONObject obj) {
		double markdownPercent = obj.optDouble("markdown_percent", ShoppingProductResult.INVALID_MARKDOWN_PERCENT);
		if (markdownPercent < -0.0d || markdownPercent > 100.0d) {
			markdownPercent = ShoppingProductResult.INVALID_MARKDOWN_PERCENT;
		}
		return markdownPercent;
	}

	private String getPrice(JSONObject obj) {
		return obj.optString("price");
	}

	private boolean getOnSale(JSONObject obj) {
		return obj.optBoolean("on_sale");
	}

	private double getRating(JSONObject obj) {
		double rating = obj.optDouble("rating", ShoppingProductResult.INVALID_RATING);
		if (rating < 0) {
			rating = ShoppingProductResult.INVALID_RATING;
		}
		return rating;
	}

	private String getRatingImageUrl(JSONObject obj) {
		return obj.optString("rating_image_url");
	}

	private int getReviewCount(JSONObject obj) {
		int reviewCount = obj.optInt("review_count", ShoppingProductResult.INVALID_REVIEW_COUNT);
		if (reviewCount < 0) {
			reviewCount = ShoppingProductResult.INVALID_REVIEW_COUNT;
		}
		return reviewCount;
	}

	@Override
	protected Result createResult() {
		return new ShoppingProductResult();
	}
}
