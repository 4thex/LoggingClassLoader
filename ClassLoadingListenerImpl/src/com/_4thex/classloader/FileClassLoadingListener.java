package com._4thex.classloader;

public class FileClassLoadingListener extends ClassLoadingListener {

	public FileClassLoadingListener() {
		super();
	}
	
	@Override
	public void loading(String name, String from) {
		System.out.println("File: " + name + " loaded from " + from);
	}

}
