package com._4thex.classloader;

import java.io.IOException;
import java.io.InputStream;

public interface Depleter {
	public byte[] deplete(InputStream inputStream) throws IOException;
}
