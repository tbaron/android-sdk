package com.infospace.sdk.models;

import org.apache.commons.lang3.builder.*;

public class VideoResult extends BaseVideoResult {
	private String format;

	public String getFormat() {
		return format;
	}

	public void setFormat(String fileType) {
		this.format = fileType;
	}

	@Override
	protected EqualsBuilder onEquals(ResultNode node) {
		VideoResult other = (VideoResult) node;

		return super.onEquals(node)
			.append(format, other.format);
	}

	@Override
	protected HashCodeBuilder onHashCode() {
		return super.onHashCode()
			.append(format);
	}
}
