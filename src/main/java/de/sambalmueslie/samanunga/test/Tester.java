package de.sambalmueslie.samanunga.test;

import org.mockito.Mockito;

public class Tester {

	/**
	 * Constructor.
	 *
	 * @param suite
	 *            {@link #suite}
	 * @param testName
	 *            {@link #testName}
	 */
	public Tester(Class<?> suite, String testName) {
		this.suite = suite;
		fileOperator = new FileOperator(suite, testName);
		result = new TestResult();
		diffController = new DiffController();
	}

	public void headline(String text) {
		result.headline(text);
	}

	public void info(String text) {
		result.info(text);
	}

	public <T> T mock(Class<T> classToMock) {
		final InvocationHandler invocationHandler = new InvocationHandler(classToMock, suite, result);
		return Mockito.mock(classToMock, Mockito.withSettings()
				.invocationListeners(invocationHandler));
	}

	/**
	 * @return <code>true</code> if test result is equals, otherwise <code>false</code>.
	 */
	public boolean verify() {
		fileOperator.writeResultFile(result.getContent());

		final Verificator verificator = new Verificator(fileOperator);
		final boolean success = verificator.verify();
		if (success) return success;

		showDiffTool();
		return success;
	}

	// public <T> T mock(Class<T> classToMock, Answer<T> defaultAnswer) {
	// return Mockito.mock(classToMock, Mockito.withSettings().defaultAnswer(defaultAnswer).invocationListeners(new
	// InvocationHandler(classToMock, result)));
	// }
	//
	// public <T> T mock(Class<T> classToMock, MockSettings mockSettings) {
	// return Mockito.mock(classToMock, mockSettings.invocationListeners(new InvocationHandler(classToMock, result)));
	// }
	//
	// public <T> T mock(Class<T> classToMock, String name) {
	// return Mockito.mock(classToMock, Mockito.withSettings().name(name).invocationListeners(new InvocationHandler(classToMock, result)));
	// }

	private void showDiffTool() {
		diffController.showDiffTool(fileOperator.getResultFilePath(), fileOperator.getReferenceFilePath());
	}

	private final DiffController diffController;
	/** the {@link FileOperator}. */
	private final FileOperator fileOperator;
	/** the {@link TestResult}. */
	private final TestResult result;
	private final Class<?> suite;

}
