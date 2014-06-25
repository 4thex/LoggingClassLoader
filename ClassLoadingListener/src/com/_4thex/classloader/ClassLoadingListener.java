package com._4thex.classloader;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public abstract class ClassLoadingListener {
	private static ServiceLoader<ClassLoadingListener> loader;
	public static ClassLoadingListener create() {
		loader = ServiceLoader.load(ClassLoadingListener.class);
		List<ClassLoadingListener> listeners = new ArrayList<ClassLoadingListener>();
		for(ClassLoadingListener listener: loader) {
			listeners.add(listener);
		}
		if(listeners.size() == 0) {
			listeners.add(new DefaultClassLoadingListener());
		}
		return new CompositeClassLoadingListener(listeners);
	}

	public abstract void loading(String name, String from);
}
