package de.sambalmueslie.samanunga.config;

public class BooleanProperty extends Property<Boolean> {

	public BooleanProperty(String name, Boolean defaultValue) {
		super(name, defaultValue);
	}

	@Override
	public Boolean get() {
		return value;
	}

	public boolean getBoolean() {
		return get();
	}

	@Override
	public void set(Boolean value) {
		if (value == null) return;
		this.value = value;
	}

	public void setBoolean(boolean value) {
		this.set(value);
	}

	@Override
	protected String marshall() {
		return Boolean.toString(value);
	}

	@Override
	protected void unmarshall(String value) {
		if (value == null) {
			this.value = getDefault();
		} else {
			try {
				this.value = new Boolean(value);
			} catch (final NumberFormatException e) {
				this.value = getDefault();
			}
		}
	}

	private Boolean value;
}
