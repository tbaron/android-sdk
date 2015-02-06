package com.infospace.sdk.models.parsers;

import com.infospace.sdk.models.*;
import com.infospace.sdk.util.*;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import java.util.*;
import java.util.regex.*;

public class WeatherResultParser extends StandardResultParser {
	private static final Pattern CONDITION_ID_URL_PATTERN = Pattern.compile("/(\\d+)\\.\\w+$");

	@Override
	protected Result createResult() {
		return new WeatherResult();
	}

	/**
	 * Populates a weather result object with the following fields
	 * including the applicable fields from <code>StandardResultParser</code>.
	 * <ul>
	 * <li>Date</li>
	 * <li>Title</li>
	 * <li>Thumbnail URL</li>
	 * <li>Temperature</li>
	 * <li>Wind Chill</li>
	 * <li>Wind Speed</li>
	 * <li>Wind Direction</li>
	 * </ul>
	 *
	 * @param obj weather JSON response
	 * @return weather result object
	 * @see StandardResultParser#parseResult(JSONObject)
	 */
	@Override
	protected WeatherResult onParse(JSONObject obj) {
		WeatherResult result = (WeatherResult) super.onParse(obj);

		String thumbnailUrl = getThumbnailUrl(obj);
		result.setConditionId(getConditionId(thumbnailUrl));
		result.setDate(getDate(obj));
		result.setForecast(getForecast(obj));
		result.setTitle(getWeatherTitle(obj));
		result.setThumbnailUrl(thumbnailUrl);
		result.setTemperature(getTemperature(obj));
		result.setWindChill(getWindChill(obj));
		result.setWindSpeed(getWindSpeed(obj));
		result.setWindDirection(getWindDirection(obj));

		return result;
	}

	private static String getConditionId(String thumbnailUrl) {
		return WeatherResultParser.parseConditionIdFromUrl(thumbnailUrl);
	}

	/**
	 * @hide
	 */
	static String parseConditionIdFromUrl(String url) {
		if (url != null) {
			Matcher matcher = CONDITION_ID_URL_PATTERN.matcher(url);

			if (matcher.find()) {
				return matcher.group(1);
			}
		}

		return null;
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

	private WeatherForecastResult[] getForecast(JSONObject obj) {
		return parseForecast(
			JsonHelpers.getCollection(obj, "forecast")
		);
	}

	private WeatherForecastResult[] parseForecast(JsonObjectCollection items) {
		List<WeatherForecastResult> results = new ArrayList<WeatherForecastResult>();

		if (items != null) {

			for (JSONObject obj : items) {
				ResultNode result = parseChildResult(obj);

				if (result instanceof WeatherForecastResult) {
					results.add((WeatherForecastResult) result);
				}
			}
		}

		return results.toArray(new WeatherForecastResult[results.size()]);
	}

	private static String getWeatherTitle(JSONObject obj) {
		String location = obj.optString("location");

		if (location != null) {
			location = StringUtils.capitalize(location);
		}

		return location;
	}

	private static String getThumbnailUrl(JSONObject obj) {
		return obj.optString("thumb_url");
	}

	private static int getTemperature(JSONObject obj) {
		return obj.optInt("temperature");
	}

	private static int getWindChill(JSONObject obj) {
		return obj.optInt("wind_chill");
	}

	private static int getWindSpeed(JSONObject obj) {
		return obj.optInt("wind_speed");
	}

	private static String getWindDirection(JSONObject obj) {
		return obj.optString("wind_direction");
	}
}
