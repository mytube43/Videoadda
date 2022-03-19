package com.video.transcoder.servie.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;

import com.video.transcoder.manager.VideoProcessManager;
import org.springframework.util.StringUtils;

public class ExecuteCommandHandler{
	public static void main(String args[]) {
		VideoProcessManager manager = new VideoProcessManager();
		manager.uploadPlaylistToObjectStore("sample");
	}
	
	public static String commandBuilder(String inputFile,String outputFileTemplate) {
		String command = "ffmpeg -i "
				+inputFile 
				+ " -vf scale=w=1280:h=720:force_original_aspect_ratio=decrease -c:a aac -ar 48000 "
				+ "-b:a 128k -c:v h264 -profile:v main -crf 20 -g 48 -keyint_min 48 "
				+ "-sc_threshold 0 -b:v 2500k -maxrate 2675k -bufsize 3750k -hls_time 4 "
				+ "-hls_playlist_type vod -hls_segment_filename "
				+ outputFileTemplate+"/song_720p_%03d.ts "+outputFileTemplate+"/song_720p.m3u8";
		return command;
	}

	public void mainBack(){
		try {
			String command = commandBuilder("/home/amit/Desktop/MyTube/song1.mp4", "/home/amit/Desktop/Song");
			ProcessBuilder processBuilder = new ProcessBuilder();
			String os_name = System.getProperty("os.name");
			if(!StringUtils.isEmpty(os_name) && os_name.toLowerCase().startsWith("windows")) {
				//processBuilder.command("cmd.exe","/c",command);
			}else {
				//processBuilder.command("bash","-c",command);
			}
			String contentDirectory = "/home/ajay/intellij/video-upload-service/src/main/resources/hls/sample1";
			String ffmpegCommand = "bash /home/ajay/Videos/create-vod.sh "+"/home/ajay/Videos/sample.mp4 "+contentDirectory;
			Runtime run = Runtime.getRuntime();
			Process pr = run.exec(ffmpegCommand);
			BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ( (line = reader.readLine()) != null) {
				builder.append(line);
			}
			String result = builder.toString();
			System.out.print(result);
		  /*ExecuteCommandRunnable processOP = new
		  ExecuteCommandRunnable(pr.getInputStream());
		  Executors.newSingleThreadExecutor().submit(processOP);*/
			int exitCode = pr.waitFor();


			/*
			 * processBuilder.directory(new
			 * File("/home/amit/Desktop/ffmpeg-git-20210724-amd64-static/"));
			 */
			/*
			 * Process process = processBuilder.start();
			 *
			 * ExecuteCommandRunnable processOP = new
			 * ExecuteCommandRunnable(process.getInputStream());
			 * Executors.newSingleThreadExecutor().submit(processOP); int exitCode =
			 * process.waitFor();
			 */
			System.out.println("ExitCode for process::"+exitCode);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
