package com.infospace.sdk.models;

import org.apache.commons.lang3.builder.*;

/**
 * A localized Ad Result.
 */
public class LocalAdResult extends AdResult {
	private Coordinate coordinate;
	private String directionsUrl;
	private String address;
	private double distance;
	private String telephoneNumber;

	/**
	 * The Coordinate of the Ad result.
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * The URL for directions to the Ad target.
	 */
	public String getDirectionsUrl() {
		return directionsUrl;
	}

	public void setDirectionsUrl(String directionsUrl) {
		this.directionsUrl = directionsUrl;
	}

	/**
	 * The address of the Ad.
	 */
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * The distance to the Ad.
	 */
	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 * The telephone number to call.
	 */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	@Override
	protected EqualsBuilder onEquals(ResultNode node) {
		LocalAdResult other = (LocalAdResult) node;

		return super.onEquals(node)
			.append(coordinate, other.coordinate)
			.append(directionsUrl, other.directionsUrl)
			.append(address, other.address)
			.append(distance, other.distance)
			.append(telephoneNumber, other.telephoneNumber);
	}

	@Override
	protected HashCodeBuilder onHashCode() {
		return super.onHashCode()
			.append(coordinate)
			.append(directionsUrl)
			.append(address)
			.append(distance)
			.append(telephoneNumber);
	}
}
