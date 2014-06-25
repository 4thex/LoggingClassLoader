package com._4thex.classloader;

import java.io.IOException;
import java.io.InputStream;

public class DepleterFactory implements Depleter {

	private Depleter depleter; 
	
	private DepleterFactory() {
		this.depleter = new DepleterImpl();
	}
	
	public static Depleter create() {
		return new DepleterFactory();
	}

	@Override
	public byte[] deplete(InputStream inputStream) throws IOException {
		return this.depleter.deplete(inputStream);
	}
}
