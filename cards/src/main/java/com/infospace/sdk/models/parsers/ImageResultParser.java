package com.infospace.sdk.models.parsers;

import com.infospace.sdk.models.*;

import org.json.JSONObject;

public class ImageResultParser extends StandardResultParser {
	/**
	 * Populates an image result object with the following fields
	 * including the applicable fields from <code>StandardResultParser</code>.
	 * <ul>
	 * <li>File Name</li>
	 * <li>File Format</li>
	 * <li>Image File Size</li>
	 * <li>Image Size</li>
	 * <li>Source URL</li>
	 * <li>Thumbnail URL</li>
	 * </ul>
	 *
	 * @param obj image JSON response
	 * @return image result object
	 * @see StandardResultParser#parseResult(JSONObject)
	 */
	@Override
	protected ImageResult onParse(JSONObject obj) {
		ImageResult result = (ImageResult) super.onParse(obj);

		result.setFileName(getFileName(obj));
		result.setFormat(getFormat(obj));
		result.setImageFileSize(getImageFileSize(obj));
		result.setImageSize(getImageSize(obj));
		result.setSourceUrl(getSourceUrl(obj));
		result.setThumbnailUrl(getThumbnailUrl(obj));

		return result;
	}

	private String getFileName(JSONObject obj) {
		return obj.optString("name");
	}

	private String getFormat(JSONObject obj) {
		return obj.optString("format");
	}

	private static FileSize getImageFileSize(JSONObject obj) {
		return new FileSize(
			obj.optDouble("size", 0),
			obj.optString("size_unit"));
	}

	private static Size getImageSize(JSONObject obj) {
		return new Size(
			obj.optInt("height"),
			obj.optInt("width"));
	}

	private static String getSourceUrl(JSONObject obj) {
		return obj.optString("source_url");
	}

	private static String getThumbnailUrl(JSONObject obj) {
		return obj.optString("thumb_url");
	}

	@Override
	protected Result createResult() {
		return new ImageResult();
	}
}
