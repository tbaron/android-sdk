package com.infospace.sdk.models;

import org.apache.commons.lang3.builder.*;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * A common set of properties shared between VideoResult types.
 */
public abstract class BaseVideoResult extends StandardResult {
	private String thumbnailUrl;
	private int duration;

	/**
	 * The URL of a thumbnail image of the video result.
	 */
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	/**
	 * The duration of the video in seconds.
	 *
	 * @return
	 */
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * A utility method which returns the duration as a HH:mm:ss string.
	 *
	 * @return
	 */
	public String getDisplayDuration() {
		if (0 == duration) {
			return "";
		}

		return convertDurationSecondsToString(duration);
	}

	private static String convertDurationSecondsToString(int duration) {
		long hours = TimeUnit.SECONDS.toHours(duration);

		long minutes = TimeUnit.SECONDS.toMinutes(duration) - TimeUnit.HOURS.toMinutes(hours);
		long seconds = duration - TimeUnit.MINUTES.toSeconds(minutes) - TimeUnit.HOURS.toSeconds(hours);

		return formatHoursMinutesSeconds(hours, minutes, seconds);
	}

	private static String formatHoursMinutesSeconds(long hours, long minutes, long seconds) {
		String result = formatHoursIfTheVideoIsLongEnough(hours);

		result += String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

		return result;
	}

	private static String formatHoursIfTheVideoIsLongEnough(long hours) {
		if (hours >= 1) {
			return String.format(Locale.getDefault(), "%01d:", hours);
		}

		return "";
	}

	@Override
	protected EqualsBuilder onEquals(ResultNode node) {
		BaseVideoResult other = (BaseVideoResult) node;

		return super.onEquals(node)
			.append(thumbnailUrl, other.thumbnailUrl)
			.append(duration, other.duration);
	}

	@Override
	protected HashCodeBuilder onHashCode() {
		return super.onHashCode()
			.append(thumbnailUrl)
			.append(duration);
	}
}
