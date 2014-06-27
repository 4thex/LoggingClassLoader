package com._4thex.classloader;

import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileClassLoadingListener extends ClassLoadingListener {
	private Logger logger;
	private PrintStream out;
	public FileClassLoadingListener() {
		super();
		this.logger = Logger.getLogger(getClass().getName());
		String fileName = System.getProperty(getClass().getName()+".FileName", "ClassLoading.properties");
		try {
			this.out = new PrintStream(fileName);
		} catch (IOException e) {
			this.logger.log(Level.SEVERE, String.format("Failed to open file '%s' for writing", fileName), e);
		}
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				if(out != null) {
					out.close();
				}
			}
		});
	}
	
	@Override
	public void loading(String name, String from) {
		Throwable t = new Throwable();
		t.fillInStackTrace();
		StackTraceElement[] stackTrace = t.getStackTrace();
		int index = 3;
		if(index > stackTrace.length) {
			index = stackTrace.length - 1;
		}
		StackTraceElement element = stackTrace[index];
		String className = element.getClassName();
		String methodName = element.getMethodName();
		if(this.out != null) {
			this.out.println(String.format("# Initially requested from %s#%s", className, methodName));
			this.out.println(String.format("%s=%s", name, from));
		}
	}

}
