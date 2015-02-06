package com.infospace.sdk.util;

import com.infospace.sdk.models.*;

import java.util.*;

public class ShoppingHelper {
	public void configureThumbnailUrls(ShoppingProductResult result) {
		List<ImageResult> thumbnails = extractImageResults(result);
		result.setThumbnailUrls(thumbnails);
	}

	public List<ImageResult> extractImageResults(StandardResult result) {
		List<ResultGroup> groups = extractSupplementalImagesGroups(result.getChildren());
		List<ImageResult> thumbnails = new ArrayList<ImageResult>();

		for (ResultGroup group : groups) {
			thumbnails.addAll(filterThumbnailUrls(group.getChildren()));
		}

		return thumbnails;
	}

	private List<ResultGroup> extractSupplementalImagesGroups(List<ResultNode> results) {
		List<ResultGroup> groups = new ArrayList<ResultGroup>();

		if (results != null) {
			for (int i = results.size() - 1; i >= 0; i--) {
				ResultNode node = results.get(i);

				if (node instanceof ResultGroup) {
					ResultGroup group = (ResultGroup) node;
					if (group.getType() == ResultGroupType.IMAGEGROUP) {
						results.remove(i);
						groups.add(group);
					}
				}
			}
		}
		return groups;
	}

	private static List<ImageResult> filterThumbnailUrls(List<ResultNode> items) {
		List<ImageResult> thumbnails = new ArrayList<ImageResult>();

		if (items != null) {
			for (int i = items.size() - 1; i >= 0; i--) {
				ResultNode item = items.get(i);
				if (item instanceof ImageResult) {
					items.remove(i);
					thumbnails.add((ImageResult) item);
				}
			}
		}
		return thumbnails;
	}

	public void configureThumbnailUrls(ShoppingOfferResult result) {
		List<ImageResult> thumbnails = extractImageResults(result);
		result.setThumbnailUrls(thumbnails);
	}
}
