package de.sambalmueslie.samanunga.mgr.api;

/**
 * The listener for the {@link BusinessObject}.
 *
 * @author Sambalmueslie
 */
public interface BusinessObjectListener {
	/**
	 * The {@link BusinessObject} has changed.
	 * 
	 * @param id
	 *            the {@link BusinessObject} id
	 */
	void changed(String id);
}
