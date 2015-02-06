package com.infospace.sdk.models.parsers;

import com.infospace.sdk.models.*;
import com.infospace.sdk.util.ShoppingHelper;

import org.json.JSONObject;

public class ShoppingOfferResultParser extends StandardResultParser {
	private ShoppingHelper shoppingHelper = new ShoppingHelper();

	@Override
	protected ShoppingOfferResult onParse(JSONObject obj) {
		ShoppingOfferResult result = (ShoppingOfferResult) super.onParse(obj);
		result.setOfferUrl(getOfferUrl(obj));
		result.setPrice(getPrice(obj));
		result.setOriginalPrice(getOriginalPrice(obj));
		// 6-aug-2014(po) - the backend currently returns this as a field labeled manufacturer.  it is supposed to be a merchant and will be surfaces as such to SDK partners.
		result.setMerchant(getManufacturer(obj));
		result.setMerchantLogoUrl(getMerchantLogoUrl(obj));
		result.setMerchantRating(getMerchantRating(obj));
		result.setFreeShipping(getFreeShipping(obj));
		result.setRating(getRating(obj));
		result.setRatingImageUrl(getRatingImageUrl(obj));
		result.setStockStatus(getStockStatus(obj));
		result.setOnSale(getOnSale(obj));

		shoppingHelper.configureThumbnailUrls(result);

		return result;
	}

	private String getOfferUrl(JSONObject obj) {
		return obj.optString("offer_url");
	}

	private String getPrice(JSONObject obj) {
		return obj.optString("price");
	}

	private String getOriginalPrice(JSONObject obj) {
		return obj.optString("original_price");
	}

	private String getManufacturer(JSONObject obj) {
		return obj.optString("manufacturer");
	}

	private String getMerchantLogoUrl(JSONObject obj) {
		return obj.optString("merchant_logo_url");
	}

	private double getMerchantRating(JSONObject obj) {
		double merchantRating = obj.optDouble("merchant_rating", ShoppingOfferResult.INVALID_RATING);
		if (merchantRating < 0d) {
			merchantRating = ShoppingOfferResult.INVALID_RATING;
		}
		return merchantRating;
	}

	private boolean getFreeShipping(JSONObject obj) {
		return obj.optBoolean("free_shipping");
	}

	private double getRating(JSONObject obj) {
		double rating = obj.optDouble("rating", ShoppingOfferResult.INVALID_RATING);
		if (rating < 0d) {
			rating = ShoppingOfferResult.INVALID_RATING;
		}
		return rating;
	}

	private String getRatingImageUrl(JSONObject obj) {
		return obj.optString("rating_image_url");
	}

	private boolean getOnSale(JSONObject obj) {
		return obj.optBoolean("on_sale");
	}

	private String getStockStatus(JSONObject obj) {
		return obj.optString("stock_status");
	}

	@Override
	protected Result createResult() {
		return new ShoppingOfferResult();
	}
}
