package com.infospace.sdk.models;

import org.apache.commons.lang3.builder.*;

/**
 * An Ad object returned from the Infospace API.
 */
public class AdResult extends StandardResult {
	public final static double NO_RATING = Double.NaN;

	private double rating = NO_RATING;

	/**
	 * The rating of the location.
	 */
	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	protected EqualsBuilder onEquals(ResultNode node) {
		AdResult other = (AdResult) node;

		return super.onEquals(other)
			.append(rating, other.rating);
	}

	@Override
	protected HashCodeBuilder onHashCode() {
		return super.onHashCode()
			.append(rating);
	}
}
