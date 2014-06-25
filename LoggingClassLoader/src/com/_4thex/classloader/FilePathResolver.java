package com._4thex.classloader;

import java.io.File;
import java.net.URL;

public class FilePathResolver implements PathResolver {

	@Override
	public String getPath(URL url, String classPath) {
		if(!"file".equals(url.getProtocol())) return null;
		String filePath = url.getFile();
		int index = filePath.indexOf(classPath);
		String folderPath = filePath.substring(0, index);
		return new File(folderPath).getPath();
	}

}
