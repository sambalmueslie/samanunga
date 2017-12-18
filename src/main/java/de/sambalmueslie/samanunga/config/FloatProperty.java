package de.sambalmueslie.samanunga.config;

public class FloatProperty extends Property<Float> {

	public FloatProperty(String name, Float defaultValue) {
		super(name, defaultValue);
	}

	@Override
	public Float get() {
		return value;
	}

	public Float getFloat() {
		return get();
	}

	@Override
	public void set(Float value) {
		if (value == null) return;
		this.value = value;
	}

	public void setFloat(Float value) {
		this.set(value);
	}

	@Override
	protected String marshall() {
		return Float.toString(value);
	}

	@Override
	protected void unmarshall(String value) {
		if (value == null) {
			this.value = getDefault();
		} else {
			try {
				this.value = new Float(value);
			} catch (final NumberFormatException e) {
				this.value = getDefault();
			}
		}
	}

	private Float value;
}
