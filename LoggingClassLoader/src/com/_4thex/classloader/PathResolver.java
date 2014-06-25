package com._4thex.classloader;

import java.net.URL;

public interface PathResolver {
	public String getPath(URL url, String classPath);
}
