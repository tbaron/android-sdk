package com.infospace.sdk.cards.events;

class OnCardClickCompositeListener
	extends CompositeListener<OnCardClickListener>
	implements OnCardClickListener {

	@Override
	public void onCardClick(CardClickEvent event) {
		for (OnCardClickListener listener : listeners) {
			listener.onCardClick(event);
		}
	}
}
