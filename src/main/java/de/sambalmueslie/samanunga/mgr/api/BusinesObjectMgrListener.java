package de.sambalmueslie.samanunga.mgr.api;

/**
 * The listener for a business object.
 *
 * @author Sambalmueslie
 */
public interface BusinesObjectMgrListener<T extends BusinessObject> {
	void added(T obj);

	void changed(T obj);

	void removed(T obj);

}
