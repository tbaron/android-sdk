package com.infospace.sdk.cards.matchers;

import android.text.TextUtils;

import com.infospace.sdk.models.*;

public class NewsWithImageResultMatcher implements ResultMatcher {
	@Override
	public boolean matches(ResultNode result) {
		return result instanceof NewsResult &&
			!TextUtils.isEmpty(((NewsResult) result).getThumbnailUrl());
	}
}
