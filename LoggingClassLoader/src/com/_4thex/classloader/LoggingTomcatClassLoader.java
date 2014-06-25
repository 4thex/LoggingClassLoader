package com._4thex.classloader;

import java.net.URL;

import org.apache.catalina.loader.WebappClassLoader;

public class LoggingTomcatClassLoader extends WebappClassLoader {

	private PathResolver pathResolver;
	private Depleter depleter;
	private ClassLoadingListener listener;

	public LoggingTomcatClassLoader(ClassLoader parentClassLoader) {
		super(parentClassLoader);
		setListener(ClassLoadingListener.create());
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		if(super.findLoadedClass(name) == null) {
			String classPath = name.replace('.', '/')+".class";
			URL url = getResource(classPath);
			String path = getPathResolver().getPath(url, classPath);
			getListener().loading(name, path);
		}
		Class<?> loadedClass = super.loadClass(name);
		return loadedClass;
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
		return listener;
	}
	
	public void setListener(ClassLoadingListener listener) {
		this.listener = listener;
	}

}
