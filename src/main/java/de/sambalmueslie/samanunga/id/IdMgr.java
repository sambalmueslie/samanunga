package de.sambalmueslie.samanunga.id;

import java.util.HashMap;
import java.util.Map;

/**
 * The id manager, which creates ids (incrementing numbers) dedicated to a specified type.
 *
 * @author Sambalmueslie
 */
public class IdMgr {

	private static final Map<Class<?>, Long> counters = new HashMap<>();

	/**
	 * Clear the id counter for a dedicated {@link Class} type.
	 *
	 * @param type
	 *            the type
	 */
	public static void clear(Class<?> type) {
		counters.remove(type);
	}

	/**
	 * Clear all counters.
	 */
	public static void clearAll() {
		counters.clear();
	}

	/**
	 * Get a new id for a dedicated {@link Class} type.
	 *
	 * @param type
	 *            the type
	 * @return the new id
	 */
	public static long getNextId(Class<?> type) {
		Long current = counters.get(type);
		if (current == null) {
			current = 0L;
		} else {
			current++;
		}

		counters.put(type, current);
		return current;
	}

	/**
	 * Get a new id for a dedicated {@link Class} type.
	 *
	 * @param type
	 *            the type
	 * @param prefix
	 *            a prefix for the id, if null the simple name of the type is used
	 * @return the new id
	 */
	public static String getNextId(Class<?> type, String prefix) {
		final long id = getNextId(type);
		final String name = prefix == null ? type.getSimpleName().toLowerCase() : prefix;
		return String.format("%s-%03d", name, id);
	}

}
