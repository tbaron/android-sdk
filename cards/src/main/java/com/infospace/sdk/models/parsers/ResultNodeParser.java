package com.infospace.sdk.models.parsers;

import com.infospace.sdk.Container;
import com.infospace.sdk.models.*;
import com.infospace.sdk.util.*;

import org.json.JSONObject;

import java.util.*;

public abstract class ResultNodeParser implements ResultParser {
	@Override
	public final ResultNode parseResult(JSONObject obj) {
		ResultNode result = onParse(obj);

		onPostParse(result);

		return result;
	}

	protected ResultNode onParse(JSONObject obj) {
		ResultNode node = createResult();

		node.setChildren(parseChildren(obj));

		return node;
	}

	protected abstract ResultNode createResult();

	private List<ResultNode> parseChildren(JSONObject obj) {
		if (obj == null) {
			return Collections.emptyList();
		}

		return parseChildren(JsonHelpers.getCollection(obj, "items"));
	}

	private List<ResultNode> parseChildren(JsonObjectCollection items) {
		List<ResultNode> results = new ArrayList<ResultNode>();

		if (items != null) {

			for (JSONObject obj : items) {
				ResultNode result = parseChildResult(obj);

				if (isValidResult(result)) {
					results.add(result);
				}
			}
		}

		return results;
	}

	protected ResultNode parseChildResult(JSONObject obj) {
		ResultParser parser = getParserForResult(obj);

		if (parser != null) {
			return parser.parseResult(obj);
		}

		return null;
	}

	private ResultParser getParserForResult(JSONObject obj) {
		return Container.resolve(ResultParserFactory.class).resolve(obj);
	}

	private boolean isValidResult(ResultNode result) {
		return result != null &&
			isNotHoroscopeAllResult(result);
	}

	private boolean isNotHoroscopeAllResult(ResultNode result) {
		// valid horoscope results that dont contain All (ie. horoscope search)

		return !(
			result instanceof HoroscopeResult &&
				((HoroscopeResult) result).getPlainTextTitle().startsWith("All")
		);
	}

	protected void onPostParse(ResultNode node) {
	}
}
