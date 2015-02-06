package com.infospace.sdk.models;

import org.apache.commons.lang3.builder.*;

/**
 * A group of Results.
 */
public class ResultGroup extends ResultNode {
	private ResultGroupType type;

	/**
	 * The Result group type.
	 */
	public ResultGroupType getType() {
		return type;
	}

	public void setType(ResultGroupType type) {
		this.type = type;
	}

	@Override
	protected EqualsBuilder onEquals(ResultNode node) {
		ResultGroup other = (ResultGroup) node;

		return super.onEquals(node)
			.append(type, other.type);
	}

	@Override
	protected HashCodeBuilder onHashCode() {
		return super.onHashCode()
			.append(type);
	}
}
