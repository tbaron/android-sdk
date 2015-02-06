package com.infospace.sdk.models;

import org.apache.commons.lang3.builder.*;

import java.util.Date;

/**
 * A News Result.
 */
public class NewsResult extends StandardResult {
	private Date date;
	private String thumbnailUrl;
	private String largeImageUrl;
	private String source;

	/**
	 * The posted date of the news story in GMT.
	 *
	 * @return
	 */
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getLargeImageUrl() {
		return largeImageUrl;
	}

	public void setLargeImageUrl(String largeImageUrl) {
		this.largeImageUrl = largeImageUrl;
	}

	/**
	 * The news source of the result.
	 *
	 * @return
	 */
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	protected EqualsBuilder onEquals(ResultNode node) {
		NewsResult other = (NewsResult) node;

		return super.onEquals(node)
			.append(date, other.date)
			.append(thumbnailUrl, other.thumbnailUrl)
			.append(largeImageUrl, other.largeImageUrl)
			.append(source, other.source);
	}

	@Override
	protected HashCodeBuilder onHashCode() {
		return super.onHashCode()
			.append(date)
			.append(thumbnailUrl)
			.append(largeImageUrl)
			.append(source);
	}
}
