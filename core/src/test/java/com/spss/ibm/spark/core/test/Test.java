package com.spss.ibm.spark.core.test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Test {

	public static void main(String[] args) throws IOException, InterruptedException {
		String host = "localhost";
		int port = 9999;
		ServerSocket ss = new ServerSocket(port );
		Socket so = ss.accept();
		while(true){
			byte[] b = new byte[3];
			so.getInputStream().read(b );
			System.out.println(new String(b));
		}

	}

}
