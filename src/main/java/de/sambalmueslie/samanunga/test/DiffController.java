package de.sambalmueslie.samanunga.test;

import java.io.IOException;

class DiffController {

	private static final String PATH_TO_DIFF = "C:\\Program Files (x86)\\WinMerge\\WinMergeU.exe";

	void showDiffTool(String resultFilePath, String referenceFilePath) {
		final String[] params = new String[] { PATH_TO_DIFF, "/e", "/s", "/wr", referenceFilePath, resultFilePath };
		try {
			Runtime.getRuntime().exec(params);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

}
