package com.infospace.sdk.cards.demo.data;

import android.content.Context;

import com.infospace.sdk.Container;
import com.infospace.sdk.models.ResultNode;
import com.infospace.sdk.models.parsers.*;
import com.infospace.sdk.util.JsonObjectCollection;

import org.apache.commons.io.IOUtils;
import org.json.*;

import java.io.*;
import java.util.*;

public class DemoResultProvider {
	private final Context context;
	private final ResultParserFactory factory;

	public DemoResultProvider(Context context) {
		this.context = context;
		this.factory = Container.resolve(ResultParserFactory.class);
	}

	public List<ResultNode> getResults(int resourceId) {
		JsonObjectCollection list = loadJson(resourceId);
		List<ResultNode> nodes = new ArrayList<ResultNode>();

		if (list != null) {
			for (JSONObject item : list) {
				ResultNode node = parseItem(item);

				if (node != null) {
					nodes.add(node);
				}
			}
		}

		return nodes;
	}

	private JsonObjectCollection loadJson(int resourceId) {
		if (resourceId == 0) {
			return null;
		}

		String str = readResource(resourceId);

		try {
			JSONArray array = new JSONArray(str);

			return new JsonObjectCollection(array);
		} catch (JSONException e) {
			e.printStackTrace();

			return null;
		}
	}

	private String readResource(int resourceId) {
		String str;
		InputStream stream = context.getResources().openRawResource(resourceId);

		try {
			str = IOUtils.toString(stream, "utf-8");
		} catch (IOException e) {
			str = null;
		}
		return str;
	}

	private ResultNode parseItem(JSONObject item) {
		ResultParser parser = factory.resolve(item);

		if (parser != null) {
			return parser.parseResult(item);
		}
		return null;
	}
}
