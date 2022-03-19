package com.video.transcoder.manager;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.video.transcoder.entity.HlsEntity;
import com.video.transcoder.model.Sources;
//import com.video.transcoder.model.Thumbnail;
import com.video.transcoder.model.VideoSource;
import com.video.transcoder.repository.HlsRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class VideoProcessManager {

    public static final String AWS_ID = "AKIAT72JMBU4GTHDXZWN";
    public static final String AWS_KEY = "dORoUbpMlUXNU2HJ5/GgpuMU3uLSnnQuS2WGbSu8";
    public static final String bucket = "mytube2011";

    @Autowired
    HlsRepository repository;

    public String processVideo(String file) {
        String fileName = file.substring(0,file.indexOf("."));
        String status = "";
        try {
            String contentDirectory= "/home/ajay/uploadFiles/hls/"+fileName;
            String ffmpegCommand = "bash /home/ajay/Videos/create-vod.sh " + "/home/ajay/uploadFiles/" + file + "  " + contentDirectory;
            System.out.println("FFMPEG conversion started...");
            Runtime run = Runtime.getRuntime();
            Process pr = run.exec(ffmpegCommand);
            BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            String result = builder.toString();
            System.out.print(result);
            int exitCode = pr.waitFor();
            System.out.println("ExitCode for process::" + exitCode);
            status = exitCode == 0 ? "Success" : "Failed";
        } catch (Exception e) {
            status = "Failed";
        }
        System.out.println("FFMPEG conversion completed...");
        return status;
    }

    public String uploadPlaylistToObjectStore(String fileName) {
        String folder = fileName.substring(0,fileName.indexOf("."));
        File file = new File("/home/ajay/uploadFiles/hls/"+folder);
        TransferManager transferManager = getTransferManger(AWS_ID,AWS_KEY);
        System.out.println("BUCKET Location : "+transferManager.getAmazonS3Client().getBucketLocation(bucket));
        try{
            List<File> list1 = FileUtils.streamFiles(file,true,"m3u8").collect(Collectors.toList());
            List<File> list2 = FileUtils.streamFiles(file,true,"ts").collect(Collectors.toList());
            for (File f : list2) {
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentType("video/MP2T");
                PutObjectRequest request = new PutObjectRequest(bucket,"hls/"+folder+"/"+f.getName(), f);
                request.withMetadata(metadata);
                transferManager.upload(request);
            }
            for (File f : list1) {
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentType("application/x-mpegURL");
                PutObjectRequest request = new PutObjectRequest(bucket,"hls/"+folder+"/"+f.getName(), f);
                request.withMetadata(metadata);
                transferManager.upload(request);
            }
        }catch (Exception e){
            System.out.println("Exception while uploading the file");
        }
        System.out.println("uploaded to object store....");
        return "Success";
    }

    public HlsEntity saveHlsSource(String name, String thumbnil) {
        String fileName = name.substring(0,name.indexOf("."));
        HlsEntity entity = new HlsEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setMasterPlaylistLocation("https://mytube2011.s3.ap-south-1.amazonaws.com/hls/" + fileName +"/"+ "playlist.m3u8");
        entity.setThumbnil("http://localhost:9001/play.jpeg");
        entity.setName(fileName);
        return repository.save(entity);
    }

    public List<VideoSource> getHlsSource() {
        List<VideoSource> list = new ArrayList<>();
        List<HlsEntity> hlsEntityList = repository.findAll();
        for(HlsEntity entity: hlsEntityList){
            List<Sources> sourcesList = new ArrayList<>();
            VideoSource videoSource = new VideoSource();
            Sources s = new Sources();
            s.setSrc(entity.getMasterPlaylistLocation());
            s.setType("types.m3u");
            sourcesList.add(s);
            videoSource.setName(entity.getName());
            videoSource.setPoster(entity.getThumbnil());
            videoSource.setSources(sourcesList);
            list.add(videoSource);
        }
        return list;
    }


    public String generateThumbnail(String file) {
        String fileName = file.substring(0,file.indexOf("."));
        String status = "";
        try {
           boolean iscreated = new File("src/main/resources/static/hls/"+fileName).mkdir();
            String fileLocation = "src/main/resources/static/hls/"+fileName+"/";// "/home/lenovo/sumit/uploadFiles/hls/thumbnail/";
            String contentDirectory= "/home/lenovo/sumit/uploadFiles/"+file;
           String ffmpegCommand = "ffmpeg -ss 1 -i "+contentDirectory+ " -vf \"select=gt(scene,0.4)\" -frames:v 3 -vsync vfr -vf fps=fps=1/5 "+fileLocation+""+fileName+"%02d.jpg";
           // String ffmpegCommand= "ffmpeg -i "+contentDirectory+ "-ss 00:00:01.000 -vframes 1 "+fileLocation+""+fileName+"%02d.jpg";
            System.out.println("ffmpegCommand for process::" + ffmpegCommand);
            System.out.println("Thumbnail generation started...");
            Runtime run = Runtime.getRuntime();
            Process pr = run.exec(ffmpegCommand);
            BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            String result = builder.toString();
            System.out.print(result);
            int exitCode = pr.waitFor();
            System.out.println("ExitCode for process::" + exitCode);
            status = exitCode == 0 ? "Success" : "Failed";
        } catch (Exception e) {
            status = "Failed";
        }
        System.out.println("Thumbnail generation completed...");
        return status;
    }

    public List<String> getThumbnails(String fileName) throws Exception {
        String folder = fileName.substring(0,fileName.indexOf("."));
        File file = new File("src/main/resources/static/hls/"+folder);
        List<File> list1 = FileUtils.streamFiles(file,true,"jpg").collect(Collectors.toList());
        List<String> thumbnailList = new ArrayList<>();
        for(File f : list1) {
            thumbnailList.add("http://localhost:9001/hls/"+folder+"/"+f.getName());
        }
        return thumbnailList;
    }

    private TransferManager getTransferManger(String id, String key){
        AmazonS3 amazonS3 = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSCredentialsProvider() {
                    @Override
                    public AWSCredentials getCredentials() {
                        return new AWSCredentials() {
                            @Override
                            public String getAWSAccessKeyId() {
                                return id;
                            }

                            @Override
                            public String getAWSSecretKey() {
                                return key;
                            }
                        };
                    }
                    @Override
                    public void refresh() {
                    }
                })
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("https://s3.ap-south-1.amazonaws.com/", "ap-south-1"))
                .build();
        //amazonS3.setEndpoint("https://mytube2011.s3.ap-south-1.amazonaws.com/");
        TransferManager tm = TransferManagerBuilder.standard()
                .withS3Client(amazonS3)
                .build();
        return tm;
    }

}
