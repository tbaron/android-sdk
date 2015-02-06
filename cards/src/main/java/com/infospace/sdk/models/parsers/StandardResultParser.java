package com.infospace.sdk.models.parsers;

import com.infospace.sdk.models.*;

import org.json.JSONObject;

import java.util.*;

public abstract class StandardResultParser extends BaseResultParser {
	/**
	 * Populates a result object with the following fields
	 * <ul>
	 * <li>Title</li>
	 * <li>Description</li>
	 * <li>Content URL</li>
	 * <li>Display URL</li>
	 * <li>Content Sources</li>
	 * </ul>
	 *
	 * @param obj JSON response
	 * @return result object
	 */
	@Override
	protected StandardResult onParse(JSONObject obj) {
		StandardResult result = (StandardResult) super.onParse(obj);

		result.setTitle(getTitle(obj));
		result.setDescription(getDescription(obj));
		result.setURL(getUrl(obj));
		result.setDisplayURL(getDisplayUrl(obj));

		setDeeplinks(result);

		return result;
	}

	private static final String getTitle(JSONObject obj) {
		return obj.optString("title");
	}

	private static final String getDescription(JSONObject obj) {
		return obj.optString("description");
	}

	private static final String getUrl(JSONObject obj) {
		return obj.optString("url");
	}

	private static final String getDisplayUrl(JSONObject obj) {
		return obj.optString("display_url");
	}

	private void setDeeplinks(StandardResult result) {
		List<DeeplinkResult> deeplinks = filterDeeplinks(result.getChildren());

		result.setDeeplinks(deeplinks);
	}

	private List<DeeplinkResult> filterDeeplinks(List<ResultNode> items) {
		List<DeeplinkResult> deeplinks = new ArrayList<DeeplinkResult>();

		if (items != null) {
			int count = items.size();

			for (int i = 0; i < count; i++) {
				ResultNode item = items.get(i);
				if (item instanceof DeeplinkResult) {
					items.remove(i);
					deeplinks.add((DeeplinkResult) item);

					i--;
					count--;
				}
			}
		}

		return deeplinks;
	}
}
