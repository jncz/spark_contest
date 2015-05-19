package com.spss.ibm.spark.core.test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class DataSendor {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		String host = "localhost";
		int port = 9999;
//		ServerSocket ss = new ServerSocket(port );
		Socket so = new Socket(host,port);
//		so.setKeepAlive(true);
//		so.setReuseAddress(true);
		System.out.println(so.isConnected());
		System.out.println(so.isBound());
		OutputStream os = so.getOutputStream();
		while(true){
			os.write("a b c".getBytes());
			os.flush();
//			System.out.println("output");
			Thread.sleep(1000);
			so.sendUrgentData(1);
		}
	}

}
