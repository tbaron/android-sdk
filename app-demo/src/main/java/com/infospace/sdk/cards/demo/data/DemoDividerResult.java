package com.infospace.sdk.cards.demo.data;

import com.infospace.sdk.models.ResultNode;

import org.apache.commons.lang3.builder.*;

public class DemoDividerResult extends ResultNode {
	private String imageName;

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	protected EqualsBuilder onEquals(ResultNode node) {
		DemoDividerResult other = (DemoDividerResult) node;

		return super.onEquals(node)
			.append(imageName, other.imageName);
	}

	@Override
	protected HashCodeBuilder onHashCode() {
		return super.onHashCode()
			.append(imageName);
	}
}
