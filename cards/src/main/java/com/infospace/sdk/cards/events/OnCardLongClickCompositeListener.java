package com.infospace.sdk.cards.events;

class OnCardLongClickCompositeListener
	extends CompositeListener<OnCardLongClickListener>
	implements OnCardLongClickListener {

	@Override
	public void onCardLongClick(CardLongClickEvent event) {
		for (OnCardLongClickListener listener : listeners) {
			listener.onCardLongClick(event);
		}
	}
}
