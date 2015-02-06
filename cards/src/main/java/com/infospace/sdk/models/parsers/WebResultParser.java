package com.infospace.sdk.models.parsers;

import com.infospace.sdk.models.*;

public class WebResultParser extends StandardResultParser {
	@Override
	protected Result createResult() {
		return new WebResult();
	}
}
