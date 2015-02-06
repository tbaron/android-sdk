package com.infospace.sdk.cards.events;

class OnCardLoadCompositeListener
	extends CompositeListener<OnCardLoadListener>
	implements OnCardLoadListener {

	@Override
	public void onCardLoad(CardLoadEvent event) {
		for (OnCardLoadListener listener : listeners) {
			listener.onCardLoad(event);
		}
	}
}
