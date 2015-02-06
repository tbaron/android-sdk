package com.infospace.sdk.models;

import org.apache.commons.lang3.builder.*;

import java.util.Date;

/**
 * Represents a shopping deal result
 */
public class ShoppingDealResult extends StandardResult {
	private String couponCode;
	private double dollarsOff;
	private Date expirationDate;
	private String merchantLogoUrl;
	private String merchantName;
	private double percentOff;
	private double price;
	private double priceSale;
	private String restrictions;
	private Date startDate;

	/**
	 * Gets the coupon code for this deal.
	 *
	 * @return the coupon code for this deal
	 */
	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	/**
	 * Gets the dollar value off of the original price for this deal.
	 *
	 * @return the dollar value off of the original price for this deal
	 */
	public double getDollarsOff() {
		return dollarsOff;
	}

	public void setDollarsOff(double dollarsOff) {
		this.dollarsOff = dollarsOff;
	}

	/**
	 * Gets the expiration date for this deal.
	 *
	 * @return the expiration date for this deal
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * Gets the merchant logo URL for this deal.
	 *
	 * @return the merchant logo URL for this deal
	 */
	public String getMerchantLogoUrl() {
		return merchantLogoUrl;
	}

	public void setMerchantLogoUrl(String merchantLogoUrl) {
		this.merchantLogoUrl = merchantLogoUrl;
	}

	/**
	 * Gets the merchant name for this deal.
	 *
	 * @return the merchant name for this deal
	 */
	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchant) {
		this.merchantName = merchant;
	}

	/**
	 * Gets the percentage off of the original price for this deal.
	 *
	 * @return the percentage off of the original price for this deal
	 */
	public double getPercentOff() {
		return percentOff;
	}

	public void setPercentOff(double percentOff) {
		this.percentOff = percentOff;
	}

	/**
	 * Gets the original price for this deal.
	 *
	 * @return the original price for this deal
	 */
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gets the sale price of this deal.
	 *
	 * @return the sale price of this deal.
	 */
	public double getPriceSale() {
		return priceSale;
	}

	public void setPriceSale(double priceSale) {
		this.priceSale = priceSale;
	}

	/**
	 * Gets the restrictions for this deal.
	 *
	 * @return the restrictions for this deal
	 */
	public String getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}

	/**
	 * Gets the start date for this deal.
	 *
	 * @return the start date for this deal
	 */
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	protected EqualsBuilder onEquals(ResultNode node) {
		ShoppingDealResult other = (ShoppingDealResult) node;

		return super.onEquals(node)
			.append(couponCode, other.couponCode)
			.append(dollarsOff, other.dollarsOff)
			.append(expirationDate, other.expirationDate)
			.append(merchantLogoUrl, other.merchantLogoUrl)
			.append(merchantName, other.merchantName)
			.append(percentOff, other.percentOff)
			.append(price, other.price)
			.append(priceSale, other.priceSale)
			.append(restrictions, other.restrictions)
			.append(startDate, other.startDate);
	}

	@Override
	protected HashCodeBuilder onHashCode() {
		return super.onHashCode()
			.append(couponCode)
			.append(dollarsOff)
			.append(expirationDate)
			.append(merchantLogoUrl)
			.append(merchantName)
			.append(percentOff)
			.append(price)
			.append(priceSale)
			.append(restrictions)
			.append(startDate);
	}
}
