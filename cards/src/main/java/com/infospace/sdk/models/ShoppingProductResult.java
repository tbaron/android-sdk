package com.infospace.sdk.models;

import org.apache.commons.lang3.builder.*;

import java.util.List;

/**
 * A shopping product result.
 * <p/>
 * Represents a shopping product with multiple potential offers.
 */
public class ShoppingProductResult extends StandardResult {
	/**
	 * Indicates the rating field was missing or unable to be parsed correctly.
	 */
	public static final double INVALID_RATING = -1;
	/**
	 * Indicates the review count value was missing or unable to be parsed correctly.
	 */
	public static final int INVALID_REVIEW_COUNT = -1;
	/**
	 * Indicates the markdown percent value was missing or unable to be parsed correctly.
	 */
	public static final double INVALID_MARKDOWN_PERCENT = -1;
	private String price;
	private boolean onSale;
	private double rating;
	private String ratingImageUrl;
	private int reviewCount;
	private double markdownPercent;
	private List<ImageResult> thumbnails;

	/**
	 * The price of the offer before tax and shipping.
	 *
	 * @return String formatted as [curreny_symbol][price], e.g. $9.99.  The currency symbol will be determined by localizing the user's IP address.
	 */
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * On sale information for the product.
	 *
	 * @return
	 */
	public boolean isOnSale() {
		return onSale;
	}

	public void setOnSale(boolean onSale) {
		this.onSale = onSale;
	}

	/**
	 * The rating score for the product.
	 * <p/>
	 * For actual rating indicators usable in Views, it is recommended to utilize <code>getRatingImageUrl()</code>
	 * <p/>
	 * Alternate Content Sources possibly have different rating systems.  The content source used can be found in Result.contentSources[i].getFriendlyName.
	 *
	 * @return A float value representing the rating.
	 * <p/>
	 * For "Ebay Content Network": Value ranges from [0-5], inclusive.
	 * <p/>
	 * Other content sources have an unspecified range.
	 * <p/>
	 * Can returns a value of INVALID_RATING if the rating was unparseable, missing or negative.
	 */
	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	/**
	 * A url for the rating score image for the product.
	 *
	 * @return
	 */
	public String getRatingImageUrl() {
		return ratingImageUrl;
	}

	public void setRatingImageUrl(String ratingImageUrl) {
		this.ratingImageUrl = ratingImageUrl;
	}

	/**
	 * The number of reviews for a given product.
	 *
	 * @return Returns a value of INVALID_REVIEW_COUNT if the review count was unparseable, missing, or less than 0.  Otherwise returns the number of reviews.
	 */
	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	/**
	 * Percentage markdown for the lowest offer for the product.
	 *
	 * @return a value between [0.0-100.0] inclusive.
	 * <p/>
	 * Can returns a value of INVALID_MARKDOWN_PERCENT if the rating was unparseable, missing, or outside the accepted range.
	 */
	public double getMarkdownPercent() {
		return markdownPercent;
	}

	public void setMarkdownPercent(double markdownPercent) {
		this.markdownPercent = markdownPercent;
	}

	/**
	 * A list of all thumbnail urls.  Will return an empty list if no thumbnail urls are present.  Guaranteed to be non-null.
	 *
	 * @return
	 */
	public List<ImageResult> getThumbnailUrls() {
		return thumbnails;
	}

	public void setThumbnailUrls(List<ImageResult> thumbnails) {
		this.thumbnails = thumbnails;
	}

	@Override
	protected EqualsBuilder onEquals(ResultNode node) {
		ShoppingProductResult other = (ShoppingProductResult) node;

		return super.onEquals(node)
			.append(price, other.price)
			.append(onSale, other.onSale)
			.append(rating, other.rating)
			.append(ratingImageUrl, other.ratingImageUrl)
			.append(reviewCount, other.reviewCount)
			.append(markdownPercent, other.markdownPercent)
			.append(thumbnailHashCode(), other.thumbnailHashCode());
	}

	@Override
	protected HashCodeBuilder onHashCode() {
		return super.onHashCode()
			.append(price)
			.append(onSale)
			.append(rating)
			.append(ratingImageUrl)
			.append(reviewCount)
			.append(markdownPercent)
			.append(thumbnailHashCode());
	}

	private int thumbnailHashCode() {
		int hashCode = 0;
		for (ImageResult result : thumbnails) {
			hashCode = 31 * hashCode + thumbnails.hashCode();
		}
		return hashCode;
	}
}
