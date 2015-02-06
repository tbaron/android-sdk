package com.infospace.sdk.models.parsers;

import com.infospace.sdk.models.*;

import org.json.JSONObject;

public class HoroscopeResultParser extends StandardResultParser {
	@Override
	protected Result createResult() {
		return new HoroscopeResult();
	}

	/**
	 * Populates a horoscope result object with the following fields
	 * including the applicable fields from <code>StandardResultParser</code>.
	 * <ul>
	 * <li>Meter</li>
	 * </ul>
	 *
	 * @param obj horoscope JSON response
	 * @return horoscope result object
	 * @see StandardResultParser#parseResult(JSONObject)
	 */
	@Override
	protected HoroscopeResult onParse(JSONObject obj) {
		HoroscopeResult result = (HoroscopeResult) super.onParse(obj);

		result.setMeter(obj.optInt("meter"));

		return result;
	}
}
