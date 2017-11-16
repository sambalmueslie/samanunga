package de.sambalmueslie.samanunga.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The file operator.
 *
 * @author Sambalmueslie
 */
class FileOperator {

	/** logger. */
	private static Logger logger = LogManager.getLogger(FileOperator.class);
	private static final String REFERENCC_FILE_EXT = ".ref";
	private static final String REFERENCE_DIR = "ref";
	private static final String RESULT_DIR = "res";
	private static final String RESULT_FILE_EXT = ".res";

	private static final String ROOT_DIR = "data";

	FileOperator(Class<?> suite, String testName) {
		final String fileName = suite.getSimpleName() + "_" + testName;
		final String resultFileName = fileName + RESULT_FILE_EXT;
		final String referenceFileName = fileName + REFERENCC_FILE_EXT;
		referenceFilePath = Paths.get(ROOT_DIR, REFERENCE_DIR, referenceFileName);
		resultFilePath = Paths.get(ROOT_DIR, RESULT_DIR, resultFileName);
	}

	String getReferenceFilePath() {
		return referenceFilePath.toString();
	}

	String getResultFilePath() {
		return resultFilePath.toString();
	}

	List<String> readReferenceFile() {
		return readFile(referenceFilePath);
	}

	List<String> readResultFile() {
		return readFile(resultFilePath);
	}

	void writeResultFile(List<String> resultContent) {
		writeFile(resultFilePath, resultContent);
	}

	private List<String> readFile(Path fileName) {
		List<String> result = new LinkedList<>();

		try {
			Files.createDirectories(fileName.getParent());
			if (!Files.exists(fileName)) {
				Files.createFile(fileName);
			}

			final Stream<String> stream = Files.lines(fileName);
			result = stream.collect(Collectors.toList());
			stream.close();

		} catch (final IOException e) {
			logger.error("Could not read file " + fileName);
		}

		return result;
	}

	private void writeFile(Path fileName, List<String> content) {
		try {
			Files.createDirectories(fileName.getParent());
			Files.write(fileName, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (final IOException e) {
			logger.error("Could not write file " + fileName);
		}
	}

	/** the reference file path. */
	private final Path referenceFilePath;
	/** the result file path. */
	private final Path resultFilePath;

}
