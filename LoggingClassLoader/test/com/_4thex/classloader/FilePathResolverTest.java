package com._4thex.classloader;

import static org.junit.Assert.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class FilePathResolverTest {

	@Test
	public void testResolve_ReturnsCorrectPath_ForValidFileURL() throws MalformedURLException {
		URL url = new URL("file:/C:/Users/4thex/Workspace/TestApplication/bin/com/_4thex/Main.class");
		PathResolver resolver = new FilePathResolver();
		String path = resolver.getPath(url, "/com/_4thex/Main.class");
		String expected = new File("C:/Users/4thex/Workspace/TestApplication/bin").getPath();
		assertEquals(expected, path);
	}
	
	@Test
	public void testResolve_ReturnsNull_NonFileURL() throws MalformedURLException {
		URL url = new URL("jar:file:/C:/Program%20Files/Java/jdk1.7.0_51/jre/lib/rt.jar!/java/io/PrintStream.class");
		PathResolver resolver = new FilePathResolver();
		String path = resolver.getPath(url, "/java/io/PrintStream.class");
		assertNull(path);		
	}

}
