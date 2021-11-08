package app.keepcash.base.client;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignCustomErrorDecoder implements ErrorDecoder {
	
	private final Logger LOGGER = LoggerFactory.getLogger(FeignCustomErrorDecoder.class);
	
	@Override
	public Exception decode(String methodKey, Response response) {

		String message = null;
		Reader reader = null;

		try {
			reader = response.body().asReader(StandardCharsets.UTF_8);
			String result = IOUtils.toString(reader);
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			ExceptionMessage exceptionMessage = mapper.readValue(result, ExceptionMessage.class);
			message = exceptionMessage.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		switch (response.status()) {
		case 400:
			LOGGER.error("Bad Request : " + message);
			return new Exception("Bad Request : " + message);
		case 401:
			LOGGER.error("Unauthorized : " + message);
			return new Exception("Unauthorized : " + message);
		case 404:
			LOGGER.error("Unidentified : " + message);
			return new Exception("Unidentified : " + message);
		case 500:
			LOGGER.error("Internal Server Error : " + message);
			return new Exception("Internal Server Error : " + message);
		default:
			LOGGER.error("Common Exception : " + message);
			return new Exception("Common Exception : " + message);
		}
	}

}
