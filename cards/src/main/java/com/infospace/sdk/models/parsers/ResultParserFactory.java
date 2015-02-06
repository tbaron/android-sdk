package com.infospace.sdk.models.parsers;

import android.util.Pair;

import com.infospace.sdk.models.parsers.matchers.JsonMatcher;

import org.json.JSONObject;

import java.util.*;

public class ResultParserFactory {
	public interface Creator<T> {
		T create();
	}

	private static class ClassCreator<T> implements Creator<T> {
		private final Class<T> type;

		public ClassCreator(Class<T> type) {
			if (type == null) {
				throw new IllegalArgumentException();
			}

			this.type = type;
		}

		@Override
		public T create() {
			try {
				return type.newInstance();
			} catch (Exception e) {
				return null;
			}
		}
	}

	private final List<Pair<JsonMatcher, Creator<? extends ResultParser>>> matchers;

	public ResultParserFactory() {
		matchers = new ArrayList<Pair<JsonMatcher, Creator<? extends ResultParser>>>();
	}

	/**
	 * Registers a new parser for the specified type name, overwriting any existing parser for that type.
	 *
	 * @param matcher    The matcher for which to associate the specified parser.
	 * @param parserType The parser class type.
	 * @param <T>        The type of the parser.
	 */
	public <T extends ResultParser> ResultParserFactory push(JsonMatcher matcher, Class<T> parserType) {
		return push(matcher, new ClassCreator<T>(parserType));
	}

	/**
	 * Registers a new parser for that matches the specified {@link JsonMatcher}, overwriting any existing parser for that type.
	 *
	 * @param matcher       The type name for which to associate the specified parser.
	 * @param parserCreator A creator that will instantiate the parser.
	 */
	public ResultParserFactory push(JsonMatcher matcher, Creator<? extends ResultParser> parserCreator) {
		if (matcher == null || parserCreator == null) {
			throw new IllegalArgumentException();
		}

		matchers.add(new Pair<JsonMatcher, Creator<? extends ResultParser>>(matcher, parserCreator));

		return this;
	}

	/**
	 * Attempts to resolve a parser that matches the specified {@link JSONObject} instance. If no parser is registered
	 * then the method returns null.
	 *
	 * @param json The JSON for which to resolve a parser.
	 * @return A new ResultParser instance for the specified type name, or null if no parser is registered.
	 */
	public ResultParser resolve(JSONObject json) {
		if (json != null) {
			Creator<? extends ResultParser> creator = findMatchingParser(json);

			if (creator != null) {
				return creator.create();
			}
		}

		return null;
	}

	private Creator<? extends ResultParser> findMatchingParser(JSONObject json) {
		for (int i = matchers.size() - 1; i >= 0; i--) {
			Pair<JsonMatcher, Creator<? extends ResultParser>> pair = matchers.get(i);

			if (pair.first.matches(json)) {
				return pair.second;
			}
		}

		return null;
	}
}
