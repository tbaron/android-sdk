package com.infospace.sdk.models;

import org.apache.commons.lang3.builder.*;

import java.util.Date;

public class WeatherForecastResult extends Result {
	private String conditionId;
	private Date date;
	private String description;
	private double temperatureMax;
	private double temperatureMin;
	private String thumbnailUrl;

	/**
	 * The condition identifier for this forecast.
	 */
	public String getConditionId() {
		return conditionId;
	}

	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}

	/**
	 * The date for this forecast.
	 */
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * The textual summary description of this forecast.
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * The high temperature for this forecast period.
	 */
	public double getTemperatureMax() {
		return temperatureMax;
	}

	public void setTemperatureMax(double temperatureMax) {
		this.temperatureMax = temperatureMax;
	}

	/**
	 * The low temperature for this forecast period.
	 */
	public double getTemperatureMin() {
		return temperatureMin;
	}

	public void setTemperatureMin(double temperatureMin) {
		this.temperatureMin = temperatureMin;
	}

	/**
	 * A thumbnail representing the weather condition for this forecast.
	 */
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	@Override
	protected EqualsBuilder onEquals(ResultNode node) {
		WeatherForecastResult other = (WeatherForecastResult) node;

		return super.onEquals(node)
			.append(date, other.date)
			.append(conditionId, other.conditionId)
			.append(description, other.description)
			.append(temperatureMax, other.temperatureMax)
			.append(temperatureMin, other.temperatureMin)
			.append(thumbnailUrl, other.thumbnailUrl);
	}

	@Override
	protected HashCodeBuilder onHashCode() {
		return super.onHashCode()
			.append(conditionId)
			.append(date)
			.append(description)
			.append(temperatureMax)
			.append(temperatureMin)
			.append(thumbnailUrl);
	}
}
