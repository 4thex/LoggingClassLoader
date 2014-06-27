package com._4thex.classloader;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class LoggingClassLoader extends ClassLoader {
	private PathResolver pathResolver;
	private Depleter depleter;
	private ClassLoadingListener listener;
	
	public LoggingClassLoader(ClassLoader parentClassLoader) {
		this(parentClassLoader, PathResolverFactory.create(), DepleterFactory.create(), ClassLoadingListener.create());
	}
	
	public LoggingClassLoader(ClassLoader parentClassLoader, PathResolver pathResolver, Depleter depleter, ClassLoadingListener listener) {
		super(parentClassLoader);
		this.depleter = depleter;
		this.pathResolver = pathResolver;
		this.listener = listener;
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		String classPath = name.replace('.', '/')+".class";
		URL url = getResource(classPath);
		try {
			String path = this.pathResolver.getPath(url, classPath);
			this.listener.loading(name, path);
			URLConnection connection = url.openConnection();
			InputStream input = connection.getInputStream();
			byte[] bytes = this.depleter.deplete(input);
            return defineClass(name, bytes, 0, bytes.length);
		} catch (Exception e) {
			return this.getParent().loadClass(name);
		}
	}
	
}
