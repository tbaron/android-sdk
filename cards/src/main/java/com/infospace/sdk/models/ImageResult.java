package com.infospace.sdk.models;

import org.apache.commons.lang3.builder.*;

/**
 * An image result.
 */
public class ImageResult extends StandardResult {
	private String fileName;
	private String format;
	private Size imageSize;
	private FileSize imageFileSize;
	private String sourceUrl;
	private String thumbnailUrl;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * The image file format.
	 *
	 * @return
	 */
	public String getFormat() {
		return format;
	}

	public void setFormat(String fileType) {
		this.format = fileType;
	}

	/**
	 * The width and height of the image.
	 *
	 * @return
	 */
	public Size getImageSize() {
		return this.imageSize;
	}

	public void setImageSize(Size imageSize) {
		this.imageSize = imageSize;
	}

	/**
	 * The size of the image file.
	 *
	 * @return
	 */
	public FileSize getImageFileSize() {
		return this.imageFileSize;
	}

	public void setImageFileSize(FileSize imageFileSize) {
		this.imageFileSize = imageFileSize;
	}

	/**
	 * The source web page of the image.
	 *
	 * @return
	 */
	public String getSourceUrl() {
		return this.sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	/**
	 * the URL of the thumbnail image.
	 *
	 * @return
	 */
	public String getThumbnailUrl() {
		return this.thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	@Override
	protected EqualsBuilder onEquals(ResultNode node) {
		ImageResult other = (ImageResult) node;

		return super.onEquals(node)
			.append(fileName, other.fileName)
			.append(format, other.format)
			.append(imageSize, other.imageSize)
			.append(imageFileSize, other.imageFileSize)
			.append(sourceUrl, other.sourceUrl)
			.append(thumbnailUrl, other.thumbnailUrl);
	}

	@Override
	protected HashCodeBuilder onHashCode() {
		return super.onHashCode()
			.append(fileName)
			.append(format)
			.append(imageSize)
			.append(imageFileSize)
			.append(sourceUrl)
			.append(thumbnailUrl);
	}
}
