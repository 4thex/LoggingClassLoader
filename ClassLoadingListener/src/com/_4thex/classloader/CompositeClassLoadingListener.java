package com._4thex.classloader;

import java.util.List;

public class CompositeClassLoadingListener extends ClassLoadingListener {
	private List<ClassLoadingListener> listeners;
	public CompositeClassLoadingListener(List<ClassLoadingListener> listeners) {
		this.listeners = listeners;
	}
	
	@Override
	public void loading(String name, String from) {
		for(ClassLoadingListener listener: this.listeners) {
			listener.loading(name, from);
		}
	}

}
