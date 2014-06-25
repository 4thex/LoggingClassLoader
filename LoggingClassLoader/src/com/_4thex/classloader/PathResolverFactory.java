package com._4thex.classloader;

import java.net.URL;

public class PathResolverFactory implements PathResolver {
	private PathResolver resolver;
	
	private PathResolverFactory() {
		this.resolver = new CompositePathResolver(new JarPathResolver(), new FilePathResolver());
	}
	
	public static PathResolver create() {
		return new PathResolverFactory();
	}

	@Override
	public String getPath(URL url, String classPath) {
		return this.resolver.getPath(url, classPath);
	}
}
