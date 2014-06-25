package com._4thex.classloader;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class LoggingClassLoader extends ClassLoader {
	private PathResolver pathResolver;
	private Depleter depleter;
	private ClassLoadingListener listener;
	
	public LoggingClassLoader(ClassLoader parentClassLoader) {
		super(parentClassLoader);
		setListener(ClassLoadingListener.create());
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		String classPath = name.replace('.', '/')+".class";
		URL url = getResource(classPath);
		try {
			String path = getPathResolver().getPath(url, classPath);
			getListener().loading(name, path);
			URLConnection connection = url.openConnection();
			InputStream input = connection.getInputStream();
			byte[] bytes = getDepleter().deplete(input);
            return defineClass(name, bytes, 0, bytes.length);
		} catch (Exception e) {
			return this.getParent().loadClass(name);
		}
	}
	
	public PathResolver getPathResolver() {
		if(this.pathResolver == null) {
			this.pathResolver = PathResolverFactory.create();
		}
		return this.pathResolver;
	}
	
	public void setPathResolver(PathResolver pathResolver) {
		this.pathResolver = pathResolver;
	}
	
	public Depleter getDepleter() {
		if(this.depleter == null) {
			this.depleter = DepleterFactory.create();
		}
		return this.depleter;
	}
	
	public void setDepleter(Depleter depleter) {
		this.depleter = depleter;
	}
	
	public ClassLoadingListener getListener() {
		if(this.listener == null) {
			this.listener = ClassLoadingListener.create();
		}
		return listener;
	}
	
	public void setListener(ClassLoadingListener listener) {
		this.listener = listener;
	}
}
