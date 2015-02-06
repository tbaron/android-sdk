package com.infospace.sdk.models;

import org.apache.commons.lang3.builder.*;

import java.util.Date;

/**
 * A weather result.
 */
public class WeatherResult extends StandardResult {
	private String conditionId;
	private Date date;
	private String thumbnailUrl;
	private int temperature;
	private int windChill;
	private int windSpeed;
	private String windDirection;
	private WeatherForecastResult[] forecast;

	/**
	 * The condition identifier.
	 */
	public String getConditionId() {
		return conditionId;
	}

	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}

	/**
	 * The forecast date in GMT.
	 *
	 * @return
	 */
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * The forecast image thumbnail url.
	 *
	 * @return
	 */
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	/**
	 * The current temperature.
	 */
	public int getTemperature() {
		return this.temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	/**
	 * The current temperature with wind chill.
	 *
	 * @return
	 */
	public int getWindChill() {
		return windChill;
	}

	public void setWindChill(int windChill) {
		this.windChill = windChill;
	}

	/**
	 * The current wind speed in MPH.
	 *
	 * @return
	 */
	public int getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(int windSpeed) {
		this.windSpeed = windSpeed;
	}

	/**
	 * The wind direction as a compass point.
	 *
	 * @return
	 */
	public String getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	/**
	 * Gets the future forecast associated with this weather result.
	 */
	public WeatherForecastResult[] getForecast() {
		return forecast;
	}

	public void setForecast(WeatherForecastResult[] forecast) {
		this.forecast = forecast;
	}

	@Override
	protected EqualsBuilder onEquals(ResultNode node) {
		WeatherResult other = (WeatherResult) node;

		return super.onEquals(node)
			.append(conditionId, other.conditionId)
			.append(date, other.date)
			.append(thumbnailUrl, other.thumbnailUrl)
			.append(temperature, other.temperature)
			.append(windChill, other.windChill)
			.append(windSpeed, other.windSpeed)
			.append(windDirection, other.windDirection)
			.append(forecast, other.forecast);
	}

	@Override
	protected HashCodeBuilder onHashCode() {
		return super.onHashCode()
			.append(conditionId)
			.append(date)
			.append(thumbnailUrl)
			.append(temperature)
			.append(windChill)
			.append(windSpeed)
			.append(windDirection)
			.append(forecast);
	}
}
