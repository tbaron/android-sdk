package com.infospace.sdk.cards.events;

public class DefaultCardObserver implements CardObserver, CardEventDispatcher {
	private final OnCardClickCompositeListener onCardClickListener;
	private final OnCardLongClickCompositeListener onCardLongClickListener;
	private final OnCardLoadCompositeListener onCardLoadListener;

	public DefaultCardObserver() {
		onCardClickListener = new OnCardClickCompositeListener();
		onCardLongClickListener = new OnCardLongClickCompositeListener();
		onCardLoadListener = new OnCardLoadCompositeListener();
	}

	@Override
	public void addOnCardClickListener(OnCardClickListener listener) {
		onCardClickListener.add(listener);
	}

	@Override
	public void addOnCardLongClickListener(OnCardLongClickListener listener) {
		onCardLongClickListener.add(listener);
	}

	@Override
	public void addOnCardLoadListener(OnCardLoadListener listener) {
		onCardLoadListener.add(listener);
	}

	@Override
	public void removeOnCardClickListener(OnCardClickListener listener) {
		onCardClickListener.remove(listener);
	}

	@Override
	public void removeOnCardLongClickListener(OnCardLongClickListener listener) {
		onCardLongClickListener.remove(listener);
	}

	@Override
	public void removeOnCardLoadListener(OnCardLoadListener listener) {
		onCardLoadListener.remove(listener);
	}

	@Override
	public void onCardClick(CardClickEvent event) {
		onCardClickListener.onCardClick(event);
	}

	@Override
	public void onCardLongClick(CardLongClickEvent event) {
		onCardLongClickListener.onCardLongClick(event);
	}

	@Override
	public void onCardLoad(CardLoadEvent event) {
		onCardLoadListener.onCardLoad(event);
	}
}
