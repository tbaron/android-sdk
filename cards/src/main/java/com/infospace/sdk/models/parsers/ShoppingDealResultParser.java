package com.infospace.sdk.models.parsers;

import com.infospace.sdk.models.*;
import com.infospace.sdk.util.ShoppingHelper;

import org.json.JSONObject;

import java.text.*;
import java.util.*;

public class ShoppingDealResultParser extends StandardResultParser {
	private final ShoppingHelper shoppingHelper = new ShoppingHelper();
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	protected Result createResult() {
		return new ShoppingDealResult();
	}

	@Override
	protected ShoppingDealResult onParse(JSONObject obj) {
		ShoppingDealResult result = (ShoppingDealResult) super.onParse(obj);

		result.setCouponCode(getCouponCode(obj));
		result.setDollarsOff(getDollarsOff(obj));
		result.setExpirationDate(getExpirationDate(obj));
		result.setMerchantLogoUrl(getMerchantLogoUrl(result));
		result.setMerchantName(getMerchantName(obj));
		result.setPercentOff(getPercentOff(obj));
		result.setPrice(getPrice(obj));
		result.setPriceSale(getPriceSale(obj));
		result.setRestrictions(getRestrictions(obj));
		result.setStartDate(getStartDate(obj));

		return result;
	}

	private String getCouponCode(JSONObject obj) {
		return obj.optString("coupon_code");
	}

	private double getDollarsOff(JSONObject obj) {
		String value = obj.optString("dollars_off");

		return parseDouble(value);
	}

	private Date getExpirationDate(JSONObject obj) {
		String value = obj.optString("expiration_date");

		return parseDate(value);
	}

	private String getMerchantLogoUrl(ShoppingDealResult result) {
		List<ImageResult> images = new ShoppingHelper().extractImageResults(result);

		if (images != null && !images.isEmpty()) {
			ImageResult image = images.get(0);
			if (image != null) {
				return image.getURL();
			}
		}

		return "";
	}

	private String getMerchantName(JSONObject obj) {
		return obj.optString("merchant_name");
	}

	private double getPercentOff(JSONObject obj) {
		String value = obj.optString("percent_off");

		return parseDouble(value);
	}

	private double getPrice(JSONObject obj) {
		String value = obj.optString("price");

		return parseDouble(value);
	}

	private double getPriceSale(JSONObject obj) {
		String value = obj.optString("price_sale");

		return parseDouble(value);
	}

	private String getRestrictions(JSONObject obj) {
		return obj.optString("restrictions");
	}

	private Date getStartDate(JSONObject obj) {
		String value = obj.optString("start_date");

		return parseDate(value);
	}

	private Date parseDate(String value) {
		if (value == null) {
			return null;
		}

		try {
			return dateFormat.parse(value);
		} catch (ParseException e) {
			return null;
		}
	}

	private double parseDouble(String value) {
		if (value == null) {
			return 0;
		}

		value = stripNonDigits(value);

		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	private String stripNonDigits(String value) {
		return value.replaceAll("[^\\d.]", "");
	}
}
