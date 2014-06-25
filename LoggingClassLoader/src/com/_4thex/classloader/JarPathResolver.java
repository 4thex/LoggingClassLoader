package com._4thex.classloader;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JarPathResolver implements PathResolver {

	private Logger logger = Logger.getLogger(JarPathResolver.class.getName());
	
	@Override
	public String getPath(URL url, String classPath) {
		URLConnection connection;
		JarURLConnection jarURLConnection;
		try {
			connection = url.openConnection();
			if(connection instanceof JarURLConnection) {
				jarURLConnection = ((JarURLConnection)connection);
				return jarURLConnection.getJarFile().getName();
			}
		} catch (IOException e) {
			logger.log(Level.WARNING, String.format("Failed to resolve the URL: %s", url), e);
		}
		return null;
	}

}
