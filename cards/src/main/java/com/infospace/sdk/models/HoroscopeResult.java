package com.infospace.sdk.models;

import org.apache.commons.lang3.builder.*;

/**
 * A horoscope sign result.
 */

public class HoroscopeResult extends StandardResult {
	private int meter;

	/**
	 * The meter amount.
	 */
	public int getMeter() {
		return meter;
	}

	public void setMeter(int meter) {
		this.meter = meter;
	}


	@Override
	protected EqualsBuilder onEquals(ResultNode node) {
		HoroscopeResult other = (HoroscopeResult) node;

		return super.onEquals(node)
			.append(meter, other.meter);
	}

	@Override
	protected HashCodeBuilder onHashCode() {
		return super.onHashCode()
			.append(meter);
	}
}
