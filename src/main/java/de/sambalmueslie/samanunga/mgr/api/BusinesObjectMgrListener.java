package de.sambalmueslie.samanunga.mgr.api;

/**
 * The listener for a business object.
 *
 * @author Sambalmueslie
 * @param <T>
 *            the {@link BusinessObject} type
 */
public interface BusinesObjectMgrListener<T extends BusinessObject> {
	/**
	 * A {@link BusinessObject} was added.
	 *
	 * @param obj
	 *            the object
	 */
	void added(T obj);

	/**
	 * A {@link BusinessObject} was chaned.
	 *
	 * @param obj
	 *            the object
	 */
	void changed(T obj);

	/**
	 * A {@link BusinessObject} was removed.
	 *
	 * @param obj
	 *            the object
	 */
	void removed(T obj);

}
