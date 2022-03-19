package com.video.transcoder.common;

/**
 * @author root
 */
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Random;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.video.transcoder.vo.ContentUploadReqVO;

public class TransCoderUtils {

	 public static ByteBuffer getRandomByteBuffer(int size) throws IOException {
	        byte[] b = new byte[size];
	        new Random().nextBytes(b);
	        return ByteBuffer.wrap(b);
	    }
	 
	 /**
	  * 
	  * @param jsonString
	  * @return
	  */
	 public static Object getObject(String jsonString,Class<? extends Object> classType) {
		 ObjectMapper mapper = new ObjectMapper();
		 try {
			return mapper.readValue(jsonString, classType);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		 return null;
	 }
	 
	
}
