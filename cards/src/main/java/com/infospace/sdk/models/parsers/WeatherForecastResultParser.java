package com.infospace.sdk.models.parsers;

import com.infospace.sdk.models.WeatherForecastResult;

import org.json.JSONObject;

import java.util.Date;

public class WeatherForecastResultParser extends BaseResultParser {
	@Override
	protected WeatherForecastResult createResult() {
		return new WeatherForecastResult();
	}

	/**
	 * Populates a weather forecast result object with the following fields
	 * including the applicable fields from {@link BaseResultParser}.
	 * <ul>
	 * <li>Date</li>
	 * <li>Description</li>
	 * <li>Thumbnail URL</li>
	 * <li>Temperature Min</li>
	 * <li>Temperature Max</li>
	 * </ul>
	 *
	 * @param obj weather JSON response
	 * @return weather result object
	 * @see com.infospace.sdk.models.parsers.StandardResultParser#parseResult(org.json.JSONObject)
	 */
	@Override
	protected WeatherForecastResult onParse(JSONObject obj) {
		WeatherForecastResult result = (WeatherForecastResult) super.onParse(obj);

		String thumbnailUrl = getThumbnailUrl(obj);
		result.setConditionId(getConditionId(thumbnailUrl));
		result.setDate(getDate(obj));
		result.setDescription(getDescription(obj));
		result.setTemperatureMax(getTemperatureMax(obj));
		result.setTemperatureMin(getTemperatureMin(obj));
		result.setThumbnailUrl(thumbnailUrl);

		return result;
	}

	private static String getConditionId(String thumbnailUrl) {
		return WeatherResultParser.parseConditionIdFromUrl(thumbnailUrl);
	}

	private static Date getDate(JSONObject obj) {
		long milliseconds = obj.optLong("date");

		return createDate(milliseconds);
	}

	private static Date createDate(long milliseconds) {
		if (milliseconds <= 0) {
			return null;
		}

		return new Date(milliseconds);
	}

	private String getDescription(JSONObject obj) {
		return obj.optString("description");
	}

	private double getTemperatureMax(JSONObject obj) {
		return parseDouble(obj, "temperature_max");
	}

	private static double parseDouble(JSONObject obj, String key) {
		double value = obj.optDouble(key);

		if (Double.isNaN(value)) {
			try {
				value = Double.parseDouble(obj.optString(key));
			} catch (NumberFormatException e) {
				value = 0;
			}
		}

		return value;
	}

	private double getTemperatureMin(JSONObject obj) {
		return parseDouble(obj, "temperature_min");
	}

	private String getThumbnailUrl(JSONObject obj) {
		return obj.optString("thumb_url");
	}
}
