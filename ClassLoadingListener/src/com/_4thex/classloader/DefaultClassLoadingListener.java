package com._4thex.classloader;

public class DefaultClassLoadingListener extends ClassLoadingListener {

	@Override
	public void loading(String name, String from) {
		System.out.println(name + " loaded from " + from);
	}

}
