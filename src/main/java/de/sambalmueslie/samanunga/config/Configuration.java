package de.sambalmueslie.samanunga.config;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Configuration {
	private static String basePath = "conf";

	/** logger. */
	private static Logger logger = LogManager.getLogger(Configuration.class);

	public static void setBasePath(String basePath) {
		Configuration.basePath = basePath;
	}

	protected Configuration(String fileName) {
		this.fileName = fileName;
		try {
			Files.createDirectories(Paths.get(basePath));
			readProperties();
			initProperties();
			saveProperties();
		} catch (final IOException e) {
			logger.error("Cannot create base path directories", e);
		}

	}

	/**
	 * @return the configuration type.
	 */
	protected abstract Class<?> getType();

	private List<Field> collectPropertyFields() {
		final Field[] declaredFields = getType().getDeclaredFields();
		final Predicate<? super Field> isPropertyPredicate = f -> Property.class.isAssignableFrom(f.getType());
		return Arrays.stream(declaredFields).filter(isPropertyPredicate).collect(Collectors.toList());
	}

	private void initProperties() {
		elements.clear();
		final List<Field> propertyFields = collectPropertyFields();

		for (final Field f : propertyFields) {
			f.setAccessible(true);

			final Object instance = Modifier.isStatic(f.getModifiers()) ? null : this;
			try {
				final Property<?> property = (Property<?>) f.get(instance);

				final String name = property.getName();
				final String configuredValue = properties.getProperty(name);
				if (configuredValue != null) {
					property.unmarshall(configuredValue);
				}
				elements.add(property);
			} catch (final IllegalAccessException e) {
				// intentionally left empty
			}
		}
	}

	private void readProperties() {
		final Path path = Paths.get(basePath, fileName);
		if (!Files.exists(path)) return;
		try {
			properties.load(Files.newInputStream(path, StandardOpenOption.CREATE, StandardOpenOption.READ));
		} catch (final IOException e) {
			logger.error("Cannot load file from path " + path, e);
		}
	}

	private void saveProperties() {
		elements.forEach(p -> properties.setProperty(p.getName(), p.marshall()));

		final Path path = Paths.get(basePath, fileName);
		try {
			properties.store(Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING), "");
		} catch (final IOException e) {
			logger.error("Cannot store file to path " + path, e);
		}
	}

	private final List<Property<?>> elements = new LinkedList<>();
	private final String fileName;
	private final Properties properties = new Properties();
}
