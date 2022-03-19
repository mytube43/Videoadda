package com.video.transcoder.controller;

import java.util.List;
import java.util.Optional;

import com.video.transcoder.entity.HlsEntity;
import com.video.transcoder.manager.VideoProcessManager;
import com.video.transcoder.model.Sources;
import com.video.transcoder.model.VideoSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.video.transcoder.framework.vo.ResponseVO;
import com.video.transcoder.vo.UploadFileInfoVO;

@RestController
@RequestMapping("/mytube/us")
public class UploadHandlerController extends AbstractHelperController {

	@Autowired
	VideoProcessManager manager;
	/*
	 * @Autowired JdbcTemplate uploadJdbcTemplate;
	 * 
	 * @GetMapping("/testDB") public ResponseEntity<Boolean> testDB(){
	 * System.out.println("JDBCTemplate::"+uploadJdbcTemplate); return new
	 * ResponseEntity<>(Boolean.TRUE,HttpStatus.OK); }
	 */

	@PostMapping("/uploadFile") 
	public @ResponseBody ResponseVO uploadRawVideo(){
        MultipartFile videoFile =null;
        if(null!=videoFile) {
			Optional<MultipartFile> videoFileOptional = Optional.ofNullable(videoFile);
			Optional<UploadFileInfoVO> response=getTranscoderBusinessLookup().getStorageService()
					.writeFileToClassPath(videoFileOptional);
			UploadFileInfoVO uploadFileInfo = response.get();
			String ffMpegStatus = manager.processVideo(videoFile.getOriginalFilename());
			String s3Status = manager.uploadPlaylistToObjectStore(videoFile.getOriginalFilename());
			if(s3Status.equals("Success")){
				manager.saveHlsSource(videoFile.getOriginalFilename(),"");
			}
			/*if(null!=uploadFileInfo && StringUtils.isNotBlank(uploadFileInfo.getFileName())) {
				ResponseVO responseVO =getTranscoderBusinessLookup().getStorageService().saveVideoMetaData(metadata,
						uploadFileInfo.getFileName());
				return responseVO;
			}*/
			return getResponseError();
		}

		return getResponseError();
	}

	@GetMapping("/source")
	public @ResponseBody List<VideoSource> getHlsSource() {
		return manager.getHlsSource();
	}


	@PostMapping("/upload")
	public @ResponseBody
	ResponseEntity uploadVideo(@RequestParam("userFile") MultipartFile videoFile) throws Exception {
		if (null != videoFile) {
			Optional<MultipartFile> videoFileOptional = Optional.ofNullable(videoFile);
		Optional<UploadFileInfoVO> response = getTranscoderBusinessLookup().getStorageService()
					.writeFileToClassPath(videoFileOptional);
			UploadFileInfoVO uploadFileInfo = response.get();
			String ffMpegStatus = manager.generateThumbnail(videoFile.getOriginalFilename());
			if("Success".equalsIgnoreCase(ffMpegStatus)){
				return new ResponseEntity<>(manager.getThumbnails(videoFile.getOriginalFilename()),HttpStatus.OK);
			}
		}
		return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
	}














	/*
	@GetMapping(value="/getM3U8",produces = {"application/x-mpegURL"})
	public ResponseEntity<byte[]> getM3U8(){

		ResponseEntity<byte[]> response = null;

        final String home = System.getProperty("user.home");
        final File directory = new File("/home/amit" + File.separator + "Desktop/Test" + File.separator + "720p.m3u8");
		try {
			 InputStream inputStream = new FileInputStream(directory);
			byte[] m3u8File = inputStream.readAllBytes();

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition","attachment;filename=720p.m3u8");
			headers.add("Access-Control-Allow-Origin", "*");
			headers.add("Access-Control-Allow-Credentials", "true");
			headers.add("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
			headers.add("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
			System.out.println("**************** Get M3U8************************");
			response = new ResponseEntity<byte[]>(m3u8File,headers,HttpStatus.OK);
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       return response;

	}

	//@CrossOrigin(origins = "http://localhost:9001")
	@GetMapping(value="/{tsFileName}",produces = {"application/x-mpegURL"})
	public ResponseEntity<byte[]> getM3U8Files(@PathVariable String tsFileName){

		ResponseEntity<byte[]> response = null;


        final File directory = new File("/home/amit" + File.separator 
        		+ "Desktop/Test" + File.separator + tsFileName);
		try {
			 InputStream inputStream = new FileInputStream(directory);
			byte[] m3u8File = inputStream.readAllBytes();

			HttpHeaders headers = new HttpHeaders();
			//headers.add("Content-Disposition","attachment;filename="+tsFileName);
			headers.add("Access-Control-Allow-Origin", "*");
			headers.add("Access-Control-Allow-Credentials", "true");
			headers.add("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
			headers.add("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");

			response = new ResponseEntity<byte[]>(m3u8File,headers,HttpStatus.OK);
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       return response;

	}
	 */

}
