package com.infospace.sdk.cards.events;

public interface CardObserver {
	public void addOnCardClickListener(OnCardClickListener listener);

	public void addOnCardLongClickListener(OnCardLongClickListener listener);

	public void addOnCardLoadListener(OnCardLoadListener listener);

	public void removeOnCardClickListener(OnCardClickListener listener);

	public void removeOnCardLongClickListener(OnCardLongClickListener listener);

	public void removeOnCardLoadListener(OnCardLoadListener listener);
}
