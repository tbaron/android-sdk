package com.infospace.sdk.models;

import org.apache.commons.lang3.builder.*;

/**
 * A shopping review result.
 * <p/>
 * Represents a single merchant offer for a product with an external link.
 */
public class ShoppingReviewResult extends StandardResult {
	/**
	 * Value which indicates the date field was invalid or missing from the response.
	 */
	public static final long INVALID_DATE = -1;
	/**
	 * Value which indicates the rating field was invalid or missing from the response.
	 */
	public static final double INVALID_RATING = -1;
	private String author;
	private String bottomLine;
	private String cons;
	private String pros;
	private double rating;
	private long date; // 01/29/2014 00:00:00 from ECN

	/**
	 * The review author.
	 */
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * The bottom line text of a shopping product.
	 * <p/>
	 * This will be the central argument in the review of a shopping product.
	 *
	 * @return shopping review bottom line string
	 */
	public String getBottomLine() {
		return bottomLine;
	}

	public void setBottomLine(String bottomLine) {
		this.bottomLine = bottomLine;
	}

	/**
	 * The cons in the review for a given shopping product.
	 *
	 * @return shopping review cons string
	 */
	public String getCons() {
		return cons;
	}

	public void setCons(String cons) {
		this.cons = cons;
	}

	/**
	 * gets the pros in the review for a given shopping product.
	 *
	 * @return shopping review pros string
	 */
	public String getPros() {
		return pros;
	}

	public void setPros(String pros) {
		this.pros = pros;
	}

	/**
	 * The rating score for the merchant.
	 * <p/>
	 * Alternate Content Sources possibly have different rating systems.  The content source used can be found in Result.contentSources[i].getFriendlyName.
	 *
	 * @return A double value representing the merchant rating.
	 * <p/>
	 * For "Ebay Content Network": Value ranges from [0-5], inclusive.
	 * <p/>
	 * Other content sources have an unspecified range.
	 */
	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	/**
	 * The review posted date.
	 *
	 * @return the UTC timestamp in milliseconds from Jan 1, 1970.  Note that there is a +/- 1 day accuracy here due to time zone conversions.  This should not be used for time sensitive analysis.
	 * <p/>
	 * Can returns a value of INVALID_DATE if the date was unparseable or missing.
	 */
	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	@Override
	protected EqualsBuilder onEquals(ResultNode node) {
		ShoppingReviewResult other = (ShoppingReviewResult) node;

		return super.onEquals(node)
			.append(author, other.author)
			.append(bottomLine, other.bottomLine)
			.append(cons, other.cons)
			.append(pros, other.pros)
			.append(rating, other.rating)
			.append(date, other.date);
	}

	@Override
	protected HashCodeBuilder onHashCode() {
		return super.onHashCode()
			.append(author)
			.append(bottomLine)
			.append(cons)
			.append(pros)
			.append(rating)
			.append(date);
	}
}
