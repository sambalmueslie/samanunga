package de.sambalmueslie.samanunga.config;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConfigurationTest {

	@Test
	public void testConfiguration() {
		assertEquals(true, SampleConfiguration.PROPERTY_BOOLEAN.get());
		assertEquals(1.0, SampleConfiguration.PROPERTY_DOUBLE.get(), 0.1);
		assertEquals(1.1f, SampleConfiguration.PROPERTY_FLOAT.get(), 0.1);
		assertEquals(20, SampleConfiguration.PROPERTY_INT.getInt());
		assertEquals(100L, SampleConfiguration.PROPERTY_LONG.getLong());
		assertEquals("Hello World", SampleConfiguration.PROPERTY_STRING.get());
	}

}
