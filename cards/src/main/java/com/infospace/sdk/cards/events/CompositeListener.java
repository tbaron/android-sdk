package com.infospace.sdk.cards.events;

import java.util.concurrent.ConcurrentLinkedQueue;

abstract class CompositeListener<TListener> {
	protected final ConcurrentLinkedQueue<TListener> listeners;

	CompositeListener() {
		listeners = new ConcurrentLinkedQueue<TListener>();
	}

	public void add(TListener listener) {
		listeners.add(listener);
	}

	public void remove(TListener listener) {
		listeners.remove(listener);
	}
}
