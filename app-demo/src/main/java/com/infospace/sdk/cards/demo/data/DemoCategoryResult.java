package com.infospace.sdk.cards.demo.data;

import com.infospace.sdk.models.*;

import org.apache.commons.lang3.builder.*;

public class DemoCategoryResult extends StandardResult {
	public static final String CATEGORY_HOME = "categories";
	public static final String CATEGORY_LIST = "results_list";
	public static final String CATEGORY_GRID = "results_grid";
	public static final String CATEGORY_EVENTLOG = "event_log";

	private String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	protected EqualsBuilder onEquals(ResultNode node) {
		DemoCategoryResult other = (DemoCategoryResult) node;

		return super.onEquals(node)
			.append(category, other.category);
	}

	@Override
	protected HashCodeBuilder onHashCode() {
		return super.onHashCode()
			.append(category);
	}
}
