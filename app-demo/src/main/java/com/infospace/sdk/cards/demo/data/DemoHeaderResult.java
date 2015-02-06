package com.infospace.sdk.cards.demo.data;

import com.infospace.sdk.models.ResultNode;

import org.apache.commons.lang3.builder.*;

public class DemoHeaderResult extends ResultNode {
	private String title;
	private String description;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	protected EqualsBuilder onEquals(ResultNode node) {
		DemoHeaderResult other = (DemoHeaderResult) node;

		return super.onEquals(node)
			.append(title, other.title)
			.append(description, other.description);
	}

	@Override
	protected HashCodeBuilder onHashCode() {
		return super.onHashCode()
			.append(title)
			.append(description);
	}
}
