package com.infospace.sdk.cards.demo.events;

import com.google.gson.annotations.SerializedName;
import com.infospace.sdk.models.ResultNode;

import org.apache.commons.lang3.builder.*;

import java.util.Date;

public class DemoCardEvent extends ResultNode {
	@SerializedName("result")
	private ResultNode result;

	@SerializedName("date")
	private Date date;

	@SerializedName("type")
	private String type;

	public ResultNode getResult() {
		return result;
	}

	public DemoCardEvent setResult(ResultNode result) {
		this.result = result;
		return this;
	}

	public Date getDate() {
		return date;
	}

	public DemoCardEvent setDate(Date date) {
		this.date = date;
		return this;
	}

	public String getType() {
		return type;
	}

	public DemoCardEvent setType(String type) {
		this.type = type;
		return this;
	}

	@Override
	protected EqualsBuilder onEquals(ResultNode node) {
		DemoCardEvent other = (DemoCardEvent) node;

		return super.onEquals(node)
			.append(type, other.type)
			.append(date, other.date)
			.append(result, other.result);
	}

	@Override
	protected HashCodeBuilder onHashCode() {
		return super.onHashCode()
			.append(type)
			.append(date)
			.append(result);
	}
}
