package com._4thex.classloader;

import static org.junit.Assert.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class JarPathResolverTest {

	@Test
	public void testResolve_ReturnsCorrectPath_ForValidJarURL() throws MalformedURLException {
		URL url = new URL("jar:file:/C:/Program%20Files/Java/jdk1.7.0_51/jre/lib/rt.jar!/java/io/PrintStream.class");
		PathResolver resolver = new JarPathResolver();
		String path = resolver.getPath(url, "/java/io/PrintStream.class");
		String expected = new File("C:/Program Files/Java/jdk1.7.0_51/jre/lib/rt.jar").getPath();
		assertEquals(expected, path);
	}
	
	@Test
	public void testResolve_ReturnsNull_NonJarURL() throws MalformedURLException {
		URL url = new URL("file:/C:/Users/4thex/Workspace/TestApplication/bin/com/_4thex/Main.class");
		PathResolver resolver = new JarPathResolver();
		String path = resolver.getPath(url, "/com/_4thex/Main.class");
		assertNull(path);		
	}

}
