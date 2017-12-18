package de.sambalmueslie.samanunga.config;

public class DoubleProperty extends Property<Double> {

	public DoubleProperty(String name, Double defaultValue) {
		super(name, defaultValue);
	}

	@Override
	public Double get() {
		return value;
	}

	public Double getDouble() {
		return get();
	}

	@Override
	public void set(Double value) {
		if (value == null) return;
		this.value = value;
	}

	public void setDouble(Double value) {
		this.set(value);
	}

	@Override
	protected String marshall() {
		return Double.toString(value);
	}

	@Override
	protected void unmarshall(String value) {
		if (value == null) {
			this.value = getDefault();
		} else {
			try {
				this.value = new Double(value);
			} catch (final NumberFormatException e) {
				this.value = getDefault();
			}
		}
	}

	private Double value;
}
