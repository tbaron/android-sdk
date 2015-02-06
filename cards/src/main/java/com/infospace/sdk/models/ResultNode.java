package com.infospace.sdk.models;

import org.apache.commons.lang3.builder.*;

import java.util.List;

/**
 * A Result Node.
 */
public abstract class ResultNode {
	private List<ResultNode> children;

	/**
	 * Any child elements of the Result.
	 *
	 * @return
	 */
	public List<ResultNode> getChildren() {
		return children;
	}

	public void setChildren(List<ResultNode> children) {
		this.children = children;
	}

	@Override
	public final boolean equals(Object object) {
		if (object == this) {
			return true;
		}

		if (object == null || object.getClass() != getClass()) {
			return false;
		}

		return onEquals((ResultNode) object)
			.isEquals();
	}

	protected EqualsBuilder onEquals(ResultNode node) {
		return new EqualsBuilder()
			.append(children, node.children);
	}

	@Override
	public final int hashCode() {
		return onHashCode()
			.toHashCode();
	}

	protected HashCodeBuilder onHashCode() {
		return new HashCodeBuilder(7, 31);
	}
}
