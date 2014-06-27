package com._4thex.classloader;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.loader.WebappClassLoader;

public class LoggingTomcatClassLoader extends WebappClassLoader {

	private PathResolver pathResolver;
	private ClassLoadingListener listener;
	private Map<String, String> classMap;
	
	public LoggingTomcatClassLoader(ClassLoader parentClassLoader) {
		this(parentClassLoader, PathResolverFactory.create(), ClassLoadingListener.create());
	}
	
	public LoggingTomcatClassLoader(ClassLoader parentClassLoader, PathResolver pathResolver, ClassLoadingListener listener) {
		super(parentClassLoader);
		this.classMap = new HashMap<String, String>();
		this.pathResolver = pathResolver;
		this.listener = listener;
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		if(super.findLoadedClass(name) == null) {
			String classPath = name.replace('.', '/')+".class";
			URL url = getResource(classPath);
			String path = this.pathResolver.getPath(url, classPath);
			if(!this.classMap.containsKey(name)) {
				this.listener.loading(name, path);
				this.classMap.put(name, path);
			}
		}
		Class<?> loadedClass = super.loadClass(name);
		return loadedClass;
	}
}
