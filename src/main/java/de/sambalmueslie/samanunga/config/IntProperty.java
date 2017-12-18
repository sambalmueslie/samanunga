package de.sambalmueslie.samanunga.config;

public class IntProperty extends Property<Integer> {

	public IntProperty(String name, Integer defaultValue) {
		super(name, defaultValue);
	}

	@Override
	public Integer get() {
		return value;
	}

	public int getInt() {
		return get();
	}

	@Override
	public void set(Integer value) {
		if (value == null) return;
		this.value = value;
	}

	public void setInt(int value) {
		this.set(value);
	}

	@Override
	protected String marshall() {
		return Integer.toString(value);
	}

	@Override
	protected void unmarshall(String value) {
		if (value == null) {
			this.value = getDefault();
		} else {
			try {
				this.value = new Integer(value);
			} catch (final NumberFormatException e) {
				this.value = getDefault();
			}
		}
	}

	private Integer value;
}
