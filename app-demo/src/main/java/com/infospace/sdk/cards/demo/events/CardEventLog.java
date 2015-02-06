package com.infospace.sdk.cards.demo.events;

import com.infospace.sdk.cards.InspCardsSDK;
import com.infospace.sdk.cards.events.*;

import java.util.*;

public class CardEventLog implements OnCardClickListener, OnCardLongClickListener, OnCardLoadListener {
	private static final int MAX_EVENT_COUNT = 100;

	private List<DemoCardEvent> events;
	private List<OnNewEventListener> listeners;

	public CardEventLog() {
		this.events = new ArrayList<DemoCardEvent>();
		this.listeners = new ArrayList<OnNewEventListener>();
	}

	public List<DemoCardEvent> getEvents() {
		return Collections.unmodifiableList(events);
	}

	public void onStart() {
		CardObserver observer = InspCardsSDK.getCardObserver();

		observer.addOnCardClickListener(this);
		observer.addOnCardLongClickListener(this);
		observer.addOnCardLoadListener(this);
	}

	public void onStop() {
		CardObserver observer = InspCardsSDK.getCardObserver();

		observer.removeOnCardClickListener(this);
		observer.removeOnCardLongClickListener(this);
		observer.removeOnCardLoadListener(this);
	}

	@Override
	public void onCardClick(CardClickEvent event) {
		addEvent(new DemoCardEvent()
			.setType("click")
			.setDate(new Date())
			.setResult(event.getResult()));
	}

	@Override
	public void onCardLoad(CardLoadEvent event) {
		addEvent(new DemoCardEvent()
			.setType("load")
			.setDate(new Date())
			.setResult(event.getResult()));
	}

	@Override
	public void onCardLongClick(CardLongClickEvent event) {
		addEvent(new DemoCardEvent()
			.setType("longClick")
			.setDate(new Date())
			.setResult(event.getResult()));
	}

	private void addEvent(DemoCardEvent event) {
		while (events.size() >= MAX_EVENT_COUNT) {
			events.remove(events.size() - 1);
		}

		events.add(0, event);

		notifyListeners();
	}

	private void notifyListeners() {
		for (OnNewEventListener listener : listeners) {
			listener.onNewEvent(this);
		}
	}

	public void addOnNewEventListener(OnNewEventListener listener) {
		listeners.add(listener);
	}

	public void removeOnNewEventListener(OnNewEventListener listener) {
		listeners.remove(listener);
	}
}
