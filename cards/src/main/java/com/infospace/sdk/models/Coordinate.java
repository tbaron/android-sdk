package com.infospace.sdk.models;

public class Coordinate {
	private double latitude;
	private double longitude;

	public Coordinate(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public static boolean equals(Coordinate a, Coordinate b) {
		return (a == null && b == null) ||
			(a != null && b != null && a.equals(b));

	}

	@Override
	public boolean equals(Object object) {
		if (object == null)
			return true;

		if (!(object instanceof Coordinate))
			return false;

		Coordinate result = (Coordinate) object;
		return super.equals(object) && this.getLatitude() == result.getLatitude()
			&& this.getLongitude() == result.getLongitude();
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public int hashCode() {
		int hash = super.hashCode();
		hash = 31 * hash + (int) getLatitude();
		hash = 31 * hash + (int) getLongitude();
		return hash;
	}
}
