package com.infospace.sdk.models;

import org.apache.commons.lang3.builder.*;

import java.util.List;

/**
 * Contains the model of an individual shopping offer result
 */
public class ShoppingOfferResult extends StandardResult {
	/**
	 * Indicates the rating field was missing or unable to be parsed correctly.
	 */
	public static final double INVALID_RATING = -1;
	private String offerUrl;
	private String price;
	private String originalPrice;
	private String merchant;
	private String merchantLogoUrl;
	private double merchantRating;
	private boolean freeShipping;
	private double rating;
	private String ratingImageUrl;
	private String stockStatus;
	private boolean onSale;
	private List<ImageResult> thumbnails;

	/**
	 * The offer click url.
	 * <p/>
	 * This is the functional url for the offer and should be used
	 * for url-sharing (via email, twitter, messaging, etc),
	 * displaying in a browser, or any other activity that interacts
	 * with the target content of the result.
	 *
	 * @return
	 */
	public String getOfferUrl() {
		return offerUrl;
	}

	public void setOfferUrl(String offerUrl) {
		this.offerUrl = offerUrl;
	}

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
	 * The original price of the offer in case of a sale.
	 *
	 * @return String formatted as [curreny_symbol][price], e.g. $9.99.  The currency symbol will be determined by localizing the user's IP address.
	 */
	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	/**
	 * The merchant for the offer.
	 *
	 * @return
	 */
	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	/**
	 * A url for the merchant's logo image.
	 *
	 * @return
	 */
	public String getMerchantLogoUrl() {
		return merchantLogoUrl;
	}

	public void setMerchantLogoUrl(String merchantLogoUrl) {
		this.merchantLogoUrl = merchantLogoUrl;
	}

	/**
	 * The rating score for the merchant.
	 * <p/>
	 * Alternate Content Sources possibly have different rating systems.  The content source used can be found in Result.contentSources[i].getFriendlyName.
	 *
	 * @return A value representing the merchant rating.
	 * <p/>
	 * For "Ebay Content Network": Value ranges from [0.0-5.0], inclusive.
	 * <p/>
	 * Other content sources have an unspecified range.
	 * <p/>
	 * Can returns a value of INVALID_RATING if the rating was unparseable or missing.
	 */
	public double getMerchantRating() {
		return merchantRating;
	}

	public void setMerchantRating(double merchantRating) {
		this.merchantRating = merchantRating;
	}

	/**
	 * Boolean indicating whether free shipping is offered.
	 *
	 * @return
	 */
	public boolean isFreeShipping() {
		return freeShipping;
	}

	public void setFreeShipping(boolean freeShipping) {
		this.freeShipping = freeShipping;
	}

	/**
	 * The rating score for the product.
	 * <p/>
	 * For actual rating indicators usable in Views, it is recommended to utilize <code>getRatingImageUrl()</code>
	 * <p/>
	 * Alternate Content Sources possibly have different rating systems.  The content source used can be found in Result.contentSources[i].getFriendlyName.
	 *
	 * @return A value representing the rating.
	 * <p/>
	 * For "Ebay Content Network": Value ranges from [0.0-5.0], inclusive.
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
	 * A url for the rating score image for the offered product if available.
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
	 * @return The range of values is indeterminate.
	 * @hide
	 * @todo: when this can be normalized, make it public.  Until then, leave it in as a hidden field
	 * <p/>
	 * Merchant stock information for the offered product.
	 */
	public String getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
	}

	/**
	 * On sale information for the offered product.
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
		ShoppingOfferResult other = (ShoppingOfferResult) node;

		return super.onEquals(node)
			.append(offerUrl, other.offerUrl)
			.append(price, other.price)
			.append(originalPrice, other.originalPrice)
			.append(merchant, other.merchant)
			.append(merchantLogoUrl, other.merchantLogoUrl)
			.append(merchantRating, other.merchantRating)
			.append(freeShipping, other.freeShipping)
			.append(rating, other.rating)
			.append(ratingImageUrl, other.ratingImageUrl)
			.append(stockStatus, other.stockStatus)
			.append(onSale, other.onSale)
			.append(thumbnailHashCode(), other.thumbnailHashCode());
	}

	@Override
	protected HashCodeBuilder onHashCode() {
		return super.onHashCode()
			.append(offerUrl)
			.append(price)
			.append(originalPrice)
			.append(merchant)
			.append(merchantLogoUrl)
			.append(merchantRating)
			.append(freeShipping)
			.append(rating)
			.append(ratingImageUrl)
			.append(stockStatus)
			.append(onSale)
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
