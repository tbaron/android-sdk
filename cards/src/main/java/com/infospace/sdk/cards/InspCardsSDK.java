package com.infospace.sdk.cards;

import android.content.Context;

import com.infospace.sdk.Container;
import com.infospace.sdk.cards.events.CardObserver;

public final class InspCardsSDK {
	private InspCardsSDK() {
	}

	public static CardObserver getCardObserver() {
		return Container.resolve(CardObserver.class);
	}

	public static void setup(Context context) {
		new InspCardsSDKInitializer(context).initialize();
	}
}
