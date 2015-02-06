package com.infospace.sdk.models;

import org.apache.commons.lang3.builder.*;

public class SpellingResult extends Result {
	private String title;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return title;
	}

	@Override
	protected EqualsBuilder onEquals(ResultNode node) {
		SpellingResult other = (SpellingResult) node;

		return super.onEquals(node)
			.append(title, other.title);
	}

	@Override
	protected HashCodeBuilder onHashCode() {
		return super.onHashCode()
			.append(title);
	}
}
