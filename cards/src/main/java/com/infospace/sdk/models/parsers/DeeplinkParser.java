package com.infospace.sdk.models.parsers;

import com.infospace.sdk.models.*;

public class DeeplinkParser extends StandardResultParser {

	@Override
	protected Result createResult() {
		return new DeeplinkResult();
	}

}
