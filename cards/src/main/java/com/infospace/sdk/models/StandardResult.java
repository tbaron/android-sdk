package com.infospace.sdk.models;

import com.infospace.sdk.util.HtmlHelper;

import org.apache.commons.lang3.builder.*;

import java.util.List;

/**
 * Represents a set of common properties between results.
 */
public abstract class StandardResult extends Result {
	private String description;
	private String url;
	private String displayURL;
	private String title;
	private List<DeeplinkResult> deeplinks;

	public StandardResult() {
	}

	/**
	 * The result click url.
	 * <p/>
	 * This is the functional url for a result and should be used
	 * for url-sharing (via email, twitter, messaging, etc),
	 * displaying in a browser, or any other activity that interacts
	 * with the target content of the result.
	 */
	public String getURL() {
		return this.url;
	}

	public void setURL(String url) {
		this.url = url;
	}

	/**
	 * A utility method to strip out the html tags from a display url.
	 *
	 * @return
	 */
	public String getPlainDisplayURL() {
		return HtmlHelper.stripHTML(this.getDisplayURL());
	}

	/**
	 * The result display url.
	 * This is for display purposes only and may not be a valid url
	 * for use in a browser.
	 */
	public String getDisplayURL() {
		return this.displayURL;
	}

	public void setDisplayURL(String displayURL) {
		this.displayURL = displayURL;
	}

	/**
	 * A utility method to strip out the html tags from a result description.
	 *
	 * @return
	 */
	public String getPlainTextDescription() {
		return HtmlHelper.stripHTML(this.getDescription());
	}

	/**
	 * The result description.
	 *
	 * @return
	 */
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * A utility method to strip the HTML from the result title.
	 *
	 * @return
	 */
	public String getPlainTextTitle() {
		return HtmlHelper.stripHTML(this.getTitle());
	}

	/**
	 * The result title.
	 *
	 * @return
	 */
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets a list of deeplinks for this result.
	 *
	 * @return A list of deeplink results for this result instance.
	 */
	public List<DeeplinkResult> getDeeplinks() {
		return deeplinks;
	}

	public void setDeeplinks(List<DeeplinkResult> deeplinks) {
		this.deeplinks = deeplinks;
	}

	@Override
	protected EqualsBuilder onEquals(ResultNode node) {
		StandardResult other = (StandardResult) node;

		return super
			.onEquals(other)
			.append(title, other.title)
			.append(url, other.url)
			.append(displayURL, other.displayURL)
			.append(description, other.description)
			.append(deeplinks, other.deeplinks);
	}

	@Override
	protected HashCodeBuilder onHashCode() {
		return super
			.onHashCode()
			.append(title)
			.append(url)
			.append(displayURL)
			.append(description)
			.append(deeplinks);
	}
}
