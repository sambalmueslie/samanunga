package de.sambalmueslie.samanunga.test;

import java.util.LinkedList;
import java.util.List;

class TestResult {

	List<String> getContent() {
		return content;
	}

	void headline(String text) {
		content.add("");
		content.add("####\t" + text + "\t####");
		content.add("");
	}

	void info(String text) {
		content.add(text);
	}

	void invocation(String text) {
		content.add(text);
	}

	private final List<String> content = new LinkedList<>();

}
