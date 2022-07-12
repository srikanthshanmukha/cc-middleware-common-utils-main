package com.uaic.cc.middleware.common.utils.file;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jodd.io.FileUtil;

public class FileUtils {

	private static final Logger log = LoggerFactory.getLogger(FileUtils.class);
	
	public static String getFileContentFromClasspath(String fName) throws IOException {
		log.debug("Fetching file from classpath: " + fName);
		File file = new File(FileUtils.class.getClassLoader().getResource(fName).getFile());
		log.debug("got file with length " + FileUtil.readString(file).length() + "\n");
		String html = FileUtil.readString(file);
		return html;
	}
	
	public static String getFileContent(String fName) throws IOException {
		File file = new File(fName);
		log.debug("got file with length " + FileUtil.readString(file).length() + "\n");
		String content = FileUtil.readString(file);
		return content;
	}

}
