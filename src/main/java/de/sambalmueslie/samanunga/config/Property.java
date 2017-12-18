package de.sambalmueslie.samanunga.config;

public abstract class Property<T> {

	protected Property(String name, T defaultValue) {
		this.name = name;
		this.defaultValue = defaultValue;
		set(defaultValue);
	}

	public abstract T get();

	public final T getDefault() {
		return defaultValue;
	}

	public final String getName() {
		return name;
	}

	public abstract void set(T value);

	protected abstract String marshall();

	protected abstract void unmarshall(String value);

	/** the default value. */
	private final T defaultValue;
	/** the name. */
	private final String name;
}
