package com._4thex.classloader;

import java.net.URL;

public class CompositePathResolver implements PathResolver {

	private PathResolver[] resolvers;
	public CompositePathResolver(PathResolver... resolvers) {
		this.resolvers = resolvers;
	}
	
	@Override
	public String getPath(URL url, String classPath) {
		for(PathResolver resolver: this.resolvers) {
			String path = resolver.getPath(url, classPath);
			if(path!=null) {
				return path;
			}
		}
		return null;
	}

}
