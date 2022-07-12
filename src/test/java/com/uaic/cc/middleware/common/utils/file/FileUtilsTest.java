package com.uaic.cc.middleware.common.utils.file;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.uaic.cc.middleware.common.utils.file.FileUtils;

class FileUtilsTest {

	@Test
	void testGetFileContentFromClasspath() {
		String resourcePath = "fileutils/testfile.txt";
		String fileContent = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce rhoncus dui ac blandit dapibus. Duis fermentum eros sit amet pretium consectetur.";
		
		try {
			assert(FileUtils.getFileContentFromClasspath(resourcePath).equals(fileContent));
		} catch (IOException e) {
			fail(e);
		}
	}

}
