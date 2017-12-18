package de.sambalmueslie.samanunga.config;

public class LongProperty extends Property<Long> {

	public LongProperty(String name, Long defaultValue) {
		super(name, defaultValue);
	}

	@Override
	public Long get() {
		return value;
	}

	public long getLong() {
		return get();
	}

	@Override
	public void set(Long value) {
		if (value == null) return;
		this.value = value;
	}

	public void setLong(long value) {
		this.set(value);
	}

	@Override
	protected String marshall() {
		return Long.toString(value);
	}

	@Override
	protected void unmarshall(String value) {
		if (value == null) {
			this.value = getDefault();
		} else {
			try {
				this.value = new Long(value);
			} catch (final NumberFormatException e) {
				this.value = getDefault();
			}
		}
	}

	private Long value;
}
