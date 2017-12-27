package de.sambalmueslie.samanunga.mgr.api;

/**
 * A business object.
 *
 * @author Sambalmueslie
 * @param <K>
 *            the id type.
 */
public interface BusinessObject {
	/**
	 * Dispose the object.
	 */
	void dispose();

	/**
	 * @return the id.
	 */
	String getId();

	/**
	 * Register.
	 *
	 * @param listener
	 *            the {@link BusinessObjectListener}
	 */
	void register(BusinessObjectListener listener);

	/**
	 * Unregister.
	 *
	 * @param listener
	 *            the {@link BusinessObjectListener}
	 */
	void unregister(BusinessObjectListener listener);
}
