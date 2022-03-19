package com.video.transcoder.servie.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ExecuteCommandRunnable implements Runnable{


	private InputStream inputStream;  

	public ExecuteCommandRunnable(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void run() {
		BufferedReader reader=	new BufferedReader(new InputStreamReader(inputStream));
		try {
			while(reader.ready()) {
				System.out.println(reader.readLine());

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




}
