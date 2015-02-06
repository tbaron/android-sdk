package com.infospace.sdk.models.parsers;

import com.infospace.sdk.models.*;

import org.json.JSONObject;

public class LocalAdResultParser extends AdResultParser {

	@Override
	protected LocalAdResult createResult() {
		return new LocalAdResult();
	}

	@Override
	protected LocalAdResult onParse(JSONObject obj) {
		LocalAdResult result = (LocalAdResult) super.onParse(obj);

		result.setCoordinate(parseCoordinate(obj));
		result.setDirectionsUrl(obj.optString("directions_url"));
		result.setAddress(obj.optString("address"));
		result.setDistance(obj.optDouble("distance"));
		result.setTelephoneNumber(obj.optString("phone_number"));
		result.setRating(obj.optDouble("rating"));

		return result;
	}

	private Coordinate parseCoordinate(JSONObject obj) {
		JSONObject coordinates = obj.optJSONObject("lat_lon");

		if (coordinates == null || coordinates.length() == 0) {
			return null;
		}

		double latitude = Double.parseDouble(coordinates.optString("latitude"));
		double longitude = Double.parseDouble(coordinates.optString("longitude"));

		return new Coordinate(latitude, longitude);
	}
}
