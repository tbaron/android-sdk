package com.infospace.sdk.cards.events;

import com.infospace.sdk.models.ResultNode;

public abstract class CardEvent {
	ResultNode result;

	public ResultNode getResult() {
		return result;
	}
}
