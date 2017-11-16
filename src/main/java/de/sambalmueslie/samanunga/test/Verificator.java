package de.sambalmueslie.samanunga.test;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The verificator for the result and reference.
 *
 * @author Sambalmueslie
 */
class Verificator {

	/** logger. */
	private static Logger logger = LogManager.getLogger(Verificator.class);

	/**
	 * Constructor.
	 *
	 * @param fileOperator
	 *            {@link #fileOperator}
	 */
	Verificator(FileOperator fileOperator) {
		this.fileOperator = fileOperator;
	}

	/**
	 * @return <code>true</code> if equals, otherwise <code>false</code>
	 */
	boolean verify() {
		final List<String> reference = fileOperator.readReferenceFile();
		final List<String> result = fileOperator.readResultFile();

		final int refSize = reference.size();
		final int resSize = result.size();

		if (refSize != resSize) {
			logger.error("Different size found ref: {}  res: {}", refSize, resSize);
			return false;
		}

		final int max = Math.min(refSize, resSize);
		for (int i = 0; i < max; i++) {
			final String refLine = reference.get(i);
			final String resLine = result.get(i);
			if (!StringUtils.equals(refLine, resLine)) {
				logger.error("Different lines found at " + i + "\n expected: " + refLine + "\n current: " + resLine);
				return false;
			}
		}
		return true;
	}

	/** the {@link FileOperator}. */
	private final FileOperator fileOperator;
}
