package de.sambalmueslie.samanunga.mgr.api;

import java.util.Collection;
import java.util.Optional;

/**
 * A manager is responsible for {@link BusinessObject}s.
 *
 * @author Sambalmueslie
 * @param <T>
 *            the object type
 */
public interface Manager<T extends BusinessObject> {
	/**
	 * Dispose the manager.
	 */
	void dipose();

	/**
	 * Get a {@link BusinessObject} by id.
	 *
	 * @param id
	 *            the id
	 * @return the {@link Optional} with the {@link BusinessObject}
	 */
	Optional<T> get(String id);

	/**
	 * @return a {@link Collection} of all {@link BusinessObject}s.
	 */
	Collection<T> getAll();

	/**
	 * Register a {@link BusinesObjectMgrListener}.
	 *
	 * @param listener
	 *            the listener
	 */
	void register(BusinesObjectMgrListener<T> listener);

	/**
	 * Setup the manager.
	 */
	void setup();

	/**
	 * Unregister a {@link BusinesObjectMgrListener}.
	 *
	 * @param listener
	 *            the listener
	 */
	void unregister(BusinesObjectMgrListener<T> listener);
}
