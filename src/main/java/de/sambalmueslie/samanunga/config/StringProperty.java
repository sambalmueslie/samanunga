package de.sambalmueslie.samanunga.config;

public class StringProperty extends Property<String> {

	public StringProperty(String name, String defaultValue) {
		super(name, defaultValue);
	}

	@Override
	public String get() {
		return value;
	}

	@Override
	public void set(String value) {
		this.value = value;
	}

	@Override
	protected String marshall() {
		return value;
	}

	@Override
	protected void unmarshall(String value) {
		this.value = value;
	}

	/** the value. */
	private String value;

}
