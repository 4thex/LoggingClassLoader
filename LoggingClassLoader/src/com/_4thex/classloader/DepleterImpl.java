package com._4thex.classloader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DepleterImpl implements Depleter {
	private static int BUFFER_SIZE = 1000;

	@Override
	public byte[] deplete(InputStream input) throws IOException {
		try {
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        int count;
	        byte[] buffer = new byte[BUFFER_SIZE];
	        while((count = input.read(buffer))!= -1) {
	        	out.write(buffer, 0, count);
	        }
	        return out.toByteArray();
		} finally {
			input.close();
		}
	}

}
