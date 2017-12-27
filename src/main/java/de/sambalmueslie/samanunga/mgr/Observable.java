package de.sambalmueslie.samanunga.mgr;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Observable<L> {
	/** logger. */
	private static Logger logger = LogManager.getLogger(Observable.class);

	public void dispose() {
		listeners.clear();
	}

	public final void register(L listener) {
		if (listener == null) {
			logger.error("Cannot register null listener");
			return;
		}
		listeners.add(listener);
	}

	public final void unregister(L listener) {
		if (listener == null) return;
		listeners.remove(listener);
	}

	protected final void notify(Consumer<? super L> action) {
		listeners.forEach(action);
	}

	private final Set<L> listeners = new LinkedHashSet<>();
}
