package de.sambalmueslie.samanunga.config;

public class SampleConfiguration extends Configuration {

	public static BooleanProperty PROPERTY_BOOLEAN = new BooleanProperty("bool", true);
	public static DoubleProperty PROPERTY_DOUBLE = new DoubleProperty("double", 1.0);
	public static FloatProperty PROPERTY_FLOAT = new FloatProperty("float", 1.1f);
	public static IntProperty PROPERTY_INT = new IntProperty("int", 10);
	public static LongProperty PROPERTY_LONG = new LongProperty("long", 100L);
	public static StringProperty PROPERTY_STRING = new StringProperty("string", "Hello World");

	private static final String FILENAME = "sample.conf";

	static {
		new SampleConfiguration();
	}

	private SampleConfiguration() {
		super(FILENAME);
	}

	@Override
	protected Class<?> getType() {
		return SampleConfiguration.class;
	}

}
